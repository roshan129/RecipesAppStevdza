package com.adivid.recipesappstevdza.data

import com.adivid.recipesappstevdza.data.database.RecipesDao
import com.adivid.recipesappstevdza.data.database.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {

    fun readDatabase(): Flow<List<RecipesEntity>>{
        return recipesDao.readRecipes()
    }

    suspend fun insertRecipes(recipeEntity: RecipesEntity){
        recipesDao.insertRecipes(recipeEntity)
    }
}