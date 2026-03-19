#language: pt
Funcionalidade: Validar funcionalidade de login
    Eu como analista de testes
    Quero acessar a aplicação demoqa
    Para testar a funcionalidade de login

    Esquema do Cenário: Criar novo usuario
        Dado que eu acesse a aplicacao demoqa
        Quando eu clico no menu <menu>
        E eu acesso o <submenu>
        E acesso a tela de New User
        Entao cadastro um novo usuario com sucesso

        Exemplos:
            | menu                   | submenu |
            | book store application | login   |

    Esquema do Cenário: Realizar login
        Dado que eu acesse a aplicacao demoqa
        Quando eu clico no menu <menu>
        E eu acesso o <submenu>
        Entao realizo login com sucesso
        E eu realizo o logout com sucesso

        Exemplos:
            | menu                   | submenu |
            | book store application | login   |

    Esquema do Cenário: Cenarios negativos de realizacao de login
        Dado que eu acesse a aplicacao demoqa
        Quando eu clico no menu <menu>
        E eu acesso o <submenu>
        Entao realizo login inserindo <motivo_erro>
        E o <motivo_erro> deve gerar a mensagem de erro <mensagem>

        Exemplos:
            | menu                   | submenu | motivo_erro       | mensagem                      |
            | book store application | login   | senha invalida    | Invalid username or password! |
            | book store application | login   | usuario invalido  | Invalid username or password! |

    