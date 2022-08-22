package nyc.charlton.marvel.comics.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import nyc.charlton.marvel.comics.data.remote.dto.*
import nyc.charlton.marvel.comics.domain.model.Comic

@Entity
data class ComicEntity (
    @PrimaryKey val id: Int, // (int, optional): The unique ID of the comic resource.,
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
    val textObjects: List<TextObject>, // (Array[TextObject], optional): A set of descriptive text blurbs for the comic.,
    val resourceURI: String, // (string, optional): The canonical URL identifier for this resource.,
    val urls: List<Url>, // (Array[Url], optional): A set of public web site URLs for the resource.,
    val series: Summary.Series, // (SeriesSummary, optional): A summary representation of the series to which this comic belongs.,
    val variants: List<Summary.Comic>, //(Array[ComicSummary], optional): A list of variant issues for this comic (includes the "original" issue if the current issue is a variant).,
    val collections: List<Summary.Comic>, // (Array[ComicSummary], optional): A list of collections which include this comic (will generally be empty if the comic's format is a collection).,
    val collectedIssues: List<Summary.Comic>, // (Array[ComicSummary], optional): A list of issues collected in this comic (will generally be empty for periodical formats such as "comic" or "magazine").,
    val dates: List<ComicDate>, // (Array[ComicDate], optional): A list of key dates for this comic.,
    val prices: List<ComicPrice>, // (Array[ComicPrice], optional): A list of prices for this comic.,
    val thumbnail: Image, // (Image, optional): The representative image for this comic.,
    val images: List<Image>, // (Array[Image], optional): A list of promotional images associated with this comic.,
    val creators: MarvelList.Creator, // (CreatorList, optional): A resource list containing the creators associated with this comic.,
    val characters: MarvelList.Character, // (CharacterList, optional): A resource list containing the characters which appear in this comic.,
    val stories: MarvelList.Story, // (StoryList, optional): A resource list containing the stories which appear in this comic.,
    val events: MarvelList.Event, // (EventList, optional): A resource list containing the events in which this comic appears.
) {
}

fun ComicEntity.toComic(): Comic {
    return Comic(
        id = id,
        digitalId = digitalId,
        title=title,
        issueNumber = issueNumber,
        variantDescription = variantDescription,
        description = description,
        modified = modified,
        isbn = isbn,
        upc = upc,
        diamondCode = diamondCode,
        ean = ean,
        issn = issn,
        format = format,
        pageCount = pageCount,
        resourceURI = resourceURI,
        thumbnail = thumbnail
    )
}

