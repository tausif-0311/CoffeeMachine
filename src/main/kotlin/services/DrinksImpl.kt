package services

import dto.Ingredients

class DrinksImpl(private val drinkName: String, private val drinkIngredient: Ingredients) : Drinks {
    override fun prepareDrink(ingredients: Ingredients) = "$drinkName is getting prepared."

    override fun canMakeDrink(ingredients: Ingredients): String {
        val lowIngredients = mutableListOf<String>()

        for (item in drinkIngredient.ingredient) {
            val ingredientName = item.key
            val ingredientValue = item.value
            if (ingredients.ingredient.getOrDefault(ingredientName, 0) == 0) {
                lowIngredients.add("$drinkName can not be prepared because $ingredientName is not available.")
                break
            }
            if ((ingredients.ingredient.getOrDefault(ingredientName, 0).minus(ingredientValue)) < 0) {
                lowIngredients.add("$drinkName can not be prepared because $ingredientName is not sufficient.")
                break
            }

        }
        return lowIngredients.joinToString(separator = " and ")
    }

}