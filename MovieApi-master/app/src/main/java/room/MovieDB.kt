package room

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class MovieDB(
    @PrimaryKey val id: Int,
    @NonNull val title: String,
    @NonNull val image: String,
    @NonNull val overview: String
)
