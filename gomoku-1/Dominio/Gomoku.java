package Dominio;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

public class Gomoku {
    private Board board;
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;
    public boolean gameEnded = false;

    public Gomoku(Board board, Player playerOne, Player playerTwo) {
        this.board = board;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.currentPlayer = playerOne;
    }

    public List<Point> getOccupiedPositions() {
        List<Point> occupiedPositions = new ArrayList<>();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (board.hasStoneAt(row, col)) {
                    occupiedPositions.add(new Point(row, col));
                }
            }
        }

        return occupiedPositions;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public Board getBoard() {
        return board;
    }
    
    public void addStone(int row, int col, Stone stone) {
        board.placeStone(stone, row, col);
    }
    
    public void handleMove(int row, int col) {
        Stone stone = board.getStoneAt(row, col);
        if (stone == null) {
            return;
        }
        // Lógica para manejar el movimiento y determinar si se deben sumar o restar puntos
        if (stone.isEnemyStone()) {
            currentPlayer.sumarPuntosEliminarPiedraEnemiga();
        } else if (stone.isOwnStone()) {
            currentPlayer.restarPuntosEliminarPiedraPropia();
        } else if (stone.isSpecialStone()) {
            currentPlayer.sumarPuntosUsarPiedraEspecial();
        }
        checkAndHandleExtraStone();
    }
    
    private void checkAndHandleExtraStone() {
        // Verificar si el jugador alcanzó 1000 puntos y otorgar una piedra extra
        if (currentPlayer.getPuntaje() >= 1000) {
            currentPlayer.sumarPiedraExtra();
        }
    }

    public boolean checkWinner(int row, int col) {
        Stone stone = board.getStoneAt(row, col);
        if (stone == null) {
            return false;
        }
        // Verificar horizontal
        if (checkConsecutive(row, col, 0, 1, stone) + checkConsecutive(row, col, 0, -1, stone) >= 4) {
            return true;
        }
        // Verificar vertical
        if (checkConsecutive(row, col, 1, 0, stone) + checkConsecutive(row, col, -1, 0, stone) >= 4) {
            return true;
        }
        // Verificar diagonal derecha
        if (checkConsecutive(row, col, 1, 1, stone) + checkConsecutive(row, col, -1, -1, stone) >= 4) {
            return true;
        }
        // Verificar diagonal izquierda
        if (checkConsecutive(row, col, 1, -1, stone) + checkConsecutive(row, col, -1, 1, stone) >= 4) {
            return true;
        }
        return false;
    }
    
    private int checkConsecutive(int row, int col, int rowIncrement, int colIncrement, Stone stone) {
        int count = 0;
        // Contar piedras consecutivas en la dirección dada
        for (int i = -4; i <= 4; i++) {
            int newRow = row + i * rowIncrement;
            int newCol = col + i * colIncrement;
    
            if (board.isValidPosition(newRow, newCol) && board.getStoneAt(newRow, newCol) == stone) {
                count++;
            } else {
                // Si encontramos una posición que no contiene la piedra actual, reiniciamos el contador
                count = 0;
            }
    
            // Si encontramos 5 piedras consecutivas, no es necesario seguir verificando
            if (count >= 5) {
                return count;
            }
        }
    
        return count;
    }


    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == playerOne) ? playerTwo : playerOne;
    }

    public void redistributeStones() {
        if (playerOne.getRemainingStones() == 0 && playerTwo.getRemainingStones() == 0) {
            int initialStones = board.getSize();
            playerOne.setRemainingStones(initialStones);
            playerTwo.setRemainingStones(initialStones);
        }
    }
}
