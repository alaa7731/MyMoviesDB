package dubizzle.android.com.moviedb.models.network

import dubizzle.android.com.moviedb.models.NetworkResponseModel
import dubizzle.android.com.moviedb.models.entity.Movie


data class DiscoverMovieResponse(
        val page: Int,
        val results: List<Movie>,
        val total_results: Int,
        val total_pages: Int
) : NetworkResponseModel
