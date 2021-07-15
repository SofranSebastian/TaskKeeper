package com.example.taskkeeper.ui.holidays

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskkeeper.R
import com.example.taskkeeper.ui.holidays.model.HolidayObject
import kotlinx.android.synthetic.main.item_holiday_layout.view.*

class HolidayAdapter : RecyclerView.Adapter<HolidayAdapter.ViewHolder>() {

    private var holidayList = emptyList<HolidayObject>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_holiday_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return holidayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_date.text = holidayList[position].date
        holder.itemView.tv_global_name.text = holidayList[position].name
        holder.itemView.tv_local_name.text = holidayList[position].localName

        if (!holidayList[position].fixed) {
            holder.itemView.tv_fixed.text = "Not fixed"
        } else {
            holder.itemView.tv_fixed.text = "Fixed"
        }

        if (!holidayList[position].global) {
            holder.itemView.tv_global.text = "Unique"
        } else {
            holder.itemView.tv_global.text = "Global"
        }
    }

    fun setHolidayList(newHolidayList: List<HolidayObject>) {
        holidayList = newHolidayList
        notifyDataSetChanged()
    }
}