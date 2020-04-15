package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    private AState goalState;

    public Solution() {
        goalState = null;
    }

    public Solution(AState goal) {
        this.goalState = goal;
    }

    public ArrayList<AState> getSolutionPath() {
        ArrayList<AState> path = new ArrayList<>();
        AState curState = goalState;
        if (curState != null) {
            path.add(curState);
            while (curState.getPrev() != null) {
                curState = curState.getPrev();
                path.add(curState);
            }
            Collections.reverse(path);
        }
        return path;
    }
}
