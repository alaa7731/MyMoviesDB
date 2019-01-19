package dubizzle.android.com.moviedb.mappers

import dubizzle.android.com.moviedb.models.NetworkResponseModel


interface NetworkResponseMapper<in FROM : NetworkResponseModel> {
    fun onLastPage(response: FROM): Boolean
}
