import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        Monster monster= new Monster();
        Game game = new Game(player);
        game.playGame(monster);
        System.out.println("----------------------------------------------------------------------------------------------");
        Player china = new Player("Си Цзиньпин",25,15,150,10,60);
        Monster usa = new Monster("Джо Байден",15,15,200,5,55);
        Game warforTaiwan = new Game(china);
        warforTaiwan.playGame(usa);
    }
}