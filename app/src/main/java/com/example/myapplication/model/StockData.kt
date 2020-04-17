package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

class StockData {
    @SerializedName("name")
    var name: String = ""     // 名稱

    @SerializedName("code")
    var code: String = ""     // 編號

    @SerializedName("quantity")
    var quantity: Int = 0     // 數量

    @SerializedName("price_buy")
    var price: Float = 0f     // 金額
}