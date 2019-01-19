package dubizzle.android.com.moviedb.modules

import dubizzle.android.com.moviedb.view.ui.details.movie.MovieDetailActivity
import dubizzle.android.com.moviedb.view.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [MainActivityFragmentModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributeMovieDetailActivity(): MovieDetailActivity

}
