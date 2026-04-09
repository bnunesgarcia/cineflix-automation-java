#language: pt
Funcionalidade: Após acesso aplicação todas as telas deverão conter
    Eu como analista de testes
    Quero acessar a aplicação cineflix da recordTv
    Para testar menus, submenus e funcionalidades do dashboard

    Contexto: 
    Dado que eu acesse a aplicacao cineflix
    E realizar o login na aplicacao com sucesso

    @automacao
    Cenário: Validar barra de menus fixa no topo do sistema
        Entao o sistema deve conter uma barra de menus fixa no topo da tela
        E a barra fixa deve conter Logo da RecordTV junto ao nome do sistema
        E a barra fixa deve conter o botao de alteracao de tema do sistema
        # E a barra fixa deve conter funcionalidade de Encerrar sessao a direita
        E a barra fixa deve conter o botao Buscar

    @automacao
    Esquema do Cenário: Validar os menus disponiveis na barra fixa
        Entao a barra fixa deve conter o menu <menu>
        Exemplos:
            | menu                   |
            | Administrar            |
            | Relatorios             |
            | Cadastro               |
            | Visualizar Programacao |

    @automacao
    Cenário: Validar submenus do menu Administrar
        Quando clicar no menu "Administrar"
        Então o sistema deve exibir os seguintes submenus:
            | Usuário          |
            | Departamento     |
            | Perfil de Acesso |
            | Log de Atividade |

    @automacao
    Cenário: Validar submenus do menu Relatório
        Quando clicar no menu "Relatório"
        Então o sistema deve exibir os seguintes submenus:
            | Catálogo                        |
            | Títulos Vencidos                |
            | Títulos Vencidos por Exibição   |
            | Títulos em Janela               |
            | Títulos Disponíveis             |
            | Títulos que ficarão Disponíveis |
            | Registro de Títulos Ancine      |

    @automacao
    Cenário: Validar submenus do menu Cadastro
        Quando clicar no menu "Cadastro"
        Então o sistema deve exibir os seguintes submenus:
            | Sessão de Filmes |

    @automacao
    Cenário: Validar o menu Visualizar Programacao
        E clicar no menu "Visualizar Programação"
        Entao eu devo ser direcionado para a tela de cadastro de filmes programados para serem exibidos

    @automacao
    Cenário: Validar o botao Buscar
        E clicar no botao Buscar
        Entao o sistema deve exibir um campo texto para busca de titulos
        E o sistema deve exibir um campo de busca por filtros

    @automacao 
    Cenário: Validar o modal de busca por filtros
        # Validar BUG
        E clicar no botao Buscar
        Quando clicar no campo de busca por filtros
        Entao a busca avancada atraves do filtro deve conter botao de limpar campos preenchidos ou selecionados
        # O botao abaixo foi substituido por um botão de fechar o modal, cancelando os filtros
        # E a busca avancada atraves do filtro deve conter botao cancelar para cancelar operacao de filtro
        E a busca avancada atraves do filtro deve conter botao filtrar para executar o filtro
        E a busca avancada atraves do filtro deve conter flag "Inédito"
        E a busca avancada atraves do filtro deve conter flag "Blockbuster"
        E o campo de busca deve conter os seguintes filtros:
            | Distribuidora                 |
            | Classificação                 |
            | Tipo                          |
            | Gênero                        |
            | Vigência do Contrato - Início |
            | Vigência do Contrato - Fim    |
            | Janela de Exibição - Início   |
            | Janela de Exibição - Fim      |
            | Tempo Início (Minutos)        |
            | Tempo Fim (Minutos)           |
            | Elenco                        |
            | Ano Produção                  |
            | Idioma                        |