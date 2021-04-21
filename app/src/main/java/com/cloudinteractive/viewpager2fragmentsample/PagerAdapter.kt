package com.cloudinteractive.viewpager2fragmentsample

import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder

class PagerAdapter(private val activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val items: MutableList<PagerItem> = mutableListOf()

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment {
        return PagerFragment.newInstance(items[position])
    }



    /**
     * 寫到這邊才發現  要 override 這兩個 function  必須要有一個 long
     * 你自己想辦法從 API 找一個可以轉成  long 又是唯一的東西吧   不行就自己訂一個吧
     */
    override fun getItemId(position: Int): Long {
        return items[position].id.toLong()
    }


    override fun containsItem(itemId: Long): Boolean {
        return items.any { it.id.toLong() == itemId }
    }


    /**
     * 這邊的 payload 是 diff 出來的結果  Change 這個結構
     */
    override fun onBindViewHolder(
        holder: FragmentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {

        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else {
            @Suppress("UNCHECKED_CAST")
            val combinedChange = createCombinedPayload(payloads as List<Change<PagerItem>>)

            val counter: TextView = holder.itemView.findViewById(R.id.tvCounter)
            counter.text = combinedChange.newData.count.toString()
        }
    }


    fun setItems(newItems: List<PagerItem>) {
        val callback = PagerDiffUtil(items, newItems)
        val diff = DiffUtil.calculateDiff(callback)

        items.clear()
        items.addAll(newItems)

        diff.dispatchUpdatesTo(this)
    }
}