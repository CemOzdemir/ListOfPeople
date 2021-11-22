package com.e.listofpeople.data

import androidx.paging.PagingSource
import androidx.paging.PagingState

class PersonPagingSource : PagingSource<Int, Person>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Person> {
            val nextPage = params.key
            val dataSource = DataSource()
            var response: FetchResponse? = null
            var errorDescription: String? = null

            dataSource.fetch(nextPage?.toString()) { fetchResponse, fetchError ->
                fetchResponse?.let { response = it }
                fetchError?.let { errorDescription = it.errorDescription }
            }
            response?.let {
                return LoadResult.Page(
                    data = it.people,
                    prevKey = null,
                    nextKey = it.next?.toInt()
                )
            }?: return LoadResult.Error(Throwable(errorDescription))
    }

    override fun getRefreshKey(state: PagingState<Int, Person>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}