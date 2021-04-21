package com.cloudinteractive.viewpager2fragmentsample

import java.io.Serializable


/**
 * 這邊看你的結構是什麼 (根據你的 API  我 sample 只是隨便定個 id  跟計數器)
 */

data class PagerItem(val id: Int, var count: Int): Serializable
