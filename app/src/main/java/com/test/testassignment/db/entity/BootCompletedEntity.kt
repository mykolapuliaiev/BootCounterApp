package com.test.testassignment.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.testassignment.models.BootEvent

@Entity
data class BootCompletedEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timestamp: Long,
) {
    fun mapToModel() = BootEvent(timestamp = timestamp)
}