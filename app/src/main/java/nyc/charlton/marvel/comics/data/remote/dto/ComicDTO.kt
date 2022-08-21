package nyc.charlton.marvel.comics.data.remote.dto

import nyc.charlton.marvel.comics.domain.model.Comic


data class ComicDTO (
    val id: Int, // (int, optional): The unique ID of the comic resource.,
    val digitalId: Int, // (int, optional): The ID of the digital comic representation of this comic. Will be 0 if the comic is not available digitally.,
    val title: String, // (string, optional): The canonical title of the comic.,
    val issueNumber: Double, // (double, optional): The number of the issue in the series (will generally be 0 for collection formats).,
    val variantDescription: String, // (string, optional): If the issue is a variant (e.g. an alternate cover, second printing, or director’s cut), a text description of the variant.,
    val description: String, // (string, optional): The preferred description of the comic.,
    val modified: String, // (Date, optional): The date the resource was most recently modified.,
    val isbn: String, // (string, optional): The ISBN for the comic (generally only populated for collection formats).,
    val upc: String, // (string, optional): The UPC barcode number for the comic (generally only populated for periodical formats).,
    val diamondCode: String, // (string, optional): The Diamond code for the comic.,
    val ean: String, // (string, optional): The EAN barcode for the comic.,
    val issn: String, // (string, optional): The ISSN barcode for the comic.,
    val format: String, // (string, optional): The publication format of the comic e.g. comic, hardcover, trade paperback.,
    val pageCount: Int, // (int, optional): The number of story pages in the comic.,
    val textObjects: Array<TextObject>, // (Array[TextObject], optional): A set of descriptive text blurbs for the comic.,
    val resourceURI: String, // (string, optional): The canonical URL identifier for this resource.,
    val urls: Array<String>, // (Array[Url], optional): A set of public web site URLs for the resource.,
    val series: Summary.Series, // (SeriesSummary, optional): A summary representation of the series to which this comic belongs.,
    val variants: Array<Summary.Comic>, //(Array[ComicSummary], optional): A list of variant issues for this comic (includes the "original" issue if the current issue is a variant).,
    val collections: Array<Summary.Comic>, // (Array[ComicSummary], optional): A list of collections which include this comic (will generally be empty if the comic's format is a collection).,
    val collectedIssues: Array<Summary.Comic>, // (Array[ComicSummary], optional): A list of issues collected in this comic (will generally be empty for periodical formats such as "comic" or "magazine").,
    val dates: Array<ComicDate>, // (Array[ComicDate], optional): A list of key dates for this comic.,
    val prices: Array<ComicPrice>, // (Array[ComicPrice], optional): A list of prices for this comic.,
    val thumbnail: Image, // (Image, optional): The representative image for this comic.,
    val images: Array<Image>, // (Array[Image], optional): A list of promotional images associated with this comic.,
    val creators: List.Creator, // (CreatorList, optional): A resource list containing the creators associated with this comic.,
    val characters: List.Character, // (CharacterList, optional): A resource list containing the characters which appear in this comic.,
    val stories: List.Story, // (StoryList, optional): A resource list containing the stories which appear in this comic.,
    val events: List.Event, // (EventList, optional): A resource list containing the events in which this comic appears.
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ComicDTO) return false

        if (id != other.id) return false
        if (digitalId != other.digitalId) return false
        if (title != other.title) return false
        if (issueNumber != other.issueNumber) return false
        if (variantDescription != other.variantDescription) return false
        if (description != other.description) return false
        if (modified != other.modified) return false
        if (isbn != other.isbn) return false
        if (upc != other.upc) return false
        if (diamondCode != other.diamondCode) return false
        if (ean != other.ean) return false
        if (issn != other.issn) return false
        if (format != other.format) return false
        if (pageCount != other.pageCount) return false
        if (!textObjects.contentEquals(other.textObjects)) return false
        if (resourceURI != other.resourceURI) return false
        if (!urls.contentEquals(other.urls)) return false
        if (series != other.series) return false
        if (!variants.contentEquals(other.variants)) return false
        if (!collections.contentEquals(other.collections)) return false
        if (!collectedIssues.contentEquals(other.collectedIssues)) return false
        if (!dates.contentEquals(other.dates)) return false
        if (!prices.contentEquals(other.prices)) return false
        if (thumbnail != other.thumbnail) return false
        if (!images.contentEquals(other.images)) return false
        if (creators != other.creators) return false
        if (characters != other.characters) return false
        if (stories != other.stories) return false
        if (events != other.events) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + digitalId
        result = 31 * result + title.hashCode()
        result = 31 * result + issueNumber.hashCode()
        result = 31 * result + variantDescription.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + modified.hashCode()
        result = 31 * result + isbn.hashCode()
        result = 31 * result + upc.hashCode()
        result = 31 * result + diamondCode.hashCode()
        result = 31 * result + ean.hashCode()
        result = 31 * result + issn.hashCode()
        result = 31 * result + format.hashCode()
        result = 31 * result + pageCount
        result = 31 * result + textObjects.contentHashCode()
        result = 31 * result + resourceURI.hashCode()
        result = 31 * result + urls.contentHashCode()
        result = 31 * result + series.hashCode()
        result = 31 * result + variants.contentHashCode()
        result = 31 * result + collections.contentHashCode()
        result = 31 * result + collectedIssues.contentHashCode()
        result = 31 * result + dates.contentHashCode()
        result = 31 * result + prices.contentHashCode()
        result = 31 * result + thumbnail.hashCode()
        result = 31 * result + images.contentHashCode()
        result = 31 * result + creators.hashCode()
        result = 31 * result + characters.hashCode()
        result = 31 * result + stories.hashCode()
        result = 31 * result + events.hashCode()
        return result
    }
}

fun ComicDTO.toComic(): Comic {
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
