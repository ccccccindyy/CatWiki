package practice.com.example.xin.app.ui.cat

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.cat_fragment.*
import practice.com.example.xin.app.Application
import practice.com.example.xin.app.data.breed.Breed
import javax.inject.Inject
import android.view.MenuItem
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main2.*
import practice.com.example.xin.app.ui.activities.display.CatDisplayActivity
import pratice.com.example.xinzhang.recyclerview.R


class CatFragment : Fragment() {


    companion object {
        const val BREED_ID_ARG = "BREED_ID_ARG"
    }

    @Inject lateinit var viewModel: CatViewModel

    override fun onAttach(context: Context) {
        val app = activity?.applicationContext as Application
        app.catComponent.inject(this)
        super.onAttach(context)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            android.R.id.home ->  view?.findNavController()?.popBackStack(R.id.cat_list_fragment, true)
//
//        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as CatDisplayActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.cat_fragment, container, false)
    }

    override fun onDestroyView() {
        (activity as CatDisplayActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setHasOptionsMenu(false)
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val breedObserver = Observer<Breed> {
            tvDescription.text = it.description
            tvName.text = it.name
            tvTemperament.text = it.temperament
            tvHypoValue.text = it.hypoallergenic.toString()
            tvOriginValue.text = it.origin.toString()
        }
        viewModel.breed.observe(this, breedObserver)
        arguments?.getString(BREED_ID_ARG)?.let {
            viewModel.initBreed(it)

        }

    }

}
