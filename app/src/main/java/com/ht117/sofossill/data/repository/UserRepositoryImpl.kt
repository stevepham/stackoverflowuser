package com.ht117.sofossill.data.repository

import androidx.paging.DataSource
import com.ht117.sofossill.data.mapper.Mapper
import com.ht117.sofossill.data.model.ReputationModel
import com.ht117.sofossill.data.model.UserModel
import com.ht117.sofossill.data.repository.db.IUserDao
import com.ht117.sofossill.data.repository.network.IUserService

class UserRepositoryImpl(private val userService: IUserService,
                         private val userDao: IUserDao): IUserRepository {

    override fun getUsersSource(): DataSource.Factory<Int, UserModel> {
        return userDao.getAllUsers().map {
            Mapper.userEntityToModel(it)
        }
    }

    override fun getBookmarkedUser(): DataSource.Factory<Int, UserModel> {
        return userDao.getBookmarkedUsers().map {
            Mapper.userEntityToModel(it)
        }
    }

    override suspend fun getUsersFromApiAndCache(page: Int, pageSize: Int): List<UserModel> {
        val result = userService.getUsers(page, pageSize).items
        if (result.isNotEmpty()) {
            userDao.insertAll(Mapper.userModelsToEntities(result))
        }
        return result
    }

    override suspend fun getUsersFromLocal(page: Int, pageSize: Int): List<UserModel> {
        return Mapper.userEntitiesToModels(userDao.getAllUserInPage(pageSize, (page - 1) * pageSize))
    }

    override suspend fun getUserReputation(userId: String, page: Int, pageSize: Int): List<ReputationModel> {
        return userService.getUserReputation(userId, page, pageSize).items
    }

    override fun markedUser(userId: Long, bookmarked: Boolean) {
        userDao.updateUser(userId, bookmarked)
    }
}