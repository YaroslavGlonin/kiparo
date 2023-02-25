
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface retrofit_interface {
    @GET("/static/it_news.json")
    fun getNews() : Call<data_news>

    @GET("/static/it_news.xml")
    fun getNewsXml() : Call<data_news_xml>
}