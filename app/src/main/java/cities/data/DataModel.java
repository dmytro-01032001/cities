package cities.data;

import java.util.List;
import java.util.HashSet;

public class DataModel {
    private String curentCity;
    private Character lastLetter;
    private Cities cities;
    private HashSet<String> usedCities = new HashSet<>();

    public DataModel(Cities cities){
        this.cities = cities;
    }

    public void setCity(String name){
        this.curentCity = name;
        putInUsedCities(name);
        setLastLetter(name);
    }
    
    private void setLastLetter(String text){
        if (text.charAt(text.length() - 1) == 'ь') {
            lastLetter = text.charAt(text.length() - 2);
        } else {
            lastLetter = text.charAt(text.length() - 1);
        }
    }

    public String getCurrentCity(){
        return curentCity;
    }

    public boolean ifCityExists(String text){
        return cities.findCity(text);
    }

    public List<String> getCitiesByLetter(){
        return cities.getByFirstChar(Character.toUpperCase(getLastLetter()));
    }

    public char getLastLetter(){
        return lastLetter;
    }

    public String findNextCity(){
        List<String> subCities = getCitiesByLetter();
        if (subCities == null){
            return null;
        } else {
            for (String city: subCities){
                if (!cityAlreadyUsed(city)){
                    putInUsedCities(city);
                    setLastLetter(city);
                    return city;
                }
            }
        }
        return null;
    }

    public boolean cityAlreadyUsed(String name){
        return usedCities.contains(name.trim().toUpperCase());
    }

    private void putInUsedCities(String name){
        usedCities.add(name.toUpperCase());
    }

    public boolean cityStartsCorrectly(String text){
        if (lastLetter == null) {
            return true;
        }
        return Character.toUpperCase(lastLetter) == Character.toUpperCase(text.charAt(0));
    }

    public void reset(){
        usedCities = new HashSet<>();
        lastLetter = null;
        curentCity = "";
    }
}
