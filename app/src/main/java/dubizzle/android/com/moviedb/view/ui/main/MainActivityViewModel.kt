package dubizzle.android.com.moviedb.view.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dubizzle.android.com.moviedb.models.Resource
import dubizzle.android.com.moviedb.models.entity.Movie
import dubizzle.android.com.moviedb.repository.DiscoverRepository
import dubizzle.android.com.moviedb.utils.AbsentLiveData
import javax.inject.Inject


class MainActivityViewModel @Inject
constructor(private val discoverRepository: DiscoverRepository) : ViewModel() {

    private var moviePageLiveData: MutableLiveData<Int> = MutableLiveData()
    private val movieListLiveData: LiveData<Resource<List<Movie>>>

    init {
        Log.d("Init", "injection MainActivityViewModel")

        movieListLiveData = Transformations.switchMap(moviePageLiveData) {
            moviePageLiveData.value?.let { discoverRepository.loadMovies(it) }
                    ?: AbsentLiveData.create()
        }

    }

    fun getMovieListObservable() = movieListLiveData
    fun getMovieListValues() = getMovieListObservable().value
    fun postMoviePage(page: Int) = moviePageLiveData.postValue(page)
}
