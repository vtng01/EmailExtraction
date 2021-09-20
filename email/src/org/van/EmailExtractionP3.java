package org.van;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailExtractionP3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String fileName = "email/sample.txt";
        // allowable email form
        String emailRegex = "[a-z0-9._+-]+[a-z0-9]+(?<domain>@(?<domainTop>[a-z0-9]+)[a-z0-9.-]+\\.[a-z]+)";
        // pattern that is not valid
        // feel free to add more sequences and characters that are not valid for emails in badEmailRegex
        String badEmailRegex = "^[^a-z0-9]|\\.{2,}|\\_{2,}|\\+{2,}|\\-{2,}";
        // turn txt to ArrayList
        ArrayList<String> text = convertToArrayList(fileName);

        ArrayList<String> emailList = filterAndGetDomain(emailRegex, badEmailRegex, text);

        HashMap<String, Integer> dictOfEmailDomains = convertToHashMap(emailList);
        System.out.println(dictOfEmailDomains);

        // final check for the count of @
        int sum = 0;
        for (int i: dictOfEmailDomains.values()) {
            sum+= i;
        }

        System.out.println(sum);
        printTopDomains(dictOfEmailDomains);

        System.out.println("Please enter the minimum occurrence of domains you wish to see:");
        int frequency = scanner.nextInt();
        printTopDomainsByCount(dictOfEmailDomains, frequency);


    }

    // method to filter
    public static ArrayList<String> filterAndGetDomain(String regex, String badRegex, ArrayList<String> list) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Pattern badPattern = Pattern.compile(badRegex, Pattern.CASE_INSENSITIVE);

        ArrayList<String> filtered = new ArrayList<>();

        // filter loop
        for (String s: list) {
            Matcher emailFilter = pattern.matcher(s);
            while (emailFilter.find()) {
                // given that the string follows the form of an email
                // need to check that it is NOT degenerate
                Matcher degenerateFilter = badPattern.matcher(emailFilter.group());
                if (!degenerateFilter.find()) {
                    filtered.add(emailFilter.group("domain"));
                }
            }
        }
        return filtered;
    }


    public static ArrayList<String> convertToArrayList (String fileName) {
        // generate new array list to store each line of text from document
        ArrayList<String> myList = new ArrayList<>();

        try (Scanner scanner = new Scanner(Paths.get(fileName))) {
            while (scanner.hasNextLine()) {
                myList.add(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return myList;
    }

    // get hashmap
    public static HashMap<String, Integer> convertToHashMap(ArrayList<String> list) {
        HashMap<String, Integer> dict = new HashMap<>();

        for (String line: list) {
            if (dict.containsKey(line)) {
                dict.put(line, dict.get(line) + 1);
            } else {
                dict.put(line, 1);
            }
        }
        return dict;
    }

    public static void printTopDomains(HashMap<String, Integer> myDict) {
        myDict.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                limit(10).forEach(System.out::println);
    }

    public static void printTopDomainsByCount(HashMap<String, Integer> myDict, Integer frequency) {
        myDict.entrySet().stream().
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                filter(entry -> entry.getValue() >= frequency).
                forEach(System.out::println);
    }
}


