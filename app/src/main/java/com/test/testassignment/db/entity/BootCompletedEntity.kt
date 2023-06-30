package com.test.testassignment.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BootCompletedEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timestamp: Long,
)