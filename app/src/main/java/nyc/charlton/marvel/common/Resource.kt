package nyc.charlton.marvel.common

import nyc.charlton.marvel.comics.data.remote.dto.ComicDTO

typealias ComicDataContainer = DataContainer<ComicDTO>

open class Resource<T>(val data: T? = null, val status: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(status: String, data: T? = null) : Resource<T>(data, status)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}

class DataContainer<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)