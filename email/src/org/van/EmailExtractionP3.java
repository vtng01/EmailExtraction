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
        String emailRegex = "[a-z0-9._+-]+[a-z0-9]+(@[a-z0-9]+[a-z0-9.-]+\\.[a-z]+)";
        // pattern that is not valid
        // feel free to add more sequences and characters that are not valid for emails in badEmailRegex
        String badEmailRegex = "^[^a-z0-9]|\\.{2,}|\\_{2,}|\\+{2,}|\\-{2,}";
        // turn txt to ArrayList
        ArrayList<String> text = convertToArrayList(fileName);

        ArrayList<String> emailFilter = filterAndGetDomain(emailRegex, badEmailRegex, text);

        HashMap<String, Integer> dict = convertToHashMap(emailFilter);
        System.out.println(dict);

        // final check for the count of @
        int sum = 0;
        for (int i: dict.values()) {
            sum+= i;
        }
        System.out.println(sum);

    }

    // method to filter
    public static ArrayList<String> filterAndGetDomain(String regex, String badRegex, ArrayList<String> list) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Pattern badPattern = Pattern.compile(badRegex, Pattern.CASE_INSENSITIVE);

        ArrayList<String> filtered = new ArrayList<>();

        // filter loop
        for (String s: list) {
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                // given that the string follows the form of an email
                // need to check that it is NOT degenerate
                Matcher matcher2 = badPattern.matcher(matcher.group());
                if (!matcher2.find()) {
                    filtered.add(matcher.group(1));
                }
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


