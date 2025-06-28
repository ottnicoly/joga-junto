

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);


        System.out.println("Quantidade de produtos para cadastrar");
        int qtd = scanner.nextInt();
        int contador= 0;
        Product product= new Product();
        while (contador < qtd){

            System.out.println("Enter product data:");
            System.out.println("Name: ");
            String name = scanner.nextLine();
            System.out.println("Price: ");
            double price = scanner.nextDouble();
            System.out. println("Quantity: ");
            int quantity = scanner.nextInt();
            product = new Product(name, price, quantity);
            contador++;
        }

        System.out.println(product);

    }
}