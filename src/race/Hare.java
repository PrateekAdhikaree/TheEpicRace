package race;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by ceto on 5/29/17.
 */
public class Hare implements MoveImpl {

    private static Move SLEEP;
    private static Move BIG_HOP;
    private static Move BIG_SLIP;
    private static Move SMALL_HOP;
    private static Move SMALL_SLIP;

    private BufferedImage img;

    private int xPos = 0;
    private int yPos = 0;

    public Hare(){
        SLEEP = new Move(Move.Direction.SLEEP, 0, 2);
        BIG_HOP = new Move(Move.Direction.RIGHT, 9, 2);
        BIG_SLIP = new Move(Move.Direction.LEFT, 12, 1);
        SMALL_HOP = new Move(Move.Direction.RIGHT, 1, 3);
        SMALL_SLIP = new Move(Move.Direction.LEFT, 2, 2);

        try {
            img = ImageIO.read(new File(absoluteFilePath + "hare.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Move getMove(){
        Move returnMove = null;

        // get a random number between 1 and 10 (inclusive)
        int prob = (int) Math.ceil(Math.random() * 10);

        if(prob <= SLEEP.getProbabilityTimes())
            returnMove = SLEEP;
        else if( (prob > SLEEP.getProbabilityTimes()) && (prob <= SLEEP.getProbabilityTimes() + BIG_HOP.getProbabilityTimes()) )
            returnMove = BIG_HOP;
        else if( (prob > SLEEP.getProbabilityTimes() + BIG_HOP.getProbabilityTimes()) && (prob <= SLEEP.getProbabilityTimes() + BIG_HOP.getProbabilityTimes() + BIG_SLIP.getProbabilityTimes()) )
            returnMove = BIG_SLIP;
        else if( (prob > SLEEP.getProbabilityTimes() + BIG_HOP.getProbabilityTimes() + BIG_SLIP.getProbabilityTimes()) && (prob <= SLEEP.getProbabilityTimes() + BIG_HOP.getProbabilityTimes() + BIG_SLIP.getProbabilityTimes() + SMALL_HOP.getProbabilityTimes()) )
            returnMove = SMALL_HOP;
        else if( (prob > SLEEP.getProbabilityTimes() + BIG_HOP.getProbabilityTimes() + BIG_SLIP.getProbabilityTimes() + SMALL_HOP.getProbabilityTimes()) )
            returnMove = SMALL_SLIP;

        return returnMove;
    }

    @Override
    public BufferedImage getImg() {
        return img;
    }

    @Override
    public int getXPos() {
        return xPos;
    }

    @Override
    public int getYPos() {
        return yPos;
    }

    @Override
    public void setXPos(int x) {
        xPos = x;
    }

    @Override
    public void setYPos(int y) {
        yPos = y;
    }
}
