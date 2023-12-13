package Presentation;


import Dominio.Board;
import Dominio.Gomoku;
import Dominio.Player;
import Dominio.HumanPlayer;
import Dominio.Stone;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;
import java.util.HashSet;

public class GomokuGUI extends JFrame {
    private Gomoku game;
    private Board board;
    private JComboBox<String> boardSize;
    private JComboBox<String> gameMode;
    private JComboBox<String> machineDifficulty;
    private JLabel selectMachineDifficultyLabel;
    private JButton newGameButton;
    private JButton exitButton;
    private JPanel boardPanel;
    private boolean gameStarted = false;
    private JPanel playerOnePanel;
    private JPanel playerTwoPanel;
    private JLabel playerOneLabel;
    private JLabel playerTwoLabel;
    private JFrame boardFrame;
    private JLabel playerOneTurnLabel;
    private JLabel playerTwoTurnLabel;
    private JComboBox<String> newGameMode;
    private JLabel selectNewGameModeLabel;
    private boolean isNewGameMode;
    private int currentBoardSize;
    private Set<String> clickedButtons = new HashSet<>();
    private JLabel playerOneScoreLabel;
    private JLabel playerTwoScoreLabel;
    //private PuntajeJuego puntajeJuego;
    
    
    private GomokuGUI() {
        prepareElements();
        prepareActions();
        initializeBoard();
        //puntajeJuego = new PuntajeJuego();
        
    }

    private void prepareElements() {
        setTitle("Gomoku");
        setWindowSize();

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel gomokuLabel = new JLabel("Gomoku");
        gomokuLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        gomokuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(Box.createVerticalStrut(30));
        titlePanel.add(gomokuLabel);

        JPanel boardSizePanel = new JPanel();
        JLabel selectBoardSizeLabel = new JLabel("Select Board Size");
        boardSize = new JComboBox<>(new String[]{"15x15", "10x10"});
        boardSize.setMaximumSize(new Dimension(200, 30));
        selectBoardSizeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        boardSize.setAlignmentX(Component.CENTER_ALIGNMENT);
        boardSizePanel.setLayout(new BoxLayout(boardSizePanel, BoxLayout.Y_AXIS));
        boardSizePanel.add(selectBoardSizeLabel);
        boardSizePanel.add(boardSize);

        // Add JComboBox for game mode
        JLabel selectGameModeLabel = new JLabel("Select Game Mode");
        gameMode = new JComboBox<>(new String[]{"Player vs. Player", "Player vs. Machine"});
        gameMode.setMaximumSize(new Dimension(200, 30));
        selectGameModeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameMode.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add JComboBox for new game mode
        JLabel selectNewGameModeLabel = new JLabel("Select New Game Mode");
        JComboBox<String> newGameMode = new JComboBox<>(new String[]{"Normal", "QuickTime", "Piedras Limitadas"});
        newGameMode.setMaximumSize(new Dimension(200, 30));
        selectNewGameModeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameMode.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add JComboBox for machine difficulty
        selectMachineDifficultyLabel = new JLabel("Select Machine Difficulty");
        machineDifficulty = new JComboBox<>(new String[]{"Miedosa", "Experta", "Agresiva"});
        machineDifficulty.setMaximumSize(new Dimension(200, 30));
        selectMachineDifficultyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        machineDifficulty.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initially, make machine difficulty and its label invisible
        machineDifficulty.setVisible(false);
        selectMachineDifficultyLabel.setVisible(false);

        boardSizePanel.add(Box.createVerticalStrut(10)); // Additional spacing
        boardSizePanel.add(selectGameModeLabel);
        boardSizePanel.add(gameMode);

        // Add JComboBox only when "Player vs. Machine" is selected
        boardSizePanel.add(Box.createVerticalStrut(10)); // Additional spacing
        boardSizePanel.add(selectMachineDifficultyLabel);
        boardSizePanel.add(machineDifficulty);
        
        boardSizePanel.add(Box.createVerticalStrut(10)); // Additional spacing
        boardSizePanel.add(selectNewGameModeLabel);
        boardSizePanel.add(newGameMode);
        titlePanel.add(boardSizePanel);

        newGameButton = new JButton("New Game");
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(Box.createVerticalStrut(30));
        titlePanel.add(newGameButton);
        titlePanel.add(Box.createVerticalStrut(30));

        exitButton = new JButton("Exit Game");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(Box.createVerticalStrut(10)); // Additional spacing
        titlePanel.add(exitButton);
        titlePanel.add(Box.createVerticalStrut(30));

        add(titlePanel, BorderLayout.NORTH);
    }

