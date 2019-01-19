package dubizzle.android.com.moviedb.view.viewholder

import android.view.View
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import dubizzle.android.com.moviedb.api.Api
import dubizzle.android.com.moviedb.models.entity.Movie
import kotlinx.android.synthetic.main.item_poster.view.*

class MovieListViewHolder(view: View, private val delegate: Delegate) : BaseViewHolder(view) {

    interface Delegate {
        fun onItemClick(movie: Movie)
    }

    private lateinit var movie: Movie

    @Throws(Exception::class)
    override fun bindData(data: Any) {
        if (data is Movie) {
            movie = data
            buildItem()
        }
    }

    private fun buildItem() {
        itemView.run {
            item_poster_title.text = movie.title
            movie.poster_path?.let {
                Glide.with(context)
                        .load(Api.getPosterPath(it))
                        .into(item_poster_post)
            }
        }
    }

    override fun onClick(v: View?) {
        delegate.onItemClick(movie)
    }

    override fun onLongClick(v: View?) = false
}
