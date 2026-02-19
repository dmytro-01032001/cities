package cities.data;

import java.util.List;
import java.util.ArrayList;

public class DataModel {
    private String curentCity;
    private Character lastLetter;
    private Cities cities = new Cities();
    private List<String> usedCities = new ArrayList<>();

    public void setCity(String text){
        this.curentCity = text;
        usedCities.add(text.toUpperCase());
        setLastLetter(text);
    }
    
    private void setLastLetter(String text){
        if(text.charAt(text.length() - 1) == 'ь'){
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
                if (!usedCities.contains(city.toUpperCase())){
                    usedCities.add(city.toUpperCase());
                    setLastLetter(city);
                    return city;
                }
            }
        }
        return null;
    }

    public boolean cityAlreadyUsed(String text){
        return usedCities.contains(text.trim().toUpperCase());
    }

    public boolean cityStartsCorrectly(String text){
        if (lastLetter == null) {
            return true;
        }
        return Character.toUpperCase(lastLetter) == Character.toUpperCase(text.charAt(0));
    }

    public void reset(){
        usedCities = new ArrayList<>();
        lastLetter = null;
        curentCity = "";
    }
}
