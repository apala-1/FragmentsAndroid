package com.example.a35b_crud.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a35b_crud.R
import com.example.a35b_crud.adapter.ProductAdapter
import com.example.a35b_crud.databinding.ActivityProductDashboardBinding
import com.example.a35b_crud.model.Product
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductDashboardActivity : AppCompatActivity() {

    private val productList = mutableListOf<Product>()
    private lateinit var productAdapter: ProductAdapter
    private lateinit var binding: ActivityProductDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityProductDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up edge-to-edge window insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize RecyclerView and Adapter
        productAdapter = ProductAdapter(productList)
        binding.productRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ProductDashboardActivity)
            adapter = productAdapter
        }

        // FloatingActionButton click listener
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val intent = Intent(this, AddProductActivity::class.java)
            startActivityForResult(intent, REQUEST_ADD_PRODUCT)
        }
    }

    // Handle result from AddProductActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ADD_PRODUCT && resultCode == RESULT_OK) {
            val newProduct = data?.getParcelableExtra<Product>("newProduct")
            if (newProduct != null) {
                productList.add(newProduct)
                productAdapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        private const val REQUEST_ADD_PRODUCT = 1
    }
}
