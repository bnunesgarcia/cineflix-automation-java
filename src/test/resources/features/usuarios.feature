# language: pt
Funcionalidade: Gerenciamento de Usuários
  Como um administrador do sistema Cineflix
  Quero acessar a tela de Administração de Usuários
  Para visualizar, filtrar, adicionar e gerenciar os usuários cadastrados

  Contexto: 
    Dado que eu acesse a aplicacao cineflix
    E realizar o login na aplicacao com sucesso
    Quando clicar no menu "Administrar"
    E clicar na opção "Usuário"
    Entao devo ser redirecionado para a tela de gerenciamento de "Usuários"

    @automacao
    Cenário: Validar elementos obrigatórios da tela de listagem
        Então devo visualizar os filtros de "Pesquisar", "Departamento", "Perfil de Acesso" e "Ativo"
        E devo visualizar os botões "Pesquisar", "Limpar", "Adicionar" e "Exportar"
        E a tabela de listagem deve conter as colunas:
        | Nome             |
        | E-mail           |
        | Login            |
        | Departamento     |
        | Perfil de Acesso |
        | Criado Em        |
        | Último Acesso    |
        | Conta Ativa      |
        | Ações            |

    @tst_manual
    Esquema do Cenário: Filtrar usuários por diferentes critérios
        # Quando eu preencher o campo de pesquisa com "<valor>"
        # E selecionar o Departamento "<departamento>"
        # E selecionar o Perfil de Acesso "<perfil>"
        # E definir o status como "<status>"
        # E clicar no botão "Executar Filtro"
        # Então a tabela deve exibir apenas os usuários que correspondam aos filtros aplicados

        # Exemplos:
        # | valor                  | departamento | perfil        | status    |
        # | Bruno Garcia           | TI           | Administrador | Ativo     |
        # | brung                  | TI           | Administrador | Ativo     |
        # | brung@rederecord.combr | TI           | Administrador | Ativo     |

    @tst_manual
    Cenário: Limpar filtros aplicados
        # Quando eu alterar os filtros para valores diferentes do padrão
        # E clicar no botão "Limpar"
        # Então todos os campos de filtro devem retornar ao estado nulo ou padrão

    @tst_manual
    Cenário: Tentar excluir um usuário mas desistir na confirmação
        # Quando eu clicar no botão "Excluir" referente a um dos usuarios
        # Então devo ver uma caixa de diálogo pedindo confirmação para excluir
        # Quando eu selecionar a opção "Cancelar" na confirmação
        # Então o usuário deve permanecer na listagem

    @tst_manual
    Cenário: Excluir um usuário com sucesso
        # Quando eu clicar no botão "Excluir" referente a um dos usuarios
        # E confirmar a exclusão na caixa de diálogo
        # Então o sistema deve exibir uma mensagem de sucesso
        # E o usuário não deve mais ser listado na tabela

    @tst_manual
    Cenário: Validar paginação e alteração de quantidade de itens
        # Quando eu selecionar para exibir '<quantidade>' itens por página
        # Então a tabela deve listar no máximo '<quantidade>' usuários
        # E devo visualizar o descritivo da página atual e o total de páginas disponíveis
        # E devo conseguir navegar usando os botões "Próximo" e "Anterior"

        # Exemplos:
        # | quantidade |
        # | 05         |
        # | 10         |
        # | 25         |
        # | 100        |

    @tst_manual
    Cenário: Extrair relatório de usuários em XLS
        # Quando eu clicar no botão "Extrair página para Excel"
        # Então o sistema deve iniciar o download do arquivo no formato "xls"
        # E o download deve ser realizado com sucesso
        # E os mesmos campos que estão sendo exibidos na aplicação devem estar presentes no arquivo baixado

    @automacao
    Cenário: Validar interface para inclusão de novo usuário
        Quando eu clicar no botão "Adicionar"
        Então o sistema deve exibir o título "Cadastrar Novo Usuário"
        E o sistema deve exibir os campos para preenchimento:
        | Nome             |
        | E-mail           |
        | Login            |
        | Departamento     |
        | Perfil de Acesso |
        # E deve exibir a flag de status "Ativo" como um botão slide-toggle
        E deve exibir o botão "Salvar"
        E deve exibir o botão "Cancelar"

    @tst_manual
    Cenário: Validar interface para edição de usuário existente
        # Quando eu clicar no botao de editar de um dos usuarios
        # Então o sistema deve exibir o título "Atualizar Usuário"
        # E o campo "Nome" deve vir preenchido com o nome atual do usuário
        # E o campo "E-mail" deve vir preenchido com o e-mail atual do usuário
        # E o campo "Login" deve vir preenchido com o login atual do usuário
        # E as caixa de seleção "Departamento" deve exibir a opção previamente cadastrada
        # E as caixa de seleção "Perfil de Acesso" deve exibir a opção previamente cadastrada
        # E a tela de edição deve conter o toggle de status do usuario

    @db_insert @tst_manual
    Cenário: Validar criação de novo usuário no SQL Server
        # Quando eu cadastrar um novo usuário na aplicação
        # Então a consulta no SQL Server deve retornar o usuário cadastrado na aplicação

    @db_update @tst_manual
    Cenário: Validar edição de usuário no SQL Server
        # Quando eu editar um usuário na aplicação
        # Então a consulta no SQL Server deve retornar o usuário editado na aplicação

    @db_delete @tst_manual
    Cenário: Validar exclusão (ou desativação) de usuário no SQL Server
        # Quando eu excluir um usuário na aplicação
        # Então a consulta no SQL Server não deve retornar dados referente ao usuário deletado na aplicação