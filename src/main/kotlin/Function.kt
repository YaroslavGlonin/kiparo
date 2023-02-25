import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory
import java.text.SimpleDateFormat
import java.util.*


private val BASE_URL = "https://api2.kiparo.com"
private lateinit var list_xml:List<element>
private lateinit var list:List<news>
open class Function {
    fun init()
    {
        println("0.Get Json | 1.Get Xml | 2.Exit")
        when(readln()){
            "0"-> getNewsJson()
            "1"-> getNewsXml()
            "2"-> System.exit(0)
        }
    }
    fun getNewsJson() = try {
        val api = retrofit.create(BASE_URL).getNews().execute()
        list = api.body()!!.results
        retrofit_ok(false)
    }
    catch (ex:Exception){
        print_console(ex.toString())
    }
    fun getNewsXml() = try {
        val ip = retrofit.create(BASE_URL).getNewsXml().execute()
        list_xml=ip.body()!!.results
        retrofit_ok(true)
    }
    catch (ex:Exception){
        print_console("ex "+ex.toString())
    }
   fun retrofit_ok(xml:Boolean){
        val xml_=xml
        println("0.Show all news(date format) | 1.Keyword search | 2.main menu | 3.exit")
        when(readln()){
            "1"-> {
                print("Ýour keyword: ")
                val keyword = readln()
                if(xml) {
                    if (keyword.isNotEmpty() && list_xml.isNotEmpty()) {
                        for (arr in list_xml) {
                            for (result in arr.keywords) {
                                if (keyword == result) {
                                    print_console(date_converter(arr.date) + " | " + arr.description)
                                }
                            }
                        }
                    }
                }
                else
                {
                    if (keyword.isNotEmpty() && list.isNotEmpty()) {
                        for (arr in list) {
                            for (result in arr.keywords) {
                                if (keyword == result) {
                                    print_console(date_converter(arr.date) + " | " + arr.description)
                                }
                            }
                        }
                    }
                }
            }
            "0"-> {
                if(xml) {
                    for (arr in list_xml.sortedWith(
                        compareBy(
                            element::date
                        )
                    )) {
                        print_console(date_converter(arr.date) + " | " + arr.description)
                    }
                }
                else
                {
                    for (arr in list.sortedWith(
                        compareBy(
                            news::date))) {
                        print_console(date_converter(arr.date)+" | "+arr.description)
                    }
                }
            }
            "2"-> init()
            "3"-> System.exit(0)
        }

              retrofit_ok(xml_)
    }
    fun date_converter(date:String):String{
        val serverDateFormat = SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss", Locale.getDefault())
        val userDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val defaultDate = serverDateFormat.parse(date)
        return userDateFormat.format(defaultDate)
    }
    private fun print_console(str:String)= println(str)
}