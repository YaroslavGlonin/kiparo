import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object Retrofit_client {
        private var retrofit: Retrofit? = null
        private var retrofit_xml: Retrofit? = null
        fun getClient(baseUrl: String): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    fun getClientXML(baseUrl: String): Retrofit {
        if (retrofit_xml == null) {
            retrofit_xml = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
        }
        return retrofit_xml!!
    }
}