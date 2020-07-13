package com.veles.authorizationflows.util

import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

fun RecyclerView.fabHideOnScroll(fab: FloatingActionButton) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            when {
                dy > 0 && fab.isShown -> fab.hide()
                dy < 0 && !fab.isShown -> fab.show();
            }
        }
    })
}

public fun <T> Task<T>.asDeferred(): Deferred<T> {
    if (isComplete) {
        val e = exception
        return if (e == null) {
            @Suppress("UNCHECKED_CAST")
            CompletableDeferred<T>().apply { if (isCanceled) cancel() else complete(result as T) }
        } else {
            CompletableDeferred<T>().apply { completeExceptionally(e) }
        }
    }
    val result = CompletableDeferred<T>()
    addOnCompleteListener {
        val e = it.exception
        if (e == null) {
            @Suppress("UNCHECKED_CAST")
            if (isCanceled) result.cancel() else result.complete(it.result as T)
        } else {
            result.completeExceptionally(e)
        }
    }
    return result
}