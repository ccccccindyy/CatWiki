package practice.com.example.xin.app.ui.recogizer


import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.cat_recognized_fragment.*


class CatRecognizerResultFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(pratice.com.example.xin.app.R.layout.cat_recognized_fragment, container, false)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
           tvOriginLabel.text =  it.getString(ARG_LABEL, "")
           tvOriginValue.text = it.getString(ARG_VALUE, "")
            it.getByteArray(ARG_IMAGE)?.let{byteArray ->
                val bmp: Bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                ivProfilePic.setImageBitmap(bmp)
            }
        }
    }

companion object {
    private const val ARG_LABEL = "CAT_REG_LABEL"
    private const val ARG_VALUE = "CAT_REG_VALUE"
    private const val ARG_IMAGE = "CAT_REG_IMAGE"

    fun newInstance(label:String, value: String, image: ByteArray): CatRecognizerResultFragment {
        val args: Bundle = Bundle()
        args.putSerializable(ARG_LABEL, label)
        args.putSerializable(ARG_VALUE, value)
        args.putByteArray(ARG_IMAGE, image)
        val fragment = CatRecognizerResultFragment()
        fragment.arguments = args
        return fragment
    }
}
}
