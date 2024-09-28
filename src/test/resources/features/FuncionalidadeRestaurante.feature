# language: pt
Funcionalidade: API - Restaurante

  Cenário: Cadastrar um novo restaurante
    Quando submeter um novo restaurante
    Então o restaurante é cadastrado com sucesso

  Cenário: Buscar restaurantes cadastrados
    Dado que existem restaurantes cadastrados
    Quando buscar restaurantes sem filtro
    Então deve retornar todos os restaurantes cadastrados