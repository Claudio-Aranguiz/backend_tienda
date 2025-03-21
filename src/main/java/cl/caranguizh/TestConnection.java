package cl.caranguizh;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase de prueba para verificar la conexión a la base de datos MySQL.
 * Establece una conexión con los parámetros definidos y la cierra automáticamente.
 * Esta clase es solo para fines de prueba y no debe usarse en producción.
 */
public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/tienda_db?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "3D,s@MdB(HL.cl";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexión exitosa!");
            System.out.println("Información de la BD: " + conn.getMetaData().getDatabaseProductName() + " " 
                + conn.getMetaData().getDatabaseProductVersion());
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
}