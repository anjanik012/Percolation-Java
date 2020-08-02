# Percolation Problem

This is a solution of **percolation problem** using disjoint sets data structure.

![demo](https://github.com/anjanik012/Percolation-Java/blob/master/demo.gif)

The data structure used is _Weighted quick union with size constraint_. 

It is based on the one of the assignments of [_Princeton University course on Coursera_.](https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php)
It has a dependency [algs4.jar](https://algs4.cs.princeton.edu/code/algs4.jar)

I've written only [Percolation.java](https://github.com/anjanik012/Percolation-Java/blob/master/src/Percolation.java)
, [PercolationStats.java](https://github.com/anjanik012/Percolation-Java/blob/master/src/PercolationStats.java) and
[StatsTest.java](https://github.com/anjanik012/Percolation-Java/blob/master/src/StatTest.java)

Other files come from _Princeton University Course._ They are visualizer classes

[InteractivePercolationVisualizer.java](https://github.com/anjanik012/Percolation-Java/blob/master/src/InteractivePercolationVisualizer.java)
Creates a clickable grid where sites can be opened by mouse clicks. If the system percolates after a mouse click,
the window shows the _percolates_ message in the bottom of the window.

[PercolationVisualizer.java](https://github.com/anjanik012/Percolation-Java/blob/master/src/PercolationVisualizer.java)
creates a special percolation grid configuration based on the input file passed to it which contains cell co-ordinates to be
opened in the NxN grid.
