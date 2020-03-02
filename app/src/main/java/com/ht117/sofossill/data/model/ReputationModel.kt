package com.ht117.sofossill.data.model

import com.squareup.moshi.Json

data class ReputationModel(@Json(name = "reputation_history_type") val historyType: String,
                           @Json(name = "reputation_change") val change: Long,
                           @Json(name = "creation_date") val creationDate: Long,
                           @Json(name = "post_id") val postId: Long)