package search.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Secretary {
    public Map<String, Set<Integer>> reader(File file, List<String> strList) throws FileNotFoundException {

        Scanner sc = new Scanner(file);

        Map<String, Set<Integer>> words = new TreeMap<>();

        int k = 0;
        while(sc.hasNextLine()){
            String str = sc.nextLine();
            strList.add(str);
            String[] splited = str.split("\\s+");
            for( String word: splited){

                Set<Integer> temp;
                if(words.containsKey(word.toLowerCase())) {
                    temp = words.get(word.toLowerCase());
                }else{
                    temp = new HashSet<>();
                }
                temp.add(k);
                words.put(word.toLowerCase(), temp);
            }
            k++;
        }

       return words;
    }

    public int printMenu(Scanner scanner ){
        do {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Find a person \n2. Print all people \n0. Exit");
            System.out.print("> ");
            int nOption = scanner.nextInt();
            scanner.nextLine();
            if (nOption > 2 || nOption < 0) {
                System.out.println("\nIncorrect option! Try again.");

            }else{
                return nOption;
            }
        }while(true);
    }


    public String matchingStrategy(Scanner scanner){
        String nString;
        do {
            System.out.print("\nSelect a matching strategy: ALL, ANY, NONE\n> ");
            nString = scanner.nextLine();
        } while (!Objects.equals(nString, "ANY") && !Objects.equals(nString, "ALL") && !Objects.equals(nString, "NONE"));
        return nString;
    }

    public String[] dataToSearch(Scanner scanner){
        System.out.print("Enter a name or email to search all suitable people.\n> ");
        return scanner.nextLine().split("\\s");
    }

}
