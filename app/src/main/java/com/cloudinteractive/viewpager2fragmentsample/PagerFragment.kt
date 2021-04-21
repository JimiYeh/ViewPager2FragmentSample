package com.cloudinteractive.viewpager2fragmentsample

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.lang.IllegalArgumentException

class PagerFragment : Fragment(R.layout.fragment_pager) {

    companion object {

        const val BUNDLE_PAGE_ITEM = "BUNDLE_PAGE_ITEM"

        fun newInstance(pagerItem: PagerItem): PagerFragment = PagerFragment().apply {
            val bundle = Bundle().apply {
                putSerializable(BUNDLE_PAGE_ITEM, pagerItem)
            }
            arguments = bundle
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerItem = arguments?.getSerializable(BUNDLE_PAGE_ITEM) as PagerItem
                ?: throw IllegalArgumentException("null pager item")

        val tvPageId = view.findViewById<TextView>(R.id.tvPageId)
        tvPageId.text = pagerItem.id.toString()

        val tvPagerCount = view.findViewById<TextView>(R.id.tvCounter)
        tvPagerCount.text = pagerItem.count.toString()

    }
}