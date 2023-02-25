package com.example.academyhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.academyhomework.BlankFragment.Companion.REQUEST_KEY
import com.example.academyhomework.BlankFragment.Companion.SOME_TEXT
import com.example.academyhomework.databinding.FragmentSecondBlankBinding

class SecondBlankFragment : Fragment() {

    private var _binding: FragmentSecondBlankBinding? = null
    val binding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBlankBinding.inflate(inflater, container, false)

        if (savedInstanceState != null) binding.tvGetData.text =
            savedInstanceState.getString(SOME_TEXT)

        setFragmentResultListener(REQUEST_KEY) { _, bundle ->
            binding.tvGetData.text = bundle.getString(SOME_TEXT)
        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SOME_TEXT, binding.tvGetData.text.toString())
    }
}