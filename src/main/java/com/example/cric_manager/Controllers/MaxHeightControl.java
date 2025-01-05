package com.example.cric_manager.Controllers;

import com.example.cric_manager.Base.Client;
import com.example.cric_manager.Base.SocketWrapper;
import com.example.cric_manager.Core_1.CricketPlayerDatabase;
import com.example.cric_manager.Core_1.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

import java.util.List;

public class MaxHeightControl {
    private Client main;
    @FXML
    TableView<Player> playertable;
    ObservableList<Player> data;
    @FXML
    TextField clubname;
    public void setMain(Client main) {
        this.main = main;
    }
    public void loadcol(){
        playertable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Player, String> name = new TableColumn<>("Name");
        name.setMinWidth(20);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

//        TableColumn<Player, String> country = new TableColumn<>("Country");
//        country.setMinWidth(20);
//        country.setCellValueFactory(new PropertyValueFactory<>("country"));
//
//        TableColumn<Player, Integer> age = new TableColumn<>("Age");
//        age.setMinWidth(20);
//        age.setCellValueFactory(new PropertyValueFactory<>("age"));
//
//        TableColumn<Player, Double> height = new TableColumn<>("Height");
//        height.setMinWidth(20);
//        height.setCellValueFactory(new PropertyValueFactory<>("height"));
//
//        TableColumn<Player, String> club = new TableColumn<>("Club");
//        club.setMinWidth(20);
//        club.setCellValueFactory(new PropertyValueFactory<>("club"));
//
//        TableColumn<Player, String> position = new TableColumn<>("Position");
//        position.setMinWidth(20);
//        position.setCellValueFactory(new PropertyValueFactory<>("position"));
//
//        TableColumn<Player, Integer> jerssy = new TableColumn<>("Jersey Number");
//        jerssy.setMinWidth(20);
//        jerssy.setCellValueFactory(new PropertyValueFactory<>("number"));
//
//        TableColumn<Player, Integer> Salary = new TableColumn<>("Salary");
//        Salary.setMinWidth(20);
//        Salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

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

        playertable.getColumns().addAll(name,ac2);
        playertable.setEditable(true);
    }
    public void show_table(String clb) throws Exception {
        data= FXCollections.observableArrayList();
        //Add Products
        SocketWrapper socketWrapper = main.getSocketWrapper();
        socketWrapper.write("any");
        List<Player> out =  ((CricketPlayerDatabase)socketWrapper.read()).maxHeight(clb);
        for(Player p : out){
            data.add(p);
            System.out.println(p);
        }
//        data.add(new Player("Virat","Bal",34,45,"RCB","Batter",45,345));

//        loadcol();
        if(data.size()==0){
            main.unavail();
            return;
        }
        playertable.setItems(data);


    }

    @FXML
    public void sentclubname(ActionEvent actionEvent) throws Exception {
        String name = clubname.getText();
        System.out.println(name);
        show_table(name);
    }
    @FXML
    public void reset(ActionEvent actionEvent) {
        clubname.clear();
    }
    @FXML
    public void back(ActionEvent actionEvent) throws Exception {
        main.showSearchClub();
    }
}
