package de.metas.ui.web.config;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.google.common.base.Stopwatch;

import de.metas.logging.LogManager;
import de.metas.ui.web.session.UserSession;
import de.metas.user.UserId;
import de.metas.util.Check;



/**
 * metasfresh-webui logging component.
 *
 *
 *
 */
@Component
public class ServletLoggingFilter implements Filter
{
	private static final Logger logger = LogManager.getLogger(ServletLoggingFilter.class);

	//
	// Core MDC parameters
	private static final String MDC_Param_RemoteAddr = "RemoteAddr";
	private static final String MDC_Param_RemoteAddr_DefaultValue = "server";
	private static final String MDC_Param_LoggedUser = "LoggedUser";
	private static final String MDC_Param_UserAgent = "UserAgent";

	//
	// Derivated MDC parameters
	/**
	 * mainly this is needed for "de/metas/ui/web/logging/FILE-byLoggedUserAndRemoteAddr.xml"
	 */
	private static final String MDC_Param_LoggedUserAndRemoteAddr = "LoggedUserAndRemoteAddr";

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException
	{
	}

	@Override
	public void destroy()
	{
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException
	{
		final Stopwatch stopwatch = Stopwatch.createStarted();
		try
		{
			updateMDC(request);

			chain.doFilter(request, response);
		}
		finally
		{
			//
			// log the request
			if (logger.isDebugEnabled())
			{
				final String requestInfo = extractRequestInfo(request);
				logger.debug("Executed in {}: {}", stopwatch.stop(), requestInfo);
			}

			//
			// Cleanup MDC (keep it last)
			cleanupMDC();
		}
	}

	public void updateMDC(final ServletRequest request)
	{
		if (!(request instanceof HttpServletRequest))
		{
			return;
		}
		final HttpServletRequest httpRequest = (HttpServletRequest)request;

		//
		// Core MDC parameters
		final String remoteAddr = extractRemoteAddr(httpRequest);
		final String loggedUser = extractLoggedUser(httpRequest);
		final String userAgent = extractUserAgent(httpRequest);
		MDC.put(MDC_Param_RemoteAddr, remoteAddr);
		MDC.put(MDC_Param_LoggedUser, loggedUser);
		MDC.put(MDC_Param_UserAgent, userAgent);

		//
		// Derivated MDC parameters
		MDC.put(MDC_Param_LoggedUserAndRemoteAddr, extractLoggedUserAndRemoteAddr(loggedUser, remoteAddr));
	}

	public void cleanupMDC()
	{
		MDC.remove(MDC_Param_RemoteAddr);
		MDC.remove(MDC_Param_LoggedUser);
		MDC.remove(MDC_Param_UserAgent);
	}

	private static final String extractRequestInfo(final ServletRequest request)
	{
		if (request instanceof HttpServletRequest)
		{
			final HttpServletRequest httpRequest = (HttpServletRequest)request;

			final String httpMethod = httpRequest.getMethod();

			final String urlStr = httpRequest.getRequestURL().toString();
			URI uri;
			try
			{
				uri = new URI(urlStr);
			}
			catch (final URISyntaxException e)
			{
				uri = null;
			}

			String path = null;
			if (uri != null)
			{
				path = uri.getPath();
			}
			if (path == null)
			{
				path = urlStr;
			}

			final String queryString = httpRequest.getQueryString();

			return (httpMethod != null ? httpMethod : "")
					+ " " + path
					+ (queryString != null ? "?" + queryString : "");
		}
		else
		{
			return request.toString();
		}
	}

	private static final String extractRemoteAddr(final HttpServletRequest httpRequest)
	{
		try
		{
			final String remoteAddr = httpRequest.getRemoteAddr();
			if (remoteAddr == null || remoteAddr.isEmpty())
			{
				return MDC_Param_RemoteAddr_DefaultValue;
			}
			return remoteAddr;
		}
		catch (final Exception e)
		{
			return "?";
		}
	}

	private static final String extractLoggedUser(final HttpServletRequest httpRequest)
	{
		try
		{
			final UserSession userSession = UserSession.getCurrentOrNull();
			if (userSession == null)
			{
				return "_noSession";
			}

			if (!userSession.isLoggedIn())
			{
				return "_notLoggedIn";
			}
			else
			{
				return extractUserName(userSession);
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
			return "_error";
		}
	}

	private static final String extractUserName(final UserSession userSession)
	{
		final String userName = userSession.getUserName();
		if (!Check.isEmpty(userName, true))
		{
			return userName;
		}

		final UserId loggedUserId = userSession.getLoggedUserIdIfExists().orElse(null);
		if (loggedUserId != null)
		{
			return String.valueOf(loggedUserId.getRepoId());
		}

		return "?";
	}

	private static final String extractUserAgent(final HttpServletRequest httpRequest)
	{
		try
		{
			final String userAgent = httpRequest.getHeader("User-Agent");
			return userAgent;
		}
		catch (final Exception e)
		{
			e.printStackTrace();
			return "?";
		}
	}

	private static final String extractLoggedUserAndRemoteAddr(final String loggedUser, final String remoteAddr)
	{
		// NOTE: guard against null parameters, if those at this point they shall not
		return (loggedUser == null ? "?" : loggedUser)
				+ "/"
				+ (remoteAddr == null ? "?" : remoteAddr);
	}
}
