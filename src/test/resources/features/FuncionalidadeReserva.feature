# language: pt
Funcionalidade: API - Reservas

  Cenário: Cadastrar uma nova reserva
    Quando submeter uma nova reserva
    Então a reserva é cadastrada com sucesso

  Cenário: Cancelar a reserva
    Dado que uma reserva já foi cadastrada
    Quando requisitar o cancelamento da reserva
    Então a reserva é cancelada com sucesso