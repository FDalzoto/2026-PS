public class TesteRequisitos {

    private static int passaram = 0;
    private static int falharam = 0;

    private static void verificar(String requisito, boolean condicao) {
        if (condicao) {
            passaram = passaram + 1;
            System.out.println("OK       " + requisito);
        } else {
            falharam = falharam + 1;
            System.out.println("FALHOU   " + requisito);
        }
    }

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();


        biblioteca.cadastrarLivro(
            new Livro("Dom Casmurro", "Machado de Assis", 1899)
        );

        biblioteca.cadastrarLivro(
            new Livro("Capitães da Areia", "Jorge Amado", 1937)
        );


        biblioteca.cadastrarLeitor(
            new Leitor("Pedro Alves", "2026010", 1)
        );

        biblioteca.cadastrarLeitor(
            new Leitor("Ana Lima", "2026011", 2)
        );


        Livro dom = biblioteca.buscarLivro("Dom Casmurro");
        Leitor pedro = biblioteca.buscarLeitor("2026010");


        verificar(
            "RF01 - livro cadastrado aparece na busca",
            dom != null
        );

        verificar(
            "RF02 - leitor cadastrado aparece na busca",
            pedro != null
        );


        verificar(
            "RF03 - livro novo cadastrado está disponível",
            dom != null && dom.estaDisponivel()
        );

        verificar(
            "RF03 - título fora do acervo não é encontrado",
            biblioteca.buscarLivro("O Cortiço") == null
        );


        verificar(
            "RF05 - empréstimo de livro disponível é aceito",
            biblioteca.emprestar("Dom Casmurro", "2026010")
        );

        verificar(
            "RF05 - livro emprestado fica indisponível",
            !dom.estaDisponivel()
        );

        verificar(
            "RF05 - leitor passa a ter 1 livro em mãos",
            pedro.getLivrosEmMaos() == 1
        );

        verificar(
            "RF05 - livro emprestado é recusado para outro leitor",
            !biblioteca.emprestar("Dom Casmurro", "2026011")
        );

        verificar(
            "RF05 - leitor no limite não pode pegar outro livro",
            !biblioteca.emprestar("Capitães da Areia", "2026010")
        );


        verificar(
            "RF04 - devolução de empréstimo ativo é aceita",
            biblioteca.devolver("Dom Casmurro", "2026010")
        );

        verificar(
            "RF04 - livro devolvido volta a ficar disponível",
            dom.estaDisponivel()
        );

        verificar(
            "RF04 - segunda devolução é recusada",
            !biblioteca.devolver("Dom Casmurro", "2026010")
        );

        // Resultado final
        System.out.println();
        System.out.println(
            passaram + " passaram, " + falharam + " falharam."
        );
    }
}