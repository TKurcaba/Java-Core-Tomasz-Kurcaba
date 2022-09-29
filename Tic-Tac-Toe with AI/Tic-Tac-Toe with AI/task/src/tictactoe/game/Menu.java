package tictactoe.game;

import tictactoe.mode.Mode;

import java.util.Objects;
import java.util.Scanner;

public class Menu {
   private final Scanner scanner;

    public Menu(Scanner scanner){
        this.scanner= scanner;
    }

    public Mode PrintMenu(){
        Mode res = null;
        boolean isConfigured = false;
        do{
            String option = null;
            System.out.print("Input command: > ");
            if(scanner.hasNext()) {
                option = scanner.next();
            }else{
                System.out.println("Bad parameters!");
                continue;
            }
            switch (option) {
                case "start":
                    String option2;
                    String option3;
                    option2 = scanner.nextLine();

                    if(!Objects.equals(option2, "")) {
                        String[] temp = (option2 + " ,").split(" ");
                        option3 = temp[2];
                        option2 = temp[1];
                    } else{
                        System.out.println("Bad parameters!");
                        continue;
                    }
                    switch (option2){
                        case "user":
                            switch (option3) {
                                case "easy":
                                    res= Mode.USEREASY;
                                    isConfigured = true;
                                    break;
                                case "user":
                                    res= Mode.USERUSER;
                                    isConfigured = true;
                                    break;
                                case "medium":
                                    res= Mode.USERMEDIUM;
                                    isConfigured = true;
                                    break;
                                case "hard":
                                    res= Mode.USERHARD;
                                    isConfigured = true;
                                    break;
                                default:
                                    System.out.println("Bad parameters!");
                            }
                            break;
                        case "easy":

                            switch (option3) {
                                case "easy":
                                    res = Mode.EASYEASY;
                                    isConfigured = true;
                                    break;
                                case "user":
                                    res= Mode.EASYUSER;
                                    isConfigured = true;
                                    break;
                                case "medium":
                                    res = Mode.EASYMEDIUM;
                                    isConfigured = true;
                                    break;
                                case "hard":
                                    res = Mode.EASYHARD;
                                    isConfigured = true;
                                    break;
                                default:
                                    System.out.println("Bad parameters!");
                            }
                            break;
                        case "medium":
                            switch (option3) {
                                case "easy":
                                    res = Mode.MEDIUMEASY;
                                    isConfigured = true;
                                    break;
                                case "user":
                                    res= Mode.MEDIUMUSER;
                                    isConfigured = true;
                                    break;
                                case "medium":
                                    res= Mode.MEDIUMMEDIUM;
                                    isConfigured = true;
                                    break;
                                case "hard":
                                    res= Mode.MEDIUMHARD;
                                    isConfigured = true;
                                    break;
                                default:
                                    System.out.println("Bad parameters!");
                            }
                            break;
                        case "hard":
                            switch (option3) {
                                case "easy":
                                    res = Mode.HARDEASY;
                                    isConfigured = true;
                                    break;
                                case "user":
                                    res= Mode.HARDUSER;
                                    isConfigured = true;
                                    break;
                                case "medium":
                                    res= Mode.HARDMEDIUM;
                                    isConfigured = true;
                                    break;
                                case "hard":
                                    res= Mode.HARDHARD;
                                    isConfigured = true;
                                    break;
                                default:
                                    System.out.println("Bad parameters!");
                            }
                            break;
                    }
                    break;

                case "exit":
                    res= Mode.EXIT;
                    isConfigured = true;
                    break;
                default:
                    scanner.nextLine();
                    System.out.println("Bad parameters!");

            }
        }while(!isConfigured);
        return res;
    }
}
