package Dominio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board{
    
    private int[][] boardSize;
    private int size;
    private Stone[][] stones;
    
    public Board(int size) { 
        this.size = size;
        stones = new Stone[size][size];
        boardSize = new int [size][size];
        initializeBoard();
    }

    private void initializeBoard(){
        for (int i = 0; i < size ; i++){
            for (int j = 0; j < size ; j++){
                boardSize[i][j] = 0;
            }
        }
    }
    
    public void printBoard(){
        for (int i = 0; i < size ; i++){
            for (int j = 0; j < size ; j++){
                //System.out.print(boardSize[i][j] + "");
            }
            //System.out.println();
        }
    }
    
    public static Board createNewBoard(int size){
        return new Board(size);
    }

    public JPanel createBoardPanel(ActionListener cellClickListener) {
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(size, size));
    
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final int row = i; 
                final int col = j;  
                JButton cellButton = new JButton();
                cellButton.setPreferredSize(new Dimension(40, 40));
                cellButton.addActionListener(cellClickListener);
                cellButton.setActionCommand(row + "," + col);  // Configura el comando de acción con las coordenadas
                cellButton.setBackground(new Color(160, 82, 45));
                cellButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                boardPanel.add(cellButton);
            }
        }
    
        return boardPanel;
    }

    
    public int getSize() {
        return size;
    }
    
    public void placeStone(Stone stone, int row, int col) {
        if (isValidPosition(row, col)) {
            stones[row][col] = stone;
        }
    }
    
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size && stones[row][col] == null;
    }
    
    public boolean hasStoneAt(int row, int col) {
        if (isValidPosition(row, col)) {
            return stones[row][col] != null;
        }
        return false;
    }
    
    public Stone getStoneAt(int row, int col) {
        if (isValidPosition(row, col)) {
            return stones[row][col];
        }
        return null;
    } 

}
