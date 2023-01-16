import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import java.math.BigDecimal;
import java.util.*;
public class task_4 {


    public static void main(String[] args) {
        System.out.println(formatByBessie(10, 7, "hello my name is Bessie and this is my essay"));
        System.out.println(split("()()()"));
        System.out.println(toCamelCase("hello_edabit"));
        System.out.println(toSnakeCase("helloEdabit"));
        System.out.println(overTime(9, 17, 30, 1.5));
        System.out.println(BMI("205 pounds", "73 inches"));
        System.out.println(bugger(999));
        System.out.println(toStarShorthand("abbccc"));
        System.out.println(doesRhyme("Sam I am!" , "Green eggs and ham.")); // t
        System.out.println(trouble(666789, 12345667));
        System.out.println(countUniqueBooks("AZYWABBCATTTA",'A')); // 4
        System.out.println(countUniqueBooks("MAAMBBCATTMCMMBM",'M'));
        System.out.println(countUniqueBooks("ZZABCDEF",'Z'));
    }

    public static String formatByBessie(int n, int k, String s) {
        String[] words = s.split("\\s"); // делим по пробелам (0 - hello)  n  - общее кол слов k - кол слов в строке
        StringBuilder result = new StringBuilder();
        int tempLen = 0;
        for (int i = 0; i < n; i++) {
            if (tempLen + words[i].length() <= k) { // проверяем выходит ли за длину строки
                result.append(words[i]);
                result.append(" ");
                tempLen += words[i].length();
            } else {
                tempLen = words[i].length();
                result.append("\n");
                result.append(words[i]);
                if (i != n - 1) {
                    result.append(" ");
                }
            }
        }
        return result.toString();
    }

    public static ArrayList<String> split(String line) {
        int leftSide = 0;
        int rightSide = 0;
        String pair = "";
        ArrayList<String> pairs = new ArrayList<>();

        for (int index = 0; index < line.length(); index++) {
            if (line.charAt(index) == '(') { // встретили левую скобку
                pair += '(';
                leftSide++;
            } else {
                pair += ')'; // встретили правую скобку
                rightSide++;
                if (leftSide == rightSide) {
                    pairs.add(pair);
                    pair = "";
                    leftSide = rightSide = 0;
                }
            }
        }
        return pairs;
    }

    public static String toCamelCase(String line) {   // CamelCase — стиль написания составных слов, при котором несколько слов пишутся слитно без пробелов
        String camelCaseLine = "";
        boolean flag = false;

        for (int index = 0; index < line.length(); index++) {
            if (line.charAt(index) == '_')
                flag = true;
            else if (flag) {
                camelCaseLine += Character.toUpperCase(line.charAt(index)); // делаем юольшую букву
                flag = false;
            } else
                camelCaseLine += line.charAt(index); // переписываем слово если не встретили символ _
        }

        return camelCaseLine;
    }

    public static String toSnakeCase(String line) {  // стиль написания составных слов, при котором несколько слов разделяются символом подчеркивания
        String snakeCaseLine = "";

        for (int index = 0; index < line.length(); index++) {
            if ((int) 'A' <= (int) line.charAt(index) && (int) line.charAt(index) <= (int) 'Z') { // (int) "A" - преобразуем к числу  ASCII
                snakeCaseLine += "_" + Character.toLowerCase(line.charAt(index));
            } else
                snakeCaseLine += line.charAt(index);
        }

        return snakeCaseLine;

    }

    public static String overTime(double start, double end, double salary, double m){
        double result = 0;
        if (end <= 17) {
            result = (end - start) * salary;
        } else {
            result = (end - 17) * m * salary + (17 - start) * salary;
        }
        return "$" + String.valueOf(result);
    }

    public static String BMI(String w, String h){
        Map<String, Double> map = new HashMap<>();
        map.put("pounds", 0.454);
        map.put("kilos", 1.0);
        map.put("meters", 1.0);
        map.put("inches", 0.0254);
        String[] arrayW = w.split("\\s");
        String[] arrayH = h.split("\\s");
        double doubleHeight = Double.parseDouble(arrayH[0]) * map.get(arrayH[1]);
        double doubleWeight = Double.parseDouble(arrayW[0]) * map.get(arrayW[1]);
        double BMI = doubleWeight / (doubleHeight * doubleHeight);
        if (BMI < 18.5){
            return  String.format("%.1f",BMI) + "  Underweight";
        } else if (BMI < 24.9) {
            return String.format("%.1f",BMI) + " Normal weight";
        }
        else{
            return String.format("%.1f",BMI) + " Overweight";
        }
    }

    public static int bugger(int num){ // напримере числа 39
        int numberOfTimes = 0;
        int temp;
        while (num > 9) {
            temp = 1;
            while (num > 0) {
                temp *= num % 10; // 9  3
                num /= 10; // 3  0
            }
            num = temp; // 9 * 3 = 27
            numberOfTimes++;
        }
        return numberOfTimes;
    }

    public static String toStarShorthand(String s){
        StringBuilder result = new StringBuilder();
        char[] chars = s.toCharArray(); // 77777geff   7*5gef*2
        int c = 1;
        char letter;
        for (int i = 0; i < chars.length - 1; i++) {
            letter = chars[i]; // 7
            if (letter == chars[i+1]) { // проверяем текущий символ равен ли следующему
                c += 1;
                if (i == chars.length-2) {
                    result.append(letter+"*"+c);
                }
            } else {
                if (c == 1) {
                    result.append(letter);
                    continue;
                }
                result.append(letter+"*"+c);
                c = 1;
            }
        }
        return result.toString();
    }

    public static boolean doesRhyme(String lineOne, String lineTwo) {
        String[] splitedLineOne = lineOne.toLowerCase().split(" ");
        String[] splitedLineTwo = lineTwo.toLowerCase().split(" ");
        char[] vowels = new char[] {'a', 'e', 'i', 'o', 'u', 'y'};

        // проверяем наличие двух гласных одновременно в строках в цикле.
        for (int charIndex = 0; charIndex < vowels.length; charIndex++) {
            if (splitedLineOne[splitedLineOne.length - 1].contains(Character.toString(vowels[charIndex]))) { // берем последний символ в 1 строке и проверяем есть ли он в масиве
                if (!splitedLineTwo[splitedLineTwo.length - 1].contains(Character.toString(vowels[charIndex])))
                    return false;
            } else if (splitedLineTwo[splitedLineTwo.length - 1].contains(Character.toString(vowels[charIndex]))) {
                if (!splitedLineOne[splitedLineOne.length - 1].contains(Character.toString(vowels[charIndex])))
                    return false;
            }
        }

        return true;
    }

    static public boolean trouble(int a, int b){
        char[] first = String.valueOf(a).toCharArray();
        char[] second = String.valueOf(b).toCharArray();
        for (int i=2; i<first.length;i++){
            for (int j=1; j<second.length;j++){
                if (first[i]==first[i-1]) {
                    if (first[i]==first[i-2]) {
                        if (second[j]==second[j-1]){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public static int countUniqueBooks(String s, char b) { // AZYWABBCATTTA  ZYW BBC  TTT
        String[] words = s.split(Character.toString(b), -1);
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < words.length-1; i = i + 2) {
            char[] letters = words[i].toCharArray();
            //System.out.println(letters); //$AA$BBCATT$C$$B$
            for (char lt : letters)
                if (result.indexOf(Character.toString(lt)) == -1)
                    result.append(lt);
        }
        return result.length();
    }

}
