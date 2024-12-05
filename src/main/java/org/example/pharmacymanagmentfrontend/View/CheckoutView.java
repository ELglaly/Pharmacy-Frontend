package org.example.pharmacymanagmentfrontend.View;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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

import static org.example.pharmacymanagmentfrontend.HelloApplication.primaryScene;
import static org.example.pharmacymanagmentfrontend.View.SharedView.alterMessage;

public class CheckoutView {
    static Boolean promocodeApplied = false;
    static Button confirmButton;
    static TextField signatureField = new TextField();
    static Pane pane = new Pane();
    static ComboBox<String> paymentMethods = new ComboBox<>();
    static TextField cardNumberField;
    static TextField expiryField;
    static TextField cvvField;
    static Group group = new Group();


    public static void addCheckoutView(Prescription prescription) {
        Stage stage = new Stage();

        // Title Label
        Label titleLabel = new Label("Pharmacy Checkout");
        titleLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #2e7d32;");

        // Customer Info Section
        GridPane customerInfo = creadCustomerInfoGridPane(prescription);

        // Items Table
        TableView<Map.Entry<String, Map<Integer, Float>>> itemsTable = createItemsTable(prescription);
        itemsTable.setPrefHeight(200);

        // Promocode Section
        VBox promocodeSection = createPromocodeSection(prescription);

        // Buttons Section
        Button cancelButton = new Button("Cancel");
        confirmButton =ManagementDashboard.createStyledButton("Checkout", "#2ecc71", "#27ae60");
        cancelButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 14px;");

        HBox buttonBox = new HBox(15, confirmButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);

        confirmButton.setOnAction(e -> {
          ValidateDataEntered(stage,prescription);
        });

        cancelButton.setOnAction(event -> stage.close());

        // Main Layout: Split into two columns
        HBox mainLayout = new HBox(20);
        mainLayout.setStyle("-fx-background-color: #f8f8f8;");
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);

        VBox leftColumn = new VBox(20, titleLabel, customerInfo, itemsTable, promocodeSection);
        VBox rightColumn = new VBox(20, showSignatureScreen() ,addPaymentMethodsUI(prescription), buttonBox);
        PharmacyPersonnelController.resetTimeUp(leftColumn);
        PharmacyPersonnelController.resetTimeUp(rightColumn);
        mainLayout.getChildren().addAll(leftColumn, rightColumn);

        // Adjust layout for smaller screens (Stack vertically on smaller screens)
        leftColumn.setMaxWidth(350); // Set a max width for the left column
        rightColumn.setMaxWidth(350); // Set a max width for the right column
        HBox.setHgrow(leftColumn, Priority.ALWAYS);
        HBox.setHgrow(rightColumn, Priority.ALWAYS);

