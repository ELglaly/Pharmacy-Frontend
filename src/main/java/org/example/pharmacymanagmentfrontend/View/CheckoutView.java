// CheckoutView.java
package org.example.pharmacymanagmentfrontend.View;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.example.pharmacymanagmentfrontend.Controller.PharmacyPersonnelController;
import org.example.pharmacymanagmentfrontend.Model.Brush;
import org.example.pharmacymanagmentfrontend.Model.Prescription;

import java.util.Map;

import static org.example.pharmacymanagmentfrontend.View.SharedView.alterMessage;

public class CheckoutView {
    static Boolean promocodeApply = false;
    static Button confirmButton;
    static TextField signatureField = new TextField();
    static Pane pane = new Pane();
    static ComboBox<String> paymentMethods = new ComboBox<>();
    static TextField cardNumberField;
    static TextField expiryField;
    static TextField cvvField;
    static Group group = new Group();

    // Main method to add the checkout view
    public static void addCheckoutView(Prescription prescription) {
        Stage stage = new Stage();
        VBox mainLayout = createMainLayout(prescription, stage);
        Scene primaryScene = new Scene(mainLayout, 700, 600);
        PharmacyPersonnelController.resetTimeUp(mainLayout);
        stage.setTitle("Checkout View");
        stage.setScene(primaryScene);
        stage.show();
    }

    // Create the main layout for the checkout view
    private static VBox createMainLayout(Prescription prescription, Stage stage) {
        VBox mainLayout = new VBox(20);
        mainLayout.setStyle("-fx-background-color: #f8f8f8;");
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);

        mainLayout.getChildren().addAll(
                createTitleLabel(),
                createCustomerInfoGridPane(prescription),
                createItemsTable(prescription),
                createPromocodeSection(prescription),
                createSignatureSection(),
                createPaymentMethodsSection(prescription),
                createButtonBox(stage, prescription)
        );

