package dubizzle.android.com.moviedb.mappers

import android.util.Log
import dubizzle.android.com.moviedb.models.network.DiscoverMovieResponse


class MovieResponseMapper : NetworkResponseMapper<DiscoverMovieResponse> {
    override fun onLastPage(response: DiscoverMovieResponse): Boolean {
        Log.d("onLastPage", "loadPage : ${response.page}/${response.total_pages}")
        return response.page > response.total_pages
    }
}
