package com.example.taskkeeper.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDao {

    //insert Task into database
    //onConflict for the case when there is an identical task inserted
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    //no annotation for the Select so a query needs to be done here
    @Query("SELECT * FROM task_table ORDER BY id ASC")
    fun getAllTasks(): LiveData<List<Task>>
}