import java.util.Random;

public class Monster_Circle extends Monster{
    Monster_Circle(int x, int y, String character){
        super(x,y,character);
    }
    @Override
    public Position move(){
            Random random = new Random();
            int rand_int = random.nextInt(4);
            switch (rand_int){
                case 0:
                    return moveUp(1);
                case 1:
                    return moveDown(1);
                case 2:
                    return moveLeft(1);
                case 3:
                    return moveRight(1);
            }
            return new Position(getPos().getX(),getPos().getY());

        }
}

