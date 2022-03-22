package character;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class InitializeButtons extends JPanel {

    private boolean alreadyPressedBtn1 = false;
    private boolean alreadyPressedBtn2 = false;
    private boolean alreadyPressedBtn3 = false;
    private boolean alreadyPressedBtn4 = false;
    private boolean alreadyPressedBtn5 = false;

    private void playSound(String soundName) {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException unsupportedAudioFileException) {
            unsupportedAudioFileException.printStackTrace();
        }
    }

    public InitializeButtons(CharacterInitializePanel initPanel) {
        setBorder(new EmptyBorder(new Insets(200, 0, 0, 0)));
        setLayout(new BorderLayout());

        JPanel checkboxes = new JPanel();
        checkboxes.setLayout(new BoxLayout(checkboxes, BoxLayout.Y_AXIS));
        checkboxes.setOpaque(false);

        JCheckBox btn1 = new JCheckBox("Horned Helmet");
        checkboxes.add(btn1);
        btn1.setOpaque(false);
        btn1.setForeground(Color.white);

        JCheckBox btn2 = new JCheckBox("Golden Armor");
        checkboxes.add(btn2);
        btn2.setOpaque(false);
        btn2.setForeground(Color.white);

        JCheckBox btn3 = new JCheckBox("Leather Bracers");
        checkboxes.add(btn3);
        btn3.setOpaque(false);
        btn3.setForeground(Color.white);

        JCheckBox btn4 = new JCheckBox("Iron Sword");
        checkboxes.add(btn4);
        btn4.setOpaque(false);
        btn4.setForeground(Color.white);

        JCheckBox btn5 = new JCheckBox("Iron Leggings");
        checkboxes.add(btn5);
        btn5.setOpaque(false);
        btn5.setForeground(Color.white);
        add(checkboxes, BorderLayout.CENTER);
        checkboxes.setBorder(new EmptyBorder(100, 30, 0, 0));

        btn1.addActionListener(e -> {
            if (!alreadyPressedBtn1) {
                alreadyPressedBtn1 = true;
                initPanel.addHornedHelmet();
            } else {
                alreadyPressedBtn1 = false;
                initPanel.removeHornedHelmet();
            }
            initPanel.repaint();

            playSound("resources/iron_sound.wav");
        });

        btn2.addActionListener(e -> {
            if (!alreadyPressedBtn2) {
                alreadyPressedBtn2 = true;
                initPanel.addGoldenArmor();
            } else {
                alreadyPressedBtn2 = false;
                initPanel.removeGoldenArmor();
            }
            initPanel.repaint();

            playSound("resources/iron_sound.wav");
        });

        btn3.addActionListener(e -> {
            if (!alreadyPressedBtn3) {
                alreadyPressedBtn3 = true;
                initPanel.addLeatherBracers();
            } else {
                alreadyPressedBtn3 = false;
                initPanel.removeLeatherBracers();
            }
            initPanel.repaint();

            playSound("resources/leather_sound.wav");
        });

        btn4.addActionListener(e -> {
            if (!alreadyPressedBtn4) {
                alreadyPressedBtn4 = true;
                initPanel.addIronSword();
            } else {
                alreadyPressedBtn4 = false;
                initPanel.removeIronSword();
            }
            initPanel.repaint();

            playSound("resources/chain_sound.wav");
        });

        btn5.addActionListener(e -> {
            if (!alreadyPressedBtn5) {
                alreadyPressedBtn5 = true;
                initPanel.addIronLeggings();
            } else {
                alreadyPressedBtn5 = false;
                initPanel.removeIronLeggings();
            }
            initPanel.repaint();

            playSound("resources/chain_sound.wav");
        });

        setVisible(true);
    }
}
