package dubizzle.android.com.moviedb.repository

import android.util.Log
import androidx.lifecycle.LiveData
import dubizzle.android.com.moviedb.api.ApiResponse
import dubizzle.android.com.moviedb.api.TheDiscoverService
import dubizzle.android.com.moviedb.mappers.MovieResponseMapper
import dubizzle.android.com.moviedb.models.Resource
import dubizzle.android.com.moviedb.models.entity.Movie
import dubizzle.android.com.moviedb.models.network.DiscoverMovieResponse
import dubizzle.android.com.moviedb.room.MovieDao
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DiscoverRepository @Inject
constructor(val discoverService: TheDiscoverService, val movieDao: MovieDao)
    : Repository {

    init {
        Log.d("Init", "Injection DiscoverRepository")
    }

    fun loadMovies(page: Int): LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundRepository<List<Movie>, DiscoverMovieResponse, MovieResponseMapper>() {
            override fun saveFetchData(items: DiscoverMovieResponse) {
                for (item in items.results) {
                    item.page = page
                }
                movieDao.insertMovieList(movies = items.results)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Movie>> {
                return movieDao.getMovieList(page_ = page)
            }

            override fun fetchService(): LiveData<ApiResponse<DiscoverMovieResponse>> {
                return discoverService.fetchDiscoverMovie(page = page)
            }

            override fun mapper(): MovieResponseMapper {
                return MovieResponseMapper()
            }

            override fun onFetchFailed(message: String?) {
                Log.d("onFetchFailed","onFetchFailed $message")
            }
        }.asLiveData()
    }

}
