package exerciseTemplate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ShopTest {

    private Shop shop;
    private Product product;

    @BeforeEach
    public void setUp() {
        shop = new Shop();
    }


    /*This test adds a new product to the shop but initially has zero items in stock.*/

    @Test
    public void addValidProductToShop() {
        product = new Product("Pancakes", 0, 1.70);
        shop.addProduct(product);
        assertEquals(0, shop.howManyInStock("Pancakes"));
    }

    /*Add a product with the same name as an existing product, but the new product has a different price than
    the old product. Should return false.*/

    @Test
    public void addExistingProductToShopDifferentPrice() {
        product = new Product("Egg", 10, 0.19);
        assertFalse(shop.addProduct(product));
    }

    /*Add a new product with the same name as an existing product, the new product has the
    same price as the old product. Should increase the stock of pre-existing product.*/

    @Test
    public void addExistingProductToShopSamePrice() {
        product = new Product("Egg", 10, 0.20);
        shop.addProduct(product);
        assertEquals(20, shop.howManyInStock("Egg"));
    }

    //When products are added to the cart the stock and cart states should be updated

    @Test
    public void addValidProductsToCart() {
        assert(shop.addToCart("Egg", 5));
        assertEquals(5, shop.howManyInCart("Egg"));
        assertEquals(5, shop.howManyInStock("Egg"));
    }

    //The user should not be able to add more products than the current stock to the cart.

    @Test
    public void addMoreProductsThanExistsToCart() {
        assertFalse(shop.addToCart("Egg", 11));
    }

    //The user should not be able to remove more products than they have in the cart.

    @Test
    public void removeTooManyProductsFromCart() {
        shop.addToCart("Egg", 10);
        assertFalse(shop.removeFromCart("Egg", 11));
    }

    //When all products are removed from the stock and cart states should be updated accordingly.

    @Test
    public void removeProductsFromCart() {
        shop.addToCart("Egg", 5);
        assertEquals(5, shop.howManyInCart("Egg"));
        assertEquals(5, shop.howManyInStock("Egg"));
        shop.removeFromCart("Egg", 5);
        assertEquals(0, shop.howManyInCart("Egg"));
        assertEquals(10, shop.howManyInStock("Egg"));
    }

    //A payment equal to the cart value should result in a successful payment and empty the cart

    @Test
    public void payCorrectAmount() {
        shop.addToCart("Egg", 5);
        assertEquals(5, shop.howManyInCart("Egg"));
        assert(shop.payCart(1.00));
        assertEquals(0, shop.howManyInCart("Egg"));
    }

    //A payment that is less than the cart value should fail, cart should not be emptied.

    @Test
    public void payWrongAmount() {
        shop.addToCart("Egg", 5);
        assertEquals(5, shop.howManyInCart("Egg"));
        assertFalse(shop.payCart(0.99));
        assertEquals(5, shop.howManyInCart("Egg"));
    }


    /* Use case test where a user adds five eggs, removes one and adds one toilet paper to the cart,
    before paying for the items in the cart. Monetary input exceeds cart value. */



    @Test
    public void buyMultipleProductsIndecisiveCustomer() {
        //add 5 eggs to cart
        shop.addToCart("Egg", 5);
        assertEquals(5, shop.howManyInCart("Egg"));
        //remove one egg with overloaded method
        shop.removeFromCart("Egg");
        assertEquals(4, shop.howManyInCart("Egg"));
        //add one toilet paper with method overload
        shop.addToCart("Toilet Paper");
        assertEquals(1, shop.howManyInCart("Toilet Paper"));
        //check cart items and total price
        assertEquals(5, shop.totalCartItems());
        assertEquals(4.19, shop.cartTotalPrice());
        //pay with more than cart total
        assert(shop.payCart(20.00)); //TODO: check this is expected behavior, is this a balance check or withdrawal
    }


    //This test checks whether the system allows for products with negative prices and stocks.
    //TODO: Check if this is allowed behaviour, negative prices might be bad for business, and negative stock might be ok
    @Test
    public void addNewProductNegativeValueNegativePrice() {
        product = new Product("Pancakes", -1, -1.70);
        assertFalse(shop.addProduct(product));

    }

    //This test checks whether the system allows for a negative item value in the cart
    //TODO: Check that this behaviour is allowed, negative amount added to cart
    @Test
    public void addNegativeAmountToCart() {
        assertFalse(shop.addToCart("Egg", -1)); //check if adding negative numbers is allowed
    }


}
