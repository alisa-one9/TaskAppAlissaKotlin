package com.example.taskappalissakotlin.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskappalissakotlin.R
import com.example.taskappalissakotlin.domain.ShopItem

class MainAdapter(val clickListener : (ShopItem) -> Unit): RecyclerView.Adapter<MainAdapter.ViewHolder> (){

    private var shopList = listOf<ShopItem>()
//    set(value) {
//        Log.e("TAG",": ${value.size}", )
//        field = value
//        notifyDataSetChanged()
//    }

    fun setList(list : List<ShopItem>){
        shopList = list
        notifyDataSetChanged()
    }

    fun disableItem(position:Int){
        shopList[position].enabled = false
        notifyItemChanged(position)
    }

    fun enableItem(position: Int){
        shopList[position].enabled = true
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = when (viewType){
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            layout,
            parent,
            false
        ),clickListener)

    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
      holder.onBind(shopList[position])
    }

    override fun getItemViewType(position: Int): Int {
        val item = shopList[position]
        return  if (item.enabled){
            VIEW_TYPE_ENABLED
        }else{
            VIEW_TYPE_DISABLED
        }
    }

    override fun getItemCount(): Int = shopList.size


    inner class ViewHolder(itemView: View,val clickListener : (ShopItem) -> Unit) :RecyclerView.ViewHolder(itemView){

        val name = itemView.findViewById<TextView>(R.id.tv_name)
        val count = itemView.findViewById<TextView>(R.id.tv_count)

        fun onBind(shopItem: ShopItem) {
            if (name!==null && count!== null) {
                name.text = shopItem.name
                count.text = shopItem.count.toString()

                itemView.setOnClickListener{
                    clickListener(shopItem)
                }
            }

        }

    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
    }

}