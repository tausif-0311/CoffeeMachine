import org.junit.jupiter.api.*

class CoffeeMachineFuncTest {

    @Test
    fun `Preparing Drinks for 3 outlets 4 drinks`() {
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

    @Test
    fun `Preparing Drinks for 3 outlets 5 drinks`() {
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
              },
              "hot_water": {
                "hot_water": 10
              }
            }
          }
        }
    """.trimIndent()
        CoffeeMachineApp(request).processDrinkMaking()
    }

    @Test
    fun `Flash Low Ingredient Stock Default Low Stock Indicator is 50`() {
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
        val coffeeMachine = CoffeeMachineApp(request)
        coffeeMachine.processDrinkMaking(indicateLowIngredientLevel = true)
    }

    @Test
    fun `Get Current Ingredient Level`() {
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

        val coffeeMachine = CoffeeMachineApp(request)
        coffeeMachine.processDrinkMaking()
        coffeeMachine.currentIngredientLevel()
    }

    @Test
    fun `Refill the ingredient`() {
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
        val coffeeMachine = CoffeeMachineApp(request)
        coffeeMachine.processDrinkMaking()
        val addStock = """
             {
                "total_items_quantity": {
                  "hot_water": 1000,
                  "hot_milk": 1000,
                  "ginger_syrup": 500,
                  "sugar_syrup": 500,
                  "tea_leaves_syrup": 500
                }
            }
        """.trimIndent()
        coffeeMachine.currentIngredientLevel()
        coffeeMachine.refillIngredients(addStock)
    }


}