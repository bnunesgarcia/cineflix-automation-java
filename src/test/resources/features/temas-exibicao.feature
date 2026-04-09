#language: pt
Funcionalidade: Aplicação deve conter dois temas de exibição
    Eu como analista de testes
    Quero acessar a aplicação cineflix da recordTv
    Para testar os temas de exibição disponíveis

    Contexto: 
    Dado que eu acesse a aplicacao cineflix
    E realizar o login na aplicacao com sucesso

    @automacao
    Esquema do Cenário: Validar temas de exibicao da aplicacao
        Entao a barra fixa deve conter o botao de alteracao de tema do sistema
        Quando eu clicar no botão de alteração de tema <tema>
        Entao o sistema deve aplicar o tema <tema>

        Exemplos:
            | tema  |
            | Light |
            | Dark  |
