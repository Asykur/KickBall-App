package com.asykurkhamid.kickball.utils

import android.content.Context
import androidx.content.edit

@Suppress("IMPLICIT_CAST_TO_ANY")
open class AppPreference{



    companion object {
        fun setPreferenceBoolean(ctx : Context?,key : String?,value : Boolean?){
            val preference = ctx?.getSharedPreferences(key,Context.MODE_PRIVATE)
            preference?.edit {
                if (value != null) {
                    putBoolean(key, value)
                }
            }
        }

        fun getPreferenceBoolean(ctx: Context?,key: String?): Boolean {
            var bool = false
            val preference = ctx?.getSharedPreferences(key,Context.MODE_PRIVATE)
            if (preference != null) {
                bool = preference.getBoolean(key,true)
            }
            return bool
        }

        fun setPreference (ctx: Context?,key: String?,value: String?){
            val preference = ctx?.getSharedPreferences(key,Context.MODE_PRIVATE)
            preference?.edit {
                if (value != null){
                    putString(key,value)
                }
            }
        }

        fun getPreference(ctx: Context?,key: String?): String? {
            val preference = ctx?.getSharedPreferences(key,Context.MODE_PRIVATE)
            return (if (preference == null) { }
            else {
                preference.getString(key,null)
            }).toString()
        }
    }

}