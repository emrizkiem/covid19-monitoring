package dev.emrizkiem.covid19.ui.explore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.data.model.explore.ArticlesItem
import kotlinx.android.synthetic.main.item_explore.view.*

class ExploreAdapter(
    private val data: List<ArticlesItem>,
    private val listener: (ArticlesItem) -> Unit
): RecyclerView.Adapter<ExploreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_explore, parent, false))


    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position], listener)

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: ArticlesItem, listener: (ArticlesItem) -> Unit) {
            with(itemView) {
                text_title.text = item.title
                text_source.text = item.source?.name

                Glide.with(itemView.context)
                    .load(item.urlToImage)
                    .into(img_article)

                setOnClickListener { listener(item) }
            }
        }
    }
}