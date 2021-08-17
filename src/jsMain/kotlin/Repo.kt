import kotlinx.browser.window
import kotlinx.coroutines.await
import org.w3c.fetch.DEFAULT


class Repo {

    suspend fun fetchDistance(from: String, to: String): GMapsData {
            val response = window
                .fetch("https://www.distance24.org/route.json?stops=$from|$to")
                .await()
                .json()
                .await()

            return response as GMapsData
        }
}