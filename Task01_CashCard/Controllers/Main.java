package Controllers;


import Entities.CashCard;
import Persistence.CashCardDAO;
import Persistence.CashCardDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.util.List;


public class Main {
    @FXML
    private TextField newCardName;
    @FXML
    private TextField newCardAmount;
    @FXML
    private FlowPane CCContainer;
    private final CashCardDAO dao = new CashCardDAOImpl();

    public void initialize(){
//        CCContainer.setClip(new ScrollPane());
        loadCashCards();
    }
    public void loadCashCards(){
        try{
            List<CashCard> cashCards = dao.getAllCards();
            CCContainer.getChildren().clear();

            for(CashCard c:cashCards){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("..//UIElements//CashCard.fxml"));
                AnchorPane cc = loader.load();
                Controllers.CashCard ccController = loader.getController();
                ccController.setCCNameField(c.getName());
                ccController.setCCAmountField(c.getAmount());

                CCContainer.getChildren().add(cc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void createCashCard(ActionEvent event){
        if((newCardName.getText().compareTo("") == 0) || (newCardAmount.getText().compareTo("") == 0)) return;

        if(!isNumeric(newCardAmount.getText())) return;

//        Update DB
        CashCard cashCard = new CashCard(newCardName.getText(), Integer.parseInt(newCardAmount.getText()));
        dao.createCard(cashCard);

//        Update UI
        loadCashCards();
    }
    private static Boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
