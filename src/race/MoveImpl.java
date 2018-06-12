package race;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by ceto on 5/29/17.
 */
public interface MoveImpl {

    // to handle OS differences for file path
    String resourcesFolder = "res" + File.separator;
    String workingDirectory = System.getProperty("user.dir");
    String absoluteFilePath = workingDirectory + File.separator + resourcesFolder;

    public Move getMove();

    public BufferedImage getImg();

    public int getXPos();

    public int getYPos();

    public void setXPos(int x);

    public void setYPos(int y);
}
