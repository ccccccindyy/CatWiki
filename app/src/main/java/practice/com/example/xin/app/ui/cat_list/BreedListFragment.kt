package practice.com.example.xin.app.ui.cat_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.subjects.PublishSubject
import practice.com.example.xin.app.data.breed.Breed
import practice.com.example.xin.app.data.breed.BreedDAO
import practice.com.example.xin.app.ui.cat_list.BreedListFragment.*
import pratice.com.example.xinzhang.recyclerview.R

class BreedListFragment : Fragment(), BreedRecyclerViewAdapter.OnItemClickListener {

    override fun onItemClick(item: Breed) {
        view?.findNavController()?.navigate(R.id.catFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cat_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager =  LinearLayoutManager(context)
                adapter = BreedRecyclerViewAdapter(
                    BreedDAO(this.context).getBreeds())
            }
        }
        ((view as RecyclerView).adapter as BreedRecyclerViewAdapter).onItemClickListener = this
        return view
    }

}
