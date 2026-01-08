package assig3_2;

public class GamePlay implements Runnable {
    private final Object lock = new Object();
    private boolean Coin_avilable;
    private int rounds_counter;

    public void makeCoinAvail(boolean val) {
        synchronized (lock) {
            if (val) {
                Coin_avilable = true;
                notifyAll();
            } else
                Coin_avilable = false;
        }
    }

    @Override
    public void run() {
    }

    public GamePlay() {
        Coin_avilable = true;
        rounds_counter = 0;
    }

    public boolean coinflip() {
        synchronized (lock) {
            while (!Coin_avilable) {
                System.out.println("Thread " + Thread.currentThread().getName() + " waiting for coin to be available.");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            System.out.println("Thread " + Thread.currentThread().getName() + "flipping the coin.");
            Coin_avilable = false;
            rounds_counter++;

            int flip = (int) (Math.random() * 2);
            boolean result = (flip == 0) ? false : true;

            Coin_avilable = true;
            lock.notifyAll();
            return result;

        }

    }

    public int getnumberofrounds() {
        synchronized (lock) {
            return rounds_counter;
        }
    }

}