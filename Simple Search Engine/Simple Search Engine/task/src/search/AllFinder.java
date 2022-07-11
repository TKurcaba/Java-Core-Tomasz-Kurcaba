package search;


import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;
import java.util.stream.Collectors;

public class AllFinder extends Finder {

    @Override
    public void findPeople(Map<String, Set<Integer>> data, List<String> inputTab, String[] toFind) {
        Map<String, Integer> peopleFound = new TreeMap<>();
        boolean doesFit = true;
        for (String temp : toFind) {
            String toFindLowerCase = temp.toLowerCase();
            if (!data.containsKey(toFindLowerCase)) {
                doesFit = false;
                break;
            } else {
                for (int line : data.get(toFindLowerCase)) {
                    peopleFound.put(inputTab.get(line), peopleFound.getOrDefault(inputTab.get(line),0)+1);
                }
            }
        }
        if(doesFit){
        Set<String> people = peopleFound.entrySet()
                .stream()
                .filter(entry -> (toFind.length) == (entry.getValue()))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        System.out.println("\n" + people.size() + " persons found:");
        for( String p : people){
            System.out.println(p);
        }
        }else{
            System.out.println("No matching people found.\n");
        }
    }
}
