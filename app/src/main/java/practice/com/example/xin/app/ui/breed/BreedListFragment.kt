package practice.com.example.xin.app.ui.breed

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_cat_list.*
import practice.com.example.xin.app.Application
import practice.com.example.xin.app.data.breed.Breed
import practice.com.example.xin.app.firebase.storage.FirebaseHandler
import practice.com.example.xin.app.ui.activities.display.CatDisplayActivity
import practice.com.example.xin.app.ui.cat.CatFragment
import pratice.com.example.xin.app.R
import javax.inject.Inject

class BreedListFragment : Fragment(), BreedRecyclerViewAdapter.OnItemClickListener, FirebaseHandler<ArrayList<Breed>> {

    @Inject lateinit var viewModel: BreedListViewModel

    override fun onItemClick(item: Breed) {
        navigateToCatFragment(item.id)
    }

    fun navigateToCatFragment(breedId: String?){
        val args = Bundle()
        args.putString(CatFragment.BREED_ID_ARG, breedId)
        view?.findNavController()?.navigate(R.id.catFragment, args)
        viewModel.initRealTimeDB(null)
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
        ((activity as CatDisplayActivity).msgCatId)?.let{
            navigateToCatFragment(it)
            (activity as CatDisplayActivity).msgCatId = null
        }
        if (view is RecyclerView) {
            with(view) {
                layoutManager =  LinearLayoutManager(context)
                adapter = BreedRecyclerViewAdapter()
            }
        }
        ((view as RecyclerView).adapter as BreedRecyclerViewAdapter).onItemClickListener = this
        return view
    }

    override fun onStart() {
        super.onStart()
        viewModel.initBreedData()

    }
    override fun onResume() {
        super.onResume()
        viewModel.initRealTimeDB(this)
        (activity as CatDisplayActivity).supportActionBar?.title =
            getString(R.string.title_activity_main)

    }

    override fun onDataFetchFailed() {

    }

    override fun onDataFetched(data: ArrayList<Breed>?) {
        data?.let {breedList ->
            (breed_list.adapter as BreedRecyclerViewAdapter).let {
                it.breeds.addAll(breedList)
                it.notifyDataSetChanged()
            }
        }
    }


}
