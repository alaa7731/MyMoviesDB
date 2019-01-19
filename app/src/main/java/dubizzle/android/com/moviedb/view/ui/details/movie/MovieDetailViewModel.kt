package dubizzle.android.com.moviedb.view.ui.details.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dubizzle.android.com.moviedb.models.Keyword
import dubizzle.android.com.moviedb.models.Resource
import dubizzle.android.com.moviedb.models.Review
import dubizzle.android.com.moviedb.models.Video
import dubizzle.android.com.moviedb.repository.MovieRepository
import dubizzle.android.com.moviedb.utils.AbsentLiveData
 import javax.inject.Inject


class MovieDetailViewModel @Inject
constructor(private val repository: MovieRepository) : ViewModel() {

    private val keywordIdLiveData: MutableLiveData<Int> = MutableLiveData()
    private val keywordListLiveData: LiveData<Resource<List<Keyword>>>

    private val videoIdLiveData: MutableLiveData<Int> = MutableLiveData()
    private val videoListLiveData: LiveData<Resource<List<Video>>>

    private val reviewIdLiveData: MutableLiveData<Int> = MutableLiveData()
    private val reviewListLiveData: LiveData<Resource<List<Review>>>

    init {
        Log.d("Init","Injection MovieDetailViewModel")

        keywordListLiveData = Transformations.switchMap(keywordIdLiveData) {
            keywordIdLiveData.value?.let { repository.loadKeywordList(it) }
                    ?: AbsentLiveData.create()
        }

        videoListLiveData = Transformations.switchMap(videoIdLiveData) {
            videoIdLiveData.value?.let { repository.loadVideoList(it) } ?: AbsentLiveData.create()
        }

        reviewListLiveData = Transformations.switchMap(reviewIdLiveData) {
            reviewIdLiveData.value?.let { repository.loadReviewsList(it) }
                    ?: AbsentLiveData.create()
        }
    }

    fun getKeywordListObservable() = keywordListLiveData
    fun postKeywordId(id: Int) = keywordIdLiveData.postValue(id)

    fun getVideoListObservable() = videoListLiveData
    fun postVideoId(id: Int) = videoIdLiveData.postValue(id)
}
