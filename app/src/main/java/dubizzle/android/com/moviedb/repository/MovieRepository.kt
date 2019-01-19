package dubizzle.android.com.moviedb.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dubizzle.android.com.moviedb.api.ApiResponse
import dubizzle.android.com.moviedb.api.MovieService
import dubizzle.android.com.moviedb.mappers.KeywordResponseMapper
import dubizzle.android.com.moviedb.mappers.ReviewResponseMapper
import dubizzle.android.com.moviedb.mappers.VideoResponseMapper
import dubizzle.android.com.moviedb.models.Keyword
import dubizzle.android.com.moviedb.models.Resource
import dubizzle.android.com.moviedb.models.Review
import dubizzle.android.com.moviedb.models.Video
import dubizzle.android.com.moviedb.models.network.KeywordListResponse
import dubizzle.android.com.moviedb.models.network.ReviewListResponse
import dubizzle.android.com.moviedb.models.network.VideoListResponse
import dubizzle.android.com.moviedb.room.MovieDao
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRepository @Inject
constructor(val service: MovieService, val movieDao: MovieDao) : Repository {

    init {
        Log.d("Init", "Injection MovieRepository")
    }

    fun loadKeywordList(id: Int): LiveData<Resource<List<Keyword>>> {
        return object : NetworkBoundRepository<List<Keyword>, KeywordListResponse, KeywordResponseMapper>() {
            override fun saveFetchData(items: KeywordListResponse) {
                val movie = movieDao.getMovie(id_ = id)
                movie.keywords = items.keywords
                movieDao.updateMovie(movie = movie)
            }

            override fun shouldFetch(data: List<Keyword>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Keyword>> {
                val movie = movieDao.getMovie(id_ = id)
                val data: MutableLiveData<List<Keyword>> = MutableLiveData()
                data.value = movie.keywords
                return data
            }

            override fun fetchService(): LiveData<ApiResponse<KeywordListResponse>> {
                return service.fetchKeywords(id = id)
            }

            override fun mapper(): KeywordResponseMapper {
                return KeywordResponseMapper()
            }

            override fun onFetchFailed(message: String?) {
                Log.d("onFetchFailed", "onFetchFailed : $message")
            }
        }.asLiveData()
    }

    fun loadVideoList(id: Int): LiveData<Resource<List<Video>>> {
        return object : NetworkBoundRepository<List<Video>, VideoListResponse, VideoResponseMapper>() {
            override fun saveFetchData(items: VideoListResponse) {
                val movie = movieDao.getMovie(id_ = id)
                movie.videos = items.results
                movieDao.updateMovie(movie = movie)
            }

            override fun shouldFetch(data: List<Video>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Video>> {
                val movie = movieDao.getMovie(id_ = id)
                val data: MutableLiveData<List<Video>> = MutableLiveData()
                data.value = movie.videos
                return data
            }

            override fun fetchService(): LiveData<ApiResponse<VideoListResponse>> {
                return service.fetchVideos(id = id)
            }

            override fun mapper(): VideoResponseMapper {
                return VideoResponseMapper()
            }

            override fun onFetchFailed(message: String?) {
                Log.d("onFetchFailed","onFetchFailed : $message")
            }
        }.asLiveData()
    }

    fun loadReviewsList(id: Int): LiveData<Resource<List<Review>>> {
        return object : NetworkBoundRepository<List<Review>, ReviewListResponse, ReviewResponseMapper>() {
            override fun saveFetchData(items: ReviewListResponse) {
                val movie = movieDao.getMovie(id_ = id)
                movie.reviews = items.results
                movieDao.updateMovie(movie = movie)
            }

            override fun shouldFetch(data: List<Review>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Review>> {
                val movie = movieDao.getMovie(id_ = id)
                val data: MutableLiveData<List<Review>> = MutableLiveData()
                data.value = movie.reviews
                return data
            }

            override fun fetchService(): LiveData<ApiResponse<ReviewListResponse>> {
                return service.fetchReviews(id = id)
            }

            override fun mapper(): ReviewResponseMapper {
                return ReviewResponseMapper()
            }

            override fun onFetchFailed(message: String?) {
                Log.d("onFetchFailed", "onFetchFailed : $message")
            }
        }.asLiveData()
    }
}
