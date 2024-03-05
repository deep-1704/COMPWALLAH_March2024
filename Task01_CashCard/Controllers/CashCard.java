package Controllers;

import Persistence.CashCardDAO;
import Persistence.CashCardDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class CashCard {
    @FXML
    private Text CCNameField;
    @FXML
    private TextField CCAmountField;
    @FXML
    private AnchorPane CCAnchor;
    private final CashCardDAO dao = new CashCardDAOImpl();

    public void initialize(){
        CCAnchor.setStyle("-fx-background-color: grey;");
    }
    public void updateCashCard(ActionEvent actionEvent){
        Entities.CashCard cashCard = new Entities.CashCard(CCNameField.getText(), Integer.parseInt(CCAmountField.getText()));
        cashCard = dao.updateCard(CCNameField.getText(), cashCard);
        CCAmountField.setText(String.valueOf(cashCard.getAmount()));
    }
    public void deleteCashCard(ActionEvent actionEvent){
        dao.deleteCard(CCNameField.getText());
        CCAnchor.setDisable(true);
    }

    public void setCCNameField(String name){
        CCNameField.setText(name);
    }
    public void setCCAmountField(int amount){
        CCAmountField.setText(String.valueOf(amount));
    }
}
