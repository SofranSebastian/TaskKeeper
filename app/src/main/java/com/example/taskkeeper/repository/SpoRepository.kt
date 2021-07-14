package com.example.taskkeeper.repository

import com.example.taskkeeper.networking.spaceone.SpoInstance
import com.example.taskkeeper.networking.spaceone.model.TaskItemSpo
import one.space.networking.core.SpoClient
import one.space.networking.core.model.api.item.ItemListGetResponse
import one.space.networking.core.model.api.item.ItemTypeListGetResponse
import retrofit2.Response
import javax.inject.Inject

class SpoRepository @Inject constructor(
    private val spoInstance: SpoInstance
) {

//    suspend fun getItemsList(authKey: String, scopeKey: String, typeKey: String): Response<List<TaskItemSpo>> {
//        return spoInstance.spoApi.getItemsList(authKey, scopeKey, typeKey)
//    }

}