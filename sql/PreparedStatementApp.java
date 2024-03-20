import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementApp {

    public static void main(String[] args) throws SQLException {
           

        Connection conn = 
            DriverManager.getConnection("""
                jdbc:mysql://localhost:3306/empresa?allowPublicKeyRetrieval=true&useSSL=false
                """, "user", "password");

        PreparedStatement stm = conn.prepareStatement("SELECT * FROM empleado WHERE id_empleado = ?");
        stm.setInt(1, 125);    

        if(!stm.execute()) {
            System.out.println("No existe el empleado con ID=125");
        }

    }

}
