package lu.aqu.projper.auth.infra.prefs.impl

import android.content.SharedPreferences
import lu.aqu.projper.auth.infra.prefs.UserPreferences
import javax.inject.Inject

class UserPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : UserPreferences {

    override var accessToken: String?
        get() = sharedPreferences.getString(ACCESS_TOKEN_KEY, null)
        set(value) {
            sharedPreferences.edit()
                .putString(ACCESS_TOKEN_KEY, value)
                .apply()
        }

    companion object {
        const val ACCESS_TOKEN_KEY = "accessToken"
    }
}
