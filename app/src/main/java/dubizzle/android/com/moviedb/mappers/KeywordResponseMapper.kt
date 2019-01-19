package dubizzle.android.com.moviedb.mappers

import dubizzle.android.com.moviedb.models.network.KeywordListResponse



class KeywordResponseMapper : NetworkResponseMapper<KeywordListResponse> {
    override fun onLastPage(response: KeywordListResponse): Boolean {
        return true
    }
}
