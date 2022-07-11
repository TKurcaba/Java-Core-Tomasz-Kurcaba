package search;

import java.util.*;
import java.util.stream.Collectors;

public class AnyFinder extends Finder{
    @Override
    public void findPeople(Map<String, Set<Integer>> data, List<String> inputTab, String[] toFind) {
        List<String> peopleFound = new ArrayList<>();
        for (String temp : toFind) {
            String toFindLowerCase = temp.toLowerCase();
            if (data.containsKey(toFindLowerCase)) {
                for (int line : data.get(toFindLowerCase)) {
                    peopleFound.add(inputTab.get(line));
                }
            }

        }
        if(peopleFound.size() != 0){

            System.out.println("\n" + peopleFound.size() + " persons found:");
            for( String p : peopleFound){
                System.out.println(p);
            }
        }else{
            System.out.println("No matching people found.\n");
        }
    }
}
