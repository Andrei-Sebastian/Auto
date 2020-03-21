package principalPACK.clase.work;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class WorkDataBsae {
    final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    final static String URL = "jdbc:mysql://localhost:3306/";
    static String username = "root";
    static String password = "MySQL@123";
    private String dbName;
    private String tableName;
    private Connection connection;
    private Statement statement;
    public WorkDataBsae(String dbName, String tableName) {
        this.dbName = dbName;
        this.tableName = tableName;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, username, password);
            statement = connection.createStatement();
            statement.executeUpdate("USE " + this.dbName);
        } catch (ClassNotFoundException e) {
            System.out.println("error: failed to load MySQL driver.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("other error:");
            e.printStackTrace();
        }


        // execute();
    }

    public void createTable(String... args) {
        StringBuilder arg = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + "( ");
        for (String argumente : args)
            arg.append(argumente).append(", ");
        arg = new StringBuilder(arg.substring(0, arg.length() - 2) + ")");
        try {
            statement.executeUpdate(arg.toString());
        } catch (SQLException e) {

            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("other error:");
            e.printStackTrace();
        }
    }

    public void insert(String... args) {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        StringBuilder arg = new StringBuilder("INSERT INTO " + tableName + " VALUES( ");
        try {
            for (String argumente : args)
                arg.append(argumente).append(", ");
            arg = new StringBuilder(arg.substring(0, arg.length() - 2) + ")");
            statement.executeUpdate(arg.toString());

        } catch (SQLException e) {
            //System.out.println(e);
            alert.setContentText("Exista deja acest id");
            alert.showAndWait();
            e.printStackTrace();

        } catch (Exception e) {
          //  System.out.println(e);
            e.printStackTrace();
        }
    }

    public void insertA(String... args) {
        int x = args.length, i = 1;
        StringBuilder arg = new StringBuilder("INSERT INTO " + tableName + "( ");
        try {
            for (String argumente : args) {
                if (i < x / 2)
                    arg.append(argumente).append(", ");

                else
                    arg.append(argumente).append(", ");
                if (i == x / 2)
                    arg = new StringBuilder(arg.substring(0, arg.length() - 2) + ") VALUES(");

                i++;
            }
            arg = new StringBuilder(arg.substring(0, arg.length() - 2) + ")");
            statement.executeUpdate(arg.toString());
            System.out.println(arg);
        } catch (SQLException e) {
            // System.out.println(e);
            e.printStackTrace();

        } catch (Exception e) {
            //System.out.println(e);
            e.printStackTrace();
        }
    }

    public void removeID(int id) {
        try {
            statement.executeUpdate("DELETE FROM " + tableName + " WHERE id = " + id);
        } catch (SQLException e) {
            // System.out.println(e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("other error:");
            e.printStackTrace();
        }
    }

    public void createDataBase() {
        try {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
        } catch (SQLException e) {
            // System.out.println(e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("other error:");
            e.printStackTrace();
        }

    }

    public void stopConnection() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDB() {
        try {
            statement.executeUpdate("DROP DATABASE " + dbName);
        } catch (SQLException e) {
            //System.out.println(e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("other error:");
            e.printStackTrace();
        }
    }
    public void update(String command)  {
        try {
            statement.executeUpdate(command);
        }catch (Exception e)
        {e.printStackTrace();}

    }



    public WorkDataBsae(String dbName, String tableName, String... args)  {
        this.dbName = dbName;
        this.tableName = tableName;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, username, password);
            statement = connection.createStatement();
            statement.executeUpdate("USE " + this.dbName);
        } catch (ClassNotFoundException e) {
            System.out.println("error: failed to load MySQL driver.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("other error:");
            e.printStackTrace();
        }


        // execute();
    }
    public ResultSet getTable() {
        try {
            return statement.executeQuery("SELECT * FROM "+tableName);
        }catch (Exception e){}
     return null;
    }
    public ResultSet getRow(String comand) {
        try {
            return statement.executeQuery("SELECT * FROM "+tableName+" "+comand);
        }catch (Exception e){}
        return null;
    }
    public ResultSet getCommand(String comand) {
        try {
            return statement.executeQuery(comand);
        }catch (Exception e){e.printStackTrace();}
        return null;
    }
}
