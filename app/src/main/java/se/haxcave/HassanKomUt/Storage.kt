package se.haxcave.HassanKomUt

import android.content.Context
import android.util.Log

class Storage(val context: Context) {

    val PREFS_FILENAME = "se.haxcave.HassanKomUt.prefs"
    val KEY_NAME = "name"
    val KEY_NUMBER = "number"
    val KEY_MESSAGE = "message"

    private fun save(name: String, number: String, message: String) {
        val prefs = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_NAME, name).apply()
        prefs.edit().putString(KEY_NUMBER, number).apply()
        prefs.edit().putString(KEY_MESSAGE, message).apply()
    }

    fun save(message: Message) {
        Log.d("ASD", "save: " + message)
        save(message.name, message.number, message.message)
    }

    fun getMessage(): Message {
        val prefs = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        val name = prefs.getString(KEY_NAME, "") ?: ""
        val number = prefs.getString(KEY_NUMBER, "") ?: ""
        val msg = prefs.getString(KEY_MESSAGE, "") ?: ""
        val message = Message(name, number, msg)
        Log.d("ASD", "getMessage: " + message)
        return message
    }

}