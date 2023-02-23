import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory
import java.text.SimpleDateFormat
import java.util.*


private val BASE_URL = "https://api2.kiparo.com"
private lateinit var mService: retrofit_interface
private lateinit var mService_xml: retrofit_interface
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
        mService = Common.retrofitService
        mService.getNews().enqueue(object : Callback<data_news> {
            override fun onFailure(call: Call<data_news>, t: Throwable) {
                print(t.toString())
            }
            override fun onResponse(call: Call<data_news>, response: Response<data_news>) {
                if(response.isSuccessful) {
                    list = response.body()!!.results
                    retrofit_ok(false)
                }
                else print_console("No Successful")
            }
        })
    }
    catch (ex:Exception){
        print_console(ex.toString())
    }
    fun getNewsXml() = try {
        mService_xml = CommonXml.retrofitService
        mService_xml.getNewsXml().enqueue(object : Callback<data_news_xml> {
            override fun onFailure(call: Call<data_news_xml>, t: Throwable) {
                print(t.toString())
            }
            override fun onResponse(call: Call<data_news_xml>, response: Response<data_news_xml>) {
                if(response.isSuccessful) {
                    list_xml = response.body()!!.results
                    retrofit_ok(true)
                }
                else print_console("No Successful")
            }
        })
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
                        print_console("xml "+date_converter(arr.date) + " | " + arr.description)
                    }
                }
                else
                {
                    for (arr in list.sortedWith(
                        compareBy(
                            news::date))) {
                        print_console("json "+date_converter(arr.date)+" | "+arr.description)
                    }
                }
            }
            "2"-> init()
            "3"-> System.exit(0)
        }
            //  retrofit_ok(xml_)
    }
    fun date_converter(date:String):String{
        val serverDateFormat = SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss", Locale.getDefault())
        val userDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val defaultDate = serverDateFormat.parse(date)
        return userDateFormat.format(defaultDate)
    }
    private fun print_console(str:String)= println(str)

    companion object Common {
        val retrofitService: retrofit_interface
            get() = Retrofit_client.getClient(BASE_URL).create(retrofit_interface::class.java)
    }
    object CommonXml {
        val retrofitService: retrofit_interface
            get() = Retrofit_client.getClientXML(BASE_URL).create(retrofit_interface::class.java)
    }
}