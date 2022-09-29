package tictactoe.game;

import tictactoe.mode.Player;

import java.util.Scanner;

public class User extends Player {
    protected User(String sign) {
        super(sign);
    }

    @Override
    public void move(String[] cellTab, Scanner scanner) {
        boolean okay =false;
        int idx = 0;
        int idy = 0;
        int i;
        while(true) {
            System.out.print("Enter the coordinates:> ");
            if (!okay) {
                if (scanner.hasNextInt()) {
                    idx = scanner.nextInt();
                    if (scanner.hasNextInt()) {
                        idy = scanner.nextInt();
                        okay = true;
                    } else {
                        scanner.nextLine();
                        System.out.println("You should enter numbers!");
                        continue;
                    }
                } else {
                    scanner.nextLine();
                    System.out.println("You should enter numbers!");
                    continue;
                }
            }
            if (idx < 1 || idx > 3 || idy < 1 || idy > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                okay = false;
                continue;
            }
            i = (idx-1)*3 + idy-1;
            if (cellTab[i].equals("X") || cellTab[i].equals("O")) {
                System.out.println("This cell is occupied! Choose another one!");
                okay = false;
            }
            else
                break;
        }
        cellTab[i] = this.sign;
    }
}
