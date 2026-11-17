package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.ProductsPage;

import java.util.List;

public class ProductsTest extends BaseTest {
    @Test
    public void searchReturnsProducts() {
        String term = "jeans";

        ProductsPage products = new HomePage(getDriver()).goToProducts();
        Assert.assertTrue(products.isDefaultHeaderVisible(), "'All products' not visible");

        products.search(term);
        Assert.assertTrue(products.isSearchHeaderVisible(), "'Searched products' not visible");

        List<String> productsNames = products.getProductNames();
        Assert.assertFalse(productsNames.isEmpty(), "Search for '" + term + "'returned no products");

        for(String name : productsNames)
            Assert.assertTrue(name.toLowerCase().contains(term.toLowerCase()),
                    "Product '" + name + "'does not match search term '" + term + "'");
    }

    @Test
    public void searchReturnsNoProducts() {
        ProductsPage products = new HomePage(getDriver()).goToProducts();

        products.search("xler123");
        Assert.assertTrue(products.isSearchHeaderVisible(), "'Searched products' not visible");

        List<String> productNames = products.getProductNames();
        Assert.assertTrue(productNames.isEmpty(), "Expected empty list");
    }

    @Test
    public void viewProductDetails() {
        ProductsPage products = new HomePage(getDriver()).goToProducts();
        Assert.assertTrue(products.isDefaultHeaderVisible(), "'All products' not visible");

        List<String> productNames = products.getProductNames();
        Assert.assertFalse(productNames.isEmpty(), "Product list is empty");
        String expectedName = productNames.get(0);

        ProductDetailsPage details = products.viewFirstProduct();

        Assert.assertEquals(details.getName(), expectedName, "Details page shows a different product");
        Assert.assertTrue(details.getCategory().startsWith("Category:"), "Category missing");
        Assert.assertTrue(details.getPrice().startsWith("Rs."), "Price missing or wrong format");
        Assert.assertTrue(details.getAvailability().contains("Availability:"), "Availability missing");
        Assert.assertTrue(details.getCondition().contains("Condition:"), "Condition missing");
        Assert.assertTrue(details.getBrand().contains("Brand:"), "Brand missing");
    }
}
