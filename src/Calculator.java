import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Введите арифметическое выражение: ");
            String input = scanner.nextLine();
            try {
                String result = calc(input);
                System.out.println(result);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private static String calc(String input) throws Exception {
        String[] parts = input.split("\\s+");
        if (parts.length != 3 || !(parts[1].equals("+") || parts[1].equals("-") || parts[1].equals("*") || parts[1].equals("/"))) {
            throw new Exception("Неверный формат выражения");
        }

        int a = parseNumber(parts[0]);
        int b = parseNumber(parts[2]);
        switch (parts[1]) {
            case "+":
                if (parts[0].matches("[I|iV|vX|x]") || parts[2].matches("[I|iV|vX|x]")) {
                    int result = a + b;
                    return convertIntToRoman(result);
                } else return Integer.toString(a + b);
            case "-":
                if (parts[0].matches("[I|iV|vX|x]") || parts[2].matches("[I|iV|vX|x]")) {
                    int result = a - b;
                    if (result < 0){
                        System.out.println("throws Exception");
                    }
                    return convertIntToRoman(result);
                } else return Integer.toString(a - b);
            case "*":
                if (parts[0].matches("[I|iV|vX|x]") || parts[2].matches("[I|iV|vX|x]")) {
                    int result = a * b;
                    return convertIntToRoman(result);
                } else return Integer.toString(a * b);
            case "/":
                if (parts[0].matches("[I|iV|vX|x]") || parts[2].matches("[I|iV|vX|x]")) {
                    int result = a / b;
                    return convertIntToRoman(result);
                } else return Integer.toString((int)(a / b));
            default: throw new Exception("Я таких операций не знаю");
        }
    }

    private static int parseNumber(String number) throws Exception {
        if (!isValidNumber(number)) {
            throw new Exception("Неверное число");
        }
        if (number.matches("^[I|iV|vX|x]+$")) {
            return romanToInt(number);
        } else {
            return Integer.parseInt(number);
        }
    }

    private static boolean isValidNumber(String number) {
        return number.matches("^[1-9]?[0-9]$") || number.matches("^[I|iV|vX|x]+$");
    }

    private static int romanToInt(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == 'I' || c == 'i') {
                res += 1;
            } else if (c == 'V' || c == 'v') {
                res += 5;
            } else if (c == 'X' || c == 'x') {
                res += 10;
            } else if (c == 'L' || c == 'l') {
                res -= 5;
            } else if (c == 'C' || c == 'c') {
                res -= 10;
            } else if (c == 'D' || c == 'd') {
                res += 50;
            } else if (c == 'M' || c == 'm') {
                res += 100;
            }
        }
        return res;
    }
    private static String convertIntToRoman (int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        String s = roman[numArabian];
        return s;
    }
}