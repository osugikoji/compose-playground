package com.playground.test.utils

/**
 * Retry the same function execution for an amount of times.
 * @param times Number of tries.
 * @param timeout Time between the tries.
 * @param function Function/block to run.
 */
fun retryer(times: Int = 3, timeout: Long = 300, function: () -> Unit) {
    for (i in 0 until times) {
        try {
            function()
            return
        } catch (e: Throwable) {
            if (i == times - 1) throw e
            Thread.sleep(timeout)
        }
    }
}
