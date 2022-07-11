package tictactoe;

import java.util.Scanner;

 abstract class Player {
    final String sign;
    final String signOpponent;
    protected Player(String sign) {

        this.sign = sign;
        this.signOpponent = sign.equals("X") ? "O" : "X";
    }
    abstract protected void move(String[] cellTab, Scanner scanner);

}
