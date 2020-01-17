import java.sql.*;

final class MySQLManager {
    private MySQLManager() {}

    private static void executeQuery(String query, Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rSet = stmt.executeQuery(query);
        printQuery(rSet);
        rSet.close();
    }

    private static void printQuery(ResultSet rSet) throws SQLException {
        ResultSetMetaData rSetMeta = rSet.getMetaData();
        int columnsNumber = rSetMeta.getColumnCount();
        for(int i = 1; i <= columnsNumber; i++) {
            System.out.printf("%20s", rSetMeta.getColumnName(i));
        }
        System.out.println();
        for(int i = 0; i < columnsNumber * 20; i++) {
            System.out.print('-');
        }
        System.out.println();
        while(rSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                System.out.printf("%20s", rSet.getString(i));
            }
            System.out.println();
            for(int i = 0; i < columnsNumber * 20; i++) {
                System.out.print('-');
            }
            System.out.println();
        }
    }

    static void addUser(String imie, String nazwisko, String kraj, int placa, Connection conn) throws SQLException {
        String query = "insert into pracownicy(imie, nazwisko, kraj, placa) VALUES(?, ?, ?, ?);";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, imie);
        stmt.setString(2, nazwisko);
        stmt.setString(3, kraj);
        stmt.setString(4, Integer.toString(placa));
        stmt.executeUpdate();
        stmt.close();
    }

    static void showTable(Connection conn, int columnId) {
        String query = "select * from pracownicy order by " + columnId + ";";
        try {
            executeQuery(query, conn);
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }

    static void showAvgSalary(Connection conn) throws SQLException {
        String query = "select avg(placa) as srednia_placa, kraj from pracownicy group by kraj;";
        executeQuery(query, conn);
    }
}
