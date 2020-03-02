package com.ht117.sofossill.data.repository.network

import com.ht117.sofossill.data.model.ReputationModel
import com.ht117.sofossill.data.model.UserModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IUserService {

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int,
                         @Query("pagesize") pageSize: Int,
                         @Query("site") site: String = "stackoverflow"): SOResponse<UserModel>

    @GET("users/{userId}/reputation-history")
    suspend fun getUserReputation(@Path("userId") userId: String,
                                  @Query("page") page: Int,
                                  @Query("pagesize") pageSize: Int,
                                  @Query("site") site: String = "stackoverflow"): SOResponse<ReputationModel>
}