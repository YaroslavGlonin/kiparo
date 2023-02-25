import com.squareup.moshi.Json

data class general_data (val id: String,
                         val description: String,
                         var date: String,
                         val keywords: List<String>,
                         val visible: Boolean)
