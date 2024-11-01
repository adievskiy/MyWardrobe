package com.example.mywardrobe

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailsActivity : AppCompatActivity() {

    private lateinit var layoutLL: LinearLayout
    private lateinit var toolbarDetails: Toolbar
    private lateinit var imageDetailsIV: ImageView
    private lateinit var nameItemTV: TextView
    private lateinit var descriptionItemTV: TextView
    private var clothes: Clothes? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()

        setSupportActionBar(toolbarDetails)
        title = "Мой гардероб"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbarDetails.setNavigationOnClickListener {
            onBackPressed()
        }

        getClothes()

        layoutLL.setOnLongClickListener {
            editDialog()
        }
    }

    private fun init() {
        toolbarDetails = findViewById(R.id.toolbarDetails)
        layoutLL = findViewById(R.id.layoutLL)
        imageDetailsIV = findViewById(R.id.imageDetailsIV)
        nameItemTV = findViewById(R.id.nameItemTV)
        descriptionItemTV = findViewById(R.id.descriptionItemTV)
    }

    private fun editDialog(): Boolean {
        val dialog = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.update_dialog, null)
        dialog.setView(dialogView)

        val editName: EditText = dialogView.findViewById(R.id.updateNameET)
        val editDescription: EditText = dialogView.findViewById(R.id.updateDescriptionET)

        editName.setText(nameItemTV.text)
        dialog.setTitle("Обновить запись")
        dialog.setPositiveButton("Обновить") { _, _ ->
            nameItemTV.text = editName.text.toString()
            descriptionItemTV.text = editDescription.text.toString()
            clothes = Clothes(
                nameItemTV.text.toString(),
                descriptionItemTV.text.toString(),
                clothes!!.image
            )
        }
        dialog.setNegativeButton("Отмена") { _, _ ->
        }
        dialog.create().show()
        return false
    }

    private fun getClothes() {
        if (intent.hasExtra("clothes")) {
            clothes = intent.getSerializableExtra("clothes") as? Clothes
        }
        if (clothes != null) {
            imageDetailsIV.setImageResource(clothes!!.image)
            nameItemTV.text = clothes!!.name
            descriptionItemTV.text = clothes!!.description
        }
    }
}
