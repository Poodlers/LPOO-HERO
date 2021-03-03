import java.util.Random;

public class Monster_Teleporter extends Monster{
    Monster_Teleporter(int x, int y, String character){
        super(x,y,character);
    }
    @Override
    public Position move(){
        Random random = new Random();
        int rand_int = random.nextInt(4);
        switch (rand_int){
            case 0:
                return moveUp(3);
            case 1:
                return moveDown(3);
            case 2:
                return moveLeft(3);
            case 3:
                return moveRight(3);
        }
        return new Position(getPos().getX(),getPos().getY());
    }
}
