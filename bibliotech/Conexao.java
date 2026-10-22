import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL =
        "jdbc:mariadb://localhost:3306/bibliotech";

    public static Connection conectar()
            throws SQLException {

        String usuario =
            System.getenv("DB_USER");

        String senha =
            System.getenv("DB_PASSWORD");

        if (usuario == null || senha == null) {
            throw new SQLException(
                "Configure DB_USER e DB_PASSWORD."
            );
        }

        return DriverManager.getConnection(
            URL, usuario, senha
        );
    }
}