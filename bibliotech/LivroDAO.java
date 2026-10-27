
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public boolean adicionarLivro(
            String titulo,
            String autor,
            int ano) {

        String sql = "INSERT INTO livros (titulo, autor, ano) "
                + "VALUES (?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, titulo);
            comando.setString(2, autor);
            comando.setInt(3, ano);

            return comando.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar livro: "
                    + e.getMessage());
            return false;
        }
    }

    public boolean atualizarLivro(
            int id,
            String titulo,
            String autor,
            int ano) {

        String sql = "UPDATE livros "
                + "SET titulo = ?, autor = ?, ano = ? "
                + "WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, titulo);
            comando.setString(2, autor);
            comando.setInt(3, ano);
            comando.setInt(4, id);

            return comando.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar livro: "
                    + e.getMessage());
            return false;
        }
    }

    public boolean excluirLivro(int id) {

        String sql = "DELETE FROM livros WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            return comando.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir livro: "
                    + e.getMessage());
            return false;
        }
    }

    public List<Livro> listarLivros() {

        List<Livro> livros = new ArrayList<>();

        String sql = "SELECT id, titulo, autor, ano "
                + "FROM livros ORDER BY titulo";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {

                Livro livro = new Livro(
                        resultado.getInt("id"),
                        resultado.getString("titulo"),
                        resultado.getString("autor"),
                        resultado.getInt("ano")
                );

                livros.add(livro);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: "
                    + e.getMessage());
        }

        return livros;
    }

    public Livro buscarLivroPorId(int id) {

        String sql = "SELECT id, titulo, autor, ano "
                + "FROM livros WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            try (ResultSet resultado = comando.executeQuery()) {

                if (resultado.next()) {

                    return new Livro(
                            resultado.getInt("id"),
                            resultado.getString("titulo"),
                            resultado.getString("autor"),
                            resultado.getInt("ano")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar livro: "
                    + e.getMessage());
        }

        return null;
    }
}
