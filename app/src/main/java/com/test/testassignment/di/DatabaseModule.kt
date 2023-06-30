package com.test.testassignment.di

import android.content.Context
import androidx.room.Room
import com.test.testassignment.db.TestAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provide(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, TestAppDatabase::class.java, TestAppDatabase.NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideDao(db: TestAppDatabase) = db.bootEventsDao()
}