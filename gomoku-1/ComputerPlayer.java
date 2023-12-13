abstract class ComputerPlayer extends Player {
    public ComputerPlayer(String name, Color stoneColor, int initialStones, String stoneType) {
        super(name, stoneColor, initialStones, stoneType);
    }

    public abstract void makeMove(Gomoku game);
}
