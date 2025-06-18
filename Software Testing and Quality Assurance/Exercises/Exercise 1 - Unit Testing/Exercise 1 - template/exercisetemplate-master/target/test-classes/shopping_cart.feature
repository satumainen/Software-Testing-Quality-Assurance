
Feature: Shopping cart functionality

  #For this exercise, I used generative AI to consult for the correct structure, but derived
  #scenatios on my own. I also asked ChatGPT to explain the flow between the different files

  #Adding a product
  Scenario: Add a product to the cart
    Given I have an empty shopping cart
    When I add 1 "Egg" to the cart
    Then the cart should contain 1 "Egg"

  #Adding a product in multiple quantities
  Scenario: Add multiple products to the cart
    Given I have an empty shopping cart
    When I add 4 "Egg" to the cart
    Then the cart should contain 4 "Egg"

  #Viewing the shopping cart - summary
  #this test does not test what the customer sees, but the state of the cart
  Scenario: Check cart content
    Given I have an empty shopping cart
    When I add 1 "Toilet Paper" to the cart
    And I add 4 "Egg" to the cart
    Then the cart should contain 1 "Toilet Paper"
    And the cart should contain 4 "Egg"

  #Viewing the shopping cart - total price
  Scenario: See the total price of the cart
    Given I have an empty shopping cart
    When I add 1 "Tuna" to the cart
    And I add 5 "Egg" to the cart
    Then my cart total should be 2.60

  #Changing the quantity of a product in the cart - removing
  Scenario: Change the quantity of a product in a cart by removing products
    Given I have 5 "Egg" in the cart
    When I remove 4 "Egg" from the cart
    Then the cart should contain 1 "Egg"

  #Changing the quantity of a product in the cart - adding
  Scenario: Change the quantity of a product in a cart by adding products
    Given I have 2 "Egg" in the cart
    When I add 1 "Egg" to the cart
    Then the cart should contain 3 "Egg"

  #Remove a product from the cart completely
  Scenario: Remove the last product of it's type from the cart
    Given I have 2 "Egg" in the cart
    When I remove 2 "Egg" from the cart
    Then the cart should contain 0 "Egg"

  #Remove (not the last) product from the cart
  Scenario: Remove the last product of it's type from the cart
    Given I have 2 "Egg" in the cart
    When I remove 1 "Egg" from the cart
    Then the cart should contain 1 "Egg"

  #Removing item from an empty cart
  Scenario: Try to remove something the cart does not contain
    Given I have an empty shopping cart
    When I try to remove 1 "Egg" from the cart
    Then my cart should be empty

  #Adding an invalid product - used ChatGPT for the idea
  Scenario: Try to add something the shop does not contain
    Given I have an empty shopping cart
    When I try to add 1 "Pancake" to the cart
    Then my cart should be empty

  #Add 0 items - cart should not change
  #check behavior: Adding 0 eggs returns true - does not change cart but executes unnecessary code
  Scenario: Try to add 0 items from cart
    Given I have an empty shopping cart
    When I add 0 "Egg" to the cart
    Then my cart should be empty

  #Remove 0 items - cart should not change
  Scenario: Try to remove 0 items from cart
    Given I have an empty shopping cart
    When I try to remove 0 "Egg" from the cart
    Then my cart should be empty