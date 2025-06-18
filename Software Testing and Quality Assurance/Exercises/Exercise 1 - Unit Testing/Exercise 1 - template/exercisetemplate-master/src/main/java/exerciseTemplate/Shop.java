package exerciseTemplate;
import java.util.HashMap;
import java.util.Map;
class Product {
    private final String name;
    private final double price;
    private int stock;
    public Product(String name, int stock, double price) {
        this.name = name;
        this.stock = stock;
        this.price = price;
    }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public boolean reduceStock(int amount) {
        if (amount <= stock) {
            stock -= amount;
            return true;
        }
        return false;
    }
    public void increaseStock(int amount) {
        stock += amount;
    }
}
public class Shop {
    private final Map<String, Product> stock = new HashMap<>();
    private final Map<String, Integer> cart = new HashMap<>();
    public Shop() {
        addProductToStock(new Product("Toilet Paper", 10, 3.39));
        addProductToStock(new Product("Minced meat", 10, 4.99));
        addProductToStock(new Product("Hand sanitizer", 10, 9.99));
        addProductToStock(new Product("Tuna", 10, 1.60));
        addProductToStock(new Product("Egg", 10, 0.20));
    }
    public boolean addProduct(Product newProduct) {
        String name = newProduct.getName();
        if (stock.containsKey(name)) {
            Product existing = stock.get(name);
            if (Double.compare(existing.getPrice(), newProduct.getPrice()) != 0) {
                return false; // Different price than for the same existing product
            }
            existing.increaseStock(newProduct.getStock());
        } else {
            stock.put(name, newProduct);
        }
        return true;
    }
    private void addProductToStock(Product p) {
        stock.put(p.getName(), p);
        System.out.println(p.getName() + " added to stock");
    }


    public boolean addToCart(String name, int amount) {
        Product p = stock.get(name);
        if (p != null && p.reduceStock(amount)) {
            cart.put(name, cart.getOrDefault(name, 0) + amount); //TODO: Check: changed - to +
            return true;
        }
        return false;
    }


    public boolean addToCart(String name) {
        return addToCart(name, 1);
    }
    public boolean removeFromCart(String name, int amount) {
        Integer inCart = cart.get(name);
        if (inCart != null && inCart >= amount) { //TODO: Check: changed > to >=
            cart.put(name, inCart - amount);
            if (cart.get(name) == 0) cart.remove(name);
            stock.get(name).increaseStock(amount);
            return true;
        }
        return false;
    }

    public boolean removeFromCart(String name) {
        return removeFromCart(name, 1);
    }

    public boolean isProductInCart(String name) {
        return cart.containsKey(name);
    }
    public int howManyInCart(String name) {
        return cart.getOrDefault(name, 0);
    }
    public int totalCartItems() {
        return cart.values().stream().mapToInt(Integer::intValue).sum();
    }
    public int howManyInStock(String name) {
        Product p = stock.get(name);
        if (p != null) return p.getStock();
        return 0;
    }
    public double cartTotalPrice() {
        return cart.entrySet().stream()
                .mapToDouble(e -> stock.get(e.getKey()).getPrice() * e.getValue())
                .sum();
    }
    public boolean payCart(double money) {
        if (cart.isEmpty() || money < cartTotalPrice()) return false;
        cart.clear();
        return true;
    }
}