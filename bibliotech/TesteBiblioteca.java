public class TesteBiblioteca {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.cadastrarLivro(
            new Livro("Dom Casmurro", "Machado de Assis", 1899)
        );

        biblioteca.cadastrarLivro(
            new Livro("Capitaes da Areia", "Jorge Amado", 1937)
        );

        biblioteca.cadastrarLeitor(
            new Leitor("Pedro Alves", "2026010", 1)
        );

        biblioteca.cadastrarLeitor(
            new Leitor("Ana Lima", "2026011", 2)
        );

        System.out.println("--- Acervo ---");
        biblioteca.listarAcervo();

        System.out.println(
            "Acha: " + biblioteca.buscarLivro("Dom Casmurro")
        );

        System.out.println(
            "Nao acha: " + biblioteca.buscarLivro("O Cortico")
        );

        System.out.println(
            "Leitor: " + biblioteca.buscarLeitor("2026011")
        );

        System.out.println(
            "Pedro pega Dom Casmurro: "
            + biblioteca.emprestar("Dom Casmurro", "2026010")
        );

        System.out.println(
            "Ana tenta o mesmo livro: "
            + biblioteca.emprestar("Dom Casmurro", "2026011")
        );

        System.out.println(
            "Pedro acima do limite: "
            + biblioteca.emprestar("Capitaes da Areia", "2026010")
        );

        System.out.println(
            "Livro que nao existe: "
            + biblioteca.emprestar("O Cortico", "2026011")
        );

        System.out.println(
            "Devolucao: "
            + biblioteca.devolver("Dom Casmurro", "2026010")
        );

        System.out.println(
            "Segunda devolucao: "
            + biblioteca.devolver("Dom Casmurro", "2026010")
        );

        System.out.println(
            "Ana pega Dom Casmurro: "
            + biblioteca.emprestar("Dom Casmurro", "2026011")
        );

        System.out.println("--- Emprestimos ---");
        biblioteca.listarEmprestimos();

        System.out.println("--- Acervo ---");
        biblioteca.listarAcervo();
    }
}