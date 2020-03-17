package backend;

import java.sql.*;
public class DB_Helper
{
    private static Connection koneksi;
    public static Connection getKoneksi(){
        if (koneksi == null){
            try {
                String url = "jdbc:mysql://localhost:3306/proyek1"; // nama databasenya login
                String user="root"; // username mysql root
                String pass=""; //passwordnya kosong

                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi = DriverManager.getConnection(url , user, pass);
            } 
            catch(SQLException t){
                System.out.print("Gagal melakukan koneksi ke database");
            }
        }
    return koneksi;
    }
    
    public static ResultSet selectQuery(String query)
    {
        getKoneksi();
        ResultSet rs = null;

        try
        {
            Statement stmt = koneksi.createStatement();
            rs = stmt.executeQuery(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return rs;
    }
    
    public static int insertQueryGetId(String query)
    {
        getKoneksi();
        int num = 0;
        int result = -1;

        try
        {
            Statement stmt = koneksi.createStatement();
            num = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next())
            {
                result = rs.getInt(1);
            }

            rs.close();
            stmt.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result = -1;
        }

        return result;
    }
    public static boolean executeQuery(String query)
    {
        getKoneksi();
        boolean result = false;

        try
        {
            Statement stmt = koneksi.createStatement();
            stmt.executeUpdate(query);

            result = true;

            stmt.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
