package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class EasyAI extends AI{

    protected EasyAI(String sign) {
        super(sign);
    }
    @Override
    protected void move(String[] cellTab, Scanner scanner) {
        int i;

        System.out.println("Making move level \"easy\"");
        Random rand = new Random();
        do{
            i=rand.nextInt(9);

        }while(cellTab[i].equals("X") || cellTab[i].equals("O"));
        cellTab[i] = sign;

    }
}
