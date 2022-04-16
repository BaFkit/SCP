package org.example;

import org.example.configuration.AppConfig;

import org.example.entity.Product;
import org.example.services.Cart;
import org.example.services.EntityService;
import org.example.services.ProductCart;
import org.example.services.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;


public class ConsoleApp {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);) {
            EntityService<Product> productService = context.getBean(ProductService.class);
            Cart<Product> productCart1 = context.getBean(ProductCart.class);
            Cart<Product> productCart2 = context.getBean(ProductCart.class);

            while (true) {
                System.out.println("Select for replenishment cart the \"add1\", \"add2\" or \"del1\", \"del2\"  command and after the space select the item number.");
                System.out.println("Select \"exit\" for close\n");
                System.out.println(productService.getListAll());
                String cmd = scanner.nextLine();
                String[] parts = cmd.trim().split("\\s+");
                if(parts[0].equals("add1")) {
                    productCart1.addEntity(productService.getById(Integer.parseInt(parts[1])));
                    System.out.println("Cart 1: " + productCart1.getCartList());
                }
                if(parts[0].equals("add2")) {
                    productCart2.addEntity(productService.getById(Integer.parseInt(parts[1])));
                    System.out.println("Cart 2: " + productCart2.getCartList());
                }
                if(parts[0].equals("del1")) {
                    productCart1.deleteEntity(Integer.parseInt(parts[1]));
                    System.out.println("Cart 1: " + productCart1.getCartList());
                }
                if(parts[0].equals("del2")) {
                    productCart2.deleteEntity(Integer.parseInt(parts[1]));
                    System.out.println("Cart 2: " + productCart2.getCartList());
                }
                if(parts[0].equals("exit")) {
                    System.out.println("Cart 1: " + productCart1.getCartList());
                    System.out.println("Cart 2: " + productCart2.getCartList());
                    return;
                }
            }
        }
    }
}
