import CalculatorSystem.CalculateForRomanNumbers;
import CalculatorSystem.Calculator;
import CalculatorSystem.CalculatorForArabNumbers;

import java.util.Scanner;


public class Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculatorForArabNumbers = new CalculatorForArabNumbers();
        Calculator calculatorForRomanNumbers = new CalculateForRomanNumbers();
        System.out.println("Каким калькулятором хотите воспользоваться ?");
        System.out.println("1. Для работы с арабскими цифрами");
        System.out.println("2. Для работы с римскими цифрами");
        int choose = scanner.nextInt();
        scanner.nextLine();
        String operation;
        switch (choose) {
            case 1:
                System.out.println("Введите вычислительную операцию (Пример: 1 + 1)");
                operation = scanner.nextLine();
                calculatorForArabNumbers.calculate(operation);
                System.out.println("Ответ: " + calculatorForArabNumbers.getResult());
                break;
            case 2:
                System.out.println("Введите вычислительную операцию (Пример: I + I)");
                operation = scanner.nextLine();
                calculatorForRomanNumbers.calculate(operation);
                System.out.println("Ответ: " + calculatorForRomanNumbers.getResult());
                break;
            default:
                System.out.println("Данного пункта в меню не существует");
        }
    }
}
