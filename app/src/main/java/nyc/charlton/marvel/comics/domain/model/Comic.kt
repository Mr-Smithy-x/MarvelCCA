package nyc.charlton.marvel.comics.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import nyc.charlton.marvel.comics.data.remote.dto.*

@Parcelize
data class Comic(
    val id: Int, // (int, optional): The unique ID of the comic resource.,
    val digitalId: Int, // (int, optional): The ID of the digital comic representation of this comic. Will be 0 if the comic is not available digitally.,
    val title: String, // (string, optional): The canonical title of the comic.,
    val issueNumber: Double, // (double, optional): The number of the issue in the series (will generally be 0 for collection formats).,
    val variantDescription: String?, // (string, optional): If the issue is a variant (e.g. an alternate cover, second printing, or director’s cut), a text description of the variant.,
    val description: String?, // (string, optional): The preferred description of the comic.,
    val modified: String, // (Date, optional): The date the resource was most recently modified.,
    val isbn: String, // (string, optional): The ISBN for the comic (generally only populated for collection formats).,
    val upc: String, // (string, optional): The UPC barcode number for the comic (generally only populated for periodical formats).,
    val diamondCode: String, // (string, optional): The Diamond code for the comic.,
    val ean: String, // (string, optional): The EAN barcode for the comic.,
    val issn: String, // (string, optional): The ISSN barcode for the comic.,
    val format: String, // (string, optional): The publication format of the comic e.g. comic, hardcover, trade paperback.,
    val pageCount: Int, // (int, optional): The number of story pages in the comic.,
    val resourceURI: String, // (string, optional): The canonical URL identifier for this resource.,
    val thumbnail: Image, // (Image, optional): The representative image for this comic.,
): Parcelable {

    companion object {
        fun sample(): Comic {
            return Comic(
                id = 0,
                digitalId = 0,
                title = "Sample",
                issueNumber = 0.0,
                variantDescription = "",
                description = "",
                modified = "",
                isbn = "",
                upc = "",
                diamondCode = "",
                ean = "",
                issn = "",
                format = "",
                pageCount = 0,
                resourceURI = "",
                thumbnail = Image(
                    "",
                    ""
                )
            )
        }
    }
}