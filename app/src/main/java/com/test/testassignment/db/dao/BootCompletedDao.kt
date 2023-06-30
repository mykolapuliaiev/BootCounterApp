package com.test.testassignment.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.testassignment.db.entity.BootCompletedEntity

@Dao
interface BootCompletedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBootCompletedEvent(event: BootCompletedEntity)

    @Query("SELECT * FROM BootCompletedEntity ORDER BY timestamp ASC")
    suspend fun getBootCompletedEvents(): List<BootCompletedEntity>
}