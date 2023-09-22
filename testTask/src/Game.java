public class Game {
    private int roundIndex = 1;
    private Player player;

    public Game(Player player) {
        this.player = player;
    }

    public void playGame(Monster monster) {
        while (player.isAlive() & monster.isAlive()) {
            System.out.println(roundIndex + " раунд.");
            player.hit(monster);
            if (monster.isAlive()) {
                monster.hit(player);
                player.healing();
            }
            roundIndex++;
        }
        if (player.isAlive()) {
            System.out.println("Существо " + player.getName() + " победило в сражении");
        } else {
            System.out.println("Существо " + monster.getName() + " победило в сражении");
        }
    }
}
