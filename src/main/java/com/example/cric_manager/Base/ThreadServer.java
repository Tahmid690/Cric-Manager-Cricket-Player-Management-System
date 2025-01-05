package com.example.cric_manager.Base;

import com.example.cric_manager.Core_1.CricketPlayerDatabase;
import com.example.cric_manager.Core_1.Player;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ThreadServer extends Thread {
    private final SocketWrapper socketWrapper;
//    Server server;
    ThreadServer(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
//        this.server = server;
    }
    @Override
    public void run() {
        System.out.println("Notun Thread Paisiiiiii");
        try {
            accsesser();
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void list_sender(String who) throws IOException {
        CricketPlayerDatabase sent = new CricketPlayerDatabase();
        for(Player p: Server.ipl.players){
            if(p.getClub().toLowerCase().equals(who.toLowerCase()) || who.equals("any")){
                sent.addPlayer(p);
            }
        }
        socketWrapper.write(sent);
    }

    synchronized public void accsesser() throws IOException, ClassNotFoundException, InterruptedException {
        while (true) {
            Object o = socketWrapper.read();
            if (o != null) {
                if (o instanceof String) {

                    if(((String)o).equals("Buy_Sell")){
                        System.out.println("Paisi");
                        List<Player> op = new ArrayList<>();
                        for(Player p:Server.onMarket) op.add(p);
                        socketWrapper.write(op);
                    }
                    else list_sender((String) o);
//                    System.out.println(o);
                }
                else if(o instanceof LoginDTO){
                    LoginDTO loginDTO = (LoginDTO) o;
                    if(loginDTO.sup){
                        //Add
                        Server.userMap.put(loginDTO.getUserName(),loginDTO.getPassword());
                    }
                    else{
                        //Check
                        String password = Server.userMap.get(loginDTO.getUserName());
                        loginDTO.setStatus(loginDTO.getPassword().equals(password));
                        socketWrapper.write(loginDTO);
                    }
                }
                else if(o instanceof Player){
                    if(((Player)o).sell==1){
                        boolean flag = true;
                        System.out.println((Player)o);
                        for(Player p: Server.onMarket){
                            if(p.getName().toLowerCase().equals((((Player)o).getName()).toLowerCase()) && p.getClub().toLowerCase().equals((((Player)o).getClub()).toLowerCase())){
                                flag = false;
                            }
                        }
                        for(Player p: Server.ipl.players){
                            if(p.getName().toLowerCase().equals((((Player)o).getName()).toLowerCase()) && !p.getClub().toLowerCase().equals((((Player)o).getClub()).toLowerCase())){
                                flag = false;
                            }
                        }
                        System.out.println(flag);
                        if(flag) Server.onMarket.add((Player)o);
                    }
                    else if(((Player)o).sell==2){
                        System.out.println((Player)o);
                        Server.ipl.removePlayer(((Player)o));
                        Player pl = (Player)o;
                        Server.onMarket.removeIf(player -> (player.getName().equalsIgnoreCase(pl.getName()) && player.getClub().equalsIgnoreCase(pl.getClub())));
                        ((Player)o).setClub(((Player)o).nextclb);
                        Server.ipl.addPlayer((Player)o);
                    }
                    else Server.ipl.addPlayer((Player)o);
                }
            }
        }
    }
}
