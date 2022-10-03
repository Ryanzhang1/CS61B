package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solver {

    private class SearchNode implements Comparable<SearchNode> {
        private WorldState state;
        private int move;
        private SearchNode pre;

        public SearchNode(WorldState s, int m, SearchNode p) {
            state = s;
            move = m;
            pre = p;
        }

        public WorldState getState() {
            return state;
        }

        public int getMoves() {
            return move;
        }

        public SearchNode getPre() {
            return pre;
        }

        @Override
        public int compareTo(SearchNode o) {
            return this.move + this.state.estimatedDistanceToGoal() - o.move - o.state.estimatedDistanceToGoal();
        }
    }

    private MinPQ<SearchNode> queue = new MinPQ<>();
    private List<WorldState> bestSolution;
    private int toMoves;

    private void getSolution(SearchNode goal) {
        toMoves = goal.move;
        bestSolution = new ArrayList<>();
        while (goal != null) {
            bestSolution.add(goal.state);
            goal = goal.pre;
        }
    }

    public Solver(WorldState initial) {
        queue.insert(new SearchNode(initial, 0, null));
        while (true) {
            SearchNode node = queue.delMin();
            if (node.state.isGoal()) {
                getSolution(node);
                return;
            } else {
                for (WorldState neighbor : node.state.neighbors()) {
                    if (node.pre == null || !neighbor.equals(node.pre.state)) {
                        queue.insert(new SearchNode(neighbor, node.move + 1, node));
                    }
                }
            }
        }
    }

    public int moves() {
        return toMoves;
    }

    public Iterable<WorldState> solution() {
        List<WorldState> ans = new ArrayList<>();
        for (int i = toMoves; i >= 0; i--) {
            ans.add(bestSolution.get(i));
        }
        return ans;
    }
}
