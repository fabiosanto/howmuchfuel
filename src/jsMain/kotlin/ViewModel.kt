import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

sealed class Action {
    data class Calculate(val from: String, val to: String) : Action()
}

data class State(
    var from: String = "Sydney",
    var to: String = "Rome",
    var distance: String = "0 km"
)

class ViewModel : CoroutineScope {
    private val repo = Repo()
    val state = mutableStateOf(State())

    fun dispatch(action: Action) {
        val oldState = state.value

        when(action){
            is Action.Calculate -> {
                launch {
                    val response = repo.fetchDistance(action.from, action.to)

                    state.value = oldState.copy(distance = runCalculations(response.distance))
                }
            }
        }
    }

    private fun runCalculations(distance: Int): String {
        // https://www.traveller.com.au/how-fuel-efficient-are-modern-commercial-aircraft-compared-with-cars-h1gc84#:~:text=A%20fully%20loaded%20787%2D9,%2C%20or%202%20litres%2F100km.

        val durationHours = distance / 800 // 800km/h average speed
        val totalFuel = durationHours * 5700 // 5700 litres average per hour
        val totalPassenger = totalFuel / 300 // 300 passengers on 787-9

        return "Your flight is $distance km long and will last $durationHours hours, consuming $totalFuel litres of fuel. " +
                "That's $totalPassenger litres for you only."
    }

    private fun calculateDuration(response: GMapsData) = response.distance / 800
    private fun calculateTotalFuel(response: GMapsData) = response.distance / 800

    override val coroutineContext: CoroutineContext = Job()
}