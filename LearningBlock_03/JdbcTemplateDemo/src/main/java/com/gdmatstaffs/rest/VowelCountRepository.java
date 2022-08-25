package com.gdmatstaffs.rest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.TreeMap;

@Repository
public class VowelCountRepository
{

    private final JdbcTemplate jdbcTemplate;

    public VowelCountRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void getAllVowels(TreeMap<String, Integer> allVowels)
    {
        try
        {
            SqlRowSet rs =
                    jdbcTemplate.queryForRowSet(
                            "SELECT * FROM VowelCount");

            while (rs.next())
            {
                allVowels.replace(
                        rs.getString("Vowel"),
                        rs.getInt("Vcount"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
        }
    }

    public void updateVowels(TreeMap<String, Integer> vowels) throws Exception
    {
        try
        {
            for (Map.Entry<String, Integer> entry : vowels.entrySet())
            {
                int numRowsUpdated =
                        jdbcTemplate.update(
                                "UPDATE VowelCount SET Vcount = Vcount + ? WHERE Vowel = ?",
                                entry.getValue(),
                                entry.getKey());

                if (numRowsUpdated == 0)
                {
                    throw new Exception("Vowel count not updated for " + entry.getKey());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
            throw new Exception("Vowel count not updated", e);
        }
    }
}
