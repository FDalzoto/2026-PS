# BiblioTech
​
Sistema de emprestimo de livros para a biblioteca do campus.
​
## 1. O projeto
​
O cliente é a biblioteca do campus. O sistema resolve o problema de organizar livros, leitores, empréstimos e devoluções, facilitando o trabalho das bibliotecárias e dos leitores.

## 2. Historias de usuario
​
| # | Historia de usuario |
|---|---|
| HU01 | Como leitor, quero consultar a disponibilidade de um livro, para saber se posso pega-lo emprestado sem ir ate o balcao. |
| HU02 | Como leitor, quero devolver um livro, para nao ficar com pendencia na biblioteca. |
| HU03 | Como bibliotecaria, quero registrar um emprestimo, para saber quem esta com cada exemplar. |
| HU04 | Como bibliotecaria, quero cadastrar um livro novo, para que ele possa ser encontrado no sistema. |
| HU05 | Como bibliotecaria, quero ver os emprestimos atrasados, para cobrar a devolucao. |

## 3. Requisitos
​
### Requisitos funcionais
​
| # | Requisito funcional | Veio da |
|---|---|---|
| RF01 | O sistema deve permitir que a bibliotecaria cadastre um livro no acervo. | HU04 |
| RF02 | O sistema deve permitir que a bibliotecaria cadastre um leitor. | regra de acesso: so quem tem cadastro leva livro |
| RF03 | O sistema deve permitir que o leitor consulte a disponibilidade de um livro. | HU01 |
| RF04 | O sistema deve permitir que a bibliotecaria registre a devolucao de um livro. | HU02 |
| RF05 | O sistema deve permitir que a bibliotecaria registre o emprestimo de um livro. | HU03 |
| RF06 | O sistema deve permitir que a bibliotecária consulte os livros atualmente emprestados. | HU05 |
​
### Requisitos nao funcionais
​
| # | Requisito nao funcional |
|---|---|
| RNF01 | A consulta de disponibilidade deve responder em menos de 3 segundos. |
| RNF02 | Somente usuarios identificados como bibliotecarios podem alterar o acervo. |

## 5. O que o codigo devolveu ao diagrama (Aula 37)

- Livro ganhou o atributo disponivel: Boolean, porque estaDisponivel()prescisa guardar o estado.
- Leitor ganhou livrosEmMaos: int, porque podePegarEmprestado() compara com o limite.

## 6. Persistência de Dados

- O banco de dados utilizado foi o mariaDB que é uma banco de dados relacional que armazena as informações de cada livro.

- A classe Conexao é responsável por conectar o sistema ao banco de dados, utilizando as variáveis de ambiente DB_USER e DB_PASSWORD para obter o usuário e a senha de acesso.

- A classe LivroDAO cuida das operações com os livros no banco de dados, como cadastrar, consultar, atualizar e excluir registros.

- Os testes são realizados executando a classe de teste de conexão e, depois, os testes de persistência, verificando se os dados são salvos e manipulados corretamente.

- Antes de executar os testes, é necessário configurar as variáveis de ambiente e garantir que o banco de dados esteja funcionando.
