import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String DB_URL = "jdbc:mysql://localhost/";

    static final String user = "Jacek";
    static final String password = "12345678";

    static void addUser(String imie, String nazwisko, String kraj, int placa, Connection conn) throws SQLException {
        String query = "insert into pracownicy(imie, nazwisko, kraj, placa) VALUES(?, ?, ?, ?);";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, imie);
        stmt.setString(2, nazwisko);
        stmt.setString(3, kraj);
        stmt.setString(4, Integer.toString(placa));
        stmt.executeUpdate();
    }

    static void showTable(Connection conn, int colId) throws SQLException {
        String query = "select * from pracownicy order by " + colId + ";";
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnsNumber = resultSetMetaData.getColumnCount();
        for(int i = 1; i <= columnsNumber; i++) {
            System.out.printf("%20s", resultSetMetaData.getColumnName(i));
        }
        System.out.println();
        while(resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                System.out.printf("%20s", resultSet.getString(i));
            }
            System.out.println();
            for(int i = 0; i < columnsNumber; i++) {
                for(int j = 0; j < 20; j++)
                    System.out.print('-');
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // register driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //database connection
            System.out.println("Connecting to database");
            conn = DriverManager.getConnection(DB_URL, user, password);
            stmt = conn.createStatement();
            String dropDatabase = "drop database if exists java;";
            stmt.executeUpdate(dropDatabase);
            System.out.println("Database dropped");
            String createDatabase = "create database if not exists java;";
            stmt.executeUpdate(createDatabase);
            System.out.println("Database created");
            stmt.executeUpdate("use java;");
            String sb = "create table if not exists Pracownicy (\n" +
                    "id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "imie varchar(20),\n" +
                    "nazwisko varchar(20),\n" +
                    "kraj varchar(20),\n" +
                    "placa int);";
            stmt.executeUpdate(sb);
            Scanner scn = new Scanner(System.in);
            boolean check = true;
            while(check) {
                System.out.println("Co chcesz zrobić?\n1. Dodaj pracownika\n2.Wyświetl tabelę\n3.Wypisz średnie płace\n");
                int x = Integer.parseInt(scn.nextLine());
                switch (x) {
                    case 1:
                        System.out.print("Podaj imie: ");
                        String imie = scn.nextLine();
                        System.out.print("Podaj nazwisko: ");
                        String nazwisko = scn.nextLine();
                        System.out.print("Podaj kraj: ");
                        String kraj = scn.nextLine();
                        System.out.println("Podaj place: ");
                        int placa = Integer.parseInt(scn.nextLine());
                        addUser(imie, nazwisko, kraj, placa, conn);
                        break;
                    case 2:
                        showTable(conn, 2);
                        check = false;
                        break;
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
