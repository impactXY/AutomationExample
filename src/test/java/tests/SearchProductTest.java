package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;

import java.util.List;

public class SearchProductTest extends BaseTest {
    @Test
    public void searchReturnsProducts() {
        String term = "jeans";

        ProductsPage products = new HomePage(getDriver()).goToProducts();
        Assert.assertTrue(products.isDefaultHeaderVisible(), "'All products' not visible");

        products.search(term);
        Assert.assertTrue(products.isSearchHeaderVisible(), "'Searched products' not visible");

        List<String> productsNames = products.getSearchedProductNames();
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

        List<String> productNames = products.getSearchedProductNames();
        Assert.assertTrue(productNames.isEmpty(), "Expected empty list");
    }
}
