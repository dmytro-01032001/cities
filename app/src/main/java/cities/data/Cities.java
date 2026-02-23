package cities.data;

import java.util.*;

public class Cities {
    private ArrayList<String> allCities ;
    private Map<Character, List<String>> citiesMap;

    public Cities(ArrayList<String> allCities, Map<Character, List<String>> citiesMap){
        this.allCities = allCities;
        this.citiesMap = citiesMap;
    }

    public void mapCities() {
        for (String line:allCities) {
            char firstChar = Character.toUpperCase(line.charAt(0));                    
            citiesMap.computeIfAbsent(firstChar, k -> new ArrayList<>()).add(line);
        }
    }

    public boolean findCity(String cityName) {
        for (String key : allCities) {
            if (key.equalsIgnoreCase(cityName)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getByFirstChar(char letter){
        return citiesMap.get(letter);
    }
}
