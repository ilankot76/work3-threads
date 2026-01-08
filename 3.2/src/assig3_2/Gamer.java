package assig3_2;

public class Gamer implements Runnable {
    private GamePlay gameplay;
    private int goodflipscounter;

    public void play() {
        while (gameplay.getnumberofrounds() < 10 && Thread.currentThread().isInterrupted() == false) {
            if (gameplay.coinflip()) {
                goodflipscounter++;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
    }

    public int getScore() {
        return goodflipscounter;
    }

    @Override
    public void run() {
        play();
    }

    public Gamer(GamePlay gameplay) {
        this.gameplay = gameplay;
        goodflipscounter = 0;
    }

}
