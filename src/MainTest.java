import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void CalculateCostPerUnit_ValidCost_ReturnCost() {
        Ingredient test1 = new Ingredient("test1", "Test 1", 2000.00, 20);
        assertEquals(10.0, Main.calculateCostPerUnit(test1), 0);
    }

    @Test
    public void CalculateCostPerUnit_VegativeCost_ReturnZero() {
        Ingredient test2 = new Ingredient("test2", "Test 2", 100.00, -24);
        assertEquals(0.0, Main.calculateCostPerUnit(test2), 0);
    }

    @Test
    public void CreateRecipe_ValidRecipe_ReturnsCorrectCost() {
        Ingredient test3 = new Ingredient("test3", "Test 3", 1000.00, 10);
        Ingredient test4 = new Ingredient("test4", "Test 4", 1000.00, 10);
        Map<Ingredient, Integer> testMap = new HashMap<Ingredient, Integer>();
        testMap.put(test3, 1000);
        testMap.put(test4, 1000);
        Ingredient validRecipe = Main.createRecipe(testMap, "test", "Test", 4000);
        assertEquals(5.0, Main.calculateCostPerUnit(validRecipe), 0);
    }

    @Test
    public void CalculateMeta_ValidIngredient_ReturnsCorrectCost() {
        Ingredient test3 = new Ingredient("test3", "Test 3", 1000.00, 10);
        Ingredient test4 = new Ingredient("test4", "Test 4", 1000.00, 10);
        Map<Ingredient, Integer> testMap = new HashMap<Ingredient, Integer>();
        testMap.put(test3, 1000);
        testMap.put(test4, 1000);
        Ingredient validRecipe = Main.createRecipe(testMap, "test", "Test", 4000);
        Meta newMeta = Main.calculateMeta(validRecipe);
        assertEquals(20.0, newMeta.cost, 0);
        assertEquals(5.0, newMeta.costPerUnit, 0);
    }
}