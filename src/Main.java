import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Ingredient choc55 = new Ingredient("1", "Chocolate 55%", 1000.00, 10.00);
        Ingredient water = new Ingredient("2", "Water", 1000.00, 0.00);
        Ingredient sugar = new Ingredient("3", "Sugar", 1500.00, 10.00);
        Ingredient egg = new Ingredient("4", "Egg", 50.00, 10.00);
        Ingredient chocolate = new Ingredient("choc", "Chocolate", 1300.00, 14.00);

        // creating the chocolate paste
        Map<Ingredient, Integer> chocPasteRecipe = new HashMap<Ingredient, Integer>();
        chocPasteRecipe.put(choc55, 1000);
        chocPasteRecipe.put(water, 1000);
        chocPasteRecipe.put(sugar, 600);
        Ingredient chocPaste = createRecipe(chocPasteRecipe, "choc-batter", "Chocolate Paste", 1300.00);

        // creating the chocolate batter
        Map<Ingredient, Integer> chocBatterRecipe = new HashMap<Ingredient, Integer>();
        chocBatterRecipe.put(chocPaste, 2000);
        chocBatterRecipe.put(water, 1000);
        chocBatterRecipe.put(sugar, 500);
        chocBatterRecipe.put(egg, 10);
        Ingredient chocBatter = createRecipe(chocBatterRecipe, "choc-batter", "Chocolate Batter", 1300.00);

        // creating the chocolate cake
        Map<Ingredient, Integer> chocCakeRecipe = new HashMap<Ingredient, Integer>();
        chocCakeRecipe.put(chocPaste, 1000);
        chocCakeRecipe.put(chocBatter, 1000);
        Ingredient chocCake = createRecipe(chocCakeRecipe, "choc-cake", "Chocolate Cake", 1300.00);

        Meta chocCakeMeta = calculateMeta(chocCake);
        DecimalFormat df2 = new DecimalFormat("#.##");
        System.out.println("The total cost of Chocolate Cake is: $" + df2.format(chocCakeMeta.cost));
        System.out.println("The cost per unit of Chocolate Cake is: $" + df2.format(chocCakeMeta.costPerUnit));

    }

    public static double calculateCostPerUnit(Ingredient ingredient) {
        double cost = ingredient.cost / (ingredient.weight / 1000.00);
        if (cost <= 0.00) {
            return 0.00;
        }
        return cost;
    }

    // creates a composite ingredient
    public static Ingredient createRecipe(Map<Ingredient, Integer> recipe, String id, String name, double weight) {
        double totalCost = 0.00;
        for (Ingredient i : recipe.keySet()) {
            totalCost += (calculateCostPerUnit(i) * (recipe.get(i) / 1000.00));
        }

        Ingredient newRecipe = new Ingredient(id, name, weight, totalCost);
        return newRecipe;
    }

    public static Meta calculateMeta(Ingredient ingredient) {
        return new Meta(ingredient.cost, ingredient.weight, calculateCostPerUnit(ingredient));
    }
}
