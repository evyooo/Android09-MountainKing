package com.boostcamp.mountainking.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.boostcamp.mountainking.entity.Achievement
import com.boostcamp.mountainking.entity.Tracking
import com.boostcamp.mountainking.util.Converters


@Database(
    entities = [Statistics::class, Achievement::class, Tracking::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun achievementDao(): AchievementDao
    abstract fun statisticsDao(): StatisticsDao
    abstract fun trackingDao(): TrackingDao

    companion object {
        private const val DATABASE_NAME = "DB"
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            return instance
        }

    }

}