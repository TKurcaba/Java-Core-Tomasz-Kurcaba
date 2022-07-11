package tictactoe;
import java.util.Scanner;


public class Main {

    static final int n =3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(scanner);
        Game game = new Game( n, scanner);
        Mode mode = menu.PrintMenu();
        game.letsPlay(mode);
    }
}