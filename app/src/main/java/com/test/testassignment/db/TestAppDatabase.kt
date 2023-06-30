package com.test.testassignment.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.testassignment.db.dao.BootCompletedDao
import com.test.testassignment.db.entity.BootCompletedEntity

@Database(entities = [BootCompletedEntity::class], version = 1)
abstract class TestAppDatabase: RoomDatabase() {

    abstract  fun bootEventsDao(): BootCompletedDao

    companion object {
        const val NAME = "test_app_db"
    }
}