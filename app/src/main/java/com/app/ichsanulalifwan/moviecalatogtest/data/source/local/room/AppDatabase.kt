package com.app.ichsanulalifwan.moviecalatogtest.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.*
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvGenreEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowEntity


@Database(
    entities = [
        MovieNowPlayingEntity::class,
        MoviePopularEntity::class,
        MovieTopRatedEntity::class,
        MovieUpcomingEntity::class,
        MovieGenreEntity::class,
        TvShowEntity::class,
        TvGenreEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moviesDao(): AppDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Movies.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}