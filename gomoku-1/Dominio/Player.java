package Dominio;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;


public abstract class Player {
    private String name;
    private Color stoneColor;
    protected int remainingStones;
    private List<Stone> stones;
    private String stoneType;
    private int puntaje;
    private static final int MAX_STONES = 1000;
     
    public Player(String name, Color stoneColor, int initialStones, String stoneType ) {
        this.name = name;
        this.stoneColor = stoneColor;
        this.remainingStones = initialStones;
        this.stones = new ArrayList<>();
        this.puntaje = 0;
        this.stoneType = stoneType;
        
        for (int i = 0; i < initialStones; i++) {
            // Modificado para alternar entre piedras normales y temporales
            Stone stone = (i % 2 == 0) ? new NormalStone() : new TemporalStone();
            stones.add(stone);
        }
    }

    public String getName() {
        return name;
    }

    public Color getStoneColor() {
        return stoneColor;
    }
    
    public String getStoneType() {
        return stoneType;
    }
    
    public int getRemainingStones() {
        return remainingStones;
    }
    
    public void decreaseStones() {
        if (remainingStones > 0) {
            remainingStones--;
        }
    }
    public int getPuntaje() {
        return puntaje;
    }

    public void sumarPuntosEliminarPiedraEnemiga() {
        puntaje += 100;
    }

    public void restarPuntosEliminarPiedraPropia() {
        puntaje -= 50;
    }

    public void sumarPuntosUsarPiedraEspecial() {
        puntaje += 100;
    }
    
    
    public void sumarPiedraExtra() {
        // Lógica para poner una piedra extra al jugador
        if (remainingStones < MAX_STONES) {
            remainingStones++;
        }
        // Restablecer el puntaje a 0 después de otorgar una piedra extra
        puntaje = 0;
    }
    
    public void sumarPuntosGanarPartida() {
        // Lógica para otorgar puntos al ganar una partida
        puntaje += 500; // Puedes ajustar la cantidad de puntos según tus criterios
        sumarPiedraExtra();
    }
    
    public void setRemainingStones(int remainingStones) {
        this.remainingStones = remainingStones;
    }
}
