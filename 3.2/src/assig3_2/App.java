package assig3_2;

public class App {
    public static void main(String[] args) throws InterruptedException {
        GamePlay gameplay = new GamePlay();
        Gamer g1 = new Gamer(gameplay);
        Gamer g2 = new Gamer(gameplay);
        Judge judge = new Judge(gameplay);

        Thread t1 = new Thread(g1, "player 1");
        Thread t2 = new Thread(g2, "player 2");
        Thread tj = new Thread(judge, "Judge");

        tj.start();
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        tj.interrupt();
        tj.join();

        int s1 = g1.getScore();
        int s2 = g2.getScore();

        if (s1 > s2)
            System.out.println("player 1 wins");
        else if (s2 > s1)
            System.out.println("player 2 wins");
        else
            System.out.println("tie");
    }
}
