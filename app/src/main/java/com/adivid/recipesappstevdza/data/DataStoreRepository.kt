package com.adivid.recipesappstevdza.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.createDataStore
import com.adivid.recipesappstevdza.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.adivid.recipesappstevdza.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.adivid.recipesappstevdza.util.Constants.Companion.PREFERENCES_BACK_ONLINE
import com.adivid.recipesappstevdza.util.Constants.Companion.PREFERENCES_DIET_TYPE
import com.adivid.recipesappstevdza.util.Constants.Companion.PREFERENCES_DIET_TYPE_ID
import com.adivid.recipesappstevdza.util.Constants.Companion.PREFERENCES_MEAL_TYPE
import com.adivid.recipesappstevdza.util.Constants.Companion.PREFERENCES_MEAL_TYPE_ID
import com.adivid.recipesappstevdza.util.Constants.Companion.PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferenceKeys {
        val selectMealType = stringPreferencesKey(PREFERENCES_MEAL_TYPE)
        val selectMealTypeId = intPreferencesKey(PREFERENCES_MEAL_TYPE_ID)
        val selectDietType = stringPreferencesKey(PREFERENCES_DIET_TYPE)
        val selectDietTypeId = intPreferencesKey(PREFERENCES_DIET_TYPE_ID)
        val backOnline = booleanPreferencesKey(PREFERENCES_BACK_ONLINE)

    }

    private val dataStore: DataStore<Preferences> = context.createDataStore(
        name = PREFERENCES_NAME
    )

    suspend fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.selectMealType] = mealType
            preferences[PreferenceKeys.selectMealTypeId] = mealTypeId
            preferences[PreferenceKeys.selectDietType] = dietType
            preferences[PreferenceKeys.selectDietTypeId] = dietTypeId
        }
    }

    suspend fun saveBackOnline(backOnline: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.backOnline] = backOnline

        }
    }

    val readMealAndDietType: Flow<MealAndDietType> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { prefereces ->
            val selectedMealType = prefereces[PreferenceKeys.selectMealType] ?: DEFAULT_MEAL_TYPE
            val selectedMealTypeId = prefereces[PreferenceKeys.selectMealTypeId] ?: 0
            val selectedDietType = prefereces[PreferenceKeys.selectDietType] ?: DEFAULT_DIET_TYPE
            val selectedDietTypeId = prefereces[PreferenceKeys.selectDietTypeId] ?: 0
            MealAndDietType(
                selectedMealType,
                selectedMealTypeId,
                selectedDietType,
                selectedDietTypeId
            )
        }

    val readBackOnline: Flow<Boolean> = dataStore.data
        .catch { exception->
            if(exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map {preferences->
            val backOnline =  preferences[PreferenceKeys.backOnline] ?: false
            backOnline
        }

}

data class MealAndDietType(
    val selectedMealType: String,
    val selectedMealTypeId: Int,
    val selectedDietType: String,
    val selectedDietTypeId: Int,
)
