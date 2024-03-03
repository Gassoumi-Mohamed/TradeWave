package Utils;

import java.sql.*;

public class MyDataBase {
    Connection connection;
    private static MyDataBase instance;
    final String URL = "jdbc:mysql://localhost:3306/user_database";
    final String USER="root";
    final String PASSWD="";

    private MyDataBase(){
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWD);
            System.out.println("connected");
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
    }

    public static MyDataBase getInstance(){
        if(instance == null)
            instance=new MyDataBase();
            return instance;
    }
    public Connection gtConnection(){
        return connection;
    }

}
