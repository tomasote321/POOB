import java.awt.Color;
import java.util.Random;

class AggressiveComputerPlayer extends ComputerPlayer {
    public AggressiveComputerPlayer(String name, Color stoneColor, int initialStones, String stoneType) {
        super(name, stoneColor, initialStones, stoneType);
    }

    @Override
    public void makeMove(Gomoku game) {
        // Lógica para un movimiento agresivo (defensivo)
        // Implementa la estrategia específica para este tipo de jugador
    }
}