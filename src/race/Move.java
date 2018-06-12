package race;

/**
 * Created by ceto on 5/29/17.
 */
public class Move {

    private int count;
    private Direction direction;
    private int probabilityTimes;

    public enum Direction{
        LEFT,
        RIGHT,
        SLEEP;
    }

    public Move(Direction d, int count, int probabilityTimes){
        this.count = count;
        this.direction = d;
        this.probabilityTimes = probabilityTimes;
    };

    public int getCount() {
        return count;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getProbabilityTimes() {
        return probabilityTimes;
    }
}
