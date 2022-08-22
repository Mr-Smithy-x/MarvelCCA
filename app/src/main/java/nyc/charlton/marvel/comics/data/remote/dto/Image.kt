package nyc.charlton.marvel.comics.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(val path: String, val extension: String): Parcelable {
    fun toURL(): String {
        return "$path.$extension"
    }
}
