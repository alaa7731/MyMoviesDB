package dubizzle.android.com.moviedb.api

import androidx.lifecycle.LiveData
import dubizzle.android.com.moviedb.models.network.KeywordListResponse
import dubizzle.android.com.moviedb.models.network.ReviewListResponse
import dubizzle.android.com.moviedb.models.network.VideoListResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface MovieService {

    @GET("/3/movie/{movie_id}/keywords")
    fun fetchKeywords(@Path("movie_id") id: Int): LiveData<ApiResponse<KeywordListResponse>>


    @GET("/3/movie/{movie_id}/videos")
    fun fetchVideos(@Path("movie_id") id: Int): LiveData<ApiResponse<VideoListResponse>>


    @GET("/3/movie/{movie_id}/reviews")
    fun fetchReviews(@Path("movie_id") id: Int): LiveData<ApiResponse<ReviewListResponse>>
}
