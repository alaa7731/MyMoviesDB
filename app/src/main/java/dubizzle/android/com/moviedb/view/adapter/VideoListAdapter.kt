package dubizzle.android.com.moviedb.view.adapter

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow
import dubizzle.android.com.moviedb.R
import dubizzle.android.com.moviedb.models.Resource
import dubizzle.android.com.moviedb.models.Video
import dubizzle.android.com.moviedb.view.viewholder.VideoListViewHolder

class VideoListAdapter(private val delegate: VideoListViewHolder.Delegate) : BaseAdapter() {

    init {
        addSection(ArrayList<Video>())
    }

    fun addVideoList(resource: Resource<List<Video>>) {
        resource.data?.let {
            sections[0].addAll(it)
        }
        notifyDataSetChanged()
    }

    override fun layout(sectionRow: SectionRow): Int {
        return R.layout.item_video
    }

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        return VideoListViewHolder(view, delegate)
    }
}
