package search.finders;


import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Finder {
    public abstract void findPeople(Map<String, Set<Integer>> data, List<String> inputTab, String[] toFind);

    public static void findEveryone(List<String> data) {
        System.out.println("\n=== List of people ===");
        for (String datum : data) {
            System.out.println(datum);
        }
    }

}
