package com.example.myapplication.model

object MenuSingleton {
    fun getMenuList(): Array<String?> {
        val menuList = arrayOfNulls<String>(50)
        for(i in 0..menuList.size - 1){
            menuList[i] = "列表:$i"
        }
        return menuList
    }
}