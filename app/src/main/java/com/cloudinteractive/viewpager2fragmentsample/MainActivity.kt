package com.cloudinteractive.viewpager2fragmentsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val pagerAdapter by lazy { PagerAdapter(this) }

    private var items = listOf(
        PagerItem(1, 0),
        PagerItem(2, 0),
        PagerItem(4, 0),
        PagerItem(8, 0),
        PagerItem(16, 0),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pager = findViewById<ViewPager2>(R.id.vpCounter)
        pager.adapter = pagerAdapter

        var count = 0

        lifecycleScope.launch {
            while (true) {
                delay(500)
                count++
                items = items.map {
                    if (count % it.id == 0)
                        PagerItem(it.id, it.count + 1)
                    else
                        it
                }

                pagerAdapter.setItems(items)
            }
        }

    }
}