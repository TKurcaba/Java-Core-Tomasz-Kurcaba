package tictactoe.mode;

import java.util.Scanner;

 public abstract class Player {
    protected final String sign;
    protected final String signOpponent;
    protected Player(String sign) {

        this.sign = sign;
        this.signOpponent = sign.equals("X") ? "O" : "X";
    }
    public abstract void move(String[] cellTab, Scanner scanner);

}
