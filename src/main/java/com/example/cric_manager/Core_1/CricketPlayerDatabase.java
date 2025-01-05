package com.example.cric_manager.Core_1;

import com.example.cric_manager.Core_1.Player;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class CricketPlayerDatabase implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public List<Player> players = new ArrayList<>();
    public void addPlayer(Player pl){
        boolean flag = true;
        pl.nextclb=null;
        pl.sell=0;
        for(Player p : players){
            if(p.getName().equalsIgnoreCase(pl.getName()) && p.getClub().equalsIgnoreCase(pl.getClub())) flag = false;
        }
        if(flag) players.add(pl);
        return;
    }
    public void removePlayer(Player pl){
        players.removeIf(player -> (player.getName().equalsIgnoreCase(pl.getName()) && player.getClub().equalsIgnoreCase(pl.getClub())));
    }

    public List<Player> searchName(String name){
        List<Player> ret = new ArrayList<>();
        name = name.toLowerCase();
        for(Player pl : players){
            if(pl.getName().toLowerCase().equals(name)){
                ret.add(pl);
            }
        }
        return ret;
    }
    public List<Player> searchClub(String country,String club){
        List<Player> ret = new ArrayList<>();
        country = country.toLowerCase();
        club = club.toLowerCase();
        for(Player pl : players){
            if(pl.getCountry().toLowerCase().equals(country) && (pl.getClub().toLowerCase().equals(club) || club.equals("any"))){
                ret.add(pl);
            }
        }
        return ret;
    }
    public List<Player> searchPos(String position){
        List<Player> ret = new ArrayList<>();
        position = position.toLowerCase();
        for(Player pl : players){
            if(pl.getPosition().toLowerCase().equals(position)){
                ret.add(pl);
            }
        }
        return ret;
    }

    public List<Player> SalaryRange(int lo,int hi){
        List<Player> ret = new ArrayList<>();
        for(Player pl : players){
            if(pl.getSalary()<=hi && pl.getSalary()>=lo){
                ret.add(pl);
            }
        }
        return ret;
    }
    public HashMap<String,Integer> country_wise_count(){
        HashMap<String,Integer> cnt = new HashMap<>();
        for(Player pl : players){
            String st = pl.getCountry();
            if(cnt.containsKey(st)) cnt.put(st,cnt.get(st)+1);
            else cnt.put(st,1);
        }

        return cnt;
    }
    public List<Player> maxSalary(String club){
        club = club.toLowerCase();
        List<Player> ret = new ArrayList<>();
        int mx = -1;
        for(Player p : players){
            if(p.getSalary() > mx && p.getClub().toLowerCase().equals(club)){
                mx = p.getSalary();
            }
        }
        for(Player p : players){
            if(p.getSalary() == mx && p.getClub().toLowerCase().equals(club)){
                ret.add(p);
            }
        }
        return ret;
    }
    public List<Player> maxHeight(String club){
        club = club.toLowerCase();
        List<Player> ret = new ArrayList<>();
        double mx = -1;
        for(Player p : players){
            if(p.getHeight() > mx && p.getClub().toLowerCase().equals(club)){
                mx = p.getHeight();
            }
        }
        for(Player p : players){
            if(p.getHeight() == mx && p.getClub().toLowerCase().equals(club)){
                ret.add(p);
            }
        }
        return ret;

    }
    public List<Player> maxAge(String club){
        club = club.toLowerCase();
        List<Player> ret = new ArrayList<>();
        int mx = -1;
        for(Player p : players){
            if(p.getAge() > mx && p.getClub().toLowerCase().equals(club)){
                mx = p.getAge();
            }
        }
        for(Player p : players){
            if(p.getAge() == mx && p.getClub().toLowerCase().equals(club)){
                ret.add(p);
            }
        }
        return ret;

    }
    public int totalYearlySalary(String club){
        club = club.toLowerCase();
        int tot = 0;
        for(Player p : players){
            if(p.getClub().toLowerCase().equals(club)){
                tot += p.getSalary();
            }
        }
        return tot;
    }
    public void writeinFile() throws Exception{

        BufferedWriter wt = new BufferedWriter(new FileWriter("E:/Java Project/Cric Manager/src/main/java/com/example/cric_manager/Core_1/players.txt"));
        for(Player p : players){
            String det = p.writer();
            wt.write(det);
            wt.write("\n");
        }

        wt.close();

    }
    public void displayList(List<Player> p){
        for(Player x:p) System.out.println(x);
    }
    public void displayList(HashMap<String,Integer> cnt){
        for(String key : cnt.keySet()){
            System.out.println(key + ": " + cnt.get(key));
        }
    }
}
