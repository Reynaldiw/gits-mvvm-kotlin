package id.gits.gitsmvvmkotlin.base

import com.google.gson.annotations.SerializedName

/**
 * Created by irfanirawansukirman on 26/01/18.
 */

data class BaseApiModel<T>(
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String,
        @SerializedName("data") val data: T? = null,
        @SerializedName("teams") val teams : T? = null,

//        Remove code below if project is running
        var page: Int,
        var total_results: Int,
        var total_pages: Int,
        var results: T? = null
)