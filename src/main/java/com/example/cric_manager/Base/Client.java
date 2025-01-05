package com.example.cric_manager.Base;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.example.cric_manager.Controllers.*;
import com.example.cric_manager.Core_1.CricketPlayerDatabase;
import com.example.cric_manager.Core_1.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Client extends Application {

    private Stage stage;
    Socket socket;
    public Stage getStage() {
        return stage;
    }
    boolean ini;
    SocketWrapper socketWrapper;
    public boolean islog = false;

    @Override
    public void start(Stage homepage) throws Exception{
        stage = homepage;
        ini=true;
        connectToServer();
        System.out.println("Server Paisi Yesss");
        showMainPage();
    }

    private void connectToServer() throws IOException {
        socketWrapper = new SocketWrapper("127.0.0.1",603);
    }

    public void showGuestPage() throws Exception {

        if(ini){
            islog=true;
//            new ClientThread(socketWrapper,"any",this).start();
            ini = false;
        }


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("guestinterface.fxml"));
        Parent root = loader.load();

        MainMenuController controller = loader.getController();
        controller.setMain(this);

        stage.setTitle("Guest Window");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public void showMainPage() throws Exception {
        islog = false;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        Parent root = loader.load();

        HomeController controller = loader.getController();
        controller.setMain(this);

        stage.setTitle("Welcome");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }
    public void showLoginPgae() throws Exception {
        islog =false;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("loginWindow.fxml"));
        Parent root = loader.load();

        loginController controller = loader.getController();
        controller.setMain(this);

        stage.setTitle("Login Page");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }
    public void showSignUp() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SignUpPage.fxml"));
        Parent root = loader.load();

        SignUpController controller = loader.getController();
        controller.setMain(this);

        stage.setTitle("Sign Up Page");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public void showSearchPlayer() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchPlayers.fxml"));
        Parent root = loader.load();

        SearchPlayersController controller = loader.getController();
        controller.setMain(this);

        stage.setTitle("Search Player");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public void showSearchClub() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchClub.fxml"));
        Parent root = loader.load();

        SearchClubController controller = loader.getController();
        controller.setMain(this);

        stage.setTitle("Search Player");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public void showbyclubandcountry() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ShowClubCountry.fxml"));
        Parent root = loader.load();

        ClubCountryController controller = loader.getController();
        controller.setMain(this);
        controller.loadcol();

        stage.setTitle("Search Player");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public void showbysalary() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ShowSalary.fxml"));
        Parent root = loader.load();

        SalaryController controller = loader.getController();
        controller.setMain(this);
        controller.loadcol();

        stage.setTitle("Search Player");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }
    public void showbyposition() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ShowPosition.fxml"));
        Parent root = loader.load();

        PositionController controller = loader.getController();
        controller.setMain(this);
        controller.loadcol();

        stage.setTitle("Search Player");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }
    public void showbyname() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchPlayerbyName.fxml"));
        Parent root = loader.load();

        SearchPlayerbyNameController controller = loader.getController();
        controller.setMain(this);
        controller.loadcol();


        stage.setTitle("Search Player");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public void showcountrywise() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CountryWise.fxml"));
        Parent root = loader.load();

        CountrywiseController controller = loader.getController();
        controller.setMain(this);
        controller.show_table();

        stage.setTitle("Details");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public void showMaxSalaryClub() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MaxSalaryClub.fxml"));
        Parent root = loader.load();

        MaxSalaryControl controller = loader.getController();
        controller.setMain(this);
        controller.loadcol();

        stage.setTitle("Search Club");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public void showMaxAgeClub() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MaxAgeClub.fxml"));
        Parent root = loader.load();

        MaxAgeControl controller = loader.getController();
        controller.setMain(this);
        controller.loadcol();

        stage.setTitle("Search Club");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public void showMaxHeightClub() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MaxHeightClub.fxml"));
        Parent root = loader.load();

        MaxHeightControl controller = loader.getController();
        controller.setMain(this);
        controller.loadcol();

        stage.setTitle("Search Club");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public void totalyearlysalary() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("totalyearlysalary.fxml"));
        Parent root = loader.load();

        TotalYearlyController controller = loader.getController();
        controller.setMain(this);


        stage.setTitle("Search Club");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public void showaddPlayer() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addPlayer.fxml"));
        Parent root = loader.load();

        addPlayerController controller = loader.getController();
        controller.setMain(this);

        stage.setTitle("Add Player");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }
    public static String capitalizeString(String str) {

        String[] words = str.split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return result.toString().trim();
    }
    public void showclubWindow(String name) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("clubWindow.fxml"));
        Parent root = loader.load();

        ClubWindowController controller = loader.getController();
        controller.clubName = name;
        controller.setMain(this);
        controller.head.setText(capitalizeString(name));
        controller.loadcol();
        controller.startScheduler();


        stage.setTitle(name);
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public void showBuyPlayer(String name) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("buyPlayerWindow.fxml"));
        Parent root = loader.load();

        BuyPlayerController controller = loader.getController();
        controller.setMain(this);
        controller.clubName = name;
        controller.loadcol();
        controller.startScheduler();

        stage.setTitle(name);
        stage.setScene(new Scene(root, 800, 600));
        stage.show();

    }

    public SocketWrapper getSocketWrapper() throws Exception {
        return socketWrapper;
    }

    public Client getClient() throws Exception {
        return this;
    }


    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }
    public void addsuc() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Player Added");
        alert.setHeaderText("Player Added Successfully");
        alert.showAndWait();
    }

    public void showAlertpass() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Password Mismatch");
        alert.setHeaderText("Pssword Mismatch");
        alert.setContentText("Type same password in both box");
        alert.showAndWait();
    }
    public void showAlertSucessSignUp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sign Up Successful");
        alert.setHeaderText("Sign Up Successful");
        alert.setContentText("Sign Up Successful");
        alert.showAndWait();
    }
    public void showAlertAdd() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sell Player");
        alert.setHeaderText("Player Added");
        alert.setContentText("Player Added to SellMarket Successfully");
        alert.showAndWait();
    }
