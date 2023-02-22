package com.example.academyhomework

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Log.d("life", "Create fragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("life", "Create view fragment")
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("life", "Created view fragment")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        Log.d("life", "Start fragment")
        super.onStart()
    }

    override fun onResume() {
        Log.d("life", "Resume fragment")
        super.onResume()
    }

    override fun onPause() {
        Log.d("life", "Pause fragment")
        super.onPause()
    }

    override fun onStop() {
        Log.d("life", "Stop fragment")
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("life", "Save instance state fragment")
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        Log.d("life", "Destroy view fragment")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("life", "Destroy fragment")
        super.onDestroy()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}