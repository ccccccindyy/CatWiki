package practice.com.example.xin.app

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


import kotlinx.android.synthetic.main.item_cat.view.*
import practice.com.example.xin.app.data.breed.Breed
import pratice.com.example.xinzhang.recyclerview.R

/**
 * [RecyclerView.Adapter] that can display a [Breed] and makes a call to the
 */
class BreedRecyclerViewAdapter(
    private val mValues: List<Breed>
) : RecyclerView.Adapter<BreedRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Breed
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mContentView.text = item.name
        with(holder.mView) {
            tag = item
            holder.mContentView.setTextColor(resources.getColor(if (position %2 == 0) R.color.colorTextLight else R.color.colorTextDark))
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mContentView: TextView = mView.breed_name

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
