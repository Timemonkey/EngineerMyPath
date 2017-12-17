package model.States;

public interface IStates {
    IStates PesquisaSala(String sala);
    IStates PesquisaItinerario(String sala1, String sala2);
    IStates Anterior();
    IStates Seguinte();
    IStates TerminarPesquisa();
}
