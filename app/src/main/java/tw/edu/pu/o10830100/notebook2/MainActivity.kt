package tw.edu.pu.o10830100.notebook2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MyAdapter
    private val contacts = ArrayList<Contact>()
    override fun onActivityResult(requestCode: Int, resultCode: Int, data:
    Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.extras?.let {
            if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
                val name = it.getString("name") ?: return@let
                val photo = it.getString("phone") ?: return@let
                contacts.add(Contact(name, photo))
                adapter.notifyDataSetChanged()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val logo = findViewById<ImageView>(R.id.logo)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        adapter = MyAdapter(contacts)
        recyclerView.adapter = adapter
        logo.setOnClickListener {
            startActivityForResult(Intent(this, SecActivity::class.java), 1)
        }
    }
}


data class Contact (
    val title: String,
    val note: String
)