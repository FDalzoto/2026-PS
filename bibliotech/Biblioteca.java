import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<Livro> livros;
    private ArrayList<Leitor> leitores;
    private ArrayList<Emprestimo> emprestimos;


    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    public void cadastrarLeitor(Leitor leitor){
        leitores.add(leitor);
    }

    public void listarAcervo(){
        for (int i = 0; i < livros.size(); i++){
            System.out.println(livros.get(i));
        }
    }

    public Biblioteca(){
        this.livros = new ArrayList<Livro>();
        this.leitores = new ArrayList<Leitor>();
        this.emprestimos = new ArrayList<Emprestimo>();
    }

    public Livro buscarLivro(String titulo){
        for (int i = 0; i < livros.size(); i++){
            Livro l = livros.get(i);
            if(l.getTitulo().equals(titulo)){
                return l;
            }
        }
        return null;
    }

    public Leitor buscarLeitor(String matricula){
        for (int i = 0; i < leitores.size(); i++){
            Leitor l = leitores.get(i);
            if(l.getMatricula().equals(matricula)){
                return l;
            }
        }
        return null;
    }

    public boolean emprestar(String titulo, String matricula){
        Livro livro = buscarLivro(titulo);
        Leitor leitor = buscarLeitor(matricula);
        if (livro == null || leitor == null){
            return false;
        }

        Emprestimo novo = new Emprestimo(livro, leitor);
        if (!novo.realizarEmprestimo()){
            return false;
        }

        emprestimos.add(novo);
        return true;
    }

    public boolean devolver(String titulo) {
        for (int i = 0; i < emprestimos.size(); i++) {
            Emprestimo e = emprestimos.get(i);
            if (e.estaAtivo() && e.getLivro().getTitulo().equals(titulo)) {
                return e.registrarDevolucao();
            }
        }
        return false;
    }

    public void listarEmprestimos(){
        for (int i = 0; i < emprestimos.size(); i++){
            System.out.println(emprestimos.get(i));
        }
    }

       // Fornece uma descricao do acervo para a interface.
        public String obterAcervoComoTexto() {
            if (livros.isEmpty()) {
             return "Nenhum livro cadastrado.";
            }

            String texto = "";
            for (int i = 0; i < livros.size(); i++) {
                texto = texto + livros.get(i) + "\n";
            }
        return texto;
    }

}
