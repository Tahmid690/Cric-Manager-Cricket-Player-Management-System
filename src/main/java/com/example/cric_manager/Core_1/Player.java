package com.example.cric_manager.Core_1;



import java.io.Serial;
import java.io.Serializable;

public class Player implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name,country,club,position;
    private int age,salary,number;
    private double height;
    public int sell = 0;
    public String nextclb;
    public Player(String name, String country, int age, double height, String club, String position, int number, int salary){
        this.name = name;
        this.country = country;
        this.age = age;
        this.height = height;
        this.club = club;
        this.position = position;
        this.number = number;
        this.salary = salary;

    }
    Player(){
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getClub() {
        return club;
    }
    public void setClub(String club) {
        this.club = club;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public String toString(){
        if(number!=-1) return "Name : " + name + "\n" + "Country : " + country + "\n" + "Age : " + age + "\n" + "Height : " + height + "\n" + "Club : " + club + "\n" + "Position : " + position + "\n" + "Number : " + number + "\n" + "Weekly Salary : " + salary + "\n" ;
        else return "Name : " + name + "\n" + "Country : " + country + "\n" + "Age : " + age + "\n" + "Height : " + height + "\n" + "Club : " + club + "\n" + "Position : " + position + "\n" + "Number : " + "Not Found " + "\n" + "Weekly Salary : " + salary + "\n" ;
    }

    public String writer(){
        if(number!=-1) return name + "," + country + "," + age + ","  + height + ","  + club + ","  + position + "," + number + "," + salary ;
        return name + "," + country + "," + age + ","  + height + ","  + club + ","  + position + "," + "," + salary ;
    }




}
