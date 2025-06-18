package exerciseTemplate;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"})

public class RunCucumberTest {

    private Shop shop = new Shop();

    /*
    For this exercise, I consulted ChatGPT at the beginning to understand the logic and flow of the .feature, step definition,
    and shop files. I got a blueprint for these files, similar to the is it Friday demo. I used this to better understand what
    should happen where and as a starting point for my learning experience.

    */
    //


    @Given("I have an empty shopping cart")
    public void i_have_an_empty_cart() {
        // No need to do anything because new Shop() starts with an empty cart
        assertThat(shop.totalCartItems()).isEqualTo(0);
    }

    @When("I add {int} {string} to the cart")
    public void i_add_products_to_cart(int count, String productName) {
        assertThat(shop.addToCart(productName, count)).isTrue();
    }

    @Then("the cart should contain {int} {string}")
    public void cart_should_contain_items(int count, String productName) {
        assertThat(shop.howManyInCart(productName)).isEqualTo(count);
    }

    @Given("I have {int} {string} in the cart")
    public void i_have_products_in_cart(int count, String productName) {
        assertThat(shop.addToCart(productName, count)).isTrue();
    }

    @When("I remove {int} {string} from the cart")
    public void i_remove_product_from_cart(int count, String productName) {
        assertThat(shop.removeFromCart(productName, count)).isTrue();
    }

    @When("I pay {double}")
    public void i_pay(double amount) {
        assertThat(shop.payCart(amount)).isTrue();
    }

    @Then("my cart should be empty")
    public void my_cart_should_be_empty() {
        assertThat(shop.totalCartItems()).isEqualTo(0);
    }

    @Then("my cart total should be {double}")
    public void my_cart_total_should_be(double expectedTotal) {
        assertThat(shop.cartTotalPrice()).isEqualTo(expectedTotal);
    }

    //the I try to method expects that the method returns false
    @When("I try to remove {int} {string} from the cart")
    public void i_try_to_remove_product_from_cart(int count, String productName) {
        assertThat(shop.removeFromCart(productName, count)).isFalse();
    }

    //the I try to method expects that the method returns false
    @When("I try to add {int} {string} to the cart")
    public void i_try_to_add_product_to_cart(int count, String productName) {
        assertThat(shop.addToCart(productName, count)).isFalse();
    }

}
