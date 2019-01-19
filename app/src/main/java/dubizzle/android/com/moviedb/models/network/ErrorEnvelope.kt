package dubizzle.android.com.moviedb.models.network


data class ErrorEnvelope(
        val status_code: Int,
        val status_message: String,
        val success: Boolean
)