    private void initializePlayerInfoPanels() {
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        // Panel for Player 1
        playerOnePanel = new JPanel();
        playerOnePanel.setLayout(new BoxLayout(playerOnePanel, BoxLayout.Y_AXIS));
        playerOnePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        playerOneLabel = new JLabel("Player 1 Info");
        playerOneTurnLabel = new JLabel("");
        JLabel playerOneScoreLabel = new JLabel("Score: 0"); // Agrega esta línea
        playerOnePanel.add(playerOneLabel);
        playerOnePanel.add(playerOneTurnLabel);
        playerOnePanel.add(playerOneScoreLabel); // Agrega esta línea

        // Panel for Player 2
        playerTwoPanel = new JPanel();
        playerTwoPanel.setLayout(new BoxLayout(playerTwoPanel, BoxLayout.Y_AXIS));
        playerTwoPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        playerTwoLabel = new JLabel("Player 2 Info");
        playerTwoTurnLabel = new JLabel("");
        JLabel playerTwoScoreLabel = new JLabel("Score: 0"); // Agrega esta línea
        playerTwoPanel.add(playerTwoLabel);
        playerTwoPanel.add(playerTwoTurnLabel);
        playerTwoPanel.add(playerTwoScoreLabel); // Agrega esta línea.add(playerTwoLabel);
        playerTwoPanel.add(playerTwoTurnLabel);
        // Add panels to the GUI
        infoPanel.add(playerOnePanel, BorderLayout.WEST);
        infoPanel.add(playerTwoPanel, BorderLayout.EAST);

        // Agregar el panel de información al contenedor principal
        add(infoPanel, BorderLayout.WEST);
    }
    
    private void updatePlayerScore() {
        updatePlayerScoreLabel(playerOneScoreLabel, game.getPlayerOne());
        updatePlayerScoreLabel(playerTwoScoreLabel, game.getPlayerTwo());
    }
    
    private void updatePlayerScoreLabel(JLabel label, Player player) {
        label.setText("Score: " + player.getPuntaje());
    }


    private void setWindowSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        setSize(screenWidth / 4, screenHeight / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void prepareActions() {
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewGame();
                
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmExit();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });

        gameMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isVsMachine = "Player vs. Machine".equals(gameMode.getSelectedItem());
                machineDifficulty.setVisible(isVsMachine);
                selectMachineDifficultyLabel.setVisible(isVsMachine);
                newGameMode.setVisible(isNewGameMode);
                selectNewGameModeLabel.setVisible(isNewGameMode);
            }
        });
    }

    private void confirmExit() {
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void initializeBoard() {
        int initialSize = 10;
        board = new Board(initialSize);
        boardPanel = new JPanel();
    }

    private void createNewGame() {
        String selectedSize = (String) boardSize.getSelectedItem();
    
        if (selectedSize != null) {
            int selectedSizeValue = Integer.parseInt(selectedSize.split("x")[0]);
            String[] stoneTypes = {"NormalStone", "TemporalStone"}; 
            if (!gameStarted) {
                // Obtener nombre y color del jugador uno
                String playerOneName = JOptionPane.showInputDialog("Enter Player One's Name:");
                String[] colorOptions = {"Negro", "Blanco"};
                String playerOneColorString = (String) JOptionPane.showInputDialog(
                        this,
                        "Choose Player One's Stone Color:",
                        "Color Selection",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        colorOptions,
                        colorOptions[0]);
    
                Color playerOneColor = playerOneColorString.equals("Negro") ? Color.BLACK : Color.WHITE;
                int initialStones = selectedSizeValue;
                String stoneTypeForPlayerOne = stoneTypes[0];
                // Modificado para darle algunas piedras normales y temporales al jugador uno
                Player playerOne = new HumanPlayer(playerOneName, playerOneColor, initialStones, stoneTypeForPlayerOne);
                // Obtener nombre y color del jugador dos
                String playerTwoName = JOptionPane.showInputDialog("Enter Player Two's Name:");
                String playerTwoColorString = (String) JOptionPane.showInputDialog(
                        this,
                        "Choose Player Two's Stone Color:",
                        "Color Selection",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        colorOptions,
                        colorOptions[1]);
    
                Color playerTwoColor = playerTwoColorString.equals("Negro") ? Color.BLACK : Color.WHITE;
                String stoneTypeForPlayerTwo = stoneTypes[1];
                Player playerTwo = new HumanPlayer(playerTwoName, playerTwoColor, initialStones, stoneTypeForPlayerTwo);
    
                // Iniciar el juego
                game = new Gomoku(board, playerOne, playerTwo);
                initializePlayerInfoPanels();
                updatePlayerInfo(playerOneLabel, playerOne);
                updatePlayerInfo(playerTwoLabel, playerTwo);
                updatePlayerInfo(playerOneLabel, game.getPlayerOne());
                updatePlayerInfo(playerTwoLabel, game.getPlayerTwo());
                updateTurnMessage();
                gameStarted = true;
            }
    
            // Create a new board regardless of the selected size
            board = new Board(selectedSizeValue);
            int windowWidth;
            int windowHeight;
    
            // Adjust window dimensions based on selected size
            if (selectedSizeValue <= 15) {
                windowWidth = 800;
                windowHeight = 680;
            } else {
                windowWidth = 1000;
                windowHeight = 880;
            }
    
            // Handle board frame and panel creation
            if (boardFrame != null) {
                boardFrame.dispose();
            }
    
            boardFrame = new JFrame("Gomoku Board");
            boardFrame.setSize(windowWidth, windowHeight);
            boardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            boardPanel = new JPanel();
            boardPanel.setLayout(new BorderLayout());
            boardPanel.add(playerOnePanel, BorderLayout.WEST);
            boardPanel.add(board.createBoardPanel(this::handleCellClick), BorderLayout.CENTER);
            boardPanel.add(playerTwoPanel, BorderLayout.EAST);
            boardPanel.revalidate();
            boardPanel.repaint();
            initializePlayerInfoPanels(); // Llamada al método modificado
            boardPanel.add(playerOnePanel, BorderLayout.WEST);
            boardPanel.add(playerTwoPanel, BorderLayout.EAST);
            boardFrame.add(boardPanel);
            boardFrame.setVisible(true);
    
            updatePlayerInfo(playerOneLabel, game.getPlayerOne());
            updatePlayerInfo(playerTwoLabel, game.getPlayerTwo());
        } else {
            JOptionPane.showMessageDialog(this, "Select a valid board size", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void handleCellClick(ActionEvent event) {
        int row = -1;
        int col = -1;
    
        if (boardPanel != null) {
            String[] coordinates = event.getActionCommand().split(",");
            row = Integer.parseInt(coordinates[0]);
            col = Integer.parseInt(coordinates[1]);
    
            String buttonKey = row + "," + col;
    
            // Verificar si el botón ya ha sido clickeado
            if (clickedButtons.contains(buttonKey)) {
                return;
            }
    
            JButton clickedButton = (JButton) event.getSource();
            if (!board.hasStoneAt(row, col)) {
                Color currentPlayerColor = game.getCurrentPlayer().getStoneColor();
                Stone newStone = Stone.createNormalStone();
                
                newStone.setColor(currentPlayerColor);
                newStone.applyVisualsToButton(clickedButton);
    
                // Agregar el botón a la lista de botones clickeados
                clickedButtons.add(buttonKey);
    
                if (game.checkWinner(row, col)) {
                    System.out.println("¡Tenemos un ganador!");
                    disableCellClick();
                    game.getCurrentPlayer().sumarPuntosGanarPartida();
                    updatePlayerScore();
                } else {
                    // Continuar con el cambio de jugador y actualización de información
                    game.getCurrentPlayer().decreaseStones();
                    game.switchPlayer();
                    updateTurnMessage();
                    updatePlayerInfo(playerOneLabel, game.getPlayerOne());
                    updatePlayerInfo(playerTwoLabel, game.getPlayerTwo());
    
                    // Redistribuir las piedras solo cuando ambos jugadores se quedan sin ellas
                    if (game.getPlayerOne().getRemainingStones() == 0 && game.getPlayerTwo().getRemainingStones() == 0) {
                        game.redistributeStones();
                        updatePlayerInfo(playerOneLabel, game.getPlayerOne());
                        updatePlayerInfo(playerTwoLabel, game.getPlayerTwo());
                    }
                }
            }
        }
    }
    
    private void disableCellClick() {
        // Desactivar todos los botones
        Component[] components = boardPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.setEnabled(false);
            }
        }
    }
    
    private void updateTurnMessage() {
        if (game.getCurrentPlayer() == game.getPlayerOne()) {
            playerOneTurnLabel.setText("Es tu turno");
            playerTwoTurnLabel.setText(""); // Reinicia el mensaje para el otro jugador
        } else {
            playerTwoTurnLabel.setText("Es tu turno");
            playerOneTurnLabel.setText(""); // Reinicia el mensaje para el otro jugador
        }
    }
    
    private void updatePlayerInfo(JLabel label, Player player) {
        String info = String.format("<html>Name: %s<br>Color: %s<br>Remaining Stones: %d</html>",
            player.getName(), getColorString(player.getStoneColor()), player.getRemainingStones());
        label.setText(info);
    }   

    private String getColorString(Color color) {
        return (color == Color.BLACK) ? "Negro" : "Blanco";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GomokuGUI gui = new GomokuGUI();
            gui.setVisible(true);
        });
    }
}
