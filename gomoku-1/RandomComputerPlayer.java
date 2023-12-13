class RandomComputerPlayer extends ComputerPlayer {
    public RandomComputerPlayer(String name, Color stoneColor, int initialStones, String stoneType) {
        super(name, stoneColor, initialStones, stoneType);
    }

    @Override
    public void makeMove(Gomoku game) {
        // Lógica para un movimiento aleatorio
        // Implementa la estrategia específica para este tipo de jugador
        Random random = new Random();
        int row = random.nextInt(game.getBoard().getSize());
        int col = random.nextInt(game.getBoard().getSize());

        // Realiza el movimiento aleatorio
        game.addStone(row, col, stones.get(0));
    }
}