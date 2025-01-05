package com.example.cric_manager.Core_1;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;




public class Main{
    public static void main(String[] args) throws Exception {
        CricketPlayerDatabase ipl = new CricketPlayerDatabase();
        Scanner in = new Scanner(System.in);

        BufferedReader rd = new BufferedReader(new FileReader("players.txt"));

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

        int x,flg;
        while(true){
            System.out.println("Main Menu:");
            System.out.println("(1) Search Players");
            System.out.println("(2) Search Clubs");
            System.out.println("(3) Add Player");
            System.out.println("(4) Exit System");
            List<Player> p;
            x = in.nextInt();
            in.nextLine();
            switch(x){
                case 1:
                    flg=0;
                    while(true){
                        if(flg == 1) break;
                        System.out.println("Player Searching Options:");
                        System.out.println("(1) By Player Name");
                        System.out.println("(2) By Club and Country");
                        System.out.println("(3) By Position");
                        System.out.println("(4) By Salary Range");
                        System.out.println("(5) Country-wise player count");
                        System.out.println("(6) Back to Main Menu");
                        x = in.nextInt();
                        in.nextLine();

                        switch (x) {
                            case 1:
                                System.out.println("Input a Player Name");
                                String s = in.nextLine();
                                p=ipl.searchName(s);
                                if(p.size()==0) System.out.println("No such player with this name");
                                else ipl.displayList(p);
                                break;
                            case 2:
                                System.out.println("Input the Country Name");
                                String country = in.nextLine();
                                System.out.println("Input the Club Name");
                                String club = in.nextLine();
                                p=ipl.searchClub(country,club);;
                                if(p.size()==0) System.out.println("No such player with this country and club");
                                else ipl.displayList(p);
                                break;
                            case 3:
                                System.out.println("Input a Position Name");
                                String pos = in.nextLine();
                                p=ipl.searchPos(pos);;
                                if(p.size()==0) System.out.println("No such player with this position");
                                else ipl.displayList(p);
                                break;
                            case 4:
                                System.out.println("Input Salary Upper Bound");
                                int high = in.nextInt();
                                in.nextLine();
                                System.out.println("Input Salary Lower Bound");
                                int lo = in.nextInt();
                                in.nextLine();
                                p=ipl.SalaryRange(lo, high);
                                if(p.size()==0) System.out.println("No such player with this weekly salary range");
                                else ipl.displayList(p);
                                break;
                            case 5:
                                HashMap<String,Integer> r=ipl.country_wise_count();
                                ipl.displayList(r);
                                break;
                            case 6:
                                flg=1;
                                break;
                            default:
                                System.out.println("Invalid option!");
                                break;
                        }
                    }
                    break;
                case 2:
                    flg=0;
                    while(true){
                        if(flg == 1) break;
                        System.out.println("Club Searching Options:");
                        System.out.println("(1) Player(s) with the maximum salary of a club");
                        System.out.println("(2) Player(s) with the maximum age of a club");
                        System.out.println("(3) Player(s) with the maximum height of a club");
                        System.out.println("(4) Total yearly salary of a club");
                        System.out.println("(5) Back to Main Menu");
                        x = in.nextInt();
                        in.nextLine();
                        String club;
                        switch (x) {
                            case 1:
                                System.out.println("Input the Club Name");
                                club = in.nextLine();
                                p=ipl.maxSalary(club);
                                if(p.size()==0) System.out.println("No such club with this name");
                                else ipl.displayList(p);
                                break;
                            case 2:
                                System.out.println("Input the Club Name");
                                club = in.nextLine();
                                p=ipl.maxAge(club);
                                if(p.size()==0) System.out.println("No such club with this name");
                                else ipl.displayList(p);

                                break;
                            case 3:
                                System.out.println("Input the Club Name");
                                club = in.nextLine();
                                p=ipl.maxHeight(club);
                                if(p.size()==0) System.out.println("No such club with this name");
                                else ipl.displayList(p);
                                break;
                            case 4:
                                System.out.println("Input the Club Name");
                                club = in.nextLine();
                                int tot=ipl.totalYearlySalary(club);
                                if(tot == 0) System.out.println("No such club with this name");
                                else System.out.println("Total Yearly Salary:" + tot);
                                break;
                            case 5:
                                flg=1;
                                break;
                            default:
                                System.out.println("Invalid option!");
                                break;
                        }
                    }
                    break;
                case 3:
                    String name,country,club,position;
                    int age,salary,number;
                    double height;
                    System.out.println("Enter name");
                    name = in.nextLine();
                    System.out.println("Enter country");
                    country = in.nextLine();
                    System.out.println("Enter age");
                    age = in.nextInt();
                    in.nextLine();
                    System.out.println("Enter height");
                    height = in.nextDouble();
                    in.nextLine();
                    System.out.println("Enter club");
                    club = in.nextLine();
                    System.out.println("Enter position");
                    position = in.nextLine();
                    System.out.println("Enter number");
                    number = in.nextInt();
                    in.nextLine();
                    System.out.println("Enter salary");
                    salary = in.nextInt();
                    in.nextLine();
                    Player pl = new Player(name, country, age, height, club, position, number, salary);
                    ipl.addPlayer(pl);

                    break;
                case 4:
                    ipl.writeinFile();
                    in.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option!");

            }
        }



    }

}