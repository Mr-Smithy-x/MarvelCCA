package nyc.charlton.marvel.di

import android.app.Application
import android.content.Context
import android.os.Environment
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import nyc.charlton.marvel.comics.data.local.ComicDataSource
import nyc.charlton.marvel.comics.data.local.converters.Converters
import nyc.charlton.marvel.comics.data.remote.ComicApi
import nyc.charlton.marvel.comics.domain.repository.ComicRepository
import nyc.charlton.marvel.comics.data.repository.ComicRepositoryImpl
import nyc.charlton.marvel.common.Constants.BASE_URL
import nyc.charlton.marvel.di.annotations.*
import nyc.charlton.marvel.di.network.MarvelInteceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val LOG get() = true

    @AuthInterceptorOkHttpClient
    @Provides
    fun provideAuthInterceptor(
        @ApplicationContext context: Context
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(MarvelInteceptor())
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
    fun providesComicApi(@AuthRetrofitClient client: Retrofit): ComicApi {
        return client.create(ComicApi::class.java)
    }

    @Provides
    @Singleton
    fun provideComicDatabase(app: Application): ComicDataSource {
        return Room.databaseBuilder(app, ComicDataSource::class.java, "comic_db")
            .addTypeConverter(Converters(Gson())).build()
    }



    @Provides
    @Singleton
    fun provideComicApi(api: ComicApi, db: ComicDataSource): ComicRepository {
        return ComicRepositoryImpl(api, db.dao)
    }
}