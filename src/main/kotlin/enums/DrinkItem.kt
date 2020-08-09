package enums

/**
 * To be used for Drink Factory Initialization
 */
enum class DrinkItem(val drinkCode: Short, val displayName: String) {

    HOT_TEA(1, "hot_tea"),
    HOT_COFFEE(2, "hot_coffee"),
    BLACK_TEA(3, "black_tea"),
    GREEN_TEA(4, "green_tea"),
    NEW_DRINK(0, "new_drink");

    companion object {
        fun getDrinkFromName(displayName: String): DrinkItem {
            DrinkItem.values().forEach {
                if (displayName.equals(it.displayName))
                    return it
            }
            return DrinkItem.NEW_DRINK
        }
    }
}