public class TesteLivroDAO {

    public static void main(String[] args) {

        LivroDAO dao = new LivroDAO();

        boolean resultado = dao.adicionarLivro(
                "O Senhor dos Anéis A Sociedade do Anel",
                "J.R.R. Tolkien",
                1954);

        System.out.println(
                "Livro adicionado: " + resultado);
    }
}
