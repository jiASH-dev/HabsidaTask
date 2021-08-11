package CalculatorSystem;

import MyExceptions.InvalidOperatorException;
import MyExceptions.NotRomanNumberException;
import MyExceptions.OutOfNumbersException;

public class CalculateForRomanNumbers extends Calculator{
    private final static String[] RomanNumbers = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};

    public void calculate(String computation) {
        String[] RomanSplit = computation.split(" ");

        try {
            if (RomanSplit.length != 3){
                throw new ArrayIndexOutOfBoundsException("Некорректный ввод");
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            result = e.getMessage();
            return;
        }

        char[] firstNumberToCharArray = RomanSplit[0].toCharArray();
        char[] secondNumberToCharArray = RomanSplit[2].toCharArray();
        int firstNumber = 0;
        int secondNumber = 0;

        for (int i = 1; i < 11; i++) {
            if (RomanNumbers[i].equals(RomanSplit[0])) {
                firstNumber = i;
            }
            if (RomanNumbers[i].equals(RomanSplit[2])) {
                secondNumber = i;
            }
        }

        try {
            for (int i = 0; i < firstNumberToCharArray.length; i++) {
                if (firstNumberToCharArray[i] >= 48 && firstNumberToCharArray[i] < 57) {
                    throw new NotRomanNumberException("Были использованы не римские цифры");
                }
            }
            for (int i = 0; i < secondNumberToCharArray.length; i++) {
                if (secondNumberToCharArray[i] >= 48 && secondNumberToCharArray[i] < 57) {
                    throw new NotRomanNumberException("Были использованы не римские цифры");
                }
            }
        } catch (NotRomanNumberException e) {
            result = e.getMessage();
            return;
        }

        try {
            if (firstNumber != 0 && secondNumber != 0) {
                switch (RomanSplit[1]) {
                    case "+":
                        result = ArabToRoman(firstNumber + secondNumber);
                        break;
                    case "-":
                        result = ArabToRoman(firstNumber - secondNumber);
                        break;
                    case "/":
                        try {
                            if (firstNumber%secondNumber != 0){
                                throw new ArithmeticException("Калькулятор может работать только с целыми числами");
                            }
                        }
                        catch (ArithmeticException e){
                            result = e.getMessage();
                            return;
                        }
                        result = ArabToRoman(firstNumber / secondNumber);
                        break;
                    case "*":
                        result = ArabToRoman(firstNumber * secondNumber);
                        break;
                    default:
                        throw new InvalidOperatorException("Использован некорректный оператор");
                }
            } else {
                throw new OutOfNumbersException("Были использованы числа вне допустимого диапазона");
            }
        } catch (OutOfNumbersException | InvalidOperatorException e) {
            result = e.getMessage();
        }
    }

    private String ArabToRoman(int res) {
        String str;
        if (res <= 10) {
            str = CalculateForRomanNumbers.RomanNumbers[res];
        } else if (res < 100) {
            int dozens = res / 10;
            str = CalculateForRomanNumbers.RomanNumbers[(dozens + 10) - 1] + CalculateForRomanNumbers.RomanNumbers[(res - (dozens * 10))];
        } else {
            str = CalculateForRomanNumbers.RomanNumbers[CalculateForRomanNumbers.RomanNumbers.length - 1];
        }
        return str;
    }

    public String getResult(){
        return result;
    }
}
