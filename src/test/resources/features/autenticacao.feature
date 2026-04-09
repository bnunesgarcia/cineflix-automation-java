#language: pt
Funcionalidade: Método de autenticação deve ser realizado através de MFA
    Eu como analista de testes
    Quero acessar a aplicação cineflix da recordTv
    Para testar a funcionalidade de realização de login

    @automacao
    Cenário: Validar tela de login da nova aplicação Cineflix
        Dado que eu acesse a aplicacao cineflix
        Entao a tela deve conter o logo da recordTv
        E a tela deve conter o nome do sistema
        E a tela deve conter o botao para acesso ao sistema com login de rede
        E a tela deve conter o botao para acesso via bot ao sistema
        E apos clique no botao de acesso via bot deve ser disponiblizado os campos para preenchimento das credenciais

    @automacao
    Cenário: Validar realizacao de login da nova aplicação Cineflix
        Dado que eu acesse a aplicacao cineflix
        E realizar o login na aplicacao com sucesso
        Entao o acesso deve ser realizado com sucesso

    @tst_manual
    Cenário: Validar login invalido:
        # Dado que eu acesse a aplicacao cineflix
        # E realizar o login na aplicacao com email e senha invalidos
        # Entao o acesso deve ser negado
        # E deve ser exibido a mensagem:
        # |Http failure response for https://cineflix-qas.recordtv.com.br/api/user/email/brung@rederecord.com.br: 401 OK|