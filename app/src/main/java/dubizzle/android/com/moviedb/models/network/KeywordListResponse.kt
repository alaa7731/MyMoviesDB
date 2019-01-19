package dubizzle.android.com.moviedb.models.network

import dubizzle.android.com.moviedb.models.Keyword
import dubizzle.android.com.moviedb.models.NetworkResponseModel


data class KeywordListResponse(
        val id: Int,
        val keywords: List<Keyword>
) : NetworkResponseModel
