package com.example.farmlink.listener

import com.example.farmlink.Cart_Delivery_Modals.DrinkModel

//For Products preview (delete)
interface IDrinkLoadListener {
    fun onDrinkLoadSuccess(drinkModelList: List<DrinkModel>?)
    fun onDrinkLoadFailed(message:String?)
}