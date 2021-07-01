package com.example.taskkeeper.ui.holidays

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskkeeper.repository.HolidaysRepository
import com.example.taskkeeper.ui.holidays.model.HolidayObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HolidaysViewerViewModel @Inject constructor(
    private val repository: HolidaysRepository
) : ViewModel() {

    val holidaysList: MutableLiveData<Response<List<HolidayObject>>> = MutableLiveData()

    fun getHolidays(year: Int, code: String) {
        viewModelScope.launch {
            val response = repository.getHolidays(year, code)
            holidaysList.value = response
        }
    }

}