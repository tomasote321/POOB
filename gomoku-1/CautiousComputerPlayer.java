class CautiousComputerPlayer extends ComputerPlayer {
    public CautiousComputerPlayer(String name, Color stoneColor, int initialStones, String stoneType) {
        super(name, stoneColor, initialStones, stoneType);
    }

    @Override
    public void makeMove(Gomoku game) {
        // Lógica para un movimiento cauteloso (evitar cercanía a las piedras enemigas)
        // Implementa la estrategia específica para este tipo de jugador
    }
}