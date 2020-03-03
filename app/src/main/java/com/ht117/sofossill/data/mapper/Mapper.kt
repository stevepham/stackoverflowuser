package com.ht117.sofossill.data.mapper

import com.ht117.sofossill.data.entity.UserEntity
import com.ht117.sofossill.data.model.UserModel

object Mapper {

    fun userEntityToModel(entity: UserEntity): UserModel {
        return UserModel(userId = entity.userId,
            displayName = entity.displayName,
            profileImage = entity.profileImage,
            reputation = entity.reputation!!,
            location = entity.location,
            lastAccessDate = entity.lastAccessDate,
            isBookmarked = entity.isBookmarked)
    }

    fun userEntitiesToModels(entities: List<UserEntity>): List<UserModel> {
        val result = mutableListOf<UserModel>()
        entities.forEach {
            result.add(userEntityToModel(it))
        }
        return result
    }

    fun userModelToEntity(model: UserModel): UserEntity {
        return UserEntity(userId = model.userId,
            displayName = model.displayName,
            profileImage = model.profileImage,
            reputation = model.reputation,
            location = model.location,
            lastAccessDate = model.lastAccessDate,
            isBookmarked = model.isBookmarked)
    }

    fun userModelsToEntities(models: List<UserModel>): List<UserEntity> {
        val result = mutableListOf<UserEntity>()
        models.forEach {
            result.add(userModelToEntity(it))
        }
        return result
    }
}