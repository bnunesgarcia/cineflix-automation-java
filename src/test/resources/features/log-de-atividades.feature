#language: pt
Funcionalidade: Log de Atividades
    Como um administrador do sistema Cineflix
    Quero acessar a tela de Log de Atividades
    Para validar atraves dos logs os eventos realizados na aplicação

  Contexto: 
    Dado que eu acesse a aplicacao cineflix
    E realizar o login na aplicacao com sucesso
    Quando clicar no menu "Administrar"
    E clicar na opção "Log de Atividade"
    Entao devo ser redirecionado para a tela de gerenciamento de "Log de Atividade"

    @automacao
    Cenário: Exibir tela de Log de Atividades
        # Validar BUG
        Então devo visualizar o campo de pesquisa por Período de atividade
        E devo visualizar o campo de pesquisa por tipo de Evento
        # E o campo de filtro por tipo de Evento deve conter as opções:
        # Este caso o documento esta Criar, Excluir e Atualizar
        # | Todos     |
        # | Criou     |
        # | Atualizou |
        # | Excluiu   |
        E devo visualizar o campo de pesquisa por usuário
        E devo visualizar os botões "Pesquisar", "Limpar" e "Exportar"
        E a tabela de listagem deve conter as colunas:
        | ID Reserva     |
        | ID Conteúdo    |
        | Data e hora    |
        | Nome Usuário   |
        | Login Usuário  |
        | Operação       |
        | Campo Alterado |
        | Valor Anterior |
        | Valor Atual    |
        # Validar BUG
        # Este caso o documento possui outros nomes de colunas - Abaixo
        # | #ID            |
        # | Data e Hora    |
        # | Nome Usuário   |
        # | Login Usuário  |
        # | Operação       |
        # | Campo Alterado |
        # | Valor Antes    |
        # | Valor Depois   |

    @tst_manual
    Esquema do Cenário: Filtrar Log de Atividade por período, tipo de evento e usuário
    #     E que o usuário informa '<tipo_filtro>' no campo de filtro
    #     Quando o usuário clicar no botão de executar filtros
    #     Então o sistema deve realizar o filtro corretamente

    #     Exemplos:
    #     | tipo_filtro                |
    #     | Período                    |
    #     | Tipo de Evento - Criar     |
    #     | Tipo de Evento - Atualizar |
    #     | Tipo de Evento - Excluir   |
    #     | Usuário                    |

    @tst_manual
    Cenário: Limpar filtros
    #     E inserir valor no campo de filtro
    #     Quando o usuário clicar no botão de limpar filtros
    #     Então o sistema deve limpar o filtro e retornar ao estado nulo
    #     E a listagem deve ser atualizada com todos os perfis de acesso

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

    @db_logs @tst_manual
    Cenário: Validar Logs de Atividade no SQL Server
        # QUando eu consultar os logs de atividade na aplicação
        # E realizar uma query no SQL Server para obter os logs de atividade
        # Então a consulta no SQL Server deve retornar dados dos logs de atividade