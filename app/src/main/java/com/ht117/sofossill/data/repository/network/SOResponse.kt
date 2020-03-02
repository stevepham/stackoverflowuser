package com.ht117.sofossill.data.repository.network

import com.squareup.moshi.Json

data class SOResponse<D>(@Json(name = "items") val items: List<D>,
                         @Json(name = "has_more") val hasMore: Boolean,
                         @Json(name = "quota_max") val quotaMax: Long,
                         @Json(name = "quota_remaining") val quotaRemaining: Long)