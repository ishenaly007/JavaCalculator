import java.util.*;

import static java.lang.System.in;

class Main {
    public static void main(String[] args) throws Exception {
        while (true) {
            Scanner scanner = new Scanner(in);
            String input = scanner.nextLine();
            try {
                String result = calc(input);
                System.out.println(result);
            } catch (Exception e) {
                throw new Exception();
            }
        }
    }

    public static String calc(String input) throws Exception {
        String[] romanNums = {"X", "IX", "VIII", "VII", "VI", "V", "IV", "III", "II", "I"};
        int[] nums = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new Exception();
        }

        int num1 = 0;
        if (Arrays.asList(romanNums).contains(parts[0])) {
            num1 += nums[Arrays.asList(romanNums).indexOf(parts[0])];
        } else {
            try {
                num1 += Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                throw new Exception();
            }
        }
        int num2 = 0;
        if (Arrays.asList(romanNums).contains(parts[2])) {
            num2 += nums[Arrays.asList(romanNums).indexOf(parts[2])];
        } else {
            try {
                num2 += Integer.parseInt(parts[2]);
            } catch (NumberFormatException e) {
                throw new Exception();
            }
        }

        String result = "";
        char operator = parts[1].charAt(0);
        if (num1 >= 1 && num1 <= 10 && num2 >= 1 && num2 <= 10) {
            if (isRomanNumeral(parts[0]) && isRomanNumeral(parts[2])) {
                result = toRoman(solution(num1, num2, operator));
            } else if (isNumeral(parts[0]) && isNumeral(parts[2])) {
                result = String.valueOf(solution(num1, num2, operator));
            } else {
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
        return result;
    }

    public static boolean isRomanNumeral(String str) {
        String romanNumerals = "IVXLCDM";
        // Проверяем, что каждый символ в строке является римской цифрой
        for (int i = 0; i < str.length(); i++) {
            if (romanNumerals.indexOf(str.charAt(i)) == -1) {
                return false;
            }
        }
        //все символы в строке являются римскими цифрами
        return true;
    }

    public static boolean isNumeral(String str) {
        String romanNumerals = "1234567890";
        // Проверяем, что каждый символ в строке является цифрой
        for (int i = 0; i < str.length(); i++) {
            if (romanNumerals.indexOf(str.charAt(i)) == -1) {
                return false;
            }
        }
        //все символы в строке являются цифрами
        return true;
    }

    public static String toRoman(int number) {
        if (number < 1 || number > 100) {
            throw new IllegalArgumentException("Число больше чем 100 или меньше чем 1");
        }
        String[] romanOnes = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] romanTens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] romanHundreds = {"", "C"};
        return romanHundreds[number / 100] + romanTens[(number % 100) / 10] + romanOnes[number % 10];
    }

    private static int solution(int a, int b, char operator) throws Exception {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new Exception();
                }
                return a / b;
            default:
                throw new Exception();
        }
    }
}