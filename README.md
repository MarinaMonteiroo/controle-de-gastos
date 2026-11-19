UnB - Universidade de Brasília  
FGA - Faculdade do Gama  
OO - Orientação à Objetos  
Prof. André Luiz Peron Martins Lanna

## Trabalho Prático de Orientação à Objetos

---

### Controle de Gastos

Aplicação de console em Java para controle financeiro pessoal.

- **Alunas:**  Bruna Medeiros Assunção Gomes - 251006764 <br>
                        Eloah Vieira Vasconcelos Avila - 252004370<br> 
                        Marina Monteiro Mota - 252024005<br>
- **Turma:** 01
- **Semestre:** 2026.2
- **Linguagem:** Java (JDK 17 ), utilizando a biblioteca padrão.

---

### Descrição

O sistema  de controle de gastos pessoal registra receitas e despesas da conta do usuário, classifica cada transação em um plano de contas, mantém o saldo atualizado e emite o extrato. Neste sistema saldo de conta que sejam negativos não são considerados, operações inválidas são recusadas por exceções, e a conta permanece inalterada.

### Funcionalidades

- Inserção, atualização e remoção de transações
- Consulta do saldo atual
- Extrato com saldo acumulado após cada transação
- Plano de contas com categorias de receita e de despesa
- Tratamento de erros por meio de exceções próprias

### Conceitos aplicados

- Classes e objetos
- Encapsulamento (pacotes, interfaces, modificadores de acesso e de escopo)
- Herança simples
- Polimorfismo (sobrescrita, sobrecarga, coerção, inclusão e paramétrico)
- Exceções

### Estrutura

```
src/controlegastos/
├── modelo/     Transacao, Receita, Despesa, Categoria
├── excecoes/   Exceções do sistema
├── servico/    Conta e interfaces
└── principal/        Classe Main
```

### Compilação e execução

Requisito: JDK 17 ou superior. Execute os comandos na pasta raiz do projeto.

Windows (Prompt de Comando):

```
dir /s /b src\*.java > fontes.txt
javac -d out @fontes.txt
java -cp out controlegastos.principal.Main
```

Linux e macIOS:

```
javac -d out $(find src -name "*.java")
java -cp out controlegastos.principal.Main
```

### Documentação

- Diagrama de classes UML: [a ser adicionado]
- Relatório e roteiro de testes: [a ser adicionado]

### Uso de ferramentas de apoio e referências.

[ a ser adicionado]