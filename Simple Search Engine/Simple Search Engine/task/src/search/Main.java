package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = null;
        if (args.length > 1) {
            if (args[0].equals("--data")) {
                file = new File(args[1]);
            } else{
                System.out.println("Wrong argument, you must pass the name of file after --data");
            }
        } else {
            System.out.println("Wrong number of arguments.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Secretary menu = new Secretary();
        Finder finder = null;
        List<String> inputTab = new ArrayList<>();
        Map<String, Set<Integer>> wordMap = menu.reader(file, inputTab);


        do {
            int option = menu.printMenu(scanner);
            switch (option) {
                case 1: {

                    String searchingStrategy = menu.matchingStrategy(scanner);
                    String[] toSearch = menu.dataToSearch(scanner);
                    switch (searchingStrategy){
                        case "ALL" :{
                            finder = new AllFinder();
                            break;
                        }
                        case "ANY" : {
                            finder = new AnyFinder();
                            break;
                        }
                        case "NONE" : {
                            finder = new NoneFinder();
                            break;
                        }
                        default:{
                            System.out.println("This option does not exist,\n" +
                                    "you have to choose one of ALL, NONE or ANY.");
                        }
                    }
                    assert finder != null;
                    finder.findPeople(wordMap, inputTab, toSearch);
                    break;
                }
                case 2: {
                    Finder.findEveryone(inputTab);
                    break;
                }
                case 0: {
                    System.out.println("\nBye!");
                    return;
                }
                default:{
                    System.out.println("This option does not exist");
                }
            }

        } while (true);

    }

}