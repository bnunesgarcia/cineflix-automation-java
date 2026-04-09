#language: pt
Funcionalidade: Após acesso a aplicação, a primeira tela apresentada será a Home
    Eu como analista de testes
    Quero acessar a aplicação cineflix da recordTv
    Para testar a home do sistema

    Contexto: 
    Dado que eu acesse a aplicacao cineflix
    E realizar o login na aplicacao com sucesso

    @tst_manual
    Cenário: Validar exibição de sessões de tipos de conteúdo na vertical
        # Então o sistema deve listar as sessões de tipo de filmes na vertical:
        # | AÇÃO          |
        # | COMÉDIA       |
        # | SCI-FI        |
        # | DOCUMENTARIO  |
        # | ANIMAÇÃO      |
        # | DRAMA         |
        # | ÉPICO         |

    @tst_manual
    Cenário: Validar organização de gêneros e carrossel
        # Quando eu visualizar a sessões
        # Então os gêneros correspondentes da sessao devem ser listados na vertical
        # E os títulos de cada gênero devem estar na horizontal em um carrossel
        # E o carrossel deve possuir botões de navegação para direita e esquerda se tiver titulo disponivel

    @tst_manual
    Cenário: Validar exibição de banners (Regra GMediaTX)
        # Então cada título deve exibir o banner que representa o título cadastrado no GMediaTX
        # E o sistema deve exibir um banner com o nome do título escrito caso não haja um banner cadastrado no GMediaTX

    @tst_manual
    Cenário: Validar expansão do banner
        # Quando eu sobrepor o mouse sobre o banner de um título
        # Então o banner deve ser exibido em modo expandido
        # E deve apresentar o botão para visualizar o trailer centralizado sobre a imagem

    @tst_manual
    Cenário: Reprodução de trailer via janela de diálogo (YouTube)
        # Quando eu clicar no botão de visualização do trailer de um título
        # Então uma janela de diálogo deve ser exibida centralizada
        # E o vídeo correspondente do YouTube deve ser carregado para reprodução
        # E o link do vídeo deve ser o mesmo cadastrado no GMediaTX

    @tst_manual
    Cenário: Validar banners que não possuem trailer cadastrado no GmediaTX
        # Quando eu sobrepor o mouse sobre o banner de um título que não possui trailer cadastrado no GMediaTX
        # Então uma mensagem solicitando Atualização do GmediaTx deve ser exibida ao usuário
