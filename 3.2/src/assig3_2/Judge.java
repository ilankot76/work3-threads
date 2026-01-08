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
        try {
            if (rnd)
                Thread.sleep(500);
            else
                Thread.sleep(700);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
