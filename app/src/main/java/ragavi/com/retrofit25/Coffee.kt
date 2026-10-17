package ragavi.com.retrofit25

// Modelo de cada café que regresa la API (sampleapis.com/coffee)
// Los campos son nullable por si algún registro viene incompleto
data class Coffee(
    val id: Int,
    val title: String?,
    val description: String?,
    val ingredients: List<String>?,
    val image: String?
)
