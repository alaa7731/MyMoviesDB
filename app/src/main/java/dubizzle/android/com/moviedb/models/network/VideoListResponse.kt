package dubizzle.android.com.moviedb.models.network

import dubizzle.android.com.moviedb.models.NetworkResponseModel
import dubizzle.android.com.moviedb.models.Video


data class VideoListResponse(
        val id: Int,
        val results: List<Video>
) : NetworkResponseModel
