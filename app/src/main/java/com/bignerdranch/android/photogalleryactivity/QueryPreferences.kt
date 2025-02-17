package com.bignerdranch.android.photogalleryactivity

import android.content.Context
import android.preference.PreferenceManager
import android.provider.Settings.Global.getString
import android.provider.Settings.Global.putString
import androidx.core.content.edit

private const val PREF_SEARCH_QUERY = "searchQuery"
private const val PREF_RESULT_ID = "lastResultId"

object QueryPreferences {



        fun getStoredQuery(context: Context): String {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return prefs.getString(PREF_SEARCH_QUERY, "")!!
        }

        fun setStoredQuery(context: Context, query: String){
            PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_SEARCH_QUERY, query)
                .apply()
        }

        fun getLastResult(context:Context): String{
            return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_RESULT_ID, "")!!
        }

        fun setLastResultId(context: Context, lastResultId: String){
            PreferenceManager.getDefaultSharedPreferences(context).edit{
               putString(PREF_RESULT_ID, lastResultId)
            }
        }
}