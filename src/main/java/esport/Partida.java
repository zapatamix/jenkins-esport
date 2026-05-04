package Esport;

import java.io.Serializable;

public class Partida implements Serializable {

    private int idPartida;
    private String nick;
    private int kills;
    private int deaths;
    private int assists;

    public Partida(int idPartida, String nick, int kills, int deaths, int assists) {
        this.idPartida = idPartida;
        this.nick = nick;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
    }

    public int getIdPartida() {
        return idPartida;
    }

    @Override
    public String toString() {
        return "P" + idPartida + " - " + nick +
                " K:" + kills +
                " D:" + deaths +
                " A:" + assists;
    }
}
