package search;

import java.util.*;

public class NoneFinder extends Finder{
    @Override
    public void findPeople(Map<String, Set<Integer>> data, List<String> inputTab, String[] toFind) {
        List<String> peopleFound = new ArrayList<>();

        for (String temp : toFind) {
            String toFindLowerCase = temp.toLowerCase();
                for (int line : data.get(toFindLowerCase)) {
                    peopleFound.add(inputTab.get(line));
                }
        }

        if(peopleFound.size() != inputTab.size()){
            List<String> nonePeople  = new ArrayList<>( inputTab);
            nonePeople.removeAll(peopleFound);
            System.out.println("\n" + nonePeople.size() + " persons found:");
            for( String p : nonePeople){
                System.out.println(p);
            }
        }else{
            System.out.println("No matching people found.\n");
        }
    }
}
