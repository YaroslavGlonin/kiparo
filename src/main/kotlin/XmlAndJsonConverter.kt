import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.lang.reflect.Type


class XmlAndJsonConverter : Converter.Factory() {
    val xml: Converter.Factory = SimpleXmlConverterFactory.create()
    val gson: Converter.Factory = MoshiConverterFactory.create()

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        if (type==data_news::class.java) {
            return gson.responseBodyConverter(type, annotations, retrofit)
        }
        else if (type==data_news_xml::class.java){
            return xml.responseBodyConverter(type,annotations,retrofit)
        }
        return null
    }
}