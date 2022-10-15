package com.app.ichsanulalifwan.moviecalatogtest.di

import android.content.Context
import com.app.ichsanulalifwan.moviecalatogtest.data.AppRepository
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.LocalDataSource
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.room.AppDatabase
import com.app.ichsanulalifwan.moviecalatogtest.data.source.remote.RemoteDataSource
import com.app.ichsanulalifwan.moviecalatogtest.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): AppRepository {

        val database = AppDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.moviesDao())
        val appExecutors = AppExecutors()

        return AppRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}