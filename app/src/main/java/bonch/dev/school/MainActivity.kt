package bonch.dev.school

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnUsers: Button
    private lateinit var btnAlbums: Button
    private lateinit var btnPhoto: Button
    private lateinit var btnCreatePost: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        setListeners()
    }


    private fun init(){
        btnUsers = findViewById(R.id.btn_users)
        btnAlbums = findViewById(R.id.btn_albums)
        btnPhoto = findViewById(R.id.btn_photo)
        btnCreatePost = findViewById(R.id.btn_create_post)
    }

    private fun setListeners(){
        btnUsers.setOnClickListener{
            val intent = Intent(this, UsersActivity::class.java)
            startActivity(intent)
        }
        btnPhoto.setOnClickListener{
            val intent = Intent(this, PhotosActivity::class.java)
            startActivity(intent)
        }
        btnAlbums.setOnClickListener{
            val intent = Intent(this, AlbumsActivity::class.java)
            startActivity(intent)
        }
        btnCreatePost.setOnClickListener{
            val dialog = PostCreateDialogFragment(applicationContext)
            dialog.show(supportFragmentManager,"Dialog")
        }
    }
}
