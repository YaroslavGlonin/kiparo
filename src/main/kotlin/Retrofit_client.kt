import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
object retrofit{
    fun create(baseUrl: String) : retrofit_interface {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(XmlAndJsonConverter())
            .baseUrl(baseUrl)
            .build()
        return retrofit.create(retrofit_interface::class.java)
    }
}