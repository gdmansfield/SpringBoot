package com.gdmatstaffs.rest;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

@Repository
public class VowelCountRepository
{

    public void getAllVowels(TreeMap<String, Integer> allVowels)
    {
        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM VowelCount");
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                allVowels.replace(rs.getString("Vowel"), rs.getInt("Vcount"));
            }

            closeConnection(conn);
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
        }
    }

    public void updateVowels(TreeMap<String, Integer> vowels) throws Exception
    {
        String msg = "";
        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE VowelCount SET Vcount = Vcount + ? WHERE Vowel = ?");

            for (Map.Entry<String, Integer> entry : vowels.entrySet())
            {
                stmt.setInt(1, entry.getValue());
                stmt.setString(2, entry.getKey());
                int numRowsUpdated = stmt.executeUpdate();

                if (numRowsUpdated == 0)
                {
                    throw new Exception("Vowel count not updated for " + entry.getKey());
                }
            }

            closeConnection(conn);
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
            throw new Exception("Vowel count not updated", e);
        }
    }

    private Connection getConnection() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException
    {
        String url = "jdbc:mysql://localhost:3306/springboot?user=root";

        Class.forName("com.mysql.cj.jdbc.Driver")
                .newInstance();
        return DriverManager.getConnection(url);
    }

    private void closeConnection(Connection conn) throws SQLException
    {
        conn.close();
    }
}
