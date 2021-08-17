external interface GMapsData {
    val distance: Int

}

data class ResponseData(
    override val distance: Int,
) : GMapsData