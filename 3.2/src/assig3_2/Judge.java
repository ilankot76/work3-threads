package assig3_2;

public class Judge implements Runnable {

    private GamePlay gameplay;

    public Judge(GamePlay gameplay) {
        this.gameplay = gameplay;
    }

    @Override
    public void run() {
        boolean rnd = Math.random() < 0.5;
        gameplay.makeCoinAvail(rnd);

    }

}
