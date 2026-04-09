#language: pt
Funcionalidade: Administração de Departamentos
    Como um administrador do sistema Cineflix
    Quero acessar a tela de Administração de Departamentos
    Para visualizar, filtrar, adicionar e gerenciar os departamentos cadastrados

  Contexto: 
    Dado que eu acesse a aplicacao cineflix
    E realizar o login na aplicacao com sucesso
    Quando clicar no menu "Administrar"
    E clicar na opção "Departamento"
    Entao devo ser redirecionado para a tela de gerenciamento de "Departamentos"

    @automacao
    Cenário: Exibir tela de Administração de Departamentos
        Então devo visualizar o campo de texto livre para pesquisa
        E devo visualizar os botões "Pesquisar", "Limpar" e "Adicionar"
        E a tabela de listagem deve conter as colunas:
        | Nome      |
        | Descrição |
        | Ações     |

    @automacao
    Cenário: Validar interface para inclusão de novo departamento
        Quando eu clicar no botão "Adicionar"
        Então o sistema deve exibir o título "Cadastrar Novo Departamento"
        E o sistema deve exibir os campos para preenchimento:
        | Nome      |
        | Descrição |
        E deve exibir o botão "Salvar"
        E deve exibir o botão "Cancelar"

    @automacao
    Cenário: Validar interface para edição de departamento existente
        Quando eu clicar no botao de editar "departamento"
        Então o sistema deve exibir o título "Atualizar Departamento"
        E os campos "Nome" e "Descrição" devem vir preenchidos
        E deve exibir o botão "Salvar"
        E deve exibir o botão "Cancelar"

    @automacao
    Cenario: Inserir departamentos
        Então eu cadastro "5" novos departamentos sequenciais

    @automacao
    Cenario: Excluir departamentos
        Então eu excluo "departamentos"


    @tst_manual
    Esquema do Cenário: Filtrar departamentos por nome ou descrição
        # E que o usuário informa '<tipo_filtro>' no campo de filtro
        # Quando o usuário clicar no botão de executar filtros
        # Então o sistema deve realizar o filtro corretamente

        # Exemplos:
        # | tipo_filtro |
        # | Nome        |
        # | Descrição   |

    @tst_manual
    Cenário: Limpar filtros
    #     E inserir valor no campo de filtro
    #     Quando o usuário clicar no botão de limpar filtros
    #     Então o sistema deve limpar o filtro e retornar ao estado nulo
    #     E a listagem deve ser atualizada com todos os departamentos

    @tst_manual
    Cenário: Excluir departamento sem usuários atrelados
        # Dado que um departamento na lista não possui usuário atrelado
        # Quando o usuário clicar no botão de excluir deste departamento
        # Então o sistema deve abrir uma caixa de diálogo pedindo confirmação
        # E se o usuário confirmar a exclusão
        # Então o sistema deve excluir o departamento
        # E deve atualizar a listagem de departamentos
        # Mas se o usuário cancelar
        # Então o sistema deve apenas fechar a caixa de diálogo

    @tst_manual
    Cenário: Validar departamento com usuários atrelados
        # Dado que um departamento na lista possui usuário atrelado
        # Então o botão de excluir deve estar desabilitado

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
    Cenário: Validar criação de novo departamento no SQL Server
        # Quando eu cadastrar um novo departamento na aplicação
        # Então a consulta no SQL Server deve retornar o departamento cadastrado na aplicação

    @db_update @tst_manual
    Cenário: Validar edição de departamento no SQL Server
        # Quando eu editar um departamento na aplicação
        # Então a consulta no SQL Server deve retornar o departamento editado na aplicação

    @db_delete @tst_manual
    Cenário: Validar exclusão de departamento no SQL Server
        # Quando eu excluir um departamento na aplicação
        # Então a consulta no SQL Server não deve retornar dados referente ao departamento deletado na aplicação