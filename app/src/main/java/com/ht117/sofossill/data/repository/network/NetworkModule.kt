package com.ht117.sofossill.data.repository.network

import com.ht117.sofossill.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single(named("header")) { provideHeaderInterceptor() }
    single(named("network")) { provideNetworkInterceptor() }
    single { provideRetrofit(get(named("network")) , get(named("header"))) }

    single { provideStackService(get()) }
}

fun provideHeaderInterceptor(): Interceptor {
    return object: Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val orgReq = chain.request()
            val customReq = orgReq.newBuilder()
                .addHeader("Accept", "application/json;charset=utf-8")
                .addHeader("Accept-Language", "en")
                .build()
            return chain.proceed(customReq)
        }
    }
}

fun provideNetworkInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}

fun provideRetrofit(networkLogger: Interceptor, headerInterceptor: Interceptor): Retrofit {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val client = OkHttpClient.Builder()
        .addInterceptor(networkLogger)
        .addInterceptor(headerInterceptor)
        .build()

    return Retrofit.Builder()
        .baseUrl("${BuildConfig.BASE_URL}${BuildConfig.API_VERSION}/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(client)
        .build()
}

fun provideStackService(retrofit: Retrofit): IUserService {
    return retrofit.create(IUserService::class.java)
}