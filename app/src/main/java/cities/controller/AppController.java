package cities.controller;

import cities.view.ModalWindow;
import cities.view.UI;
import cities.data.DataModel;

public class AppController {
    private DataModel model;
    private UI view;
    private String noSuchCityMsg = "Такого міста нема у списку. Спробуйте ще.";
    private String thisCityAlreadyUsedMsg = "Це місто вже було. Спробуйте ще.";
    private int userScore = 0;
    private int computerScore = 0;
    private String giveUpText = "здаюсь";

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

    public AppController(DataModel model, UI view) {
        this.model = model;
        this.view = view;

        this.view.showMessage("Ваш хід!");

        this.view.getActionButton().addActionListener(e -> {
            String input = this.view.getCityInputText().trim();
            if (input.equals(giveUpText)){
                showModal(false);
                setAllZero();
                return;
            }
            if (model.ifCityExists(input)){
                if (model.cityAlreadyUsed(input)){
                    this.view.showMessage(thisCityAlreadyUsedMsg);
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
                this.view.showMessage(noSuchCityMsg);
            }
        });
    }
}
