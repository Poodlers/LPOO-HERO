public class Monster_Slider extends Monster{
    Monster_Slider(int x, int y, String character){
        super(x,y,character);
    }
    private int stageOfMovement = 0;
    @Override
    public Position move() {
        switch (stageOfMovement){
            case 0:
                stageOfMovement++;
                return this.moveComposite(-3,-3);

            case 1:
                stageOfMovement++;
                return this.moveComposite(-3,3);

            case 2:
                stageOfMovement++;
                return this.moveComposite(3,3);
            case 3:
                stageOfMovement = 0;
                return this.moveComposite(3,-3);
        }
        return getPos();
    }


}
