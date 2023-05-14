package room

import androidx.room.Dao
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query
/*
@Dao
interface MovieDao {

    @Query("SELECT * FROM movie ORDER BY title ASC")
    fun getAll(): Flow<List<MovieDB>>

    @Query("SELECT * FROM movie WHERE image = :image ORDER BY title ASC")
    fun getAll(image: String): Flow<List<MovieDB>>


}*/