        // Scene and Stage
        primaryScene = new Scene(mainLayout, 700, 600);
        PharmacyPersonnelController.resetTimeUp(mainLayout);
        stage.setTitle("Checkout View");
        stage.setScene(primaryScene);
        stage.show();
    }

    private static GridPane creadCustomerInfoGridPane(Prescription prescription) {
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

    private static void ValidateDataEntered(Stage stage, Prescription prescription) {
        String signature = signatureField.getText().trim();
        if (!(!signature.isEmpty() || group.getChildren().stream().anyMatch(node -> node instanceof Circle))) {
            System.out.println(group.getChildren().stream().anyMatch(node -> node instanceof Circle));
            Stage alert = alterMessage("Please Add Signature.",
                    "Signature",
                    "OK",null);
            alert.show();
        }
        else if (paymentMethods.getValue() == null) {
            Stage alert = alterMessage("Please add Payment Method.",
                    "Payment Method",
                    "OK",null);
            alert.show();
        }
        else if(paymentMethods.getValue().equalsIgnoreCase("Credit Card") || paymentMethods.getValue().equalsIgnoreCase("Debit Card") )
        {
            if(cardNumberField.getText().isEmpty() || cvvField.getText().isEmpty() || expiryField.getText().isEmpty())
            {
                Stage alert = alterMessage("Please add Card Details.",
                        "Payment Method",
                        "OK",null);
                alert.show();
            }
            else {
                Stage alert = alterMessage(
                        "Checkout completed successfully!\nThank you for your purchase.",
                        "Success",
                        "OK",null
                );
                alert.show();
                stage.close();
            }
        }
        else if(paymentMethods.getValue().equalsIgnoreCase("insurance"))
        {
            PharmacyPersonnelController.showInsurancClaim(prescription);
        }
    }


    private static TableView<Map.Entry<String, Map<Integer, Float>>> createItemsTable(Prescription prescription) {
        TableView<Map.Entry<String, Map<Integer, Float>>> itemsTable = new TableView<>();

        // Columns
        TableColumn<Map.Entry<String, Map<Integer, Float>>, String> drugNameColumn = new TableColumn<>("Drug Name");
        drugNameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getKey()));

        TableColumn<Map.Entry<String, Map<Integer, Float>>, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getValue().size()).asObject());

        TableColumn<Map.Entry<String, Map<Integer, Float>>, Float> totalPriceColumn = new TableColumn<>("Price");
        totalPriceColumn.setCellValueFactory(cellData -> {
            float price = cellData.getValue().getValue().values().stream().reduce(0.0f, Float::sum);
            return new SimpleFloatProperty(price).asObject();
        });

        itemsTable.getColumns().addAll(drugNameColumn, quantityColumn, totalPriceColumn);

        // Data
        ObservableList<Map.Entry<String, Map<Integer, Float>>> tableData =
                FXCollections.observableArrayList(prescription.getDrugAndQuantity().entrySet());
        itemsTable.setItems(tableData);

        // Style
        itemsTable.setStyle("-fx-font-size: 14px; -fx-background-color: #ffffff;");
        itemsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        PharmacyPersonnelController.resetTimeUp(itemsTable);
        return itemsTable;
    }

    private static VBox createPromocodeSection(Prescription prescription) {
        TextField promoCodeField = new TextField();
        promoCodeField.setPromptText("Enter Promocode");

        Button applyButton = new Button("Apply Promocode");
        applyButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-size: 14px;");

        Label promoFeedback = new Label();
        promoFeedback.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");

        applyButton.setOnAction(e -> {
            String promoCode = promoCodeField.getText().trim();
            if ("SHERIF".equalsIgnoreCase(promoCode) && !promocodeApplied) {
                float discountedPrice = prescription.getTotalPrice() * 0.9f;
                // Update the prescription total price
                prescription.setTotalPrice(discountedPrice);

                // Provide feedback
                promoFeedback.setText(String.format("Promocode applied successfully!\nPrice after discount: $%.2f", discountedPrice));
                promoFeedback.setStyle("-fx-text-fill: green;");
                promocodeApplied = true; // Ensure promocode is applied only once
            } else {
                promoFeedback.setText("Invalid promocode or already applied.");
                promoFeedback.setStyle("-fx-text-fill: red;");
            }

        });

        VBox layout = new VBox(10, promoCodeField, applyButton, promoFeedback);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(15));
        PharmacyPersonnelController.resetTimeUp(layout);
        return layout;
    }

    private static VBox showSignatureScreen() {
        Label signatureLabel = new Label("Signature Confirmation");
        signatureLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label instructions = new Label("Please provide a signature to confirm the transaction.");
        instructions.setStyle("-fx-font-size: 16px;");
        signatureField.setPromptText("Enter your signature here...");
        pane =sigintureBrush();
        VBox layout = new VBox(10.0, signatureLabel, instructions ,pane, signatureField);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f3e5f5;");
        PharmacyPersonnelController.resetTimeUp(layout);
        return layout;
    }

    private static Pane sigintureBrush() {
        Brush brush = new Brush();
        group = new Group();
        Pane pane = new Pane(group); // Use a Pane to control size
        pane.setStyle("-fx-background-color: white");
        pane.setPrefSize(100, 50); // Set width and height for the pane
        pane.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int xCoord = (int) mouseEvent.getX();
                int yCoord = (int) mouseEvent.getY();
                // Prevent drawing outside the bounds
                if (xCoord >= 0 && xCoord <= pane.getWidth() && yCoord >= 0 && yCoord <= pane.getHeight()) {
                    brush.drawDot(xCoord, yCoord, group);
                }
            }
        });
        PharmacyPersonnelController.resetTimeUp(pane);
        return pane;
    }

    public static VBox addPaymentMethodsUI(Prescription prescription) {
        VBox paymentLayout = new VBox(10);
        paymentLayout.setPadding(new Insets(20));
        paymentLayout.setAlignment(Pos.CENTER);

        // Payment method selection
        Label paymentLabel = new Label("Select Payment Method:");
        paymentMethods.getItems().addAll("Cash", "Debit Card", "Credit Card","Insurance");
        paymentMethods.setPromptText("Choose payment method");

        // Dynamic input fields for payment details
        VBox paymentDetailsBox = new VBox(10);
        paymentDetailsBox.setAlignment(Pos.CENTER);

        // Action for payment method selection
        paymentMethods.setOnAction(event -> {
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
        });

        paymentLayout.getChildren().addAll(paymentLabel, paymentMethods, paymentDetailsBox);
        PharmacyPersonnelController.resetTimeUp(paymentLayout);
        return paymentLayout;
    }
}
