package org.example;

import org.example.repository.ProductRepository;
import org.example.services.Cart;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan("org.example")
public class ConsoleApp {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsoleApp.class);) {
            ProductRepository productRepository = context.getBean(ProductRepository.class);
            Cart cart = context.getBean(Cart.class);

            while (true) {
                System.out.println("Select for replenishment cart the \"add\" or \"del\" command and after the space select the item number.");
                System.out.println("Select \"exit\" for close\n");
                System.out.println(productRepository.getProductsList());
                String cmd = scanner.nextLine();
                String[] parts = cmd.trim().split("\\s+");
                if(parts[0].equals("add")) {
                    cart.addProduct(productRepository.getProductById(Integer.parseInt(parts[1])));
                    System.out.println(cart.getProductsCartList());
                }
                if(parts[0].equals("del")) {
                    cart.deleteProduct(Integer.parseInt(parts[1]));
                    System.out.println(cart.getProductsCartList());
                }
                if(parts[0].equals("exit")) {
                    return;
                }
            }
        }
    }
}
