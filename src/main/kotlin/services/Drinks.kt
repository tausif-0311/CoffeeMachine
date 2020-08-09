package services

import dto.Ingredients

interface Drinks {
    /**
     * drinkIngredient: ingredient needed for the drink
     * As of now it has basic functionality of simple print
     */
    fun prepareDrink(ingredients: Ingredients): String

    /**
     * Checks if drink can be prepared
     */
    fun canMakeDrink(ingredients: Ingredients): String
}