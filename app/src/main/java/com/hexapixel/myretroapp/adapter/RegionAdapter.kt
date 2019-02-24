package com.hexapixel.myretroapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hexapixel.myretroapp.R
import com.hexapixel.myretroapp.entity.Region


class RegionAdapter(val regions: MutableList<Region>)
    : RecyclerView.Adapter<RegionAdapter.RegionViewHolder>() {

    override fun getItemCount(): Int {
        return regions.size
    }

    override fun onBindViewHolder(holder: RegionViewHolder, position: Int) {
        val region = regions[position]
        holder.bind(region)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.region_card, parent, false)
        return RegionViewHolder(view)

    }
    inner class RegionViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        val regionName = itemView.findViewById<TextView>(R.id.region_name)
        val regionFlag = itemView.findViewById<ImageView>(R.id.region_flag)
        fun bind(region: Region) {
            regionName.text = region.regionNameMm
        }
    }
}