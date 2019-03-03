import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Anagram {

    //HOMEMADE HASHTABLE
    public static void main(String[] args) throws IOException {
        String pathname = args[0];
        String word = args[1];

        long startTime = System.nanoTime();
        File file = new File(pathname);
        StringBuilder result = new StringBuilder();
        word = word.toLowerCase();

        Hashtable<Character, Double> hash = new Hashtable<Character,Double>();
        hash.put('a', 2.0);hash.put('b', 3.0);hash.put('c', 5.0);hash.put('d', 7.0);
        hash.put('e', 11.0);hash.put('f', 13.0);hash.put('g', 17.0);hash.put('h', 19.0);
        hash.put('i', 23.0);hash.put('j', 29.0);hash.put('k', 31.0);hash.put('l', 37.0);
        hash.put('m', 41.0);hash.put('n', 43.0);hash.put('o', 47.0);hash.put('p', 53.0);
        hash.put('q', 59.0);hash.put('r', 61.0);hash.put('s', 67.0);hash.put('t', 71.0);
        hash.put('u', 73.0);hash.put('v', 79.0);hash.put('w', 83.0);hash.put('x', 89.0);
        hash.put('y', 97.0);hash.put('z', 101.0);hash.put('õ', 103.0);hash.put('ä', 107.0);
        hash.put('ö', 109.0);hash.put('ü', 113.0);hash.put('š', 1127.0);hash.put('ž', 131.0);
        hash.put(' ', 137.0);
        double code = 1.0;
        for(Character ch : word.toCharArray()) {
            code*=hash.get(ch);
        }
        double n; //to hold input word's code value

        //read from bufferreader
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);

        try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (word.length() != line.length())
                    continue;
                line = line.toLowerCase();
                n = code; //input word's hashcode
                for(Character ch : line.toCharArray()) {
                    if (hash.get(ch) != null){
                        n/=hash.get(ch);
                    }
                }
                if (n==1.0) {
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
}