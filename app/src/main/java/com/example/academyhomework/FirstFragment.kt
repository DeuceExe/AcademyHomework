package com.example.academyhomework

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.academyhomework.MainActivity.Companion.AGE
import com.example.academyhomework.MainActivity.Companion.IS_MAN
import com.example.academyhomework.MainActivity.Companion.NAME
import com.example.academyhomework.MainActivity.Companion.TAG
import com.example.academyhomework.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    val binding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Create fragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "Create view fragment")
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "Created view fragment")
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            arguments?.let {
                tvName.text = it.getString(NAME)
                tvAge.text = it.getInt(AGE).toString()
                tvIsMan.text = if (it.getBoolean(IS_MAN)) "Man" else "Woman"
            }

            btnSetData.setOnClickListener {
                childFragmentManager
                    .beginTransaction()
                    .add(
                        R.id.container,
                        SecondFragment.newInstance(
                            tvName.text.toString(),
                            tvAge.text.toString().toInt(),
                            tvIsMan.text.toString().toBoolean()
                        )
                    )
                    .commit()
            }
        }
    }

    override fun onStart() {
        Log.d(TAG, "Start fragment")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "Resume fragment")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "Pause fragment")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "Stop fragment")
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG, "Save instance state fragment")
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        Log.d(TAG, "Destroy view fragment")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(TAG, "Destroy fragment")
        super.onDestroy()
    }

    companion object {

        @JvmStatic
        fun newInstance(name: String, age: Int, isMan: Boolean) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(NAME, name)
                    putInt(AGE, age)
                    putBoolean(IS_MAN, isMan)
                }
            }
    }
}