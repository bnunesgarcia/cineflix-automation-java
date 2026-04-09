#language: pt
Funcionalidade: Tela de detalhes do título
    Eu como analista de testes
    Quero acessar a aplicação cineflix da recordTv
    Para testar os detalhes dos títulos do sistema

    Contexto: 
    Dado que eu acesse a aplicacao cineflix
    E realizar o login na aplicacao com sucesso
    E clicar em um banner pelo título "Mullewapp - Das Große Kinoabenteuer Der Freunde" na tela inicial

@automacao
Cenário: Validar elementos da área fixa à esquerda
    # Então o sistema deve exibir o banner do título
    E deve exibir os botões de ação:
    | Voltar           |
    | Contratos        |
    | Trailer          |
    | Informação       |
    | Ancine           |
    | Reservar         |


@automacao
Cenário: Validar detalhes do título
    Então ao lado direito da tela sistema deve exibir os seguintes detalhes:
    | Título                |
    | Nº Processo           |
    | Nº Episódios          |
    | Distribuidor          |
    | Diretor               |
    | Título original       |
    | Classificação         |
    | Ano                   |
    | Tempo                 |
    | Categoria             |
    | Exibições Permitidas  |
    | Consumido             |
    | Inédito               |
    # | Sinópse             |
    E deve conter uma tabela com Detalhes de Exibições
    # E a tabela de "Detalhes de Exibições" deve conter as colunas:
    # | Exibição |
    # | Dia      |
    # | Período  |
    E deve exibir as listas de cards para "Direção"
    E deve exibir as listas de cards para "Elenco"
    # Validar manualmente o nome e as fotos
    
@tst_manual
Esquema do Cenário: Validar marcadores e situações dinâmicas do título
    # Dado que o título possua a condição "<condicao>"
    # Então o sistema deve exibir o marcador "<marcador>" sobre o banner
    # E a situação deve ser exibida como "<situacao>"

    # Exemplos:
    # | condicao                          | marcador    | situacao             |
    # | blockbuster e contrato ativo      | Blockbuster | Ativo                |
    # | vencido por data de contrato      | Vencido     | Vencido por contrato |
    # | exibições esgotadas               | Vencido     | Vencido por consumo  |

@automacao
Cenário: Validar visualização de contratos em caixa de diálogo
    Quando eu clicar no botão "Contratos"
    # E Aguarda 5 segundos
    # Validar BUG (Ainda nao está pronto)
    # Então o sistema deve abrir uma caixa de diálogo
    # E deve exibir a tabela de vigência com as colunas:
    # | Data Contrato | Episódio | Exibições | Consumido | Planejado | Vigência Início | Vigência Fim | Início Janela | Fim Janela | Custo |
    # E deve exibir a tabela de direitos com as colunas:
    # | Direitos negociados | Data início | Sem limite | Data fim | Notas Contrato |

@automacao
Cenário: Validar modal de Informações Técnicas
    Quando eu clicar no botão "Informação"
    # Validar BUG (Ainda nao está pronto)
    # Então o sistema deve exibir os detalhes técnicos:
    # | Título | Nº Contrato | Descrição | Referência Externa | HD | Áudio Descrição | Inédito | IMDb | Filme B |

@automacao
Cenário: Validar botão de assistir trailer
    Quando eu clicar no botão "Trailer"
    # Então deve abrir caixa de diálogo contendo o trailer cadastrado pronto para executar

@automacao
Cenário: Validar botão Ancine
    E deve exibir os botões de ação:
    | Ancine           |
    Quando eu clicar no botão "Ancine"
    # Validar BUG (Ainda nao está pronto)
    # Então o sistema deve abrir uma caixa de diálogo
    # E deve exibir as informações:
    # | Título | Numero do Contrato | Registro Ancine | Número Condecine | Vencimento Ancine |

@automacao
Cenário: Validar botão Reservar
    E deve exibir os botões de ação:
    | Reservar           |
    Quando eu clicar no botão "Reservar"
    # Validar BUG (Ainda nao está pronto)
    # Então o sistema deve abrir uma caixa de diálogo
    # E a caixa de dialogo deve conter os campos:
    # | Dia | Sessão  |
    # E deve conter o botão "Reservar"
    # E deve conter o botão "Cancelar"

@tst_manual @nao_faz_parte_da_primeira_fase
Cenário: Realizar reserva de título em uma sessão
    Quando eu clicar no botão "Reservar"
    # E selecionar o dia "15/04/2026"
    # E selecionar a sessão "Cine Record Especial"
    # E clicar no botão "Confirmar Reserva"
    # Então o sistema deve processar a alocação com sucesso
