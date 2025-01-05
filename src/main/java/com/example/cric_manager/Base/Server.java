package com.example.cric_manager.Base;

import com.example.cric_manager.Core_1.CricketPlayerDatabase;
import com.example.cric_manager.Core_1.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.ServerSocket;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Server{
    public static CricketPlayerDatabase ipl;
    public static HashMap<String, String> userMap;
    public static List<Player> onMarket = new ArrayList<>();
    void load_data() throws IOException {
        BufferedReader rd = new BufferedReader(new FileReader("E:/Java Project/Cric Manager/src/main/java/com/example/cric_manager/Core_1/players.txt"));
        ipl = new CricketPlayerDatabase();
        while(true){
            String inp = rd.readLine();
            if(inp == null) break;
            String[] data = inp.split(",");
            String name,country,club,position;
            int age,salary,number;
            double height;
            name = data[0];
            country = data[1];
            age = Integer.parseInt(data[2]);
            height = Double.parseDouble(data[3]);
            club = data[4];
            position = data[5];
            if(data[6].isEmpty()){
                number = -1;
            }
            else number = Integer.parseInt(data[6]);
            salary = Integer.parseInt(data[7]);

            Player pl = new Player(name, country, age, height, club, position, number, salary);
            ipl.addPlayer(pl);
        }
        rd.close();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Login_Credentials.txt"))) {
            userMap = (HashMap<String, String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {

        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("On_Market.txt"))) {
            onMarket = (List<Player>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {

        }

//        for(Player v:ipl.players) System.out.println(v);
    }

    Server() throws Exception{
        //Getting Information From the File
        userMap = new HashMap<>();
        System.out.println("Server started");
        load_data();
        ServerSocket serverSocket = new ServerSocket(603);
        new DataUpdateThread(this).start();

        while(true){
            Socket clientSocket = serverSocket.accept();
            System.out.println("Server e lagse Yayy");
            serve(clientSocket);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
        ThreadServer mine = new ThreadServer(socketWrapper);
        mine.start();
    }
    public static void main(String[] args) throws Exception{
        System.setProperty("java.awt.headless", "true");
        new Server();
    }
}


