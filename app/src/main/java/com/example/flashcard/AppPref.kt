import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.myDataStore: DataStore<Preferences> by preferencesDataStore(name = "app_settings")

class AppPref(private val context: Context) {

    companion object {
        // put your settings or configs here
        val USERNAME = "username"
        val PASSWORD = "password"
        val DARK_MODE = "dark_mode"
        val IS_LOGINED = "is_loginedd"
    }


    suspend fun putString(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        context.myDataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
        Log.d("checkLogin", "saved ")
    }

    suspend fun getString(key: String): String? {
        val dataStoreKey = stringPreferencesKey(key)
        val preferences: Preferences = context.myDataStore.data.first()
        return preferences[dataStoreKey]
    }
}

