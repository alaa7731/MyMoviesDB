package dubizzle.android.com.moviedb.view.adapter

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow
import dubizzle.android.com.moviedb.R
import dubizzle.android.com.moviedb.models.Resource
import dubizzle.android.com.moviedb.models.entity.Movie
import dubizzle.android.com.moviedb.view.viewholder.MovieListViewHolder

class MovieListAdapter(private val delegate: MovieListViewHolder.Delegate) : BaseAdapter() {

    init {
        addSection(ArrayList<Movie>())
    }

    fun addMovieList(resource: Resource<List<Movie>>) {
        resource.data?.let {
            sections[0].addAll(it)
            notifyDataSetChanged()
        }
    }

    fun refreshMovieList(resource: Resource<List<Movie>>) {
        resource.data?.let {
            clearSections()
            addSection(ArrayList<Movie>())
            sections[0].addAll(it)
             notifyDataSetChanged()
        }
    }

    fun getMovieList(): ArrayList<Movie> {
        var allMovies = ArrayList<Movie>(0)
        sections.forEach {
            it.forEach {
                if (it is Movie)
                    allMovies.add(it)
            }
        }
        return allMovies
    }

    override fun layout(sectionRow: SectionRow): Int {
        return R.layout.item_poster
    }

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        return MovieListViewHolder(view, delegate)
    }
}
