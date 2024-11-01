package com.example.mywardrobe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WardrobeActivity : AppCompatActivity() {

    private lateinit var recyclerViewRV: RecyclerView
    private lateinit var toolbarWardrobe: Toolbar
    private val clothes = mutableListOf(
        Clothes("Шляпа 01", "Шляпа первая", R.drawable.hat01),
        Clothes("Шляпа 02", "Шляпа вторая", R.drawable.hat02),
        Clothes("Шляпа 03", "Шляпа третья", R.drawable.hat03),
        Clothes("Шляпа 04", "Шляпа четвертая", R.drawable.hat04),
        Clothes("Шляпа 05", "Шляпа пятая", R.drawable.hat05),
        Clothes("Шляпа 06", "Шляпа шестая", R.drawable.hat06),
        Clothes("Куртка 01", "Куртка первая", R.drawable.jacket01),
        Clothes("Куртка 02", "Куртка вторая", R.drawable.jacket02),
        Clothes("Куртка 03", "Куртка третья", R.drawable.jacket03),
        Clothes("Куртка 04", "Куртка четвертая", R.drawable.jacket04),
        Clothes("Ботинки 01", "Ботинки первые", R.drawable.shoes01),
        Clothes("Ботинки 02", "Ботинки вторые", R.drawable.shoes02),
        Clothes("Ботинки 03", "Ботинки третьи", R.drawable.shoes03),
        Clothes("Ботинки 04", "Ботинки четвертые", R.drawable.shoes04),
        Clothes("Ботинки 05", "Ботинки пятые", R.drawable.shoes05),
        Clothes("Штаны 01", "Штаны первые", R.drawable.trousers01),
        Clothes("Штаны 02", "Штаны вторые", R.drawable.trousers02),
        Clothes("Штаны 03", "Штаны третьи", R.drawable.trousers03),
        Clothes("Штаны 04", "Штаны четвертые", R.drawable.trousers04),
        Clothes("Штаны 05", "Штаны пятые", R.drawable.trousers05),
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_wardrobe)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarWardrobe = findViewById(R.id.toolbarWardrobe)
        setSupportActionBar(toolbarWardrobe)
        title = "Гардероб"

        recyclerViewRV = findViewById(R.id.recyclerViewRV)
        recyclerViewRV.layoutManager = LinearLayoutManager(this)
        val adapter = CustomAdapter(clothes)
        recyclerViewRV.adapter = adapter
        recyclerViewRV.setHasFixedSize(true)
        adapter.setOnUserClickListener(object : CustomAdapter.OnClothesClickListener {
            override fun onClothesClick(clothes: Clothes, position: Int) {
                val intent = Intent(this@WardrobeActivity, DetailsActivity::class.java)
                intent.putExtra("clothes", clothes)
                startActivity(intent)
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitItem -> finishAffinity()
        }
        return super.onOptionsItemSelected(item)
    }
}