        return mainLayout;
    }

    // Create the title label
    private static Label createTitleLabel() {
        Label titleLabel = new Label("Pharmacy Checkout");
        titleLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #2e7d32;");
        return titleLabel;
    }

    // Create the customer info grid pane
    private static GridPane createCustomerInfoGridPane(Prescription prescription) {
        GridPane customerInfo = new GridPane();
        customerInfo.setHgap(10);
        customerInfo.setVgap(10);
        customerInfo.setAlignment(Pos.CENTER);
        customerInfo.setPadding(new Insets(15));

        Label nameLabel = new Label("Customer Name:");
        nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        Label nameValue = new Label(prescription.getPatient().getName());
        nameValue.setStyle("-fx-font-size: 16px;");

        Label totalLabel = new Label("Total Cost:");
        totalLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        Label totalValue = new Label(String.format("$%.2f", prescription.getTotalPrice()));
        totalValue.setStyle("-fx-font-size: 16px;");

        customerInfo.add(nameLabel, 0, 0);
        customerInfo.add(nameValue, 1, 0);
        customerInfo.add(totalLabel, 0, 1);
        customerInfo.add(totalValue, 1, 1);
        return customerInfo;
    }

    // Create the items table
    private static TableView<Map.Entry<String, Map<Integer, Float>>> createItemsTable(Prescription prescription) {
        TableView<Map.Entry<String, Map<Integer, Float>>> itemsTable = new TableView<>();

        TableColumn<Map.Entry<String, Map<Integer, Float>>, String> drugNameColumn = new TableColumn<>("Drug Name");
        drugNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));

        TableColumn<Map.Entry<String, Map<Integer, Float>>, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getValue().size()).asObject());

        TableColumn<Map.Entry<String, Map<Integer, Float>>, Float> totalPriceColumn = new TableColumn<>("Price");
        totalPriceColumn.setCellValueFactory(cellData -> {
            float price = cellData.getValue().getValue().values().stream().reduce(0.0f, Float::sum);
            return new SimpleFloatProperty(price).asObject();
        });

        itemsTable.getColumns().addAll(drugNameColumn, quantityColumn, totalPriceColumn);
        ObservableList<Map.Entry<String, Map<Integer, Float>>> tableData = FXCollections.observableArrayList(prescription.getDrugAndQuantity().entrySet());
        itemsTable.setItems(tableData);
        itemsTable.setStyle("-fx-font-size: 14px; -fx-background-color: #ffffff;");
        itemsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        PharmacyPersonnelController.resetTimeUp(itemsTable);
        return itemsTable;
    }

    // Create the promo code section
    private static VBox createPromocodeSection(Prescription prescription) {
        TextField promoCodeField = new TextField();
        promoCodeField.setPromptText("Enter Promocode");

        Button applyButton = new Button("Apply Promocode");
        applyButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-size: 14px;");

        Label promoFeedback = new Label();
        promoFeedback.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");

        applyButton.setOnAction(e -> applyPromoCode(prescription, promoCodeField, promoFeedback));

        VBox layout = new VBox(10, promoCodeField, applyButton, promoFeedback);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(15));
        PharmacyPersonnelController.resetTimeUp(layout);
        return layout;
    }

    // Apply the promo code
    private static void applyPromoCode(Prescription prescription, TextField promoCodeField, Label promoFeedback) {
        String promoCode = promoCodeField.getText().trim();
        if ("SHERIF".equalsIgnoreCase(promoCode) && !promocodeApply) {
            float discountedPrice = prescription.getTotalPrice() * 0.9f;
            prescription.setTotalPrice(discountedPrice);
            promoFeedback.setText(String.format("Promocode applied successfully!\nPrice after discount: $%.2f", discountedPrice));
            promoFeedback.setStyle("-fx-text-fill: green;");
            promocodeApply = true;
        } else {
            promoFeedback.setText("Invalid promocode or already applied.");
            promoFeedback.setStyle("-fx-text-fill: red;");
        }
    }

    // Create the signature section
    private static VBox createSignatureSection() {
        Label signatureLabel = new Label("Signature Confirmation");
        signatureLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label instructions = new Label("Please provide a signature to confirm the transaction.");
        instructions.setStyle("-fx-font-size: 16px;");
        signatureField.setPromptText("Enter your signature here...");
        pane = createSignaturePane();

        VBox layout = new VBox(10.0, signatureLabel, instructions, pane, signatureField);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f3e5f5;");
        PharmacyPersonnelController.resetTimeUp(layout);
        return layout;
    }

    // Create the signature pane
    private static Pane createSignaturePane() {
        Brush brush = new Brush();
        group = new Group();
        Pane pane = new Pane(group);
        pane.setStyle("-fx-background-color: white");
        pane.setPrefSize(100, 50);
        pane.addEventFilter(MouseEvent.MOUSE_DRAGGED, event -> {
            int xCord = (int) event.getX();
            int yCord = (int) event.getY();
            if (xCord >= 0 && xCord <= pane.getWidth() && yCord >= 0 && yCord <= pane.getHeight()) {
                brush.drawDot(xCord, yCord, group);
            }
        });
        PharmacyPersonnelController.resetTimeUp(pane);
        return pane;
    }

    // Create the payment methods section
    private static VBox createPaymentMethodsSection(Prescription prescription) {
        VBox paymentLayout = new VBox(10);
        paymentLayout.setPadding(new Insets(20));
        paymentLayout.setAlignment(Pos.CENTER);

        Label paymentLabel = new Label("Select Payment Method:");
        paymentMethods.getItems().addAll("Cash", "Debit Card", "Credit Card", "Insurance");
        paymentMethods.setPromptText("Choose payment method");

        VBox paymentDetailsBox = new VBox(10);
        paymentDetailsBox.setAlignment(Pos.CENTER);

        paymentMethods.setOnAction(event -> updatePaymentDetailsBox(paymentDetailsBox));

        paymentLayout.getChildren().addAll(paymentLabel, paymentMethods, paymentDetailsBox);
        PharmacyPersonnelController.resetTimeUp(paymentLayout);
        return paymentLayout;
    }

    // Update the payment details box based on the selected payment method
    private static void updatePaymentDetailsBox(VBox paymentDetailsBox) {
        paymentDetailsBox.getChildren().clear();
        String selectedMethod = paymentMethods.getValue();

        if ("Cash".equals(selectedMethod)) {
            paymentDetailsBox.getChildren().add(new Label("Please proceed to pay cash at the counter."));
        } else if ("Debit Card".equals(selectedMethod) || "Credit Card".equals(selectedMethod)) {
            cardNumberField = new TextField();
            cardNumberField.setPromptText("Card Number");

            expiryField = new TextField();
            expiryField.setPromptText("MM/YY");

            cvvField = new TextField();
            cvvField.setPromptText("CVV");

            paymentDetailsBox.getChildren().addAll(
                    new Label("Enter Card Details:"),
                    cardNumberField, expiryField, cvvField
            );
        }
    }

    // Create the button box
    private static HBox createButtonBox(Stage stage, Prescription prescription) {
        Button cancelButton = new Button("Cancel");
        confirmButton = ManagementDashboard.createStyledButton("Checkout", "#2ecc71", "#27ae60");
        cancelButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 14px;");

        HBox buttonBox = new HBox(15, confirmButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);

        confirmButton.setOnAction(e -> validateDataEntered(stage, prescription));
        cancelButton.setOnAction(event -> stage.close());

        return buttonBox;
    }

    // Validate the data entered by the user
    private static void validateDataEntered(Stage stage, Prescription prescription) {
        String signature = signatureField.getText().trim();
        if (!(!signature.isEmpty() || group.getChildren().stream().anyMatch(node -> node instanceof Circle))) {
            Stage alert = alterMessage("Please Add Signature.", "Signature", "OK", null);
            alert.show();
        } else if (paymentMethods.getValue() == null) {
            Stage alert = alterMessage("Please add Payment Method.", "Payment Method", "OK", null);
            alert.show();
        } else if (isCardPaymentSelected() && areCardDetailsEmpty()) {
            Stage alert = alterMessage("Please add Card Details.", "Payment Method", "OK", null);
            alert.show();
        } else if ("insurance".equalsIgnoreCase(paymentMethods.getValue())) {
            PharmacyPersonnelController.showInsuranceClaim(prescription);
        } else {
            Stage alert = alterMessage("Checkout completed successfully!\nThank you for your purchase.", "Success", "OK", null);
            alert.show();
            stage.close();
        }
    }

    // Check if card payment is selected
    private static boolean isCardPaymentSelected() {
        return "Credit Card".equalsIgnoreCase(paymentMethods.getValue()) || "Debit Card".equalsIgnoreCase(paymentMethods.getValue());
    }

    // Check if card details are empty
    private static boolean areCardDetailsEmpty() {
        return cardNumberField.getText().isEmpty() || cvvField.getText().isEmpty() || expiryField.getText().isEmpty();
    }
}