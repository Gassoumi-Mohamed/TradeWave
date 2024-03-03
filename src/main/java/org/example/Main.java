package org.example;

import Models.User;
import Services.UserService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        //MyDataBase d = MyDataBase.getInstance();


        UserService us=new UserService();
        /*
        try {

            us.ajouter(new User(121212,"aaa","bbbb",33344554,"Tunis","email","admin"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            us.modifier(new User(1,333333,"Saa","CCcb",33344511,"Ariena","outlook","user"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
*/
        try {
                us.supprimer(6);
                } catch (SQLException e) {
                System.out.println(e.getMessage());
        }

/*
        try {
                System.out.println(us.recuperer());
                } catch (SQLException e) {
                System.out.println(e.getMessage());
        }
*/
    }
}