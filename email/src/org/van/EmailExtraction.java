package org.van;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class EmailExtraction {
    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("@softwire.com");




        int counter = 0;

        // added each line to list of string

        // **************************************
        ArrayList<String> list = new ArrayList<>();
        String fileName = "email/sample.txt";
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(list::add);


        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // **************************************


        for (String s: list) {
            Matcher matcher = pattern.matcher(s);

            while (matcher.find()) {
                counter++;
            }
        }

        /*
        Matcher matcher = pattern.matcher("eyde@softwire.com straphangs Natchez geals caterers overdue Brownie facebook.com incurable's rubetta@softwire.com");

        System.out.println(matcher);
        System.out.println(matcher.find());
        */
        System.out.println(counter);







    }
}
