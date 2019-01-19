package dubizzle.android.com.moviedb.view.viewholder

import android.view.View
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import dubizzle.android.com.moviedb.api.Api
import dubizzle.android.com.moviedb.models.Video
import kotlinx.android.synthetic.main.item_video.view.*

class VideoListViewHolder(val view: View, private val delegate: Delegate) : BaseViewHolder(view) {

    interface Delegate {
        fun onItemClicked(video: Video)
    }

    private lateinit var video: Video

    override fun bindData(data: Any) {
        if (data is Video) {
            video = data
            buildItem()
        }
    }

    private fun buildItem() {
        itemView.run {
            item_video_title.text = video.name
            Glide.with(context)
                    .load(Api.getYoutubeThumbnailPath(video.key))
                    .listener(GlidePalette.with(Api.getYoutubeThumbnailPath(video.key))
                            .use(BitmapPalette.Profile.VIBRANT)
                            .intoBackground(item_video_palette)
                            .crossfade(true))
                    .into(item_video_cover)
        }
    }

    override fun onClick(v: View?) {
        delegate.onItemClicked(video)
    }

    override fun onLongClick(v: View?) = false
}
