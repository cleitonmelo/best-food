package com.fiap.techchalleng.best_food.bdd;

abstract class BaseBdd {

    private final String URI = "http://localhost:8080/api/v1";

    public String getUriReservas()
    {
        return URI + "/reservas";
    }

    public String getUriRestaurantes()
    {
        return URI + "/restaurantes";
    }

    public String getUriComentarios()
    {

        return URI + "/comentarios";
    }

}
