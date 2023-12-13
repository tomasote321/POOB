class ExpertComputerPlayer extends ComputerPlayer {
    public ExpertComputerPlayer(String name, Color stoneColor, int initialStones, String stoneType) {
        super(name, stoneColor, initialStones, stoneType);
    }

    @Override
    public void makeMove(Gomoku game) {
        // Lógica para un movimiento experto (5 en raya)
        // Implementa la estrategia específica para este tipo de jugador
    }
}