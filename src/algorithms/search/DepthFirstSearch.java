package algorithms.search;

import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {
    protected Stack<AState> openList;
    protected HashSet<String> visited;

    public DepthFirstSearch() {
        this.Name = "Depth First Search";
        this.openList = new Stack<>();
        this.numOfNodesEval = 0;
    }

    @Override
    public Solution solve(ISearchable domain) {
        int countEval = 0;
        this.openList.clear();
        this.setNumOfNodesEval(0);
        visited = new HashSet<>();
        AState start = domain.getStartState();
        AState goal = domain.getGoalState();
        openList.push(start);
        AState curState;
        while (!openList.empty()) {
            curState = openList.pop();
            if (curState.equals(goal)){
                setNumOfNodesEval(countEval);
                return new Solution(curState);
            }
            if (!(visited.contains(curState.toString()))){
                visited.add(curState.toString());
                List<AState> neighbors = domain.getAllPossibleStates(curState);
                countEval++;
                openList.addAll(neighbors);
            }
        }
        this.setNumOfNodesEval(countEval);
        return new Solution();
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return numOfNodesEval;
    }
}