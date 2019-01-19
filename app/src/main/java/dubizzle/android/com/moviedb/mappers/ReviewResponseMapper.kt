package dubizzle.android.com.moviedb.mappers

import dubizzle.android.com.moviedb.models.network.ReviewListResponse


class ReviewResponseMapper : NetworkResponseMapper<ReviewListResponse> {
    override fun onLastPage(response: ReviewListResponse): Boolean {
        return true
    }
}
