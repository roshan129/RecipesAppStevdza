package com.adivid.recipesappstevdza.data

import com.adivid.recipesappstevdza.data.network.FoodRecipesApi
import com.adivid.recipesappstevdza.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
)  {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }

    suspend fun searchRecipe(searchQuery: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.searchRecipes(searchQuery)
    }

}