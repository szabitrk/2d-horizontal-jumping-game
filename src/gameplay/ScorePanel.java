package gameplay;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

//keeps track of score and refreshes it every time the character moves up the platforms
public class ScorePanel extends JPanel {

    private final JLabel scoreBoard1;
    private final JLabel scoreBoard2;
    Font arcadeFont;

    public ScorePanel(){

        scoreBoard1 = new JLabel("Score: ");
        scoreBoard2 = new JLabel("0");

        try{
            arcadeFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/ARCADE_N.ttf")).deriveFont(22f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/ARCADE_N.ttf")));

        }catch (IOException | FontFormatException e){
            e.printStackTrace();
        }

        scoreBoard1.setFont(arcadeFont);
        scoreBoard2.setFont(arcadeFont);

        scoreBoard1.setForeground(Color.WHITE);
        scoreBoard2.setForeground(Color.WHITE);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(scoreBoard1);
        add(scoreBoard2);
        setVisible(true);
    }

    public void setScore(int score) {
        this.scoreBoard2.setText(Integer.toString(score));
    }

    public int getScore(){
        return Integer.parseInt(scoreBoard2.getText());
    }
}
