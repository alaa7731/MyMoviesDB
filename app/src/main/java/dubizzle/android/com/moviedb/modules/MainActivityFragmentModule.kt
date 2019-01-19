package dubizzle.android.com.moviedb.modules

import dubizzle.android.com.moviedb.view.ui.main.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class MainActivityFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): MovieListFragment

}
