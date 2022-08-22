package nyc.charlton.marvel.comics.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nyc.charlton.marvel.comics.data.local.converters.Converters
import nyc.charlton.marvel.comics.data.local.entity.ComicEntity

@Database (
    entities = [ComicEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ComicDataSource: RoomDatabase() {
    abstract val dao: ComicDao
}