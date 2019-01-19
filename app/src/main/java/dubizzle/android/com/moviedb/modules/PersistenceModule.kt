package dubizzle.android.com.moviedb.modules

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import dubizzle.android.com.moviedb.room.AppDatabase
import dubizzle.android.com.moviedb.room.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class PersistenceModule {
    @Provides
    @Singleton
    fun provideDatabase(@NonNull application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "TheMovies.db").allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(@NonNull database: AppDatabase): MovieDao {
        return database.movieDao()
    }


}
