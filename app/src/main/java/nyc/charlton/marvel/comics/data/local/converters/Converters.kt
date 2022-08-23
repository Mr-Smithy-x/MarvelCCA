package nyc.charlton.marvel.comics.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import nyc.charlton.marvel.comics.data.remote.dto.*

@ProvidedTypeConverter
class Converters(private val gson: Gson) {

    @TypeConverter
    fun fromJsonToTextObjects(
        json: String
    ): List<TextObject> {
        val token = object: TypeToken<List<TextObject>>() {}.type
        val fromJson: List<TextObject> = gson.fromJson(json, token)
        return fromJson
    }

    @TypeConverter
    fun fromTextObjectsToJson(textObject: List<TextObject>): String {
        return gson.toJson(textObject)
    }


    @TypeConverter
    fun fromJsonToUrls(
        json: String
    ): List<Url> {
        val token = object: TypeToken<List<Url>>() {}.type
        val fromJson: List<Url> = gson.fromJson(json, token)
        return fromJson
    }

    @TypeConverter
    fun fromUrlsToJson(urls: List<Url>): String {
        return gson.toJson(urls)
    }


    @TypeConverter
    fun fromJsonToSummarySerie(
        json: String
    ): Summary.Series {
        val token = object: TypeToken<Summary.Series>() {}.type
        val fromJson: Summary.Series = gson.fromJson(json, token)
        return fromJson
    }

    @TypeConverter
    fun fromSummarySerieToJson(series: Summary.Series): String {
        return gson.toJson(series)
    }

    @TypeConverter
    fun fromJsonToSummarySeries(
        json: String
    ): List<Summary.Series> {
        val token = object: TypeToken<List<Summary.Series>>() {}.type
        val fromJson: List<Summary.Series> = gson.fromJson(json, token)
        return fromJson
    }

    @TypeConverter
    fun fromSummarySeriesToJson(series: List<Summary.Series>): String {
        return gson.toJson(series)
    }

    @TypeConverter
    fun fromJsonToSummaryComics(
        json: String
    ): List<Summary.Comic> {
        val token = object: TypeToken<List<Summary.Comic>>() {}.type
        val fromJson: List<Summary.Comic> = gson.fromJson(json, token)
        return fromJson
    }

    @TypeConverter
    fun fromSummaryComicsToJson(comics: List<Summary.Comic>): String {
        return gson.toJson(comics)
    }


    @TypeConverter
    fun fromJsonToImage(
        json: String
    ): Image {
        val token = object: TypeToken<Image>() {}.type
        val fromJson: Image = gson.fromJson(json, token)
        return fromJson
    }

    @TypeConverter
    fun fromImageToJson(comics: Image): String {
        return gson.toJson(comics)
    }


    @TypeConverter
    fun fromJsonToImages(
        json: String
    ): List<Image> {
        val token = object: TypeToken<List<Image>>() {}.type
        val fromJson: List<Image> = gson.fromJson(json, token)
        return fromJson
    }

    @TypeConverter
    fun fromImagesToJson(comics: List<Image>): String {
        return gson.toJson(comics)
    }


    @TypeConverter
    fun fromJsonToComicPrice(
        json: String
    ): List<ComicPrice> {
        val token = object: TypeToken<List<ComicPrice>>() {}.type
        val fromJson: List<ComicPrice> = gson.fromJson(json, token)
        return fromJson
    }

    @TypeConverter
    fun fromComicPriceToJson(comics: List<ComicPrice>): String {
        return gson.toJson(comics)
    }

    @TypeConverter
    fun fromJsonToComicDate(
        json: String
    ): List<ComicDate> {
        val token = object: TypeToken<List<ComicDate>>() {}.type
        val fromJson: List<ComicDate> = gson.fromJson(json, token)
        return fromJson
    }

    @TypeConverter
    fun fromComicDateToJson(comics: List<ComicDate>): String {
        return gson.toJson(comics)
    }


    @TypeConverter
    fun fromJsonToSummaryStories(
        json: String
    ): List<Summary.Story> {
        val token = object: TypeToken<List<Summary.Story>>() {}.type
        val fromJson: List<Summary.Story> = gson.fromJson(json, token)
        return fromJson
    }

    @TypeConverter
    fun fromSummaryStoriesToJson(comics: List<Summary.Story>): String {
        return gson.toJson(comics)
    }


    @TypeConverter
    fun fromJsonToMarvelListStories(
        json: String
    ): MarvelList.Story {
        val token = object: TypeToken<MarvelList.Story>() {}.type
        val fromJson: MarvelList.Story = gson.fromJson(json, token)
        return fromJson
    }

    @TypeConverter
    fun fromMarvelListStoriesToJson(comics: MarvelList.Story): String {
        return gson.toJson(comics)
    }

    @TypeConverter
    fun fromJsonToMarvelListEvents(
        json: String
    ): MarvelList.Event {
        val token = object: TypeToken<MarvelList.Event>() {}.type
        val fromJson: MarvelList.Event = gson.fromJson(json, token)
        return fromJson
    }

    @TypeConverter
    fun fromMarvelListEventsToJson(comics: MarvelList.Event): String {
        return gson.toJson(comics)
    }

    @TypeConverter
    fun fromJsonToMarvelListCharacters(
        json: String
    ): MarvelList.Character {
        val token = object: TypeToken<MarvelList.Character>() {}.type
        val fromJson: MarvelList.Character = gson.fromJson(json, token)
        return fromJson
    }

    @TypeConverter
    fun fromMarvelListCharactersToJson(comics: MarvelList.Character): String {
        return gson.toJson(comics)
    }

   @TypeConverter
    fun fromJsonToMarvelListCreators(
        json: String
    ): MarvelList.Creator {
        val token = object: TypeToken<MarvelList.Creator>() {}.type
        val fromJson: MarvelList.Creator = gson.fromJson(json, token)
        return fromJson
    }

    @TypeConverter
    fun fromMarvelListCreatorsToJson(comics: MarvelList.Creator): String {
        return gson.toJson(comics)
    }

}
