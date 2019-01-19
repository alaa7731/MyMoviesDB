package dubizzle.android.com.moviedb.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dubizzle.android.com.moviedb.factory.AppViewModelFactory
import dubizzle.android.com.moviedb.view.ui.details.movie.MovieDetailViewModel
import dubizzle.android.com.moviedb.view.ui.main.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Suppress("unused")
@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    internal abstract fun bindMainActivityViewModels(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    internal abstract fun bindMovieDetailViewModel(movieDetailViewModel: MovieDetailViewModel): ViewModel


    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}
