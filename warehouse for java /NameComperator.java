//Nicholas Genco 
//Copyright Hardest ASSIGNMENT EVER



import java.util.Comparator;



class NameComparator implements Comparator<FoodItemsStatistics<Inventory.FoodItem>.DatedItem> {
    @Override public int compare(FoodItemsStatistics<Inventory.FoodItem>.DatedItem left, FoodItemsStatistics<Inventory.FoodItem>.DatedItem right) {
        return left.getItem().getUpcCode().compareTo(right.getItem().getUpcCode());
    }

}
//