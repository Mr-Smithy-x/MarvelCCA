package nyc.charlton.marvel.di

import android.content.Context
import android.os.Environment
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import nyc.charlton.marvel.comics.data.local.ComicDao
import nyc.charlton.marvel.comics.data.local.entity.ComicEntity
import nyc.charlton.marvel.comics.data.remote.ComicApi
import nyc.charlton.marvel.comics.data.remote.dto.ComicDTO
import nyc.charlton.marvel.comics.domain.repository.ComicRepository
import nyc.charlton.marvel.comics.data.repository.ComicRepositoryImpl
import nyc.charlton.marvel.common.Constants.BASE_URL
import nyc.charlton.marvel.common.DataContainer
import nyc.charlton.marvel.common.Resource
import nyc.charlton.marvel.di.annotations.*
import nyc.charlton.marvel.di.network.MarvelInterceptorTest
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
    )
object AppModuleTest {

    private val LOG get() = true

    @AuthInterceptorOkHttpClient
    @Provides
    fun provideAuthInterceptor(
        @ApplicationContext context: Context
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(MarvelInterceptorTest())
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .cache(Cache(Environment.getDownloadCacheDirectory(), (20 * 1024 * 1024).toLong()))
        if (LOG) {
            builder.addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.HEADERS
            }).addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            })
        }
        return builder.build()
    }

    @Provides
    @AuthRetrofitClient
    fun provideAuthenticatedRetrofit(
        @AuthInterceptorOkHttpClient okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .setDateFormat("yyyy-MM-dd HH:mm:ss")
                        .create()
                )
            )
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesComicApi(@ApplicationContext context: Context): ComicApi {
        val open = context.assets.open("v1/public/comics/200.json").bufferedReader().readText()
        val token = object: TypeToken<Resource<DataContainer<ComicDTO>>>() {}.type
        val fromJson: Resource<DataContainer<ComicDTO>> = GsonBuilder().create().fromJson(open, token)
        return object: ComicApi {
            override suspend fun getComics(): Resource<DataContainer<ComicDTO>>   {
                return fromJson
            }

            override suspend fun getComic(comicId: Int): Resource<DataContainer<ComicDTO>> {
                return fromJson
            }

            override suspend fun getLatestComics(): Resource<DataContainer<ComicDTO>> {
                return fromJson
            }

        }
    }


    @Provides
    @Singleton
    fun providesComicDao(@ApplicationContext context: Context): ComicDao {
        val open = context.assets.open("v1/public/comics/200.json").bufferedReader().readText()
        val token = object: TypeToken<Resource<DataContainer<ComicDTO>>>() {}.type
        val fromJson: Resource<DataContainer<ComicDTO>> = GsonBuilder().create().fromJson(open, token)
        return object: ComicDao {

            private val data = ArrayList<ComicEntity>()

            override suspend fun insert(comics: List<ComicEntity>) {
                data.addAll(comics)
            }

            override suspend fun insert(comics: ComicEntity) {
                data.add(comics)
            }

            override suspend fun delete(comic: ComicEntity) {
                data.removeIf { it.id == comic.id }
            }

            override suspend fun getComics(query: String): List<ComicEntity> {
                return data
            }

            override suspend fun getComic(id: Int): ComicEntity? {
                return data.find { it.id == id }
            }

        }
    }

    @Provides
    @Singleton
    fun provideComicApi(api: ComicApi, dao: ComicDao): ComicRepository {
        return ComicRepositoryImpl(api, dao)
    }
}