package com.example.cric_manager.Base;

import com.example.cric_manager.Core_1.Player;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class DataUpdateThread extends Thread {
    Server server;
    DataUpdateThread(Server h) {
        server = h;
    }
    @Override
    public void run() {
        while(true) {
            try {
                Server.ipl.writeinFile();
                writeHashMapToFile(Server.userMap,"Login_Credentials.txt");
                writeArrayList(Server.onMarket,"On_Market.txt");

//                for(Player p:Server.onMarket) System.out.println(p.getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void writeHashMapToFile(HashMap<String, String> map, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(map);
        } catch (IOException e) {
        }
    }
    public static void writeArrayList(List<Player> lt, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(lt);
        } catch (IOException e) {
        }
    }

}
