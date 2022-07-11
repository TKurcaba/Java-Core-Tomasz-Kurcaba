package tictactoe;

import java.util.Scanner;

public class Game {
    private final String[] cellTab;
    private final Scanner scanner;
    public Game( int n, Scanner scanner){
        this.scanner = scanner;
        this.cellTab = new String[n*n];

        for(int i = 0; i < n*n; i++){
                cellTab[i] =String.valueOf(i);
        }
    }


    public void letsPlay(Mode mode){

        Player player1 = null;
        Player player2 = null;
        switch (mode) {
            case EXIT:
                return;
            case EASYEASY:
                player1 = new EasyAI("X");
                player2 = new EasyAI("O");
                break;
            case USEREASY:
                player1 = new User("X");
                player2 = new EasyAI("O");
                break;
            case USERUSER:
                player1 = new User("X");
                player2 = new User("O");
                break;
            case EASYUSER:
                player1 = new EasyAI("X");
                player2 = new User("O");
                break;
            case MEDIUMEASY:
                player1 = new MediumAI("X");
                player2 = new EasyAI("O");
                break;
            case MEDIUMMEDIUM:
                player1 = new MediumAI("X");
                player2 = new MediumAI("O");
                break;
            case EASYMEDIUM:
                player1 = new EasyAI("X");
                player2 = new MediumAI("O");
                break;
            case MEDIUMUSER:
                player1 = new MediumAI("X");
                player2 = new User("O");
                break;
            case USERMEDIUM:
                player1 = new User("X");
                player2 = new MediumAI("O");
                break;
            case MEDIUMHARD:
                player1 = new MediumAI("X");
                player2 = new HardAI("O");
                break;
            case HARDMEDIUM:
                player1 = new HardAI("X");
                player2 = new MediumAI("O");
                break;
            case HARDEASY:
                player1 = new HardAI("X");
                player2 = new EasyAI("O");
                break;
            case EASYHARD:
                player1 = new EasyAI("X");
                player2 = new HardAI("O");
                break;
            case USERHARD:
                player1 = new User("X");
                player2 = new HardAI("O");
                break;
            case HARDUSER:
                player1 = new HardAI("X");
                player2 = new User("O");
                break;
            case HARDHARD:
                player1 = new HardAI("X");
                player2 = new HardAI("O");
                break;

        }
        this.printStatus();
        while (true) {
            String res ;

            player1.move(cellTab,scanner);
            res = checkStatus();
            this.printStatus();
            if(res != null){
                System.out.println(res);
                return;
            }

            player2.move(cellTab,scanner);
            res = checkStatus();
            this.printStatus();

            if (res != null) {
                System.out.println(res);
                break;
            }

        }
    }
    private void printStatus(){
        String line = "---------";
        System.out.println(line);
        int k = 0;
        for (String ints : this.cellTab) {
            if( k == 0)
                System.out.print("| ");
            if(ints.equals("X") || ints.equals("O"))
                System.out.print(ints + " ");
            else {
                System.out.print("  ");
            }
            k++;
            if(k == 3) {
                System.out.println("|");
                k=0;
            }

        }
        System.out.println(line);
    }


    private String checkStatus(){
        for(int i = 0; i< cellTab.length-1; i++){
            String line = null;
            switch (i){
                case 0 :
                    line = cellTab[0] + cellTab[1]+cellTab[2];
                    break;
                case 1 :
                    line = cellTab[3] + cellTab[4]+cellTab[5];
                    break;
                case 2 :
                    line = cellTab[6] + cellTab[7]+cellTab[8];
                    break;
                case 3 :
                    line = cellTab[0] + cellTab[3]+cellTab[6];
                    break;
                case 4 :
                    line = cellTab[1] + cellTab[4]+cellTab[7];
                    break;
                case 5 :
                    line = cellTab[2] + cellTab[5]+cellTab[8];
                    break;
                case 6 :
                    line = cellTab[0] + cellTab[4]+cellTab[8];
                    break;
                case 7 :
                    line = cellTab[2] + cellTab[4]+cellTab[6];
                    break;
            }
            assert line != null;

            if(line.equals("XXX")){
                return "X wins";
            }
            if(line.equals("OOO")){
                return "O wins";
            }
        }
        for (String strings : cellTab) {
                if (!strings.equals("X") && !strings.equals("O"))
                    return null;

        }
        return "Draw";
    }
}
