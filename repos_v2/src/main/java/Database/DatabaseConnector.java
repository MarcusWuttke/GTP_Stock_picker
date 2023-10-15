package Database;

import ChatGpt.TextScore;
import Forum.Post;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.sql.*;
import java.time.LocalDate;

public class DatabaseConnector {

    /**
     * create table post (
     *     ID varchar(255),
     *     TICKER varchar(55),
     *     POST_DATE date,
     *     TEXT varchar(10000),
     *     RELEVANCE_SCORE integer,
     *     POSITIVITY_SCORE integer
     *                   );
     *
     * ALTER TABLE post ADD CONSTRAINT unique_id UNIQUE (ID);
     */

    public DatabaseConnector(){

    }

    public void testSave(){
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/stockpicker_db", "sd", "sd")) {
            // SQL statement with placeholders for data
            String ID = "123";
            String Ticker = "abc";
            LocalDate postDate = LocalDate.now();
            String text = "Test text";
            int relevanceScore = 4;
            int positivityScore = 7;

            String insertQuery = "INSERT INTO stockpicker_schema.post (id, ticker, post_date, text, relevance_score, positivity_score) VALUES (?, ?, ?, ?, null, null)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                // Set the values for the placeholders
                preparedStatement.setString(1, ID);
                preparedStatement.setString(2, Ticker);
                preparedStatement.setDate(3, Date.valueOf(postDate));
                preparedStatement.setString(4, text);
                preparedStatement.setInt(5, relevanceScore);
                preparedStatement.setInt(6, positivityScore);

                // Execute the statement
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) inserted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveSinglePost(Post post, String ticker) throws Exception {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/stockpicker_db", "sd", "sd")) {
            // SQL statement with placeholders for data
            String ID = post.getId();
            String Ticker = ticker;
            LocalDate postDate = post.getPostTime();
            String text = post.getPostContent();
            String analyzed = "NO";


            String insertQuery = "INSERT INTO stockpicker_schema.post (id, ticker, post_date, text, relevance_score, positivity_score, analyzed) VALUES (?, ?, ?, ?, null, null, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                // Set the values for the placeholders
                preparedStatement.setString(1, ID);
                preparedStatement.setString(2, Ticker);
                preparedStatement.setDate(3, Date.valueOf(postDate));
                preparedStatement.setString(4, text);
                preparedStatement.setString(5, analyzed);

                // Execute the statement
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) inserted successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Saving to database failed because of: ");
            e.printStackTrace();
            throw e;
        }
    }

    public String getTextFromPostId(String postId) throws Exception{
        String text = new String();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/stockpicker_db", "sd", "sd")) {
            String getQuery = "SELECT text FROM stockpicker_schema.post WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(getQuery)) {
                preparedStatement.setString(1, postId);
                // Execute the statement
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    text = resultSet.getString("text"); // Replace 'column_name' with the actual column name in the table
                }
            }
        } catch (SQLException e) {
            System.out.println("Getting text from post failed because of: ");
            e.printStackTrace();
            throw e;
        }
        return text;
    }

    public void updateScoreOnPost(TextScore textScore){
        //TODO
    }

}
