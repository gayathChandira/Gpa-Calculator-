package gpacalculator;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBOperations {

    String url = "jdbc:mysql://localhost:3306/gpaCalculator";
    String username = "root";
    String password = "";
    Connection con = null;
    PreparedStatement pst = null;

    boolean addCourseDetails(CourseDetails cd) {
        try {
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO courses VALUES (?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);

            pst.setString(1, cd.getCourseID());
            pst.setString(2, cd.getCourseName());
            pst.setInt(3, cd.getCredits());

            pst.executeUpdate();

            return true;
        } catch (SQLException ex) {
            //Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return false;

        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }

        }
    }
}
