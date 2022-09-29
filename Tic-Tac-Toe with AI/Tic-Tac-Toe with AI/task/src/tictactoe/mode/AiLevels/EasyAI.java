package tictactoe.mode.AiLevels;

import tictactoe.mode.AI;

import java.util.Random;
import java.util.Scanner;

public class EasyAI extends AI {

    public EasyAI(String sign) {
        super(sign);
    }
    @Override
    public void move(String[] cellTab, Scanner scanner) {
        int i;

        System.out.println("Making move level \"easy\"");
        Random rand = new Random();
        do{
            i=rand.nextInt(9);

        }while(cellTab[i].equals("X") || cellTab[i].equals("O"));
        cellTab[i] = sign;

    }
}
