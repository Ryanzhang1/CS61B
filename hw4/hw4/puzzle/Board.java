package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;


public class Board implements WorldState {

    /**
     * Returns the string representation of the board.
     * Uncomment this method.
     */
    private int N;
    private int estimatedDist;
    private final int[][] board;

    /*Constructs a board from an N-by-N array of tiles where
      tiles[i][j] = tile at row i, column j */
    public Board(int[][] tiles) {
        if (tiles == null || tiles[0] == null || tiles.length != tiles[0].length) {
            throw new IllegalArgumentException();
        }
        N = tiles.length;
        estimatedDist = -1;
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = tiles[i][j];
            }
        }
    }

    /*Returns value of tile at row i, column j (or 0 if blank)*/
    public int tileAt(int i, int j) {
        if (i >= N || j >= N || i < 0 || j < 0) {
            throw new IndexOutOfBoundsException();
        }
        return board[i][j];
    }

    /*Returns the board size N*/
    public int size() {
        return N;
    }

    /*Returns the neighbors of the current board*/
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int x1 = 0;
        int y1 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) == 0) {
                    x1 = i;
                    y1 = j;
                }
            }
        }
        int[][] neighbor = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                neighbor[i][j] = tileAt(i, j);
            }
        }
        for (int i = x1 - 1; i <= x1 + 1; i++) {
            for (int j = y1 - 1; j <= y1 + 1; j++) {
                if (i >= N || j >= N || i < 0 || j < 0) {
                    continue;
                } else {
                    if (Math.abs(i - x1) + Math.abs(j - y1) == 1) {
                        neighbor[x1][y1] = neighbor[i][j];
                        neighbor[i][j] = 0;
                        Board newState = new Board(neighbor);
                        neighbors.enqueue(newState);
                        neighbor[i][j] = neighbor[x1][y1];
                        neighbor[x1][y1] = 0;
                    }
                }
            }
        }
        return neighbors;
    }

    /*Return the number in the right position */
    private int trans(int i, int j) {
        return i * N + j + 1;
    }

    /*The number of tiles in the wrong position.*/
    public int hamming() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) != trans(i, j) && tileAt(i, j) != 0) {
                    sum += 1;
                }
            }
        }
        return sum;
    }

    /*The sum of the Manhattan distances (sum of the vertical and horizontal distance)
    from the tiles to their goal positions.*/
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    continue;
                } else {
                    int targetI = (board[i][j] - 1) / N;
                    int targetJ = (board[i][j] - 1) % N;
                    sum += Math.abs(targetI - i);
                    sum += Math.abs(targetJ - j);
                }
            }
        }
        return sum;
    }

    /*Estimated distance to goal. This method should
                  simply return the results of manhattan() when submitted to Gradescope.*/
    public int estimatedDistanceToGoal() {
        if (estimatedDist == -1) {
            estimatedDist = manhattan();
        }
        return estimatedDist;
    }

    /*Returns true if this board's tile values are the same
              position as y's*/
    @Override
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null || this.getClass() != y.getClass()) {
            return false;
        }
        Board other = (Board) y;
        if (this.N != other.N) {
            return false;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != other.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /*Returns the string representation of the board. This
              method is provided in the skeleton*/
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
