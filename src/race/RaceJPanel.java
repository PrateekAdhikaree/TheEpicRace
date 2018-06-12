package race;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by ceto on 5/29/17.
 */
public class RaceJPanel extends JPanel implements ActionListener {
    private static final int HOR_WIDTH = 1200;
    private static final int VER_HEIGHT = 800;

    private Timer timer;
    private double SQUARE_SIZE;
    private int LAST_SQUARE_X_POS;

    private Tortoise tortoise;
    private Hare hare;

    private int counter = 0;

    // to handle OS differences for file path
    private String resourcesFolder = "res" + File.separator;
    private String workingDirectory = System.getProperty("user.dir");
    private String absoluteFilePath = workingDirectory + File.separator + resourcesFolder;

    private BufferedImage sunImg;
    private BufferedImage flagImg;

    private Clip startClip;
    private Clip ouchClip;
    private Clip tortoiseWinsClip;
    private Clip hareWinsClip;

    private int ySlopeStartPos = VER_HEIGHT - 100;
    private int ySlopeEndPos = 100;
    private double radians;

    public RaceJPanel() {
        radians = - getRadiansBetweenTwoPointsWithFixedPoint(HOR_WIDTH, ySlopeStartPos, HOR_WIDTH, ySlopeEndPos, 0, ySlopeStartPos);

        double length = Math.hypot(HOR_WIDTH - 0, ySlopeEndPos - ySlopeStartPos);

        SQUARE_SIZE = (int) (length / 300);
        LAST_SQUARE_X_POS = (int) (SQUARE_SIZE * 299);

        hare = new Hare();
        hare.setYPos(VER_HEIGHT - hare.getImg().getHeight() - 100);

        tortoise = new Tortoise();
        tortoise.setYPos(VER_HEIGHT - tortoise.getImg().getHeight() - 95);

        try {
            sunImg = ImageIO.read(new File(absoluteFilePath + "sun.png"));
            flagImg = ImageIO.read(new File(absoluteFilePath + "flag.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadAudioClips();

        timer = new Timer(100, this);
        timer.start();

        playStartClip();
    }

    private void loadAudioClips(){
        File file;
        AudioInputStream sound;

        try {
            file = new File(absoluteFilePath + "ouch.wav");
            sound = AudioSystem.getAudioInputStream(file);
            ouchClip = AudioSystem.getClip();
            ouchClip.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            file = new File(absoluteFilePath + "start.wav");
            sound = AudioSystem.getAudioInputStream(file);
            startClip = AudioSystem.getClip();
            startClip.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            file = new File(absoluteFilePath + "tortoise_wins.wav");
            sound = AudioSystem.getAudioInputStream(file);
            tortoiseWinsClip = AudioSystem.getClip();
            tortoiseWinsClip.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            file = new File(absoluteFilePath + "hare_wins.wav");
            sound = AudioSystem.getAudioInputStream(file);
            hareWinsClip = AudioSystem.getClip();
            hareWinsClip.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform oldAxis = ((Graphics2D) g).getTransform();

        // creating the gradient for the sky
        GeneralPath sky = new GeneralPath();
        sky.moveTo(0, 0);
        sky.lineTo(HOR_WIDTH, 0);
        sky.lineTo(HOR_WIDTH, ySlopeEndPos);
        sky.lineTo(0, ySlopeStartPos);
        sky.closePath();

        Color c1 = new Color(0, 115, 230);
        Color c2 = Color.WHITE;
        g2d.setPaint(new GradientPaint(0, 0, c1, 0, ySlopeStartPos, c2, true));
        g2d.fill(sky);

        // setting the sun in the sky
        g2d.drawImage(sunImg, null, counter, 25);

        // drawing the mountain
        GeneralPath mountain = new GeneralPath();
        mountain.moveTo(0, ySlopeStartPos);
        mountain.lineTo(HOR_WIDTH, ySlopeEndPos);
        mountain.lineTo(HOR_WIDTH, VER_HEIGHT);
        mountain.lineTo(0, VER_HEIGHT);
        mountain.closePath();

        g2d.setColor(new Color(45, 134, 89));
        g2d.fill(mountain);

        // rotating the axes to drawing the hare and tortoise
        g2d.rotate(radians, 0, ySlopeStartPos);

        g2d.drawImage(tortoise.getImg(), null, tortoise.getXPos(), tortoise.getYPos());
        g2d.drawImage(hare.getImg(), null, hare.getXPos(), hare.getYPos());

        g2d.drawImage(flagImg, null, (int)(SQUARE_SIZE * 300), VER_HEIGHT - flagImg.getHeight() - 100);

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Jokerman", Font.PLAIN, 55));
        if (tortoise.getXPos() == hare.getXPos() && tortoise.getXPos() != 0 && hare.getXPos() != 0) {
            // both are at the same square
            playOuchClipAndReload();
            g2d.setFont(new Font("Jokerman", Font.PLAIN, 25));
            g2d.setColor(Color.RED);
            g2d.drawString("Ouch!!", hare.getXPos(), hare.getYPos());
        } else if (tortoise.getXPos() == LAST_SQUARE_X_POS && hare.getXPos() == LAST_SQUARE_X_POS) {
            // race tied
            playTortoiseWinsClip();
            g2d.setTransform(oldAxis);
            g2d.drawString("The underdog, TORTOISE is winner!!", HOR_WIDTH / 2 - 300, VER_HEIGHT / 2);
        } else if (tortoise.getXPos() == LAST_SQUARE_X_POS) {
            // tortoise won
            playTortoiseWinsClip();
            g2d.setTransform(oldAxis);
            g2d.drawString("TORTOISE WINS!!! YAY!!!", HOR_WIDTH / 2 - 275, VER_HEIGHT / 2);
        } else if (hare.getXPos() == LAST_SQUARE_X_POS) {
            // hare won
            playHareWinsClip();
            g2d.setTransform(oldAxis);
            g2d.drawString("Hare wins. Yuch.", HOR_WIDTH / 2 - 200, VER_HEIGHT / 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((tortoise.getXPos() == LAST_SQUARE_X_POS) || (hare.getXPos() == LAST_SQUARE_X_POS)) {
            timer.stop();
        }
        
        setHareMove(hare.getMove());
        setTortoiseMove(tortoise.getMove());

        if (counter % 100 == 0)
            System.gc();

        repaint();
        counter++;
    }


    private void setTortoiseMove(Move move) {
        if (move.getDirection() == Move.Direction.LEFT) {
            tortoise.setXPos(tortoise.getXPos() - ((int)(move.getCount() * SQUARE_SIZE)));
            if (tortoise.getXPos() < 0)
                tortoise.setXPos(0);
        } else if (move.getDirection() == Move.Direction.RIGHT) {
            tortoise.setXPos(tortoise.getXPos() + ((int)(move.getCount() * SQUARE_SIZE)));
            if (tortoise.getXPos() > LAST_SQUARE_X_POS)
                tortoise.setXPos(LAST_SQUARE_X_POS);
        }
    }

    private void setHareMove(Move move) {
        if (move.getDirection() == Move.Direction.LEFT) {
            hare.setXPos(hare.getXPos() - ((int)(move.getCount() * SQUARE_SIZE)));
            if (hare.getXPos() < 0)
                hare.setXPos(0);
        } else if (move.getDirection() == Move.Direction.RIGHT) {
            hare.setXPos(hare.getXPos() + ((int)(move.getCount() * SQUARE_SIZE)));
            if (hare.getXPos() > LAST_SQUARE_X_POS)
                hare.setXPos(LAST_SQUARE_X_POS);
        }
    }

    private double getRadiansBetweenTwoPointsWithFixedPoint(double point1X, double point1Y, double point2X, double point2Y, double fixedX, double fixedY) {

        double angle1 = Math.atan2(point1Y - fixedY, point1X - fixedX);
        double angle2 = Math.atan2(point2Y - fixedY, point2X - fixedX);

        return angle1 - angle2;
    }

    private void playStartClip(){
        startClip.start();
    }

    private void playHareWinsClip(){
        hareWinsClip.start();
    }

    private void playTortoiseWinsClip(){
        tortoiseWinsClip.start();
    }

    private void playOuchClipAndReload(){
        ouchClip.start();

        try {
            File file = new File(absoluteFilePath + "ouch.wav");
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            ouchClip = AudioSystem.getClip();
            ouchClip.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
