package com.example.daynightdesign

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat


class FragmentOne : Fragment(), OnDayNightStateChanged {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nightModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

        if (nightModeFlags == Configuration.UI_MODE_NIGHT_NO){
            onDayNightApplied(OnDayNightStateChanged.DAY)
        }else{
            onDayNightApplied(OnDayNightStateChanged.NIGHT)
        }

        switcher.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }

    override fun onDayNightApplied(state: Int) {
        if(state == OnDayNightStateChanged.DAY){
            tvTitle.setTextColor(ContextCompat.getColor(context!!, R.color.colorText))
            parent.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorPrimary))
            switcher.setTextColor(ContextCompat.getColor(context!!, R.color.colorText))
            switcher.isChecked = false
        }else{
            tvTitle.setTextColor(ContextCompat.getColor(context!!, R.color.colorTextNight))
            parent.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorPrimaryNight))
            switcher.setTextColor(ContextCompat.getColor(context!!, R.color.colorTextNight))
            switcher.isChecked = true
        }
    }
}