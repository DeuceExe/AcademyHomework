package com.example.academyhomework

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText

class CustomTextWatcher(edList: Array<EditText>, bttn: Button) : TextWatcher {
    var button1: View
    var edList: Array<EditText>

    init {
        this.button1 = bttn
        this.edList = edList
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    override fun afterTextChanged(s: Editable) {

        for (editText in edList) {
            if (editText.text.toString().trim { it <= ' ' }.isEmpty()) {
                button1.isEnabled = false
                break
            } else button1.isEnabled = true
        }
    }
}