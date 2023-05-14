package room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
/*
@Database(entities = arrayOf(MovieDB::class), version = 1)
 abstract class MovieAppDatabase: RoomDatabase() {

     abstract  fun getMovieDao(): MovieDao

     companion object {
         @Volatile
         private var INSTANCE:  MovieAppDatabase? = null

         fun getMovieDatabase(context: Context): MovieAppDatabase{
             return INSTANCE ?: synchronized(this){
                 val instance = Room.databaseBuilder(
                     context,
                     MovieAppDatabase::class.java,
                     "movie_app_database")
                     .createFromAsset("3/discover/movie")
                     .build()
                 INSTANCE = instance
                 instance
             }
         }






     }
}*/