package ragavi.com.retrofit25

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CoffeeAdapter(
    private val list: List<Coffee>
) : RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder>() {

    class CoffeeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgCoffee: ImageView = view.findViewById(R.id.imgCoffee)
        val txtTitle: TextView = view.findViewById(R.id.txtCoffeeTitle)
        val txtDescription: TextView = view.findViewById(R.id.txtCoffeeDescription)
        val txtIngredients: TextView = view.findViewById(R.id.txtCoffeeIngredients)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_coffee, parent, false)
        return CoffeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {
        val coffee = list[position]

        holder.txtTitle.text = coffee.title ?: "Sin nombre"
        holder.txtDescription.text = coffee.description ?: "Sin descripción"

        // La lista de ingredientes la convierto a un solo texto separado por comas
        val ingredientes = coffee.ingredients
        holder.txtIngredients.text =
            if (ingredientes.isNullOrEmpty()) "Ingredientes: no disponibles"
            else "Ingredientes: " + ingredientes.joinToString(", ")

        // Glide descarga la imagen desde la URL
        Glide.with(holder.itemView.context)
            .load(coffee.image)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_close_clear_cancel)
            .centerCrop()
            .into(holder.imgCoffee)
    }

    override fun getItemCount() = list.size
}
