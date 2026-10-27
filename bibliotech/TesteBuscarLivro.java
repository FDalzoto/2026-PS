import java.util.Scanner;

public class TesteBuscarLivro {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        LivroDAO dao = new LivroDAO();

        System.out.println(
            "=== BUSCAR LIVRO ==="
        );

        System.out.print(
            "Informe o ID do livro: "
        );

        int id = entrada.nextInt();

        String livro = dao.buscarLivroPorId(id);

        if (livro != null) {

            System.out.println(
                "Livro encontrado:"
            );

            System.out.println(livro);

        } else {

            System.out.println(
                "Livro não encontrado."
            );
        }

        entrada.close();
    }
}