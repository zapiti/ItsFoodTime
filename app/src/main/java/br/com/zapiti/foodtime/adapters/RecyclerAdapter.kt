package br.com.zapiti.foodtime.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import br.com.zapiti.foodtime.R
import br.com.zapiti.foodtime.models.NicePlace
import kotlinx.android.synthetic.main.layout_listitem.view.*

import java.util.ArrayList


class RecyclerAdapter(private val mContext: Context, private val mNicePlaces: ArrayList<NicePlace>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_listitem, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {

        initViewHolder(viewHolder  as ViewHolder, i)
    }

    private fun initViewHolder(holder: ViewHolder, i: Int) {

        holder.itemView.image_name.text = mNicePlaces[i].title

        val defaultOptions = RequestOptions()
            .error(R.drawable.ic_launcher_background)
        Glide.with(mContext)
            .setDefaultRequestOptions(defaultOptions)
            .load(mNicePlaces[i].imageUrl)
            .into(holder.itemView.image)
    }

    override fun getItemCount(): Int {
        return mNicePlaces.size
    }

    private inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
