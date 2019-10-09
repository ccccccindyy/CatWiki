package practice.com.example.xin.app.ui.cat_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import practice.com.example.xin.app.Application
import practice.com.example.xin.app.data.breed.Breed
import practice.com.example.xin.app.ui.cat.CatFragment
import pratice.com.example.xinzhang.recyclerview.R
import javax.inject.Inject

class BreedListFragment : Fragment(), BreedRecyclerViewAdapter.OnItemClickListener {

    @Inject lateinit var viewModel: BreedListViewModel

    override fun onItemClick(item: Breed) {
        val args = Bundle()
        args.putString(CatFragment.BREED_ID_ARG, item.id)
        view?.findNavController()?.navigate(R.id.catFragment, args)
    }

    override fun onAttach(context: Context) {
        val app = activity?.applicationContext as Application
        app.catComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cat_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager =  LinearLayoutManager(context)
                adapter = BreedRecyclerViewAdapter(viewModel.getBreeds())
            }
        }
        ((view as RecyclerView).adapter as BreedRecyclerViewAdapter).onItemClickListener = this
        return view
    }

}
