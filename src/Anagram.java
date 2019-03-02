import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Anagram {

    public static void main(String[] args) throws IOException {
        String pathname = args[0];
        String word = args[1];

        long startTime = System.nanoTime();
        File file = new File(pathname);
        StringBuilder result = new StringBuilder();

        //read from file BUFFEREDREADER
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);

        try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (isAnagram(word, line)) {
                    result.append(", ");
                    result.append(line);
                }
            }
        }
        long endTime = System.nanoTime();
        long timeElapsed = (endTime - startTime) / 1000; //nanotime to microtime
        System.out.print(timeElapsed);
        System.out.println(result);
    }


    private static boolean isAnagram(String a, String b) {

        if (a == null || b == null || a.equals("") || b.equals(""))
            throw new IllegalArgumentException();

        //testing lenght
        if (a.length() != b.length())
            return false;

        a = a.toLowerCase();
        b = b.toLowerCase();

        // populate a map with letters and frequencies of String b
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < b.length(); i++) {
            char letter = b.charAt(i);

            if (!map.containsKey(letter)) {
                map.put(letter, 1);
            } else {
                Integer frequency = map.get(letter);
                map.put(letter, ++frequency);
            }
        }

        //test letters in String a against letters in String b map
        for (int i = 0; i < a.length(); i++) {
            char letter = a.charAt(i);

            if (!map.containsKey(letter))
                return false;

            Integer frequency = map.get(letter);

            if (frequency == 0)
                return false;
            else
                map.put(letter, --frequency);
        }

        return true;
    }
}