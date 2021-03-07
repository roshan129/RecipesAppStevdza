package com.adivid.recipesappstevdza.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adivid.recipesappstevdza.models.FoodRecipe
import com.adivid.recipesappstevdza.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity (
    var foodRecipe: FoodRecipe

) {
    @PrimaryKey(autoGenerate = false)
    var id : Int  = 0
}