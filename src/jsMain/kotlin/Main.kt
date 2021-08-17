import androidx.compose.runtime.*
import androidx.compose.web.attributes.Draggable
import androidx.compose.web.attributes.value
import androidx.compose.web.css.padding
import androidx.compose.web.css.px
import androidx.compose.web.elements.*
import androidx.compose.web.events.WrappedTextInputEvent
import androidx.compose.web.renderComposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.w3c.dom.HTMLElement

fun main() {
    renderComposable(rootElementId = "root") {
        val model = ViewModel()

        home(model.state) {
            model.dispatch(it)
        }
    }
}

val disposableEffect = object : DisposableEffectResult {
    override fun dispose() {

    }
}

// che cosa dobbiamo dedicare il tempo a ??

@Composable
fun home(state: MutableState<State>, dispatch: (Action) -> Unit){
    var inputFrom : HTMLElement? = null
    Div(style = { padding(25.px) }) {

        TextArea(
            value = state.value.from,
            attrs = {
                draggable(Draggable.False)
                onTextInput { wrappedTextInputEvent ->
                    // wrappedTextInputEvent is of `WrappedTextInputEvent` type
                    state.value.from = wrappedTextInputEvent.inputValue
                }
            }
        )

        TextArea(
            value = state.value.to,
            attrs = {
                draggable(Draggable.False)
                onTextInput { wrappedTextInputEvent ->
                    // wrappedTextInputEvent is of `WrappedTextInputEvent` type
                    state.value.from = wrappedTextInputEvent.inputValue
                }
            }
        )

        Button(attrs = {
            onClick { dispatch(Action.Calculate(state.value.from, state.value.to)) }
        }) {
            Text("Calculate")
        }

        Div(style = { padding(15.px) }) {
            Text(state.value.distance)
        }
    }
}