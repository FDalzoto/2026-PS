import java.util.List;

public class TesteListarLivros {

    public static void main(String[] args) {

        LivroDAO dao = new LivroDAO();

        List<Livro> livros = dao.listarLivros();

        System.out.println(
            "=== LIVROS CADASTRADOS ==="
        );

        if (livros.isEmpty()) {

            System.out.println(
                "Nenhum livro encontrado."
            );

        } else {

            for (Livro livro : livros) {

                System.out.println(
                    livro.getId() + " - " +
                    livro.getTitulo() + " - " +
                    livro.getAutor() + " (" +
                    livro.getAno() + ")"
                );
            }
        }
    }
}