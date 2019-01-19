package dubizzle.android.com.moviedb.mappers

import dubizzle.android.com.moviedb.models.network.VideoListResponse


class VideoResponseMapper : NetworkResponseMapper<VideoListResponse> {
    override fun onLastPage(response: VideoListResponse): Boolean {
        return true
    }
}
