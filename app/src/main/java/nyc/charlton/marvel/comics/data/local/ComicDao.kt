package nyc.charlton.marvel.comics.data.local

import androidx.room.*
import nyc.charlton.marvel.comics.data.local.entity.ComicEntity

@Dao
interface ComicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comics: List<ComicEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comics: ComicEntity)

    @Delete
    fun delete(comic: ComicEntity)

    @Query("SELECT * FROM ComicEntity WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun getComics(query: String): List<ComicEntity>

    @Query("SELECT * FROM ComicEntity WHERE id = :id")
    fun getComic(id: Int): ComicEntity?


}
