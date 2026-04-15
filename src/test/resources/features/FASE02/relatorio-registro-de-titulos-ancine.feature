#language: pt
Funcionalidade: Tela de relatório de registros de título ancine
    Eu como analista de testes
    Quero acessar a aplicação cineflix da recordTv
    Para testar a tela de relatório de registros de título ancine do sistema

    Contexto: 
    Dado que eu acesse a aplicacao cineflix
    E realizar o login na aplicacao com sucesso
    Quando clicar no menu "Relatório"
    E clicar na opção "Registro de Títulos Ancine"

    @automacao
    Cenário: Validar elementos da tela de relatório de Registro de Títulos Ancine
        Então o sistema deve exibir o titulo "Registro de Títulos Ancine"
        E o sistema deve exibir a tela de relatório "Registro de Títulos Ancine" contendo os seguintes filtros:
        | Período Vencimento Ancine |
        E deve exibir o botão "Limpar filtros"
        E deve exibir o botão "Filtrar"

    @automacao
    Cenário: Validar caixa de visualização de total de itens
        E clicar no botao "Filtrar"
        Então deve ser exibido a caixa de visualização de total de itens encontrados
        E o botao de exportar "Excel" deve estar disponível
        E o botao de exportar "PDF" deve estar disponível

    @automacao
    Cenário: Validar tabela de exibição de resultados
        E clicar no botao "Filtrar"
        Então deve ser exibida a tabela de exibição de resultados contendo as seguintes colunas:
        | Título                |
        | Distribuidora         |
        | Ano Conteúdo          |
        | Número Processo       |
        | Código Contrato       |
        | Descrição do Contrato |
        | Registro Ancine       |
        | Número Condecine      |
        | Vencimento Ancine     |

    @tst_manual
    Cenário: Validar paginação e alteração de quantidade de itens
        # Quando eu selecionar para exibir 05, 10, 25 ou 100 itens por página
        # Então a tabela deve listar no máximo 05, 10, 25 ou 100 itens por pagina
        # E devo visualizar o descritivo da página atual e o total de páginas disponíveis
        # E devo conseguir navegar usando os botões "Próximo" e "Anterior"

    @tst_manual
    Cenário: Validar funcionamento dos filtros de pesquisa
        # Quando eu seleciona o filtro por:
        # | Período Vencimento Ancine |
        # E clicar no botão "Filtrar"
        # Então o filtro deve estar funcionando corretamente
        # E o botão Limpar filtro deve estar funcionando corretamente

    @tst_manual
    Cenário: Validar funcionamento da caixa com total de itens encontrados
        # Quando eu realizar o filtro na tela de relatório de Registro de Títulos Ancine
        # Então a caixa com total de itens deve ser atualizada com o número correto de itens encontrados

    @tst_manual
    Cenário: Validar funcionamento da export do relatório
        # Quando eu realizar o filtro na tela de relatório de Registro de Títulos Ancine
        # Então a caixa com total de itens deve ser exibida
        # E o botao export "Excel" deve estar funcionando corretamente
        # E o botao export "PDF" deve estar funcionando corretamente

    @tst_manual
    Cenário: Validar exibição dos dados na tabela de resultados
        # Quando eu realizar o filtro na tela de relatório de Registro de Títulos Ancine
        # Então a tabela deve exibir corretamente os dados dos itens encontrados