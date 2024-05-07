import java.util.*;
import java.util.stream.Collectors;

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
            try {
                throw new Exception("throws Exception");
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }

        int a = parseNumber(parts[0]);
        int b = parseNumber(parts[2]);
        try {
            switch (parts[1]) {
                case "+":
                    if (parts[0].matches("^[I|iV|vX|x]+$") && parts[2].matches("^[I|iV|vX|x]+$")) {
                        int result = a + b;
                        if (result < 0) {
                            throw new Exception("throws Exception");
                        }
                        return convertArabicToRoman(result);
                    } else if (parts[0].matches("^[1-9]?[0-9]$") && parts[2].matches("^[1-9]?[0-9]$")) {
                        return Integer.toString(a + b);
                    } else {
                        throw new Exception("throws Exception");
                    }
                case "-":
                    if (parts[0].matches("^[I|iV|vX|x]+$") && parts[2].matches("^[I|iV|vX|x]+$")) {
                        int result = a - b;
                        if (result < 0) {
                            throw new Exception("throws Exception");
                        }
                        return convertArabicToRoman(result);
                    } else if (parts[0].matches("^[1-9]?[0-9]$") && parts[2].matches("^[1-9]?[0-9]$")) {
                        return Integer.toString(a - b);
                    } else {
                        throw new Exception("throws Exception");
                    }
                case "*":
                    if (parts[0].matches("^[I|iV|vX|x]+$") && parts[2].matches("^[I|iV|vX|x]+$")) {
                        int result = a * b;
                        return convertArabicToRoman(result);
                    } else if (parts[0].matches("^[1-9]?[0-9]$") && parts[2].matches("^[1-9]?[0-9]$")) {
                        return Integer.toString(a * b);
                    } else {
                        throw new Exception("throws Exception");
                    }
                case "/":
                    if (parts[0].matches("^[I|iV|vX|x]+$") && parts[2].matches("^[I|iV|vX|x]+$")) {
                        int result = a / b;
                        return convertArabicToRoman(result);
                    } else if (parts[0].matches("^[1-9]?[0-9]$") && parts[2].matches("^[1-9]?[0-9]$")) {
                        return Integer.toString((int) (a / b));
                    } else {
                        throw new Exception("throws Exception");
                    }
                default:
                    throw new Exception("Я таких операций не знаю");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1); // Завершение работы приложения с кодом ошибки
            return null;
        }
    }

    private static int parseNumber(String number) throws Exception {
        if (!isValidNumber(number)) {
            throw new Exception("Неверное число");
        }
        if (number.matches("^[I|iV|vX|x]+$")) {
            int num = romanToArabic(number);
            if (num < 1 || num > 10) {
                throw new Exception("Число должно быть в диапазоне от I до X");
            }
            return num;
        } else {
            int num = Integer.parseInt(number);
            if (num < 1 || num > 10) {
                throw new Exception("Число должно быть в диапазоне от 1 до 10");
            }
            return num;
        }
    }

    private static boolean isValidNumber(String number) {
        return number.matches("^[1-9]?[0-9]$") || number.matches("^[I|iV|vX|x]+$");
    }

    private static String convertArabicToRoman(int numArabian) {
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

    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);

        private int value;

        RomanNumeral(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<RomanNumeral> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }
    }
    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " cannot be converted");
        }

        return result;
    }
}