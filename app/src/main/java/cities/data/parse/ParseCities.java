package cities.data.parse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseCities {
    private static String filePath = "cities.txt";

    protected static ArrayList<String> allCities = new ArrayList<>();
    protected static Map<Character, List<String>> citiesMap = new HashMap<>();

    public void parseCities() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    allCities.add(line);

                    char firstChar = Character.toUpperCase(line.charAt(0));                    
                    citiesMap.computeIfAbsent(firstChar, k -> new ArrayList<>()).add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getMessage());
        }
    }

    public ArrayList<String> getAllCities(){
        return allCities;
    }

    public Map<Character, List<String>> getCitiesMap(){
        return citiesMap;
    }
}
