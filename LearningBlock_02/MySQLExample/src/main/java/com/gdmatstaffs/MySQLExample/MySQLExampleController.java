package com.gdmatstaffs.MySQLExample;

import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "book")
public class MySQLExampleController
{
    Connection conn;

    @GetMapping
    public String welcome()
    {
        return "Welcome to the MySQL example";
    }

    @GetMapping(path = "/all")
    public List<Book> allBooks()
    {
        List<Book> books = new ArrayList<>();

        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Book");
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                books.add(
                        new Book(
                                rs.getInt("Id"),
                                rs.getString("Title")));
            }

            closeConnection(conn);
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
        }
        return books;
    }

    @GetMapping(path = "/{id}")
    public Book getBook(@PathVariable("id") int id)
    {
        Book book = null;

        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Book WHERE Id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                 book = new Book(
                                rs.getInt("Id"),
                                rs.getString("Title"));
            }

            closeConnection(conn);
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
        }
        return book;
    }

    @PostMapping(path = "/create")
    public String createBook(@RequestBody String title)
    {
        String msg = "";
        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Book(title) VALUES (?)");
            stmt.setString(1, title);
            int numRowsInserted = stmt.executeUpdate();

            if (numRowsInserted == 0)
            {
                msg = "Book not created";
            }
            else
            {
                int id = getBookId(title, conn);
                msg = "Book created with id: " + id;
            }

            closeConnection(conn);
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
            msg = "ERROR: Book not created";
        }

        return msg;
    }

    private int getBookId(String title, Connection conn) throws SQLException
    {
        int bookId = -1;

        PreparedStatement stmt = conn.prepareStatement("SELECT id FROM Book WHERE Title = ?");
        stmt.setString(1, title);
        ResultSet rs = stmt.executeQuery();

        if (rs.next())
        {
            bookId = rs.getInt("Id");
        }

        return bookId;
    }

    @PostMapping(path = "/update")
    public String updateBook(@RequestBody Book b)
    {
        String msg = "";
        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE Book SET Title = ? WHERE Id = ?");
            stmt.setString(1, b.getTitle());
            stmt.setInt(2, b.getId());
            int numRowsUpdated = stmt.executeUpdate();

            if (numRowsUpdated == 0)
            {
                msg = "Book not updated";
            }
            else
            {
                msg = "Book updated";
            }

            closeConnection(conn);
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
            msg = "Book not updated";
        }

        return msg;
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deleteBook(@PathVariable("id") int id)
    {
        String msg = "";
        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Book WHERE Id = ?");
            stmt.setInt(1, id);
            int numRowsUpdated = stmt.executeUpdate();

            if (numRowsUpdated == 0)
            {
                msg = "Book not deleted";
            }
            else
            {
                msg = "Book deleted";
            }

            closeConnection(conn);
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
            msg = "Book not deleted";
        }

        return msg;
    }

    private Connection getConnection() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException
    {
        String url = "jdbc:mysql://localhost:3306/library?user=root";

        Class.forName("com.mysql.cj.jdbc.Driver")
                .newInstance();
        return DriverManager.getConnection(url);
    }

    private void closeConnection(Connection conn) throws SQLException
    {
        conn.close();
    }
}
