package services

import dto.Machine
import dto.Ingredients
import kotlinx.coroutines.*

class CoffeeMachineImpl(machineDtoRequest: Machine?) : CoffeeMachine {

    private var ingredients: Ingredients? = null
    private val defaultLowCapacityIndicator = 50

    private var outlets: Int? = null
    private var inventory: Map<String, Int>? = null
    private var beverages = machineDtoRequest?.beverages

    init {
        outlets = machineDtoRequest?.outlets?.get("count_n") as Int?
        inventory = machineDtoRequest?.total_items_quantity?.mapValues {
            it.value as Int
        } ?: emptyMap()
        ingredients = Ingredients(inventory!!)
        beverages = machineDtoRequest?.beverages
    }

    override fun serveBeverages(): String {

        val result = mutableListOf<String>()
        val deferred = beverages?.map {
            GlobalScope.async {
                prepareBeverage(it, result)
            }
        }
        runBlocking(newFixedThreadPoolContext(outlets as Int, "synchronizationPool")) {
            deferred?.map { it.await() }
        }
        return result.joinToString(separator = "\n")
    }

    @Synchronized
    private fun prepareBeverage(it: Map.Entry<String, Any>, result: MutableList<String>): Boolean {

        val drinkIngredients = Ingredients(it.value as Map<String, Int>)
        val canMakeDrink = DrinksImpl(it.key, drinkIngredients).canMakeDrink(ingredients!!)
        return when (canMakeDrink.isEmpty()) {
            true -> {
                updateRawIngredient(drinkIngredients)
                result.add(DrinksImpl(it.key, drinkIngredients).prepareDrink(ingredients!!))
            }
            else -> {
                result.add(canMakeDrink)
            }
        }
    }

    /**
     * updates the ingredients once a drink is prepared
     */
    private fun updateRawIngredient(drinkIngredient: Ingredients) {
        val remainingStock = mutableMapOf<String, Int>()
        drinkIngredient.ingredient.map {
            (ingredients?.ingredient?.get(it.key)?.minus((drinkIngredient.ingredient.get(it.key)
                    ?: 0)))?.let { it1 -> remainingStock.putIfAbsent(it.key, it1) }
        }
        ingredients?.ingredient?.map {
            if (!remainingStock.containsKey(it.key))
                remainingStock.put(it.key, it.value)
        }
        ingredients = Ingredients(remainingStock)
    }


    override fun checkIngredientLevel(): String {
        var lowIngredients = ""
        ingredients?.ingredient?.map {
            if (it.value <= defaultLowCapacityIndicator) {
                lowIngredients = lowIngredients.plus("\n [ALERT] ${it.key} is running low with capacity ${it.value}.")
            }
        }
        return lowIngredients
    }

    override fun refillIngredients(addStock: Map<String, Int>): String {
        val addedStock = ingredients?.ingredient?.toMutableMap()
        addStock.map {
            ingredients?.ingredient?.get(it.key)?.plus(it.value)?.let { it1 -> addedStock?.put(it.key, it1) }
        }
        ingredients = addedStock?.let { Ingredients(it) }
        return "Updated Stock to ${ingredients.toString()}"
    }

    override fun currentIngredientLevel(): Ingredients? {
        return ingredients
    }
}