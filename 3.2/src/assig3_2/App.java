package assig3_2;

public class App {
    public static void main(String[] args) throws Exception {

        GamePlay gameplay = new GamePlay();
        Judge j = new Judge(gameplay);
        Gamer gamer1 = new Gamer(gameplay);
        Gamer gamer2 = new Gamer(gameplay);

        Thread gamerThread1 = new Thread(gamer1, "Gamer-1");
        Thread gamerThread2 = new Thread(gamer2, "Gamer-2");
        Thread judgeThread = new Thread(j, "Judge");
        gamerThread1.start();
        gamerThread2.start();
        judgeThread.start();

        System.out.println("Gamer 1 Score: " + gamer1.getScore());
        System.out.println("Gamer 2 Score: " + gamer2.getScore());

    }
}
