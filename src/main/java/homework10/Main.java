package homework10;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Product> products = List.of(
                Product.builder().type("Notebook").price(100).discount(false).dateOfAddition(LocalDateTime.of(2024,
                        1, 1, 9, 20)).build(),
                Product.builder().type("Book").price(50).discount(false).dateOfAddition(LocalDateTime.of(2024,
                        1, 1, 9, 25)).build(),
                Product.builder().type("Notebook").price(120).discount(true).dateOfAddition(LocalDateTime.of(2024,
                        1, 1, 9, 35)).build(),
                Product.builder().type("Book").price(60).discount(false).dateOfAddition(LocalDateTime.of(2024,
                        1, 2, 9, 10)).build(),
                Product.builder().type("Book").price(200).discount(true).dateOfAddition(LocalDateTime.of(2024,
                        1, 2, 9, 20)).build(),
                Product.builder().type("Notebook").price(80).discount(false).dateOfAddition(LocalDateTime.of(2024,
                        1, 3, 9, 15)).build(),
                Product.builder().type("Book").price(280).discount(true).dateOfAddition(LocalDateTime.of(2024,
                        1, 3, 9, 20)).build(),
                Product.builder().type("Segregatork").price(70).discount(true).dateOfAddition(LocalDateTime.of(2024,
                        1, 3, 9, 30)).build(),
                Product.builder().type("Segregatork").price(90).discount(false).dateOfAddition(LocalDateTime.of(2024,
                        1, 4, 9, 30)).build(),
                Product.builder().type("Notebook").price(130).discount(true).dateOfAddition(LocalDateTime.of(2024,
                        1, 4, 9, 40)).build(),
                Product.builder().type("Book").price(300).discount(false).dateOfAddition(LocalDateTime.of(2024,
                        1, 4, 9, 50)).build()
        );

        productSearch(products, "Book", 250);
        productWithDiscounts(products, "Book", 10);
        cheapestProduct(products, "Book");
        latestAddedProducts(products);
        calculatingTotalCost(products, "Book", 75);
        cheapestProduct(products, "Pen");
    }

    public static void productSearch(List<Product> products, String category, Integer price) {
        List<String> productSpecifiedCategory = products.stream()
                .filter(product -> product.getType().equals(category) && product.getPrice() > price)
                .map(product -> product.getType() + " " + product.getPrice())
                .collect(Collectors.toList());
        System.out.println("Products of the specified category and the specified price " + productSpecifiedCategory);
    }

    public static void productWithDiscounts(List<Product> products, String category, double discountPercentage) {
        double discount = discountPercentage / 100;
        List<String> productWithDiscounts = products.stream()
                .filter(product -> product.getType().equals(category) && product.isDiscount())
                .map(product -> product.getType() + " " + (product.getPrice() - (product.getPrice() * discount)))
                .collect(Collectors.toList());
        System.out.println("Products in this category at a discount " + productWithDiscounts);
    }

    public static void cheapestProduct(List<Product> products, String category) {
        Double cheapestProduct = products.stream()
                .filter(product -> product.getType().equals(category))
                .map(Product::getPrice)
                .min(Double::compare)
                .orElseThrow(() -> new NoSuchElementException("Product [category : " + category + "] was not found"));
        System.out.printf("The cheapest product from the specified category %s %s%n", category, cheapestProduct);
    }

    public static void latestAddedProducts(List<Product> products) {
        List<String> latestAddedProducts = products.stream()
                .sorted((product1, product2) -> product2.getDateOfAddition().compareTo(product1.getDateOfAddition()))
                .map(product -> product.getType() + " " + product.getDateOfAddition())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("Three most recently added products " + latestAddedProducts);
    }

    public static void calculatingTotalCost(List<Product> products, String category, Integer price) {
        double totalCost = products.stream()
                .filter(product -> product.getDateOfAddition().getYear() == LocalDateTime.now().getYear())
                .filter(product -> product.getType().equals(category) && product.getPrice() < price)
                .mapToDouble(Product::getPrice)
                .sum();
        System.out.println("Total cost of products, the price of which does not exceed the specified price " + totalCost);
    }
}

