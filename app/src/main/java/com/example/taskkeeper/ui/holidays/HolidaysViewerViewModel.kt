package com.example.taskkeeper.ui.holidays

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskkeeper.exceptions.NoInternetException
import com.example.taskkeeper.networking.spaceone.model.TaskItemSpo
import com.example.taskkeeper.repository.HolidaysRepository
import com.example.taskkeeper.repository.SpoRepository
import com.example.taskkeeper.ui.holidays.model.HolidayObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import one.space.networking.core.SpoClient
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HolidaysViewerViewModel @Inject constructor(
    private val repository: HolidaysRepository,
    private val spoRepository: SpoRepository,
    private val spoClient: SpoClient
) : ViewModel() {

    val holidaysList: MutableLiveData<Response<List<HolidayObject>>> = MutableLiveData()
    val tasksList: MutableLiveData<Response<List<TaskItemSpo>>> = MutableLiveData()


    fun getHolidays(year: Int, code: String) {
        viewModelScope.launch {
            try {
                //val response = repository.getHolidays(year, code)
                //holidaysList.value = response
                val responseSpo = spoClient.item().getItemList(TaskItemSpo::class.java)
                //tasksList.value = responseSpo
                if (responseSpo.isSuccessful) {
                    val yourItemList = responseSpo.items
                    Log.d("INTERNET RESPONSE", yourItemList.toString())
                } else {
                    Log.i(
                        "SpoClient",
                        "receiving data failed with responseCode:'${responseSpo.responseCode}'"
                    )
                    responseSpo.errorList.forEach { Log.i("SpoClient", it.toString()) }
                }

            } catch (e: NoInternetException) {
                //the holidaysList needs to be initialised because otherwise the observer
                //from the fragment will not do anything regarding the list.
                //Need to create a 'fake' response because when the try fails the response
                //from the repository is not available in the catch anymore
                holidaysList.value = Response.error(
                    404,
                    "".toResponseBody("text/plain".toMediaType())
                )
            }

        }
    }
}
