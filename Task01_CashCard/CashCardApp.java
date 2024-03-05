import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CashCardApp extends Application {

    private ObservableList<Cashcard> cashcardList = FXCollections.observableArrayList();
    private TextField nameField, amountField;
    private ListView<Cashcard> listView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cashcard Management");

        // UI components
        nameField = new TextField();
        amountField = new TextField();
        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        listView = new ListView<>();
        listView.setItems(cashcardList);

        // Event handlers
        addButton.setOnAction(e -> addCashcard());
        updateButton.setOnAction(e -> updateCashcard());
        deleteButton.setOnAction(e -> deleteCashcard());

        // Layout
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(
                new Label("Name:"), nameField,
                new Label("Amount:"), amountField,
                addButton, updateButton, deleteButton,
                new Label("Cashcards:"), listView
        );

        // Set the scene
        Scene scene = new Scene(vbox, 300, 400);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    private void addCashcard() {
        String name = nameField.getText();
        double amount = Double.parseDouble(amountField.getText());

        Cashcard newCashcard = new Cashcard(name, amount);
        cashcardList.add(newCashcard);

        clearFields();
    }

    private void updateCashcard() {
        Cashcard selectedCashcard = listView.getSelectionModel().getSelectedItem();

        if (selectedCashcard != null) {
            String newName = nameField.getText();
            double newAmount = Double.parseDouble(amountField.getText());

            selectedCashcard.setName(newName);
            selectedCashcard.setAmount(newAmount);

            listView.refresh();
            clearFields();
        }
    }

    private void deleteCashcard() {
        Cashcard selectedCashcard = listView.getSelectionModel().getSelectedItem();

        if (selectedCashcard != null) {
            cashcardList.remove(selectedCashcard);
            clearFields();
        }
    }

    private void clearFields() {
        nameField.clear();
        amountField.clear();
    }

    public static class Cashcard {
        private String name;
        private double amount;

        public Cashcard(String name, double amount) {
            this.name = name;
            this.amount = amount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Amount: $" + amount;
        }
    }
}
