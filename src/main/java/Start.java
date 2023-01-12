import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Start {

    private static final String DBURL = "jdbc:mysql://localhost:3306/newdb";
    private static final String USER = "developer";
    private static final String PASSWORD = "passwordhere";

    public static void main(String[] args) {
        Connection connection = null;
        try {

            // create a connection to the database
            connection = DriverManager.getConnection(DBURL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            String createQuery = """
                    CREATE TABLE IF NOT EXISTS students
                    ( student_id INT(10) NOT NULL AUTO_INCREMENT,
                      last_name VARCHAR(30),
                      first_name VARCHAR(30),
                      CONSTRAINT students_pk PRIMARY KEY (student_id)
                    );
                    """;
            statement.executeUpdate(createQuery);
            System.out.println("Student table has been created");

            statement.executeUpdate("INSERT INTO students (last_name, first_name) VALUES ('Verdi', 'Marco')");
            statement.executeUpdate("INSERT INTO students (last_name, first_name) VALUES ('Rossi', 'Mario')");
            statement.executeUpdate("INSERT INTO students (last_name, first_name) VALUES ('Bianchi', 'Luca')");
            statement.executeUpdate("INSERT INTO students (last_name, first_name) VALUES ('Neri', 'Gabriele')");
            System.out.println("Students have been created");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
