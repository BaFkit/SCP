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
            Cart<Product> productCart = context.getBean(ProductCart.class);

            while (true) {
                System.out.println("Select for replenishment cart the \"add\" or \"del\" command and after the space select the item number.");
                System.out.println("Select \"exit\" for close\n");
                System.out.println(productService.getListAll());
                String cmd = scanner.nextLine();
                String[] parts = cmd.trim().split("\\s+");
                if(parts[0].equals("add")) {
                    productCart.addEntity(productService.getById(Integer.parseInt(parts[1])));
                    System.out.println(productCart.getCartList());
                }
                if(parts[0].equals("del")) {
                    productCart.deleteEntity(Integer.parseInt(parts[1]));
                    System.out.println(productCart.getCartList());
                }
                if(parts[0].equals("exit")) {
                    return;
                }
            }
        }
    }
}
