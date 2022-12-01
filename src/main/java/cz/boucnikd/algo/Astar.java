package cz.boucnikd.algo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class Astar {

    public State astar(State start, State goal, int h) {
        var openSet = new TreeSet<>(Comparator.comparingInt(State::getCost).thenComparing(State::hashCode));
        openSet.add(start);

        while (!openSet.isEmpty()) {
            var current = openSet.pollFirst();
            if (goal.equals(current)) { //this should just check the pivot was found
                return current;
            }
            var expanded = current.expand();
            expanded.forEach(n -> {
                        if (!openSet.contains(n)) {
                            openSet.add(n);
                        }
                    }
            );
        }

        return null;
    }

    public static interface State {
        int getCost();

        List<State> expand();

        List<State> getPath();

        default List<State> copyListAndAdd(List<State> path, State state) {
            var result = new ArrayList<>(path);

            result.add(state);

            return result;
        }
    }
}
