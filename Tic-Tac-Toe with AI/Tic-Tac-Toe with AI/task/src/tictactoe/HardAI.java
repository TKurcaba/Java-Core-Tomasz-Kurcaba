package tictactoe;

import java.util.*;

public class HardAI extends Player{
    protected HardAI(String sign) {
        super(sign);
    }

    @Override
    protected void move(String[] cellTab, Scanner scanner) {
        System.out.println("Making move level \"hard\"");
        if(sign.equals("X"))
            cellTab[minimax(cellTab,sign)] = sign;
        else
            cellTab[minimax(cellTab,signOpponent)] = sign;

    }


    private int minimax(String[] newBoard, String player){

        int[] possibleMoves = emptyIndexies(newBoard);

        if(this.winning(newBoard, signOpponent)){
            return 10;
        } else if( this.winning(newBoard, sign)){
            return -10;
        }else if(newBoard.length == 0){
            return 0;
        }

        HashMap<Integer, Integer> moves = new HashMap<Integer, Integer>();

        for (int possibleMove : possibleMoves) {
            int[] moveDict = new int[2];
            moveDict[0] = Integer.parseInt(newBoard[possibleMove]);
            newBoard[possibleMove] = player;

            if (Objects.equals(player, sign)) {
                moveDict[1] = this.minimax(newBoard, signOpponent);

            } else {
                moveDict[1] = this.minimax(newBoard, sign);
            }
            newBoard[possibleMove] = String.valueOf(moveDict[0]);
            moves.put(moveDict[0], moveDict[1]);

        }
        int bestMove = 1;
        if(Objects.equals(player, signOpponent)){
            int bestScore = -10000;
            for(int key : moves.keySet()){
                if(moves.get(key) > bestScore){

                    bestScore = moves.get(key);

                    bestMove = key;
                }
            }
        }else {

            int bestScore = 10000;
            for(int key : moves.keySet()){
                if(moves.get(key) < bestScore){
                    bestScore = moves.get(key);

                    bestMove = key;
                }
            }
        }

        return bestMove;
    }


    private boolean winning(String[] board, String player){
        if (
                (Objects.equals(board[0], player) && Objects.equals(board[1], player) && Objects.equals(board[2], player)) ||
                        (Objects.equals(board[3], player) && Objects.equals(board[4], player) && Objects.equals(board[5], player)) ||
                        (Objects.equals(board[6], player) && Objects.equals(board[7], player) && Objects.equals(board[8], player)) ||
                        (Objects.equals(board[0], player) && Objects.equals(board[3], player) && Objects.equals(board[6], player)) ||
                        (Objects.equals(board[1], player) && Objects.equals(board[4], player) && Objects.equals(board[7], player)) ||
                        (Objects.equals(board[2], player) && Objects.equals(board[5], player) && Objects.equals(board[8], player)) ||
                        (Objects.equals(board[0], player) && Objects.equals(board[4], player) && Objects.equals(board[8], player)) ||
                        (Objects.equals(board[2], player) && Objects.equals(board[4], player) && Objects.equals(board[6], player))
        ) {
            return true;
        } else {
            return false;
        }
    }

    private int[] emptyIndexies(String[] board){
        List<Integer> temp = new ArrayList<Integer>();
        for(String cell: board){
            if(!cell.equals("X") && !cell.equals("O")){
                temp.add(Integer.parseInt(cell));
            }
        }
        return temp.stream().mapToInt(i -> i).toArray();
    }

}
