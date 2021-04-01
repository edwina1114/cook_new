package com.cooking.merge.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cooking.merge.databinding.FavCardviewLayoutBinding
import com.cooking.merge.favoriteDataBase.FavDataBase
import com.cooking.merge.models.FavitemsModel
import kotlinx.android.synthetic.main.fav_cardview_layout.view.*

class FavitemsAdapter(private var context: Context,
                      private var favitems: ArrayList<FavitemsModel>)
    : RecyclerView.Adapter<FavitemsAdapter.FavViewHolder>() {

    lateinit var binding: FavCardviewLayoutBinding
    lateinit var favdb: FavDataBase


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        favdb = FavDataBase(context)
        binding = FavCardviewLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return FavViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return favitems.size
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.favIMV.setImageResource(favitems[position].fav_image)
        holder.favTV.text = favitems[position].fav_title
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    inner class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var favIMV: ImageView = itemView.IMV_fav
        var favTV: TextView = itemView.TV_fav
        private val favBtn: Button = itemView.BTN_fav

        init {
            favBtn.setOnClickListener { removefav(it) }
        }

        //remove from fav after click
        private fun removefav(itemView: View) {
            val position = adapterPosition
            val favM: FavitemsModel = favitems[adapterPosition]
            favdb.remove_fav(favM.key_id)

            favitems.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, favitems.size)
        }

    }

}
