package app.magic.wilson.zach.com.magicappkotlin.fragments


import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import android.support.v7.preference.ListPreference
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat

import app.magic.wilson.zach.com.magicappkotlin.R


/**
 * A Fragment to display the app settings screen.
 */
class SettingsFragment : PreferenceFragmentCompat(), OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pref_app_settings)

        for (i in 0 until preferenceScreen.preferenceCount) {
            val pref = preferenceScreen.getPreference(i)
            if (pref != null) {
                val value = preferenceScreen.sharedPreferences.getString(pref.key, "")
                setPreferenceSummary(pref, value)
            }
        }

        val preference = findPreference(getString(R.string.settings_language_key))
        preference.onPreferenceChangeListener = this
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        val preference = findPreference(key)
        if (preference != null){
            val value = sharedPreferences?.getString(preference.key, "")
            setPreferenceSummary(preference, value)
        }
    }

    override fun onPreferenceChange(preference: Preference?, value: Any?): Boolean {
        // Required override method.  No need to make changes until we add more preferences.
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    private fun setPreferenceSummary(preference: Preference?, value: String?){
        if (preference is ListPreference) {
            val prefIndex = preference.findIndexOfValue(value)
            if (prefIndex >= 0) {
                preference.summary = preference.entries[prefIndex]
            }
        }
    }

}
