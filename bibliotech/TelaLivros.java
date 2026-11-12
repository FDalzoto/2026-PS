import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TelaLivros extends JFrame {

    private final LivroDAO dao = new LivroDAO();

    private final DefaultTableModel modelo;
    private final JTable tabela;

    private final JTextField campoId = new JTextField(8);
    private final JLabel status = new JLabel("Pronto.");
    private final JTextField campoTitulo = new JTextField(18);

    // CORES DO PAINEL ADMINISTRATIVO BIBLIOTECH

    private final Color FUNDO = new Color(11, 18, 32);
    private final Color PAINEL = new Color(17, 24, 39);
    private final Color CARTAO = new Color(30, 41, 59);

    private final Color AZUL = new Color(37, 99, 235);
    private final Color VERDE = new Color(16, 185, 129);
    private final Color TURQUESA = new Color(8, 145, 178);

    private final Color TEXTO = new Color(248, 250, 252);
    private final Color CINZA = new Color(148, 163, 184);

    public TelaLivros() {

        setTitle("BiblioTech - Administração");
        setSize(1000, 550);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(FUNDO);

        setLayout(new BorderLayout(8, 8));

        modelo = new DefaultTableModel(
                new Object[] {
                        "ID", "CAPA", "Título", "Autor", "Ano"
                }, 0) {

            @Override
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int coluna) {
                if (coluna == 1) {
                    return ImageIcon.class;
                }
                return Object.class;
            }
        };

        tabela = new JTable(modelo);

        // PERSONALIZAÇÃO DA TABELA

        tabela.setRowHeight(95);

        tabela.setFont(
                new Font("SansSerif", Font.PLAIN, 14)
        );

        tabela.setBackground(PAINEL);
        tabela.setForeground(TEXTO);

        tabela.setSelectionBackground(
                new Color(51, 65, 85)
        );

        tabela.setSelectionForeground(TEXTO);

        tabela.setShowGrid(true);
        tabela.setShowVerticalLines(true);
        tabela.setShowHorizontalLines(true);

        tabela.setGridColor(Color.WHITE);

        tabela.setIntercellSpacing(
                new Dimension(1, 1)
        );

        tabela.setFillsViewportHeight(true);

        // CABEÇALHO DA TABELA

        tabela.getTableHeader().setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );

        tabela.getTableHeader().setBackground(CARTAO);
        tabela.getTableHeader().setForeground(TEXTO);

        tabela.getTableHeader().setPreferredSize(
                new Dimension(0, 40)
        );

        // CENTRALIZAR AS CAPAS

        DefaultTableCellRenderer centralizar =
                new DefaultTableCellRenderer();

        centralizar.setHorizontalAlignment(
                JLabel.CENTER
        );

        tabela.getColumnModel()
                .getColumn(0)
                .setCellRenderer(centralizar);

        tabela.getColumnModel()
                .getColumn(1)
                .setCellRenderer(new DefaultTableCellRenderer() {

                    @Override
                    public Component getTableCellRendererComponent(
                            JTable tabela,
                            Object valor,
                            boolean selecionado,
                            boolean foco,
                            int linha,
                            int coluna) {

                        JLabel label = (JLabel) super
                                .getTableCellRendererComponent(
                                        tabela,
                                        valor,
                                        selecionado,
                                        foco,
                                        linha,
                                        coluna
                                );

                        label.setHorizontalAlignment(
                                JLabel.CENTER
                        );

                        if (valor instanceof ImageIcon) {
                            label.setIcon((ImageIcon) valor);
                            label.setText("");
                        } else {
                            label.setIcon(null);
                            label.setText("Sem capa");
                        }

                        return label;
                    }
                });

        // LARGURA DAS COLUNAS

        tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(250);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(220);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(80);

        // PAINEL DE ROLAGEM

        JScrollPane rolagem = new JScrollPane(tabela);

        rolagem.setBorder(
                BorderFactory.createLineBorder(Color.WHITE)
        );

        rolagem.getViewport().setBackground(PAINEL);

        add(rolagem, BorderLayout.CENTER);

        // PAINEL DE CONTROLES

        JPanel controles = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 8, 12));

        controles.setBackground(PAINEL);

        JButton atualizar = new JButton("Atualizar listagem");
        JButton buscar = new JButton("Buscar por ID");
        JButton mostrarTodos = new JButton("Mostrar todos");
        JButton botaoConsultar = new JButton("Consultar livros");
        JButton botaoBuscarTitulo = new JButton("Buscar por título");
        JButton botaoCapa = new JButton("Selecionar capa");

        // PERSONALIZAÇÃO DOS BOTÕES

        configurarBotao(atualizar, AZUL);
        configurarBotao(buscar, TURQUESA);
        configurarBotao(mostrarTodos, VERDE);
        configurarBotao(botaoConsultar, AZUL);
        configurarBotao(botaoBuscarTitulo, TURQUESA);
        configurarBotao(botaoCapa, VERDE);

        // PERSONALIZAÇÃO DOS CAMPOS

        personalizarCampo(campoId);
        personalizarCampo(campoTitulo);

        JLabel labelId = new JLabel("ID do livro:");
        labelId.setForeground(TEXTO);

        JLabel labelTitulo = new JLabel("Título:");
        labelTitulo.setForeground(TEXTO);

        controles.add(labelId);
        controles.add(campoId);
        controles.add(buscar);
        controles.add(atualizar);
        controles.add(mostrarTodos);
        controles.add(botaoConsultar);
        controles.add(labelTitulo);
        controles.add(campoTitulo);
        controles.add(botaoBuscarTitulo);
        controles.add(botaoCapa);

        add(controles, BorderLayout.NORTH);

        // BARRA DE STATUS

        status.setOpaque(true);
        status.setBackground(PAINEL);
        status.setForeground(CINZA);

        status.setFont(
                new Font("SansSerif", Font.BOLD, 13)
        );

        status.setBorder(
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        );

        add(status, BorderLayout.SOUTH);

        // EVENTOS

        atualizar.addActionListener(
                e -> carregarLivros());

        mostrarTodos.addActionListener(
                e -> carregarLivros());

        buscar.addActionListener(
                e -> buscarLivro());

        botaoConsultar.addActionListener(e -> {
            new TelaLivros().setVisible(true);
        });

        botaoBuscarTitulo.addActionListener(
                e -> buscarPorTitulo());

        botaoCapa.addActionListener(
                e -> selecionarCapa());

        campoId.addActionListener(
                e -> buscarLivro());

        campoTitulo.addActionListener(
                e -> buscarPorTitulo());

        carregarLivros();
    }

    // PERSONALIZAÇÃO DOS CAMPOS

    private void personalizarCampo(JTextField campo) {

        campo.setBackground(CARTAO);
        campo.setForeground(TEXTO);
        campo.setCaretColor(TEXTO);

        campo.setFont(
                new Font("SansSerif", Font.PLAIN, 13)
        );

        campo.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(51, 65, 85)
                        ),
                        BorderFactory.createEmptyBorder(
                                7, 9, 7, 9
                        )
                )
        );
    }

    // PERSONALIZAÇÃO DOS BOTÕES

    private void configurarBotao(JButton botao, Color cor) {

        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);

        botao.setFont(
                new Font("SansSerif", Font.BOLD, 12)
        );

        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setOpaque(true);

        botao.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );
    }

    // CARREGAR CAPA PELO CAMINHO SALVO NO BANCO

    private ImageIcon carregarCapa(String caminho) {

        if (caminho == null || caminho.trim().isEmpty()) {
            return null;
        }

        File arquivo = new File(caminho);

        if (!arquivo.isAbsolute()) {
            arquivo = arquivo.getAbsoluteFile();
        }

        if (!arquivo.exists() || !arquivo.isFile()) {

            System.out.println(
                    "Capa não encontrada: " +
                    arquivo.getAbsolutePath()
            );

            return null;
        }

        ImageIcon imagem = new ImageIcon(
                arquivo.getAbsolutePath()
        );

        int larguraOriginal = imagem.getIconWidth();
        int alturaOriginal = imagem.getIconHeight();

        if (larguraOriginal <= 0 || alturaOriginal <= 0) {

            System.out.println(
                    "Não foi possível ler a imagem: " +
                    arquivo.getAbsolutePath()
            );

            return null;
        }

        int larguraMaxima = 65;
        int alturaMaxima = 85;

        double escala = Math.min(
                (double) larguraMaxima / larguraOriginal,
                (double) alturaMaxima / alturaOriginal
        );

        int novaLargura = Math.max(
                1, (int) (larguraOriginal * escala)
        );

        int novaAltura = Math.max(
                1, (int) (alturaOriginal * escala)
        );

        Image imagemRedimensionada =
                imagem.getImage().getScaledInstance(
                        novaLargura,
                        novaAltura,
                        Image.SCALE_SMOOTH
                );

        return new ImageIcon(imagemRedimensionada);
    }

    // SELECIONAR E SALVAR CAPA

    private void selecionarCapa() {

        int linha = tabela.getSelectedRow();

        if (linha == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um livro na tabela."
            );

            return;
        }

        int linhaModelo =
                tabela.convertRowIndexToModel(linha);

        int id = (int) modelo.getValueAt(
                linhaModelo, 0
        );

        // ABRIR SELETOR DE ARQUIVOS

        JFileChooser seletor = new JFileChooser();

        seletor.setDialogTitle(
                "Selecione a capa do livro"
        );

        seletor.setFileSelectionMode(
                JFileChooser.FILES_ONLY
        );

        seletor.setAcceptAllFileFilterUsed(true);

        FileNameExtensionFilter filtro =
                new FileNameExtensionFilter(
                        "Imagens PNG, JPG e JPEG",
                        "png", "jpg", "jpeg"
                );

        seletor.setFileFilter(filtro);

        // COMEÇAR NA PASTA DO PROJETO
        // (usa a subpasta "capas" se ela existir; senão, a raiz do projeto)

        File pastaInicial = new File(
                System.getProperty("user.dir"),
                "capas"
        );

        if (!pastaInicial.isDirectory()) {
            pastaInicial = new File(
                    System.getProperty("user.dir")
            );
        }

        seletor.setCurrentDirectory(pastaInicial);

        // EXIBIR A JANELA

        int resultado = seletor.showOpenDialog(this);

        if (resultado != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File imagemSelecionada = seletor.getSelectedFile();

        // VALIDAR O ARQUIVO SELECIONADO

        if (imagemSelecionada == null ||
                !imagemSelecionada.isFile()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um arquivo de imagem válido."
            );

            return;
        }

        String nome = imagemSelecionada.getName().toLowerCase();

        if (!nome.endsWith(".png") &&
                !nome.endsWith(".jpg") &&
                !nome.endsWith(".jpeg")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Formato não permitido. Escolha uma imagem PNG ou JPG."
            );

            return;
        }

        Path destino = null;

        try {

            // SALVAR A IMAGEM CONVERTIDA PARA PNG

            destino = Path.of(
                    ImagemLivro.salvar(
                            imagemSelecionada.toPath(),
                            id
                    )
            );

            // ATUALIZAR O CAMINHO NO BANCO DE DADOS

            boolean atualizado =
                    dao.atualizarCapa(
                            id,
                            destino.toString().replace('\\', '/')
                    );

            if (!atualizado) {

                Files.deleteIfExists(destino);

                JOptionPane.showMessageDialog(
                        this,
                        "Não foi possível atualizar o livro no banco de dados."
                );

                return;
            }

            // ATUALIZAR A TABELA

            carregarLivros();

            JOptionPane.showMessageDialog(
                    this,
                    "Capa salva com sucesso!"
            );

        } catch (Exception ex) {

            if (destino != null) {
                try {
                    Files.deleteIfExists(destino);
                } catch (IOException ignorado) {
                }
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao salvar capa: " +
                            ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );

            ex.printStackTrace();
        }
    }

    // CARREGAR LIVROS

    private void carregarLivros() {

        modelo.setRowCount(0);

        List<Livro> livros = dao.listarLivros();

        for (Livro livro : livros) {
            adicionarLinha(livro);
        }

        status.setText(
                "Livros apresentados: " + livros.size());
    }

    // ADICIONAR LINHA NA TABELA

    private void adicionarLinha(Livro livro) {

        modelo.addRow(new Object[] {
                livro.getId(),
                carregarCapa(livro.getCapa()),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getAno()
        });
    }

    // BUSCAR LIVRO POR ID

    private void buscarLivro() {

        String texto = campoId.getText().trim();

        int id;

        try {

            id = Integer.parseInt(texto);

            if (id <= 0) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Informe um ID inteiro positivo.",
                    "Entrada inválida",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Livro livro = dao.buscarLivroPorId(id);

        modelo.setRowCount(0);

        if (livro == null) {

            status.setText(
                    "Nenhum livro encontrado para o ID " + id);

            JOptionPane.showMessageDialog(
                    this,
                    "Livro não encontrado.");

        } else {

            adicionarLinha(livro);

            status.setText(
                    "Livro encontrado: " + livro.getTitulo());
        }

        campoId.setText("");
    }

    // BUSCAR LIVROS POR TÍTULO

    private void buscarPorTitulo() {

        String titulo = campoTitulo.getText().trim();

        if (titulo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Informe o título do livro.",
                    "Campo obrigatório",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        List<Livro> livros = dao.buscarLivrosPorTitulo(titulo);

        modelo.setRowCount(0);

        for (Livro livro : livros) {
            adicionarLinha(livro);
        }

        status.setText(
                "Livros encontrados: " + livros.size());

        if (livros.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum livro encontrado.");
        }

        campoTitulo.setText("");
    }

    // MÉTODO PRINCIPAL

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new TelaLivros().setVisible(true);
        });
    }
}