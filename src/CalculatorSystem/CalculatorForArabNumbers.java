package CalculatorSystem;

import MyExceptions.InvalidOperatorException;
import MyExceptions.NotArabianNumberException;
import MyExceptions.OutOfNumbersException;


public class CalculatorForArabNumbers extends Calculator{
    public void calculate(String computation) {
        String[] computationSplit = computation.split(" ");

        try {
            if (computationSplit.length != 3){
                throw new ArrayIndexOutOfBoundsException("Некорректый ввод");
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            result = e.getMessage();
            return;
        }

        char[] firstNumber = computationSplit[0].toCharArray();
        char[] secondNumber = computationSplit[2].toCharArray();

        try {
            for (int i = 0; i < firstNumber.length; i++){
                if ((firstNumber[i] == '.' || firstNumber[i] == ',') && (i != 0 && i != firstNumber.length-1)){
                    throw new NumberFormatException("Были использованы не целые числа");
                }
            }
            for (int i = 0; i < secondNumber.length; i++){
                if ((secondNumber[i] == '.' || secondNumber[i] == ',') && (i != 0 && i != secondNumber.length-1)){
                    throw new NumberFormatException("Были использованы не целые числа");
                }
            }
        } catch (NumberFormatException e){
            result = e.getMessage();
            return;
        }


        try{
            for (int i = 0; i < firstNumber.length; i++){
                if (firstNumber[i] < 48 || firstNumber[i] > 57){
                    throw new NotArabianNumberException("Были использованы не арабские числа");
                }
            }
            for (int i = 0; i < secondNumber.length; i++){
                if (secondNumber[i] < 48 || secondNumber[i] > 57){
                    throw new NotArabianNumberException("Были использованы не арабские числа");
                }
            }
        }
        catch (NotArabianNumberException e){
            result = e.getMessage();
            return;
        }

        //

        try {
            if ((Integer.parseInt(computationSplit[0]) <= 10 && Integer.parseInt(computationSplit[0]) != 0)
                    && (Integer.parseInt(computationSplit[2]) <= 10 && Integer.parseInt(computationSplit[2]) != 0)) {
                switch (computationSplit[1]){
                    case "+":
                        result = Integer.parseInt(computationSplit[0]) + Integer.parseInt(computationSplit[2]) + "";
                        break;
                    case "-":
                        result = Integer.parseInt(computationSplit[0]) - Integer.parseInt(computationSplit[2]) + "";
                        break;
                    case "/":
                        try {
                            if (Integer.parseInt(computationSplit[0])%Integer.parseInt(computationSplit[2]) != 0){
                                throw new ArithmeticException("Калькулятор может работать только с целыми числами");
                            }
                        }
                        catch (ArithmeticException e){
                            result = e.getMessage();
                            return;
                        }
                        result = Integer.parseInt(computationSplit[0]) / Integer.parseInt(computationSplit[2]) + "";
                        break;
                    case "*":
                        result = Integer.parseInt(computationSplit[0]) * Integer.parseInt(computationSplit[2]) + "";
                        break;
                    default:
                        throw new InvalidOperatorException("Использован некорректный оператор");
                }
            }
            else {
                throw new OutOfNumbersException("Были использованы числа вне допустимого диапазона");
            }
        } catch (OutOfNumbersException | InvalidOperatorException e) {
            result = e.getMessage();
        }
    }

    public String getResult(){
        return result;
    }
}