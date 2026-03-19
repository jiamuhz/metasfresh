package de.metas.ui.web.session;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.adempiere.util.concurrent.CustomizableThreadFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSession;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

import de.metas.logging.LogManager;



@Configuration
@EnableSpringHttpSession
@EnableConfigurationProperties(SessionProperties.class)
public class SessionConfig
{
	private static final Logger logger = LogManager.getLogger(SessionConfig.class);

	private static final String BEANNAME_SessionScheduledExecutorService = "sessionScheduledExecutorService";

	@Value("${metasfresh.session.checkExpiredSessionsRateInMinutes:10}")
	private int checkExpiredSessionsRateInMinutes;

	@Bean
	public SessionRepository<MapSession> sessionRepository(
			final SessionProperties properties,
			final ApplicationEventPublisher applicationEventPublisher)
	{
		final FixedMapSessionRepository sessionRepository = FixedMapSessionRepository.builder()
				.applicationEventPublisher(applicationEventPublisher)
				.defaultMaxInactiveInterval(properties.getTimeout())
				.build();
		logger.info("Using session repository: {}", sessionRepository);

		if (checkExpiredSessionsRateInMinutes > 0)
		{
			final ScheduledExecutorService scheduledExecutor = sessionScheduledExecutorService();
			scheduledExecutor.scheduleAtFixedRate(
					sessionRepository::purgeExpiredSessionsNoFail, // command, don't fail because on failure the task won't be re-scheduled so it's game over
					checkExpiredSessionsRateInMinutes, // initialDelay
					checkExpiredSessionsRateInMinutes, // period
					TimeUnit.MINUTES // timeUnit
			);
			logger.info("Checking expired sessions each {} minutes", checkExpiredSessionsRateInMinutes);
		}

		return sessionRepository;
	}

	@Bean(BEANNAME_SessionScheduledExecutorService)
	public ScheduledExecutorService sessionScheduledExecutorService()
	{
		return Executors.newScheduledThreadPool(
				1, // corePoolSize
				CustomizableThreadFactory.builder()
						.setDaemon(true)
						.setThreadNamePrefix(SessionConfig.class.getName())
						.build());
	}
}
