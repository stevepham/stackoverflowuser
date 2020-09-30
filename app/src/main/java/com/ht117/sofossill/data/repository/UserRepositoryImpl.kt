package com.ht117.sofossill.data.repository

import androidx.paging.*
import com.ht117.sofossill.data.mapper.Mapper
import com.ht117.sofossill.data.model.ReputationModel
import com.ht117.sofossill.data.model.UserModel
import com.ht117.sofossill.data.repository.db.IUserDao
import com.ht117.sofossill.data.repository.network.IUserService
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(private val service: IUserService, private val dao: IUserDao): IUserRepository {

    override suspend fun getUsers(): Flow<PagingData<UserModel>> {
        return Pager(config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { UserSource(service, dao) })
            .flow
    }

    override suspend fun getReputation(userId: String): Flow<PagingData<ReputationModel>> {
        return Pager(config = PagingConfig(pageSize = 20), pagingSourceFactory = { UserReputationSource(userId, service) })
            .flow
    }

}

class UserSource(private val service: IUserService, private val dao: IUserDao)
    : PagingSource<Int, UserModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserModel> {
        val page = params.key?: 0 + 1
        val prevPage = if (page == 1) null else page - 1
        val size = params.loadSize
        return try {
            val result = service.getUsers(page, size)
            return LoadResult.Page(prevKey = prevPage, nextKey = page + 1, data = result.items)
        } catch (exp: Exception) {
            LoadResult.Error(exp)
        }
    }
}

class UserReputationSource(private val userId: String, private val service: IUserService)
    : PagingSource<Int, ReputationModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReputationModel> {
        val page = params.key?: 1
        val prevPage = if (page == 1) null else page - 1
        val size = params.loadSize
        return try {
            val result = service.getUserReputation(userId, page, size)
            LoadResult.Page(result.items, prevPage, page + 1)
        } catch (exp: Exception) {
            LoadResult.Error(exp)
        }
    }

}