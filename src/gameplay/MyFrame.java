//Torok Szabolcs - tsim2095 - Java project / 2022

package gameplay;

import character.CharacterInitializePanel;
import character.InitializeButtons;

import javax.swing.*;
import java.awt.*;

//main frame of the application where everything is set up, initialized and ran
public class MyFrame extends JFrame {

    private final JPanel cardContainer;
    private final JPanel gameContainer;

    public MyFrame() {
        setTitle("Mighty JumpCraft");
        int frameHeight = 780;
        int frameWidth = 500;
        setBounds(200, 200, frameWidth, frameHeight + 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //initialization of components:
        //main containers
        cardContainer = new JPanel();
        CardLayout crd = new CardLayout();
        //main container panels swapped by the card layout of the card container
        JPanel initContainer = new ContainerPanel("resources/og4.png");
        gameContainer = new ContainerPanel("resources/space-background.png");
        JPanel gameOverContainer = new JPanel();
        gameOverContainer.setBackground(new Color(255, 128, 0));

        //panels
        //secondary panels used inside the containers with different functionalities
        CharacterInitializePanel initPanel = new CharacterInitializePanel();
        InitializeButtons initializeButtons = new InitializeButtons(initPanel);
        GamePanel gamePanel = new GamePanel(frameWidth, frameHeight, initPanel);
        ScorePanel scorePanel = new ScorePanel();
        GameOverPanel gameOverPanel = new GameOverPanel(this);
        ControlPanel controlPanel = new ControlPanel(gamePanel, scorePanel, cardContainer, crd, gameOverPanel);

        //setup main card container
        getContentPane().add(cardContainer);
        cardContainer.setBounds(200, 200, frameWidth, frameHeight);
        cardContainer.setLayout(crd);
        cardContainer.setFocusable(true);

        //setup of panel for character initialization
        initContainer.setLayout(new BorderLayout());
        initContainer.add(initPanel, BorderLayout.CENTER);
        initContainer.add(initializeButtons, BorderLayout.WEST);
        JButton startButton = new JButton("Start Game!");
        initContainer.add(startButton, BorderLayout.SOUTH);

        startButton.addActionListener(e -> {
            crd.next(cardContainer);
            gameContainer.getComponent(0).requestFocusInWindow();
        });

        initPanel.setOpaque(false);
        initializeButtons.setOpaque(false);
        cardContainer.add(initContainer);

        //setup of gameplay related components
        gameContainer.add(controlPanel);
        gameContainer.setLayout(new BorderLayout());
        gameContainer.add(scorePanel, BorderLayout.NORTH);
        scorePanel.setOpaque(false);
        gameContainer.add(gamePanel, BorderLayout.CENTER);
        gamePanel.setOpaque(false);
        cardContainer.add(gameContainer);

        //game over
        gameOverContainer.add(gameOverPanel);
        cardContainer.add(gameOverContainer);

        initContainer.getComponent(0).requestFocusInWindow();
        setVisible(true);
    }
}