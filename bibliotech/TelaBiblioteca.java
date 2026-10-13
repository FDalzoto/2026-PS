import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;

public class TelaBiblioteca extends JFrame {
    private Biblioteca biblioteca;
    private JTextField campoTitulo;
    private JTextField campoMatricula;
    private JTextArea areaAcervo;

    public TelaBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;

        setTitle("BiblioTech");
        setSize(760, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel campos = new JPanel(new GridLayout(2, 2, 8, 8));
        campos.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 0, 10)
        );

        campoTitulo = new JTextField();
        campoMatricula = new JTextField();

        campos.add(new JLabel("Titulo do livro:"));
        campos.add(campoTitulo);
        campos.add(new JLabel("Matricula do leitor:"));
        campos.add(campoMatricula);
        add(campos, BorderLayout.NORTH);

        areaAcervo = new JTextArea();
        areaAcervo.setEditable(false);
        areaAcervo.setLineWrap(true);
        areaAcervo.setWrapStyleWord(true);
        add(new JScrollPane(areaAcervo), BorderLayout.CENTER);

        JPanel botoes = new JPanel(new FlowLayout());

        JButton botaoEmprestar = new JButton("Emprestar");
        JButton botaoDevolver = new JButton("Devolver");
        JButton botaoAtualizar = new JButton("Atualizar acervo");

        botaoEmprestar.addActionListener(e -> emprestar());
        botaoDevolver.addActionListener(e -> devolver());
        botaoAtualizar.addActionListener(e -> atualizarAcervo());

        botoes.add(botaoEmprestar);
        botoes.add(botaoDevolver);
        botoes.add(botaoAtualizar);
        add(botoes, BorderLayout.SOUTH);

        atualizarAcervo();
    }

    private void atualizarAcervo() {
        areaAcervo.setText(biblioteca.obterAcervoComoTexto());
        areaAcervo.setCaretPosition(0);
    }

    private void emprestar() {
        String titulo = campoTitulo.getText().trim();
        String matricula = campoMatricula.getText().trim();

        if (titulo.isEmpty() || matricula.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "Preencha o titulo e a matricula."
            );
            return;
        }

        boolean resultado = biblioteca.emprestar(titulo, matricula);
        atualizarAcervo();

        if (resultado) {
            JOptionPane.showMessageDialog(
                this, "Emprestimo realizado."
            );
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Emprestimo recusado. Confira o titulo, a matricula, "
                + "a disponibilidade e o limite do leitor."
            );
        }
    }

    private void devolver() {
        String titulo = campoTitulo.getText().trim();
        String matricula = campoMatricula.getText().trim();

        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "Preencha o titulo do livro."
            );
            return;
        }

        boolean resultado = biblioteca.devolver(titulo, matricula);
        atualizarAcervo();

        if (resultado) {
            JOptionPane.showMessageDialog(
                this, "Devolucao registrada."
            );
        } else {
            JOptionPane.showMessageDialog(
                this, "Nenhum emprestimo ativo encontrado para esse titulo."
            );
        }
    }
    
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

        SwingUtilities.invokeLater(() -> {
            TelaBiblioteca tela = new TelaBiblioteca(biblioteca);
            tela.setVisible(true);
        });
    }
}