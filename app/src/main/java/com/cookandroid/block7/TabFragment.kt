package com.cookandroid.block7

// TabFragment.kt
// TabFragment.kt
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class TabFragment : Fragment() {

    private var tabTitle: String? = null
    private lateinit var textView: TextView // 추가된 부분

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tabTitle = it.getString(ARG_TAB_TITLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up your fragment content here


        // Access the ImageView and set the default image for Tab 1
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(R.drawable.game_intro) // Default Image for Tab 1
    }

    fun onTabSelected() {
        val imageView = view?.findViewById<ImageView>(R.id.imageView)
        imageView?.setImageResource(R.drawable.profile) // Custom Image for Tab 2
    }

    companion object {
        private const val ARG_TAB_TITLE = "tab_title"

        fun newInstance(tabTitle: String): TabFragment {
            val fragment = TabFragment()
            val args = Bundle()
            args.putString(ARG_TAB_TITLE, tabTitle)
            fragment.arguments = args
            return fragment
        }
    }
}
