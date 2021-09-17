package org.van;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.HashMap;
public class EmailExtractionP3 {
    public static void main(String[] args) {

        String fileName = "email/sample.txt";
        // allowable email form
        Pattern emailRegex = Pattern.compile("[a-zA-Z0-9._+-]+(@[a-zA-Z0-9.-]+\\.[a-zA-Z]+)");

        // turn txt to ArrayList
        ArrayList<String> text = convertToArrayList(fileName);

        ArrayList<String> emailFilter1 = filterAndGetDomain(emailRegex, text);

        HashMap<String, Integer> dict = convertToHashMap(emailFilter1);
        System.out.println(dict);

        // final check for the count of @
        int sum = 0;
        for (int i: dict.values()) {
            sum+= i;
        }
        System.out.println(sum);

    }

    // method to filter
    public static ArrayList<String> filterAndGetDomain(Pattern regex, ArrayList<String> list) {

        ArrayList<String> filtered = new ArrayList<>();

        // filter loop
        for (String s: list) {
            Matcher matcher = regex.matcher(s);
            while (matcher.find()) {
                filtered.add(matcher.group(1));
            }
        }
        return filtered;
    }

    // get hashmap
    public static ArrayList<String> convertToArrayList (String fileName) {

        ArrayList<String> list = new ArrayList<>();

        try (Scanner scanner = new Scanner(Paths.get(fileName))) {
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return list;
    }

    public static HashMap<String, Integer> convertToHashMap(ArrayList<String> list) {
        HashMap<String, Integer> dict = new HashMap<>();

        for (String d: list) {
            if (dict.containsKey(d)) {
                dict.put(d, dict.get(d) + 1);
            } else {
                dict.put(d, 1);
            }
        }

        return dict;
    }



}

