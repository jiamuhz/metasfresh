package de.metas.util.lang;

/** */

/**
 * @implNote Following static methods shall be implemented
 * <ul>
 *     <li>ofRepoId(int)</li>
 *     <li>ofRepoIdOrNull(int)</li>
 * </ul>
 * Note that instances of RepoIdAware can be used in query-filters, instead of integers. 
 * metasfresh will unbox them by calling {@link #getRepoId()}.
 */
public interface RepoIdAware extends Comparable<RepoIdAware>
{
	int getRepoId();

	@Override
	default int compareTo(final RepoIdAware other)
	{
		return getRepoId() - other.getRepoId();
	}
}
