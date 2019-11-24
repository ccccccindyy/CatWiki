package practice.com.example.xin.app.ui.breed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.item_cat.view.*
import practice.com.example.xin.app.data.breed.Breed
import pratice.com.example.xin.app.R


/**
 * [RecyclerView.Adapter] that can display a [Breed] and makes a call to the
 */
class BreedRecyclerViewAdapter() : RecyclerView.Adapter<BreedRecyclerViewAdapter.ViewHolder>() {
    var breeds = arrayListOf<Breed>()


    internal lateinit var onItemClickListener: OnItemClickListener
    interface OnItemClickListener {
        fun onItemClick(item: Breed)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = breeds[position]
        holder.mContentView.text = item.name
        with(holder.mView) {
            tag = item
            holder.mContentView.setTextColor(resources.getColor(if (position %2 == 0) R.color.colorTextLight else R.color.colorTextDark))
            setOnClickListener{ onItemClickListener.onItemClick(item) }
        }
    }

    override fun getItemCount(): Int = breeds.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mContentView: TextView = mView.breed_name

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
