package dubizzle.android.com.moviedb

 import dubizzle.android.com.moviedb.modules.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


@Suppress("unused")
class MyApplication : DaggerApplication() {

    private val appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)

     }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }
}
