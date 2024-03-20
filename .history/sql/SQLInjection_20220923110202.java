import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLInjection {

        public static void main(String[] args) {
            
            // Resto de código para conectar con la base de datos.

            Connection conn = DriverManager.getConnection("url", "user", "password");

            Statement stm = conn.createStatement();

            String username = "user";
            String password = "pass' OR '1'='1";

            ResultSet rs = stm.executeQuery("SELECT * FROM users WHERE username = '" 
                                    + username + "'' AND password = '" + password + "'");

            //Resto del código

        }


}