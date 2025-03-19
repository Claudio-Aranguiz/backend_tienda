package cl.caranguizh;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/tienda_db?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "3D,s@MdB(HL.cl";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexión exitosa!");
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
}