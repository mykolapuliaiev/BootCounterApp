package com.test.testassignment.models

import com.test.testassignment.db.entity.BootCompletedEntity

data class BootEvent(val timestamp: Long) {
    fun mapToEntity() = BootCompletedEntity(timestamp = timestamp)
}