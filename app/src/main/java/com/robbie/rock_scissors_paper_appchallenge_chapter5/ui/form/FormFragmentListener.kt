package com.robbie.rock_scissors_paper_appchallenge_chapter5.ui.form

import android.content.Context
import android.content.SharedPreferences

interface FormFragmentListener {
    fun onNameSubmitted(text: String)

}

class GetName(context: Context){

private var preference: SharedPreferences = context.getSharedPreferences(NAME, MODE)

companion object {
    private const val NAME = ""
    private const val MODE = Context.MODE_PRIVATE
    private val PREF_USER_NAME = Pair("NAME", null)
}

var userName: String?
    get() = preference.getString(PREF_USER_NAME.first, PREF_USER_NAME.second)
    set(value) = preference.edit {
        it.putString(PREF_USER_NAME.first, value)
    }
}

private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
    val editor = edit()
    operation(editor)
    editor.apply()
}