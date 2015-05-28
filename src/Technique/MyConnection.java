package Technique;

import DAO.UtilisateurDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyConnection {

 private static Connection instance;
    
    
    private MyConnection()

    {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url="jdbc:mysql://localhost:3306/agenceimmob";
            String login="root";
            String pwd="root";

            instance = DriverManager.getConnection(url, login, pwd);
            if (instance != null) {
                System.out.println("Connected to the database");
            }

            }catch(SQLException e)
            {
               Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, e);  
            } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static Connection getInstance()
       { 
           if (instance==null)
                new MyConnection();
        
            return instance;
        }
}