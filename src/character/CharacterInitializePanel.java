package character;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class CharacterInitializePanel extends JPanel {

    private final int startingPosX = 20;
    private final int startingPosY = 400;

    Vector<Orc> arr = new Vector<>();
    Vector<String> img = new Vector<>();

    private final OrcCreature creature;
    private Clothing clothing;

    public Orc getCreature() {
        return arr.get(arr.size() - 1);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        arr.get(arr.size() - 1).draw(g);
    }

    public CharacterInitializePanel() {
        creature = new OrcCreature();
        creature.setPosX(startingPosX);
        creature.setPosY(startingPosY);
        arr.add(creature);
        img.add("creature");
        repaint();
    }

    public void addHornedHelmet() {
        clothing = new HornedHelmet(arr.get(arr.size() - 1));
        clothing.setPosX(startingPosX);
        clothing.setPosY(startingPosY);
        arr.add(clothing);
        img.add("horned helmet");
    }

    public void removeHornedHelmet() {
        int i = 0;
        while (i < img.size()) {
            if (img.get(i).equals("horned helmet")) {
                img.remove(i);
                arr.remove(i);
                break;
            }
            i++;
        }
        rebuild();
    }

    public void addGoldenArmor() {
        clothing = new GoldenArmor(arr.get(arr.size() - 1));
        clothing.setPosX(startingPosX);
        clothing.setPosY(startingPosY);
        arr.add(clothing);
        img.add("golden armor");
    }

    public void removeGoldenArmor() {
        int i = 0;
        while (i < img.size()) {
            if (img.get(i).equals("golden armor")) {
                img.remove(i);
                arr.remove(i);
                break;
            }
            i++;
        }
        rebuild();
    }

    public void addLeatherBracers() {
        clothing = new LeatherBracers(arr.get(arr.size() - 1));
        clothing.setPosX(startingPosX);
        clothing.setPosY(startingPosY);
        arr.add(clothing);
        img.add("leather bracers");
    }

    public void removeLeatherBracers() {
        int i = 0;
        while (i < img.size()) {
            if (img.get(i).equals("leather bracers")) {
                img.remove(i);
                arr.remove(i);
                break;
            }
            i++;
        }
        rebuild();
    }

    public void addIronSword() {
        clothing = new IronSword(arr.get(arr.size() - 1));
        clothing.setPosX(startingPosX);
        clothing.setPosY(startingPosY);
        arr.add(clothing);
        img.add("iron sword");
    }

    public void removeIronSword() {
        int i = 0;
        while (i < img.size()) {
            if (img.get(i).equals("iron sword")) {
                img.remove(i);
                arr.remove(i);
                break;
            }
            i++;
        }
        rebuild();
    }

    public void addIronLeggings() {
        clothing = new IronLeggings(arr.get(arr.size() - 1));
        clothing.setPosX(startingPosX);
        clothing.setPosY(startingPosY);
        arr.add(clothing);
        img.add("iron leggings");
    }

    public void removeIronLeggings() {
        int i = 0;
        while (i < img.size()) {
            if (img.get(i).equals("iron leggings")) {
                img.remove(i);
                arr.remove(i);
                break;
            }
            i++;
        }
        rebuild();
    }

    private void rebuild() {
        Vector<String> temp = new Vector<>(img);
        img.subList(1, img.size()).clear();
        arr.subList(1, arr.size()).clear();
        for (int i = 1; i < temp.size(); i++) {
            switch (temp.get(i)) {
                case "horned helmet" -> addHornedHelmet();
                case "golden armor" -> addGoldenArmor();
                case "leather bracers" -> addLeatherBracers();
                case "iron sword" -> addIronSword();
                case "iron leggings" -> addIronLeggings();
            }
        }
    }
}
