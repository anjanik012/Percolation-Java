/*
*  Created by Anjani Kumar <anjanik012@gmail.com>
*  2019
* */

public class Percolation {

    private final WeightedQuickUnionPC qu;
    private boolean[][] grid;
    private int numberOfOpenSites;
    private int[] bottom;
    private int bottomIndex;

    public Percolation(int n) {

        if (n <= 0)
            throw new java.lang.IllegalArgumentException();
        grid = new boolean[n][n];
        bottom = new int[n];
        bottomIndex = 0;
        numberOfOpenSites = 0;
        qu = new WeightedQuickUnionPC(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }

        for (int i = 1; i <= n; i++)
            qu.union(0, i);
    }

    public boolean isOpen(int row, int col) {
        if (row < 1 || row > grid[0].length || col < 1 || col > grid[0].length)
            throw new java.lang.IllegalArgumentException("row:" + row + " col:" + col);

        try {
            return grid[row - 1][col - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public void open(int row, int col) {
        if (row < 1 || row > grid[0].length || col < 1 || col > grid[0].length)
            throw new java.lang.IllegalArgumentException();

        int s = grid[0].length;
        int x = col - 1;
        int y = row - 1;

        if (!isOpen(row, col)) {
            grid[y][x] = true;
            numberOfOpenSites++;

            if (s == 1) {
                bottom[bottomIndex++] = y * s + x + 1;
                return;
            }


            if (x == 0) {
                if (y == 0) {
                    if (isOpen(row, col + 1))
                        qu.union(y * s + x + 2, y * s + x + 1);
                    if (isOpen(row + 1, col))
                        qu.union((y + 1) * s + x + 1, y * s + x + 1);
                } else if (y == grid[0].length - 1) {
                    if (isOpen(row - 1, col))
                        qu.union((y - 1) * s + x + 1, y * s + x + 1);
                    if (isOpen(row, col + 1))
                        qu.union(y * s + x + 2, y * s + x + 1);
                } else {
                    if (isOpen(row, col + 1))
                        qu.union(y * s + x + 2, y * s + x + 1);
                    if (isOpen(row + 1, col))
                        qu.union((y + 1) * s + x + 1, y * s + x + 1);
                    if (isOpen(row - 1, col))
                        qu.union((y - 1) * s + x + 1, y * s + x + 1);
                }
            } else if (x == grid[0].length - 1) {
                if (y == 0) {
                    if (isOpen(row, col - 1))
                        qu.union(y * s + x, y * s + x + 1);
                    if (isOpen(row + 1, col))
                        qu.union((y + 1) * s + x + 1, y * s + x + 1);
                } else if (y == grid[0].length - 1) {
                    if (isOpen(row - 1, col))
                        qu.union((y - 1) * s + x + 1, y * s + x + 1);
                    if (isOpen(row, col - 1))
                        qu.union(y * s + x, y * s + x + 1);
                } else {
                    if (isOpen(row, col - 1))
                        qu.union(y * s + x, y * s + x + 1);
                    if (isOpen(row + 1, col))
                        qu.union((y + 1) * s + x + 1, y * s + x + 1);
                    if (isOpen(row - 1, col))
                        qu.union((y - 1) * s + x + 1, y * s + x + 1);
                }
            } else if (y == 0) {
                if (isOpen(row + 1, col))
                    qu.union((y + 1) * s + x + 1, y * s + x + 1);
                if (isOpen(row, col + 1))
                    qu.union(y * s + x + 2, y * s + x + 1);
                if (isOpen(row, col - 1))
                    qu.union(y * s + x, y * s + x + 1);
            } else if (y == grid[0].length - 1) {
                if (isOpen(row, col + 1))
                    qu.union(y * s + x + 2, y * s + x + 1);
                if (isOpen(row, col - 1))
                    qu.union(y * s + x, y * s + x + 1);
                if (isOpen(row - 1, col))
                    qu.union((y - 1) * s + x + 1, y * s + x + 1);
            } else {
                if (isOpen(row, col + 1))
                    qu.union(y * s + x + 2, y * s + x + 1);
                if (isOpen(row, col - 1))
                    qu.union(y * s + x, y * s + x + 1);
                if (isOpen(row - 1, col))
                    qu.union((y - 1) * s + x + 1, y * s + x + 1);
                if (isOpen(row + 1, col))
                    qu.union((y + 1) * s + x + 1, y * s + x + 1);
            }

            if (y == s - 1)
                bottom[bottomIndex++] = y * s + x + 1;
        }
    }

    public boolean isFull(int row, int col) {
        if (row < 1 || row > grid[0].length || col < 1 || col > grid[0].length)
            throw new java.lang.IllegalArgumentException();

        return isOpen(row, col) && qu.connected((row - 1) * grid[0].length + col, 0);
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {

        for (int i = 0; i < bottomIndex; i++) {
            if (qu.connected(0, bottom[i]))
                return true;
        }
        return false;
    }

    private class WeightedQuickUnionPC {

        private int[] id;
        private int[] sz;

        public  WeightedQuickUnionPC(int n) {
            id = new int[n * n + 1];
            sz = new int[n * n + 1];
            for (int i = 0; i < n * n + 1; i++) {
                id[i] = i;
                sz[i] = 1;
            }
        }

        private int root(int i) {
            while (id[i] != i) {
                id[i] = id[id[i]];
                i = id[i];
            }
            return i;
        }

        public boolean connected(int p, int q) {
            return root(p) == root(q);
        }

        public void union(int p, int q) {
            int i = root(p);
            int j = root(q);

            if (i == j)
                return;
            if (sz[i] < sz[j]) {
                id[i] = j;
                sz[j] += sz[i];
            } else {
                id[j] = i;
                sz[i] += sz[j];
            }
        }
    }
}
