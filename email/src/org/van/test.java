package org.van;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
public class test {
    public static void main(String[] args) {
        String s= "hello friend31.lie2@ni.123.com  my@myhome.com hielaelro   asdasdad 12+3@na.m.net bye.hi.my@am.com  hasdas123..123.43@@hasd.c22 " +
                "......@.....     hi.....byee..hello@facebook.com";
        Pattern pattern = Pattern.compile("[a-zA-Z0-9._+-]+(@[a-zA-Z0-9.-]+\\.[a-zA-Z]+)");
        Pattern patternBad = Pattern.compile("^(?!.*(\\.{2,}|\\_{2,}|\\+{2,}|\\-{2,}))");
        Matcher matcher = pattern.matcher(s);
        ArrayList<String> acceptedOnce = new ArrayList<>();
        ArrayList<String> filtered = new ArrayList<>();

        int counter = 0;

        while (matcher.find()) {
            counter++;
            System.out.println(counter);
            System.out.println(matcher.group());
            System.out.println(matcher.group(1));
            acceptedOnce.add(matcher.group());
        }

        System.out.println("Accepted group, no filter");
        System.out.println(acceptedOnce);






    }


}
