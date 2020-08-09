package services

import dto.Ingredients

interface CoffeeMachine {

    /**
     * Process the beverage making
     */
    fun serveBeverages(): String

    /**
     * Check the current ingredient level
     */
    fun checkIngredientLevel(): String

    /**
     * Refill the current stock
     */
    fun refillIngredients(addStock: Map<String, Int>): String

    /**
     * Returns the current capacity of ingredients
     */
    fun currentIngredientLevel(): Ingredients?
}