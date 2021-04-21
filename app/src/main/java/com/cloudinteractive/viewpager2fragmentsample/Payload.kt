package com.cloudinteractive.viewpager2fragmentsample

/**
 * 這是我之前看到的寫法  覺得可以拿到之前跟現在的值
 * 覺得不錯   你可以隨便改成你想要的樣式  或者只存新值
 */
data class Change<out T>(
    val oldData: T,
    val newData: T
)

fun <T> createCombinedPayload(payloads: List<Change<T>>): Change<T> {
    if (com.cloudinteractive.viewpager2fragmentsample.BuildConfig.DEBUG && !payloads.isNotEmpty()) {
        error("Assertion failed")
    }
    val firstChange = payloads.first()
    val lastChange = payloads.last()

    return Change(firstChange.oldData, lastChange.newData)
}