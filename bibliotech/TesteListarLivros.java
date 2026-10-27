import java.util.List;

public class TesteListarLivros {

    public static void main(String[] args) {

        LivroDAO dao = new LivroDAO();

        List<String> livros = dao.listarLivros();

        System.out.println(
            "=== LIVROS CADASTRADOS ==="
        );

        if (livros.isEmpty()) {

            System.out.println(
                "Nenhum livro encontrado."
            );

        } else {

            for (String livro : livros) {
                System.out.println(livro);
            }
        }
    }
}