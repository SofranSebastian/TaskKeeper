package com.example.taskkeeper.ui.holidays

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.taskkeeper.repository.HolidaysRepository
import com.example.taskkeeper.ui.holidays.model.HolidayObject
import kotlinx.coroutines.launch
import retrofit2.Response

class HolidaysViewerViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HolidaysRepository = HolidaysRepository()

    val holidaysList: MutableLiveData<Response<List<HolidayObject>>> = MutableLiveData()

    fun getHolidays(year: Int, code: String) {
        viewModelScope.launch {
            val response = repository.getHolidays(year, code)
            holidaysList.value = response
        }
    }

}