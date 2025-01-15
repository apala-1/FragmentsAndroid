package com.example.a35b_crud.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.a35b_crud.model.ProductModel
import com.example.a35b_crud.repository.ProductRepository

class ProductViewModel (val repository: ProductRepository) {

    fun addProduct(productModel: ProductModel,
                   callback: (Boolean, String) -> Unit){
        repository.addProduct(productModel,callback)
    }

    fun updateProduct(
        productId: String,
        data: MutableMap<String, Any>,
        callback: (Boolean, String) -> Unit
    ){
        repository.updateProduct(productId,data,callback)
    }

    fun deleteProduct(
        productId: String,
        callback: (Boolean, String) -> Unit
    ){
        repository.deleteProduct(productId,callback)
    }

    val _products = MutableLiveData<ProductModel?>()
    var products = MutableLiveData<ProductModel?>()
        get() = _products

    var _allProducts = MutableLiveData<List<ProductModel>?>()
    var allProducts = MutableLiveData<List<ProductModel>?>()
        get() = _allProducts

    fun getProductById(productId: String){
        repository.getProductById(productId){
            product, success, message ->
            if(success){
                _products.value = product
            }
        }
    }

    var _loadingState = MutableLiveData<Boolean>()
    var loadingState = MutableLiveData<Boolean>()
        get() = _loadingState

    fun getAllProduct(){
        _loadingState.value = true
        repository.getAllProduct {
              allProducts, success, message ->
            if(success){
                _allProducts.value = allProducts
            }
        }

    }
}

//In software development, especially when working with architectures like MVVM (Model-View-ViewModel) or similar patterns, understanding how components relate to each other is crucial. Here's an overview of how different components typically interact in a repository-led architecture:
//
//1. Model
//Definition: Represents the underlying data structure or business logic. It could be a simple class or more complex with attributes, relationships, etc.
//Purpose: Encapsulates the data and handles operations such as data validation and manipulation.
//Usage: Often used by both ViewModel and Repository layers to interact with data.
//2. Repository
//Definition: A layer responsible for handling data operations like fetching, saving, updating, and deleting data from a database or external service.
//Purpose: Acts as a bridge between the data source (e.g., database, API) and other layers. It abstracts the data operations, making them independent of the ViewModel.
//Usage: Communicates with the data source and provides the required data to the ViewModel. It does not contain any business logic.
//3. ViewModel
//Definition: A class that acts as a bridge between the Model and the View. It provides data and business logic to the View.
//Purpose: Provides observable data to the View, handles user interactions, and manages application logic.
//Usage: Receives data from the Repository and exposes it to the View while managing UI state and events.
//4. View
//Definition: Represents the user interface (UI) or the presentation layer. It displays data and interacts with the user.
//Purpose: Binds to the ViewModel to receive data and updates UI components accordingly.
//Usage: Uses data from the ViewModel, which gets data from the Repository.
//Flow of Data:
//View communicates with the ViewModel.
//ViewModel communicates with the Repository to fetch or save data.
//Repository interacts with the Model or data source.
//Summary:
//Model: Represents data and business logic.
//Repository: Handles data operations (CRUD) independently of the ViewModel.
//ViewModel: Transforms data from the Repository into observable data for the View.
//View: Displays data from the ViewModel to the user and handles user interactions.
//This layered architecture helps in maintaining separation of concerns, promoting testability, and making the application more maintainable.