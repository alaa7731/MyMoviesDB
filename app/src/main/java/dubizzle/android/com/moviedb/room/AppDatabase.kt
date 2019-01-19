package dubizzle.android.com.moviedb.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dubizzle.android.com.moviedb.models.entity.Movie
import dubizzle.android.com.moviedb.utils.StringListConverter
import dubizzle.android.com.moviedb.utils.IntegerListConverter
import dubizzle.android.com.moviedb.utils.KeywordListConverter
import dubizzle.android.com.moviedb.utils.VideoListConverter
import dubizzle.android.com.moviedb.utils.ReviewListConverter


@Database(entities = [(Movie::class)], version = 4, exportSchema = false)
@TypeConverters(value = [(StringListConverter::class), (IntegerListConverter::class),
    (KeywordListConverter::class), (VideoListConverter::class), (ReviewListConverter::class)])
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