public void showPlayerDetails(Player p) {
    Dialog<Void> dialog = new Dialog<>();
    dialog.setTitle("Player Details");

    DialogPane dialogPane = dialog.getDialogPane();
    dialogPane.getStylesheets().add(getClass().getResource("Homepage.css").toExternalForm());
    dialogPane.getStyleClass().add("player-details-dialog");

    VBox container = new VBox(20);
    container.getStyleClass().add("details-container");
    container.setPadding(new Insets(20));

    Label nameLabel = new Label(p.getName());
    nameLabel.getStyleClass().add("player-name");

    HBox badgeContainer = new HBox(10);
    badgeContainer.setAlignment(Pos.CENTER);

    Label positionBadge = new Label(p.getPosition());
    positionBadge.getStyleClass().add("position-badge");

    Label jerseyBadge = new Label("#" + (p.getNumber() != -1 ? String.valueOf(p.getNumber()) : "N/A"));
    jerseyBadge.getStyleClass().add("jersey-badge");

    badgeContainer.getChildren().addAll(positionBadge, jerseyBadge);

    GridPane detailsGrid = new GridPane();
    detailsGrid.getStyleClass().add("details-grid");
    detailsGrid.setHgap(15);
    detailsGrid.setVgap(15);

    int row = 0;
    addDetailRow(detailsGrid, row++, "🌍", "Country", p.getCountry());
    addDetailRow(detailsGrid, row++, "📅", "Age", p.getAge() + " years");
    addDetailRow(detailsGrid, row++, "📏", "Height", String.format("%.2f m", p.getHeight()));
    addDetailRow(detailsGrid, row++, "⚽", "Club", p.getClub());
    addDetailRow(detailsGrid, row++, "💰", "Weekly Salary", "$" + String.format("%,d", p.getSalary()));

    if (p.sell > 0) {
        String status = p.sell == 2 ? "Transfer Pending" : "For Sale";
        String nextClub = p.nextclb != null ? " (Next: " + p.nextclb + ")" : "";
        addDetailRow(detailsGrid, row++, "🔄", "Status", status + nextClub);
    }

    container.getChildren().addAll(nameLabel, badgeContainer, detailsGrid);

    Button closeButton = new Button("Close");
    closeButton.getStyleClass().add("close-button");
    closeButton.setOnAction(e -> dialog.close());

    dialogPane.setContent(container);
    dialogPane.getButtonTypes().add(ButtonType.CLOSE);
    Node closeButtonNode = dialogPane.lookupButton(ButtonType.CLOSE);
    closeButtonNode.setVisible(false);

    dialog.showAndWait();
}

    private void addDetailRow(GridPane grid, int row, String icon, String label, String value) {
        Label iconLabel = new Label(icon);
        iconLabel.getStyleClass().add("detail-icon");

        Label titleLabel = new Label(label + ":");
        titleLabel.getStyleClass().add("detail-title");

        Label valueLabel = new Label(value);
        valueLabel.getStyleClass().add("detail-value");

        grid.add(iconLabel, 0, row);
        grid.add(titleLabel, 1, row);
        grid.add(valueLabel, 2, row);
    }

    public void unavail() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Not Found");
        alert.setHeaderText(null);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setMinWidth(400);
        dialogPane.setMinHeight(200);
        dialogPane.setStyle(
                "-fx-background-color: linear-gradient(to bottom right,#4A90E2,#50E3C2,#9013FE);"+ "-fx-background-radius: 0;"
        );
        Label contentLabel = new Label("Information Unavailable");
        contentLabel.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-padding: 10px;" +
                        "-fx-font-weight: bold;"
        );

        dialogPane.setContent(contentLabel);

        dialogPane.getStyleClass().add("custom-alert");
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        okButton.setStyle(
                "-fx-background-color: #E74C3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 5px;" +
                        "-fx-padding: 8px 20px;"
        );

        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



