package race;

import javax.swing.*;

/**
 * Created by ceto on 5/30/17.
 */
public class RaceJFrame extends JFrame {

    private static final int HOR_WIDTH = 1200;
    private static final int VER_HEIGHT = 800;

    public static void main(String[] args) {
        JFrame obj = new RaceJFrame();
        obj.setSize(HOR_WIDTH, VER_HEIGHT + 20);
        obj.setTitle("Hare and tortoise race");
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.setLocationRelativeTo(null);
        obj.add(new RaceJPanel());
        obj.setVisible(true);
    }
}
