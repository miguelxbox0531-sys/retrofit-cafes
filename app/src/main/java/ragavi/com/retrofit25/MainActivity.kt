package ragavi.com.retrofit25

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnHot: Button
    private lateinit var btnIced: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewCoffees)
        btnHot = findViewById(R.id.btnHot)
        btnIced = findViewById(R.id.btnIced)

        recyclerView.layoutManager = LinearLayoutManager(this)

        btnHot.setOnClickListener { loadCoffees(RetrofitClient.instance.getHotCoffees()) }
        btnIced.setOnClickListener { loadCoffees(RetrofitClient.instance.getIcedCoffees()) }
    }

    // Recibe la petición ya armada (caliente o frío) y la ejecuta
    private fun loadCoffees(request: Call<List<Coffee>>) {
        request.enqueue(object : Callback<List<Coffee>> {

            override fun onResponse(
                call: Call<List<Coffee>>,
                response: Response<List<Coffee>>
            ) {
                if (response.isSuccessful) {
                    val coffees = response.body() ?: emptyList()
                    recyclerView.adapter = CoffeeAdapter(coffees)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Error en la respuesta (${response.code()})",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Coffee>>, t: Throwable) {
                Log.e("RETROFIT", "Falló la petición", t)
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
