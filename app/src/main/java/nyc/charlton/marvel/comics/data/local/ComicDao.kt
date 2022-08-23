package nyc.charlton.marvel.comics.data.local

import androidx.room.*
import nyc.charlton.marvel.comics.data.local.entity.ComicEntity

@Dao
interface ComicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comics: List<ComicEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comics: ComicEntity)

    @Delete
    suspend fun delete(comic: ComicEntity)

    @Query("SELECT * FROM ComicEntity WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    suspend fun getComics(query: String): List<ComicEntity>

    @Query("SELECT * FROM ComicEntity WHERE id = :id")
    suspend fun getComic(id: Int): ComicEntity?


}
