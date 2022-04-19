package newproject;

import java.sql.*;

public class databaseCon {
        
        public Connection databaseLink;
        
        public Connection getConnection(){
            String databaseName= "mydb";
            String databaseUser= "root";
            String databasePassword = "FuckYouHacker:3xD";
            String url = "jdbc:mysql://localhost/"+databaseName;
            
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
            } 
            catch(ClassNotFoundException | SQLException e){
            }
            
            return databaseLink;
        }
        public static void main(String args[]) {
        // TODO code application logic here
    }
}
