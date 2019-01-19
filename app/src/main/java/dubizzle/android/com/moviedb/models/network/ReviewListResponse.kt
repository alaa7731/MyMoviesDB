package dubizzle.android.com.moviedb.models.network

import dubizzle.android.com.moviedb.models.NetworkResponseModel
import dubizzle.android.com.moviedb.models.Review


class ReviewListResponse(
        val id: Int,
        val page: Int,
        val results: List<Review>,
        val total_pages: Int,
        val total_results: Int
) : NetworkResponseModel
