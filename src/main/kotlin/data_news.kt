import com.squareup.moshi.Json


data class data_news (@field:Json(name = "name") val name: String,
                      @field:Json(name = "location") val location: String,
                      @field:Json(name="news") val results: List<news>)
 data class news(@field:Json(name = "id") val id: String,
                 @field:Json(name = "description") val description: String,
                 @field:Json(name = "date") var date: String,
                 @field:Json(name = "keywords") val keywords: List<String>,
                 @field:Json(name = "visible") val visible: Boolean)
