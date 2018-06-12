package race;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by ceto on 5/29/17.
 */
public class Tortoise implements MoveImpl {

    private static Move FAST_PLOD;
    private static Move SLIP;
    private static Move SLOW_PLOD;

    private BufferedImage img;

    private int xPos = 0;
    private int yPos = 0;

    public Tortoise(){
        FAST_PLOD = new Move(Move.Direction.RIGHT, 3, 5);
        SLIP = new Move(Move.Direction.LEFT, 6, 2);
        SLOW_PLOD = new Move(Move.Direction.RIGHT, 1, 3);

        try {
            img = ImageIO.read(new File(absoluteFilePath + "tortoise.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Move getMove(){
        Move returnMove = null;

        // get a random number between 1 and 10 (inclusive)
        int prob = (int) Math.ceil(Math.random() * 10);

        if(prob <= FAST_PLOD.getProbabilityTimes())
            returnMove = FAST_PLOD;
        else if( (prob > FAST_PLOD.getProbabilityTimes()) && (prob <= FAST_PLOD.getProbabilityTimes() + SLIP.getProbabilityTimes()) )
            returnMove = SLIP;
        else if( (prob > FAST_PLOD.getProbabilityTimes() + SLIP.getProbabilityTimes()) )
            returnMove = SLOW_PLOD;

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
