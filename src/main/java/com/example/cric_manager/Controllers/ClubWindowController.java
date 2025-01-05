package com.example.cric_manager.Controllers;

import com.example.cric_manager.Base.Client;
import com.example.cric_manager.Base.Server;
import com.example.cric_manager.Base.SocketWrapper;
import com.example.cric_manager.Core_1.CricketPlayerDatabase;
import com.example.cric_manager.Core_1.Player;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClubWindowController {
    @FXML
    public Text head;
    @FXML
    public TableView<Player> tablemine;
    ObservableList<Player> data;
    private Client main;
    public String clubName;
    @FXML
    Text sala;

    public void setMain(Client main) {
        this.main = main;
    }

    public void loadcol() throws InterruptedException {
        tablemine.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tablemine.getItems().clear();
        TableColumn<Player, String> name = new TableColumn<>("Name");
        name.setMinWidth(25);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

//        TableColumn<Player, String> country = new TableColumn<>("Country");
//        country.setMinWidth(20);
//        country.setCellValueFactory(new PropertyValueFactory<>("country"));
//
//        TableColumn<Player, Integer> age = new TableColumn<>("Age");
//        age.setMinWidth(5);
//        age.setCellValueFactory(new PropertyValueFactory<>("age"));
//
//        TableColumn<Player, Double> height = new TableColumn<>("Height");
//        height.setMinWidth(5);
//        height.setCellValueFactory(new PropertyValueFactory<>("height"));

//        TableColumn<Player, String> club = new TableColumn<>("Club");
//        club.setMinWidth(20);
//        club.setCellValueFactory(new PropertyValueFactory<>("club"));

//        TableColumn<Player, String> position = new TableColumn<>("Position");
//        position.setMinWidth(20);
//        position.setCellValueFactory(new PropertyValueFactory<>("position"));
//
//        TableColumn<Player, Integer> jerssy = new TableColumn<>("Jersey Number");
//        jerssy.setMinWidth(5);
//        jerssy.setCellValueFactory(new PropertyValueFactory<>("number"));
//
//        TableColumn<Player, Integer> Salary = new TableColumn<>("Salary");
//        Salary.setMinWidth(10);
//        Salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        // Button Column
        TableColumn<Player, Void> actionCol = new TableColumn<>("Sell");
        actionCol.setMinWidth(20);
        actionCol.setCellFactory(param -> new TableCell<>() {
            private final Button sellButton = new Button("Sell");
            {
                sellButton.getStyleClass().add("button-cell");
                sellButton.setOnAction(event -> {
                    Player player = getTableView().getItems().get(getIndex());
                    try {
                        SocketWrapper socketWrapper = main.getSocketWrapper();
                        player.sell = 1;
                        socketWrapper.write(player);
                        main.showAlertAdd();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }


                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(sellButton);
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


//        tablemine.getColumns().addAll(name,country,age,height,position,jerssy,Salary,actionCol,ac2);
        tablemine.getColumns().addAll(name,actionCol,ac2);
        load();
    }

    void load(){
        Platform.runLater(() -> {

            data= FXCollections.observableArrayList();
            CricketPlayerDatabase l;
            try {
                SocketWrapper socketWrapper = main.getSocketWrapper();
                socketWrapper.write(clubName);
//                Thread.sleep(1000);
                l = (CricketPlayerDatabase) socketWrapper.read();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            sala.setText("Total Yearly Salary : $" + l.totalYearlySalary(clubName));
            data.addAll(l.players);
            tablemine.setEditable(true);
            tablemine.setItems(data);
            tablemine.refresh();
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
    public void buyplayer() throws Exception {
        stopScheduler();
        main.showBuyPlayer(clubName);
    }

    @FXML
    public void back(ActionEvent actionEvent) throws Exception {
        main.islog = false;
        stopScheduler();
        main.showLoginPgae();
    }

    public void maxsalplayer(ActionEvent actionEvent) {
        CricketPlayerDatabase l;
        try {
            SocketWrapper socketWrapper = main.getSocketWrapper();
            socketWrapper.write(clubName);
            l = (CricketPlayerDatabase) socketWrapper.read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Player p=l.maxSalary(clubName).getFirst();
        main.showPlayerDetails(p);
    }

    public void maxheiplayer(ActionEvent actionEvent) {
        CricketPlayerDatabase l;
        try {
            SocketWrapper socketWrapper = main.getSocketWrapper();
            socketWrapper.write(clubName);
            l = (CricketPlayerDatabase) socketWrapper.read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Player p=l.maxHeight(clubName).getFirst();
        main.showPlayerDetails(p);
    }

    public void maxageplayer(ActionEvent actionEvent) {
        CricketPlayerDatabase l;
        try {
            SocketWrapper socketWrapper = main.getSocketWrapper();
            socketWrapper.write(clubName);
            l = (CricketPlayerDatabase) socketWrapper.read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Player p=l.maxAge(clubName).getFirst();
        main.showPlayerDetails(p);
    }
}
