import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost/";

    private static final String user = "Jacek";
    private static final String password = "12345678";

    public static void main(String[] args) throws IOException, InterruptedException {
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
            File f = new File("dane.txt");
            Scanner fileReader = new Scanner(f);
            while (fileReader.hasNextLine()) {
                String[] array = fileReader.nextLine().split(" ");
                MySQLManager.addUser(array[0], array[1], array[2], Integer.parseInt(array[3]), conn);
            }
            fileReader.close();
            Scanner scn = new Scanner(System.in);
            boolean check = true;
            while (check) {
                System.out.print("Co chcesz zrobić?\n1. Dodaj pracownika\n2. Wyświetl tabelę\n3. Wypisz średnie płace\n0. Zakończ\n> ");
                int x = Integer.parseInt(scn.nextLine());
                switch (x) {
                    case 1:
                        System.out.print("Podaj imie:\n> ");
                        String imie = scn.nextLine();
                        System.out.print("Podaj nazwisko:\n> ");
                        String nazwisko = scn.nextLine();
                        System.out.print("Podaj kraj:\n> ");
                        String kraj = scn.nextLine();
                        System.out.print("Podaj place:\n> ");
                        try {
                            int placa = Integer.parseInt(scn.nextLine());
                            MySQLManager.addUser(imie, nazwisko, kraj, placa, conn);
                        } catch (NumberFormatException e) {
                            System.out.println("--Błędnie podana płaca--");
                            break;
                        }
                        break;
                    case 2:
                        int columnID;
                        System.out.print("Podaj id kolumny (1 - id, 2 - imie, 3 - nazwisko, 4 - kraj, 5 - placa):\n> ");
                        columnID = Integer.parseInt(scn.nextLine());
                        MySQLManager.showTable(conn, columnID);
                        break;
                    case 3:
                        MySQLManager.showAvgSalary(conn);
                        break;
                    case 0:
                        check = false;
                        stmt.close();
                        conn.close();
                        break;
                    default:
                        break;
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
