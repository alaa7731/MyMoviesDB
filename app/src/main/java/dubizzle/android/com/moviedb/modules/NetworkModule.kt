package dubizzle.android.com.moviedb.modules

import androidx.annotation.NonNull
import dubizzle.android.com.moviedb.api.RequestInterceptor
import dubizzle.android.com.moviedb.api.LiveDataCallAdapterFactory
import dubizzle.android.com.moviedb.api.TheDiscoverService
import dubizzle.android.com.moviedb.api.MovieService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(RequestInterceptor())
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(@NonNull okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
    }

    @Provides
    @Singleton
    fun provideDiscoverService(@NonNull retrofit: Retrofit): TheDiscoverService {
        return retrofit.create(TheDiscoverService::class.java)
    }


    @Provides
    @Singleton
    fun provideMovieService(@NonNull retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

}
