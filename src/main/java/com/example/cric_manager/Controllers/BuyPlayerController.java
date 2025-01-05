package com.example.cric_manager.Controllers;

import com.example.cric_manager.Base.Client;
import com.example.cric_manager.Base.Server;
import com.example.cric_manager.Base.SocketWrapper;
import com.example.cric_manager.Core_1.Player;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BuyPlayerController {

    @FXML
    public TableView<Player> tablebuy;

    private Client main;
    public String clubName;


    public void setMain(Client main) {
        this.main = main;
    }
    ObservableList<Player> data;
    public void loadcol() throws Exception {
        tablebuy.getItems().clear();
        tablebuy.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Player, String> name = new TableColumn<>("Name");
//        name.setMinWidth(20);
        name.setPrefWidth(45);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

//        TableColumn<Player, String> country = new TableColumn<>("Country");
////        country.setMinWidth(20);
//        country.setPrefWidth(30);
//        country.setCellValueFactory(new PropertyValueFactory<>("country"));
//
//        TableColumn<Player, Integer> age = new TableColumn<>("Age");
////        age.setMinWidth(10);
//        age.setPrefWidth(20);
//        age.setCellValueFactory(new PropertyValueFactory<>("age"));
//
//        TableColumn<Player, Double> height = new TableColumn<>("Height");
////        height.setMinWidth(10);
//        height.setPrefWidth(20);
//        height.setCellValueFactory(new PropertyValueFactory<>("height"));
//
//        TableColumn<Player, String> club = new TableColumn<>("Club");
////        club.setMinWidth(20);
//        club.setPrefWidth(40);
//        club.setCellValueFactory(new PropertyValueFactory<>("club"));
//
//        TableColumn<Player, String> position = new TableColumn<>("Position");
////        position.setMinWidth(20);
//        position.setPrefWidth(28);
//        position.setCellValueFactory(new PropertyValueFactory<>("position"));

//        TableColumn<Player, Integer> jerssy = new TableColumn<>("Jersey Number");
//        jerssy.setMinWidth(5);
//        jerssy.setCellValueFactory(new PropertyValueFactory<>("number"));
//
//        TableColumn<Player, Integer> Salary = new TableColumn<>("Salary");
//        Salary.setMinWidth(10);
//        Salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        // Button Column
        TableColumn<Player, Void> actionCol = new TableColumn<>("Buy");
//        actionCol.setMinWidth(50);
        actionCol.setPrefWidth(20);
        actionCol.setCellFactory(param -> new TableCell<>() {
            private final Button buyButton = new Button("Buy");

            {
                buyButton.getStyleClass().add("button-cell");
                buyButton.setOnAction(event -> {
                    Player player = getTableView().getItems().get(getIndex());
                    player.sell=2;
                    player.nextclb=clubName;
                    //Remove From Market
                    try {
                        SocketWrapper socketWrapper = main.getSocketWrapper();
                        socketWrapper.write(player);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    //Update Player DataBase
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(buyButton);
                }
            }
        });


        TableColumn<Player, Void> ac2 = new TableColumn<>("Details");
        ac2.setMinWidth(20);
        ac2.setCellFactory(param -> new TableCell<>() {
            private final Button det = new Button("Details");
            {
                det.getStyleClass().add("bc2");
                det.setOnAction(event -> {
                    Player player = getTableView().getItems().get(getIndex());
                    main.showPlayerDetails(player);
                });
                StackPane stackPane = new StackPane(det);
                stackPane.setAlignment(Pos.CENTER);
                setGraphic(stackPane);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(det);
                }
            }
        });

        tablebuy.setRowFactory(tv -> {
            TableRow<Player> row = new TableRow<>();
            row.setStyle("-fx-pref-height: 40px;"); // Set the preferred height for rows
            return row;
        });

//        tablebuy.getColumns().addAll(name,country,age,height,club,position,actionCol);
        tablebuy.getColumns().addAll(name,actionCol,ac2);
        load();

    }

    void load(){
        Platform.runLater(() -> {
            data= FXCollections.observableArrayList();

            SocketWrapper socketWrapper = null;
            try {
                socketWrapper = main.getSocketWrapper();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                socketWrapper.write((String)"Buy_Sell");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            List<Player> tp = null;
            try {
                tp = (List<Player>)socketWrapper.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            List<Player> pt = new ArrayList<>();
            for(Player p : tp) {
                if(!p.getClub().toLowerCase().equals(clubName.toLowerCase())) {
                    pt.add(p);
                }
            }


            data.addAll(pt);
            tablebuy.setEditable(true);
            tablebuy.setItems(data);
            tablebuy.refresh();
        });
    }

    ScheduledExecutorService scheduler;
    public void startScheduler() {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::load, 0, 1, TimeUnit.SECONDS);
    }

    public void stopScheduler() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }


    @FXML
    public void back(ActionEvent actionEvent) throws Exception {
        stopScheduler();
        main.islog = false;
        main.showclubWindow(clubName);
    }
}
