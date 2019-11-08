package bonch.dev.school

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.school.adapters.AlbumsAdapter
import bonch.dev.school.models.DataAlbum
import bonch.dev.school.networking.RetrofitFactory
import kotlinx.android.synthetic.main.activity_users.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException


class AlbumsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)

        val rv = findViewById<RecyclerView>(R.id.recyclerView)
        rv.layoutManager = LinearLayoutManager(this)

        val service = RetrofitFactory.makeRetrofitService()

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getAlbums(1)
            try {
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        initRecyclerView(response.body()!!)
                    } else {
                        Toast.makeText(applicationContext, "${response.errorBody()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (err : HttpException) {
                Log.e("Retrofit", "${err.printStackTrace()}")
            }
        }
    }

    private fun initRecyclerView (list : List<DataAlbum>) {
        recyclerView.adapter = AlbumsAdapter(list as MutableList, this)
    }




}
