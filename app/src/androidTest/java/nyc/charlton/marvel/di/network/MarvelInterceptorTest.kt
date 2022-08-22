package nyc.charlton.marvel.di.network

import android.icu.util.Calendar
import android.util.Log
import okhttp3.*
import java.math.BigInteger
import java.security.MessageDigest

class MarvelInterceptorTest : Interceptor {

    private val pk: String get() = nyc.charlton.marvel.BuildConfig.MARVEL_PK
    private val sk: String get() = nyc.charlton.marvel.BuildConfig.MARVEL_SK

    private fun md5(string: String): String {
        val md5 = MessageDigest.getInstance("MD5")
        val digest = md5.digest(string.toByteArray())
        val bigInteger = BigInteger(1, digest)
        return bigInteger.toString(16).padStart(32, '0')
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val ts = Calendar.getInstance().timeInMillis.toString()
        val hash = md5(ts + sk + pk)

        val req = chain.request()
        val request = req.newBuilder()
            .url(req.url.newBuilder()
                .addQueryParameter("apikey",pk)
                .addQueryParameter("ts", ts)
                .addQueryParameter("hash", hash)
                .build())
        val build = request.build()
        Log.e("URL", build.url.query?:"Mothing")
        return chain.proceed(build)
    }

}
