#language: pt
Funcionalidade: Administração de Perfis de Acesso
    Como um administrador do sistema Cineflix
    Quero acessar a tela de Administração de Perfis de Acesso
    Para visualizar, filtrar, adicionar e gerenciar os perfis de acesso cadastrados

  Contexto: 
    Dado que eu acesse a aplicacao cineflix
    E realizar o login na aplicacao com sucesso
    Quando clicar no menu "Administrar"
    E clicar na opção "Perfil de Acesso"
    Entao devo ser redirecionado para a tela de gerenciamento de "Perfis de Acesso"

    @automacao
    Cenário: Exibir tela de Administração de Perfil de Acesso
        Então devo visualizar o campo de texto livre para pesquisa
        E devo visualizar os botões "Pesquisar", "Limpar" e "Adicionar"
        E a tabela de listagem deve conter as colunas:
        | Título    |
        | Descrição |
        | Ações     |

    @automacao
    Cenário: Validar interface para inclusão de novo perfil de acesso
        Quando eu clicar no botão "Adicionar"
        Então o sistema deve exibir o título "Cadastrar Novo Perfil de Acesso"
        E o sistema deve exibir os campos para preenchimento:
        | Título    |
        | Descrição |
        E o sistema deve exibir campo filtro para pesquisa de permissoes
        E deve exibir o botão "Salvar"
        E deve exibir o botão "Cancelar"

    @automacao
    Cenário: Validar interface para edição de perfil de acesso
        Quando eu clicar no botao de editar "perfil de acesso"
        # Então o sistema deve exibir o título "Atualizar Perfil de Acesso"
        E os campos "Título" e "Descrição" devem vir preenchidos
        E deve ser exibido campo filtro para pesquisa de permissoes funcionando corretamente
        E deve ser exibido permissoes atribuidas ao usuario
        # E as permissoes atribuidas podem ser removidas
        E deve exibir o botão "Salvar"
        E deve exibir o botão "Cancelar"

    @automacao
    Cenario: Inserir perfis de acesso
        Então eu cadastro "3" novos perfis de acesso sequenciais

    @automacao
    Cenario: Excluir perfis de acesso
        Então eu excluo "perfis de acesso"

    @tst_manual
    Esquema do Cenário: Filtrar perfil de acesso por nome ou descrição
    #     E que o usuário informa '<tipo_filtro>' no campo de filtro
    #     Quando o usuário clicar no botão de executar filtros
    #     Então o sistema deve realizar o filtro corretamente

    #     Exemplos:
    #     | tipo_filtro |
    #     | Nome        |
    #     | Descrição   |

    @tst_manual
    Cenário: Limpar filtros
    #     E inserir valor no campo de filtro
    #     Quando o usuário clicar no botão de limpar filtros
    #     Então o sistema deve limpar o filtro e retornar ao estado nulo
    #     E a listagem deve ser atualizada com todos os perfis de acesso

    @tst_manual
    Cenário: Excluir Perfil de Acesso
        # Dado que um perfil de acesso exista na listagem de perfis de acesso
        # Quando o usuário clicar no botão de excluir deste perfil de acesso
        # Então o sistema deve abrir uma caixa de diálogo pedindo confirmação
        # E se o usuário confirmar a exclusão
        # Então o sistema deve excluir o perfil de acesso
        # E deve atualizar a listagem de perfis de acesso
        # Mas se o usuário cancelar
        # Então o sistema deve apenas fechar a caixa de diálogo

    @tst_manual
    Cenário: Validar paginação e alteração de quantidade de itens
        # Quando eu selecionar para exibir '<quantidade>' itens por página
        # Então a tabela deve listar no máximo '<quantidade>' departamentos
        # E devo visualizar o descritivo da página atual e o total de páginas disponíveis
        # E devo conseguir navegar usando os botões "Próximo" e "Anterior"

        # Exemplos:
        # | quantidade |
        # | 05         |
        # | 10         |
        # | 25         |
        # | 100        |

    @db_insert @tst_manual
    Cenário: Validar criação de novo Perfil de Acesso no SQL Server
        # Quando eu cadastrar um novo perfil de acesso na aplicação
        # Então a consulta no SQL Server deve retornar o perfil de acesso cadastrado na aplicação

    @db_update @tst_manual
    Cenário: Validar edição de perfil de acesso no SQL Server
        # Quando eu editar um perfil de acesso na aplicação
        # Então a consulta no SQL Server deve retornar o perfil de acesso editado na aplicação

    @db_delete @tst_manual
    Cenário: Validar exclusão de perfil de acesso no SQL Server
        # Quando eu excluir um perfil de acesso na aplicação
        # Então a consulta no SQL Server não deve retornar dados referente ao perfil de acesso deletado na aplicação