package tictactoe.mode.AiLevels;

import tictactoe.mode.AI;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class MediumAI extends AI {

    public MediumAI(String sign) {
        super(sign);
    }

    @Override
    public void move(String[] cellTab, Scanner scanner) {
        int i;

        System.out.println("Making move level \"medium\"");
        Random rand = new Random();
        int temp;
        int temp2;
        do{
            temp = possibleWin(cellTab);
            temp2 = stopThem(cellTab);
            if(temp != -1){
                i= temp;
            }else if(temp2 != -1){
                i= temp2;
            }
            else{
                i = rand.nextInt(9);
            }
        }while(cellTab[i].equals("X") || cellTab[i].equals("O"));
        cellTab[i] = sign;
    }

    private int possibleWin(String[] cellTab){
        for(int i =0; i< cellTab.length; i+= (int)Math.sqrt(cellTab.length)){
            int letsEnd = 0;
            boolean canIWin = false;
            int jTemp = 0;
            for(int j=i; j< i+3; j++){

                if(Objects.equals(cellTab[j], sign)){
                    letsEnd +=1;
                }
                if(!cellTab[i].equals("X") && !cellTab[i].equals("O")) {
                    canIWin = true;
                    jTemp = j;
                }
            }
            if(letsEnd == 2 && canIWin){
                return jTemp;
            }
        }
        return -1;
    }

    private int stopThem(String[] cellTab) {
        for (int i = 0; i < cellTab.length * cellTab.length - 1; i++) {
            switch (i) {
                case 0:
                    if(this.check(cellTab,new int[]{0,1,2}) != -1){
                        return this.check(cellTab,new int[]{0,1,2});
                    }
                    break;
                case 1:
                    if(this.check(cellTab,new int[]{3,4,5}) != -1){
                        return this.check(cellTab,new int[]{3,4,5});
                    }

                    break;
                case 2:
                    if(this.check(cellTab,new int[]{6,7,8}) != -1) {
                        return this.check(cellTab, new int[]{6,7,8});
                    }
                    break;
                case 3:
                    if(this.check(cellTab,new int[]{0,3,6}) != -1){
                        return this.check(cellTab,new int[]{0,3,6});
                    }
                    break;
                case 4:
                    if(this.check(cellTab,new int[]{1,4,7}) != -1){
                        return this.check(cellTab,new int[]{2,5,8});
                    }
                    break;
                case 5:
                    if(this.check(cellTab,new int[]{2,5,8}) != -1){
                        return this.check(cellTab,new int[]{2,5,8});
                    }
                    break;
                case 6:
                    if(this.check(cellTab,new int[]{0,4,8}) != -1){
                        return this.check(cellTab,new int[]{0,4,8});
                    }
                    break;
                case 7:
                    if(this.check(cellTab,new int[]{2,4,6}) != -1){
                        return this.check(cellTab,new int[]{2,4,6});
                    }
                    break;
            }
        }
        return -1;
    }

    private int check(String[] cellTab, int[] indexes ){
        String line =(cellTab[indexes[0]] + cellTab[indexes[1]]+
                cellTab[indexes[2]]).replaceAll("\\d","");

        if(line.equals(signOpponent+signOpponent)){
            for(int row : indexes){

                if(!cellTab[row].equals("X") && !cellTab[row].equals("O")){
                    return row;
                }
            }
        }
        return -1;
    }
}
