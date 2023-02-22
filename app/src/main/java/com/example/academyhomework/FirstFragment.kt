package com.example.academyhomework

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.academyhomework.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var binding: FragmentFirstBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("life", "Create fragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("life", "Create view fragment")

        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("life", "Created view fragment")
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            binding?.tvName?.text = it.getString("Name", "")
            binding?.tvAge?.text = it.getInt("Age").toString()
            if (it.getBoolean("IsMan")) binding?.tvIsMan?.text = "Man"
            else binding?.tvIsMan?.text = "Woman"
        }
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
        @JvmStatic
        fun newInstance(name: String, age: Int, isMan: Boolean) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString("Name", name)
                    putInt("Age", age)
                    putBoolean("IsMan", isMan)
                }
            }
    }
}