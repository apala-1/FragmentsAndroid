package com.example.a35b_crud.ui.activity

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.a35b_crud.R
import com.example.a35b_crud.model.Product
import java.util.UUID

class AddProductActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        val nameInput = findViewById<EditText>(R.id.productNameInput)
        val priceInput = findViewById<EditText>(R.id.productPriceInput)

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            val name = nameInput.text.toString().trim()
            val price = priceInput.text.toString().toDoubleOrNull()

            if (name.isNotEmpty() && price != null) {
                // Create a new product with user input
                val newProduct = Product(
                    id = UUID.randomUUID().toString(), // Generate a unique ID
                    name = name,
                    price = price
                )
                val resultIntent = intent
                resultIntent.putExtra("newProduct", newProduct)
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                // Optionally, show an error message if fields are empty or invalid
                nameInput.error = if (name.isEmpty()) "Enter a valid product name" else null
                priceInput.error = if (price == null) "Enter a valid product price" else null
            }
        }
    }
}
