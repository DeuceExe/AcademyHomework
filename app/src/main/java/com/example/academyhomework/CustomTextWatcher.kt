package com.example.academyhomework

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText

class CustomTextWatcher(edList: Array<EditText>, btnWrite: Button) : TextWatcher {
    var btnWrite: View
    var edList: Array<EditText>

    init {
        this.btnWrite = btnWrite
        this.edList = edList
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    override fun afterTextChanged(s: Editable) {
        val regex = "[a-zA-Zа-яА-Я]+"
        for (editText in edList) {
            editText.filters = arrayOf(
                InputFilter { source, start, end, dest, dstart, dend ->
                    if (source == "") { // for backspace
                        return@InputFilter source
                    }
                    if (source.toString() matches (regex).toRegex()) {
                        source
                    } else ""
                }
            )
            if (editText.text.toString().trim { it <= ' ' }.isEmpty()) {
                btnWrite.isEnabled = false
                break
            } else btnWrite.isEnabled = true
        }
    }
}