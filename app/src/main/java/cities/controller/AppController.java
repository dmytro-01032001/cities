package cities.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import cities.view.ModalWindow;
import cities.view.UI;
import cities.data.Cities;
import cities.data.DataModel;
import cities.data.parse.ParseCities;

public class AppController {
    private DataModel model;
    private UI view;
    private static final String NO_SUCH_CITY = "Такого міста нема у списку. Спробуйте ще.";
    private static final String CITY_ALREADY_USED = "Це місто вже було. Спробуйте ще.";
    private static final String YOUR_MOVE = "Ваш хід!";
    private int userScore = 0;
    private int computerScore = 0;
    private static final String giveUpText = "ЗДАЮСЬ";

    private String yourMoveMsg(){
        return "Назвіть місто, що починається на " + model.getLastLetter();
    }

    private String wrongStartCity(){
        return "Неправильне місто: має починатися з " + model.getLastLetter();
    }

    private void makeCompScore(){
        computerScore++;
        view.setCompScoreText(computerScore);
    }

    private void makeUserScore(){
        userScore++;
        view.setUserScoreText(userScore);
    }

    private void setAllZero(){
        userScore = 0;
        computerScore = 0;
        model.reset();
        view.setCityInputText("");
        view.setCompCityText("");
        view.setCompScoreText(computerScore);
        view.setUserScoreText(userScore);
        view.showMessage(YOUR_MOVE);
    }

    private void compMakeMove(){
        String nextCity = model.findNextCity();
        if (nextCity == null){
            showModal(true);
            return;
        }
        view.setCompCityText(nextCity);
        makeCompScore();
        view.setCityInputText("");
        view.showMessage(yourMoveMsg());
    }

    private void showModal(boolean win){
        String modalTtile;
        if (win){
            modalTtile = "Вітаємо!";
        } else {
            modalTtile = "Шкода :(";
        }
        ModalWindow modal = new ModalWindow(view.getMainFrame(), modalTtile, userScore, computerScore);
        modal.setVisible(true);
        setAllZero();
    }

    public AppController() {
        ParseCities parser = new ParseCities();
        parser.parseCities();
        ArrayList<String> allCities = parser.getAllCities();
        Map<Character, List<String>> citiesMap = parser.getCitiesMap();
        Cities cities = new Cities(allCities, citiesMap);

        this.model = new DataModel(cities);
        this.view = new UI();

        this.view.showMessage(YOUR_MOVE);

        this.view.getActionButton().addActionListener(e -> {
            String input = this.view.getCityInputText().trim();
            if (input.equalsIgnoreCase(giveUpText)){
                showModal(false);
                setAllZero();
                return;
            }
            if (model.ifCityExists(input)){
                if (model.cityAlreadyUsed(input)){
                    this.view.showMessage(CITY_ALREADY_USED);
                } else {
                    if (model.cityStartsCorrectly(input)){
                        model.setCity(input);
                        makeUserScore();
                        compMakeMove();
                    } else {
                        this.view.showMessage(wrongStartCity());
                    }
                }
            } else {
                this.view.showMessage(NO_SUCH_CITY);
            }
        });
    }

    public void start(){
        view.show();
    }
}
