package dto

data class CoffeeMachineDTO(val machine: Machine)

data class Machine(val outlets: Map<String, Any>,
                   val total_items_quantity: Map<String, Any>,
                   val beverages: Map<String, Any>)