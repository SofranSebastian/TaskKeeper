package com.example.taskkeeper.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.taskkeeper.database.model.Task

@Dao
interface TaskDao {

    //insert Task into database
    //onConflict for the case when there is an identical task inserted
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    //no annotation for the Select so a query needs to be done here
    @Query("SELECT * FROM task_table ORDER BY id ASC")
    fun getAllTasks(): LiveData<List<Task>>

    //update a task
    @Update
    suspend fun updateTask(task: Task)

    //delete a single task
    @Delete
    suspend fun deleteTask(task: Task)

    //delete all tasks
    @Query("DELETE FROM task_table")
    suspend fun deleteAllTasks()
}