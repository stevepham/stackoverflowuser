package com.ht117.sofossill.app.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor

open class BaseViewModel: ViewModel() {

    private val job = Job()
    protected var ioScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}
