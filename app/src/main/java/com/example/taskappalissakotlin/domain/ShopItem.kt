package com.example.taskappalissakotlin.domain

import android.widget.AdapterView

data class ShopItem(
    val name:String,
    val count:Int,
    var enabled:Boolean,
    var id:Int = UNDEFINED_ID,

)
{


    companion object{
        const val UNDEFINED_ID = -1
    }


}
