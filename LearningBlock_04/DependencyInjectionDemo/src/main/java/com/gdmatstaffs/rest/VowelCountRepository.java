package com.gdmatstaffs.rest;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.TreeMap;

@Repository
@AllArgsConstructor
public class VowelCountRepository
{

    private final ApplicationContext context;
    private final JdbcTemplate jdbcTemplate;

    public TreeMap<String, Integer> getAllVowels()
    {
        TreeMap<String, Integer> allVowels = (TreeMap<String, Integer>)context.getBean("vowelMap");

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

        return allVowels;
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
