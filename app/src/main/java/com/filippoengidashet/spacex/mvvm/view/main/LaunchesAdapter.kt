package com.filippoengidashet.spacex.mvvm.view.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.filippoengidashet.spacex.R
import com.filippoengidashet.spacex.common.DateHelper
import com.filippoengidashet.spacex.common.ImageLoader
import com.filippoengidashet.spacex.common.SimpleDiffUtilCallback
import com.filippoengidashet.spacex.mvvm.model.entity.bean.Launch

/**
 * @author Filippo 09/11/2021
 */
class LaunchesAdapter constructor(
    private val context: Context,
    private val dateHelper: DateHelper,
    private val imageLoader: ImageLoader,
    private val onClick: (Launch) -> Unit = {},
) : RecyclerView.Adapter<LaunchesAdapter.LaunchViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val launches: MutableList<Launch> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        return LaunchViewHolder(
            inflater.inflate(R.layout.item_launch_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(launches[position])
    }

    override fun getItemCount() = launches.size

    fun setLaunches(_items: List<Launch>) {
        val oldList = ArrayList(launches)
        launches.clear()
        launches.addAll(_items)
        val diffCallback = object : SimpleDiffUtilCallback<Launch>(oldList, _items) {

            override fun isSameItem(oldItem: Launch, newItem: Launch): Boolean {
                return oldItem == newItem
            }
        }
        DiffUtil.calculateDiff(diffCallback).dispatchUpdatesTo(this)
    }

    inner class LaunchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imagePatch = itemView.findViewById<ImageView>(R.id.image_patch)
        private val imageSuccessStatus = itemView.findViewById<ImageView>(R.id.image_success_status)
        private val textMission = itemView.findViewById<TextView>(R.id.text_mission_name)
        private val textDateTime = itemView.findViewById<TextView>(R.id.text_datetime)
        private val textRocketType = itemView.findViewById<TextView>(R.id.text_rocket_type)
        private val textDays = itemView.findViewById<TextView>(R.id.text_days)
        private val textLaunchDate = itemView.findViewById<TextView>(R.id.text_launch_date)

        private lateinit var item: Launch

        init {
            itemView.setOnClickListener {
                onClick(item)
            }
        }

        fun bind(_item: Launch) {
            item = _item
            textMission.text = _item.mission
            textDateTime.text = _item.datetime
            textRocketType.text = _item.rocketType

            val days = dateHelper.formatDays(_item.dateMillis)
            textDays.text = context.getString(R.string.text_days, days.first)
            textLaunchDate.text = "${days.second}"

            val imageRes = if (_item.succeeded) R.drawable.ic_success else R.drawable.ic_failed
            imageLoader.load(imageRes, imageSuccessStatus)
            imageLoader.load(_item.url, imagePatch)
        }
    }
}
