import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dto.CoffeeMachineDTO
import services.CoffeeMachineImpl

class CoffeeMachineApp(request: String) {

    var machineDtoRequest: CoffeeMachineDTO? = null
    var coffeeMachineImpl: CoffeeMachineImpl? = null
    init {
        machineDtoRequest = stockUpInventory(request)
        coffeeMachineImpl = CoffeeMachineImpl(machineDtoRequest?.machine)
    }

    private fun stockUpInventory(request: String): CoffeeMachineDTO {
        val mapper = jacksonObjectMapper()
        return mapper.readValue(request, CoffeeMachineDTO::class.java)
    }

    fun processDrinkMaking(indicateLowIngredientLevel: Boolean = false) {
        when(indicateLowIngredientLevel) {
            true -> {
                println(coffeeMachineImpl?.serveBeverages())
                println(coffeeMachineImpl?.checkIngredientLevel())
            }
            else -> println(coffeeMachineImpl?.serveBeverages())
        }
    }

    fun currentIngredientLevel() {
        println(coffeeMachineImpl?.currentIngredientLevel())
    }

    fun refillIngredients(addIngredient: String) {
        val addStock = jacksonObjectMapper().readValue(addIngredient, Map::class.java)
        println(coffeeMachineImpl?.refillIngredients(addStock.get("total_items_quantity") as Map<String, Int>))
    }
}

fun main() {
    val request = """
        {
          "machine": {
            "outlets": {
              "count_n": 3
            },
            "total_items_quantity": {
              "hot_water": 500,
              "hot_milk": 500,
              "ginger_syrup": 100,
              "sugar_syrup": 100,
              "tea_leaves_syrup": 100
            },
            "beverages": {
              "hot_tea": {
                "hot_water": 200,
                "hot_milk": 100,
                "ginger_syrup": 10,
                "sugar_syrup": 10,
                "tea_leaves_syrup": 30
              },
              "hot_coffee": {
                "hot_water": 100,
                "ginger_syrup": 30,
                "hot_milk": 400,
                "sugar_syrup": 50,
                "tea_leaves_syrup": 30
              },
              "black_tea": {
                "hot_water": 300,
                "ginger_syrup": 30,
                "sugar_syrup": 50,
                "tea_leaves_syrup": 30
              },
              "green_tea": {
                "hot_water": 100,
                "ginger_syrup": 30,
                "sugar_syrup": 50,
                "green_mixture": 30
              }
            }
          }
        }
    """.trimIndent()

    CoffeeMachineApp(request).processDrinkMaking()

}