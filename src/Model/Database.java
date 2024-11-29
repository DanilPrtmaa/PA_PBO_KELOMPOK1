/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.*;
/**
 *
 * @author Danil Pratama
 */
public class Database {
    // Public properties untuk akses dari class lain
    public static Connection connection = null;
    public static PreparedStatement preparedStatement = null;
    public static ResultSet resultSet = null;

    // Konfigurasi database
    private final static String DB_HOST = "localhost";
    private final static String DB_NAME = "datadesa";
    private final static String DB_USERNAME = "root";
    private final static String DB_PASSWORD = "";

    // Constructor untuk memuat driver JDBC
    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver MySQL berhasil dimuat!");
        } catch (ClassNotFoundException e) {
            System.err.println("Gagal memuat driver MySQL!");
            e.printStackTrace();
        }
    }

    // Metode untuk membuat koneksi ke database
    public static Connection connect() {
        if (connection == null) {
            try {
                String url = "jdbc:mysql://" + DB_HOST + "/" + DB_NAME + "?useSSL=false";
                connection = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
                System.out.println("Koneksi ke database berhasil!");
            } catch (SQLException e) {
                System.err.println("Gagal terhubung ke database!");
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Metode untuk memutus koneksi dari database
    public static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Koneksi ke database berhasil ditutup!");
            } catch (SQLException e) {
                System.err.println("Gagal menutup koneksi database!");
                e.printStackTrace();
            }
        }
    }

    // Metode untuk mendapatkan PreparedStatement
    public static PreparedStatement getPreparedStatement(String query) throws SQLException {
        if (connection == null) {
            connect();
        }
        preparedStatement = connection.prepareStatement(query);
        return preparedStatement;
    }

    // Metode untuk mengeksekusi query SELECT
    public static ResultSet executeQuery(String query) throws SQLException {
        if (connection == null) {
            connect();
        }
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        return resultSet;
    }

    // Metode untuk mengeksekusi query INSERT, UPDATE, DELETE
    public static int executeUpdate(String query) throws SQLException {
        if (connection == null) {
            connect();
        }
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }

    // Menutup resources PreparedStatement dan ResultSet
    public static void closeResources() {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            System.out.println("Resources berhasil ditutup!");
        } catch (SQLException e) {
            System.err.println("Gagal menutup resources!");
            e.printStackTrace();
        }
    }
}