package dubizzle.android.com.moviedb.view.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import dubizzle.android.com.moviedb.R
import dubizzle.android.com.moviedb.extension.observeLiveData
import dubizzle.android.com.moviedb.models.Resource
import dubizzle.android.com.moviedb.models.Status
import dubizzle.android.com.moviedb.models.entity.Movie
import dubizzle.android.com.moviedb.view.adapter.MovieListAdapter
import dubizzle.android.com.moviedb.view.ui.details.movie.MovieDetailActivity
import dubizzle.android.com.moviedb.view.viewholder.MovieListViewHolder
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.main_fragment_movie.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject


@Suppress("SpellCheckingInspection")
class MovieListFragment : Fragment(), MovieListViewHolder.Delegate {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainActivityViewModel

    private val adapter = MovieListAdapter(this)
    private lateinit var paginator: RecyclerViewPaginator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
        observeViewModel()
    }

    private fun initializeUI() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        paginator = RecyclerViewPaginator(
                recyclerView = recyclerView,
                isLoading = { viewModel.getMovieListValues()?.status == Status.LOADING },
                loadMore = { loadMore(it) },
                onLast = { viewModel.getMovieListValues()?.onLastPage!! }
        )
        paginator.currentPage = 1
    }

    private fun observeViewModel() {
        observeLiveData(viewModel.getMovieListObservable()) { updateMovieList(it) }
        viewModel.postMoviePage(1)
    }

    private var filteredMovies: Resource<List<Movie>>? = null
    private var originalMovies: Resource<List<Movie>>? = null

    private var allMovies = ArrayList<List<Movie>>(0)

    private fun updateMovieList(resource: Resource<List<Movie>>) {
        when (resource.status) {
            Status.SUCCESS -> {
                resource.data?.let {
                    allMovies.addAll(listOf(it))
                }
                if (isFiltered) {
                    filteredMovies = resource
                    originalMovies = resource

                    filterMovies(startDate, endDate)
                } else {
                    adapter.addMovieList(resource)
                    originalMovies = resource
                    filteredMovies = resource
                }
            }
            Status.ERROR -> toast(resource.errorEnvelope?.status_message.toString())
            Status.LOADING -> Unit
        }
    }

    private fun loadMore(page: Int) {
        currentPage = page
        viewModel.postMoviePage(page)
    }

    override fun onItemClick(movie: Movie) {
        startActivity<MovieDetailActivity>("movie" to movie)
    }

    private var isFiltered: Boolean = false
    private var endDate: Int = 0
    private var startDate: Int = 0

    fun resetFilter() {
        isFiltered = false
        val originalMoviesList = ArrayList<Movie>(0)

        allMovies.forEach {
            it.forEach {
                originalMoviesList.add(it)
            }
        }
        originalMovies?.data = originalMoviesList
        adapter.refreshMovieList(originalMovies!!)
    }

    private var currentPage: Int = 1

    fun filterMovies(startDate: Int, endDate: Int) {
        isFiltered = true
        this.startDate = startDate
        this.endDate = endDate
        val filteredMoviesList = ArrayList<Movie>(0)

        allMovies.forEach {
            it.forEach {
                if (it.release_date.length > 3) {
                    var releaseDate = it.release_date.subSequence(0, 4).toString().toInt()
                    if ((releaseDate >= startDate) && (releaseDate <= endDate)) {
                        filteredMoviesList.add(it)
                    }
                }
            }
        }
        filteredMovies?.data = filteredMoviesList
        adapter.refreshMovieList(filteredMovies!!)
        loadMore(++currentPage)
    }
}
