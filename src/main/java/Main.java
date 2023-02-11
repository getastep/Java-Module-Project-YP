import java.util.ArrayList;
import java.util.Scanner;

// dev branch for Y.Practicum
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfPeople = 0;
        while (numberOfPeople <= 1) {
            while (true) {
                Scanner sc = new Scanner(System.in);
                System.out.println("На скольких человек поделить счет?");
                if (sc.hasNextInt()) {
                    numberOfPeople = sc.nextInt();
                    break;
                }
                else {
                    System.out.println("Некорректное значение! Убедитесь, что вводите целое положительное число!");
                }
            }
            if (numberOfPeople <= 1) {
                System.out.println("Значение должно быть больше 1");
            }
        }
        Calculator check = new Calculator();
        while (true) {
            check.addNewProduct();
            System.out.println("Товар был успешно добавлен!");
            System.out.println("Хотите добавить еще один? (Напишите 'завершить', чтобы отказаться)");
            String answer = scanner.next();
            if (answer.equalsIgnoreCase("завершить")) {
                break;
            }
        }
        check.printProducts();
        double result = check.countIndividualBill(numberOfPeople);
        String formatResult = "Каждый должен заплатить: %.2f %s";
        String ruble = check.formatted(result);
        System.out.println(String.format(formatResult, result, ruble));
    }
}

class Calculator {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Product> products = new ArrayList<>();
    double totalPrice = 0.0;

    void addNewProduct() {
        System.out.println("Введите название товара: ");
        String name = scanner.nextLine();
        double price = 0;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите цену товара в формате РУБЛИ,КОПЕЙКИ: ");
            if (sc.hasNextDouble()) {
                double x = sc.nextDouble();
                if (x > 0) {
                    price = x;
                    break;
                }
                else {
                    System.out.println("Цена товара не может быть меньше или равной нулю!");
                }
            }
            else {
                System.out.println("Некорректное значение! Убедитесь, что вводите цифры в формате РУБЛИ,КОПЕЙКИ");
            }
        }
        Product product = new Product(name, price);
        products.add(product);
        totalPrice += price;
    }

    void printProducts() {
        System.out.println("Добавленные товары:");
        for (Product x : products) {
            System.out.println(x.name);
        }
    }

    double countIndividualBill(int numberOfPeople) {
        double result = 0.0;
        result = totalPrice / numberOfPeople;
        return result;
    }

    String formatted(double number) {
        int x = (int)number;
        if (x % 100 / 10 == 1) {
            return "рублей";
        }
        else {
            switch (x % 10) {
                case 1:
                    return "рубль";
                case 2:
                case 3:
                case 4:
                    return "рубля";
                default:
                    return "рублей";
            }
        }
    }
}

class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
