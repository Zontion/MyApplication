package com.example.myapplication.model

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.myapplication.R

object StockSingleton {
    fun getStockData(): String {
        return "[{\"name\":\"永豐金\",\"code\":\"2890\",\"quantity\":10000,\"price_buy\":13.00}" +
                ",{\"name\":\"國眾\",\"code\":\"5410\",\"quantity\":5000,\"price_buy\":19.19}]"
    }

    fun getColorList(context: Context, num: Int): ArrayList<Int> {
        var colorList: ArrayList<Int> = ArrayList()
        var number = num
        while(number-- >= 0) {
            when (number % 6) {
                0 -> colorList.add(ContextCompat.getColor(context, R.color.color_red))
                1 -> colorList.add(ContextCompat.getColor(context, R.color.color_oragne))
                2 -> colorList.add(ContextCompat.getColor(context, R.color.color_yellow))
                3 -> colorList.add(ContextCompat.getColor(context, R.color.color_green))
                4 -> colorList.add(ContextCompat.getColor(context, R.color.color_blue))
                5 -> colorList.add(ContextCompat.getColor(context, R.color.color_purple))
            }
        }
        return colorList
    }
}