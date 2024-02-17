# Assignment 1

## Exercise 1

### 1.a

In the case h_1 worstcase scenario where all the tiles are misplaced the count will be 1 per misplaced tile and therefore sum to 8.\
In Gaschnig’s heuristic we instead look into the number of tiles that would be placed correctly by teleporting them into the correct and blank tile.
This would therefore also sum up to 8 moves in the worst case scenario, and can therefore be concludes to be at least as accurate as h_1.

## 1.b

### Goal state

```8-puzzle
  1 2
3 4 5
6 7 8
```

### Initial state

```8-puzzle
  1 2
4 3 5
6 7 8
```

### Heuristic

h_1 = Tile number 3 is misplaced plus tile number 4 is misplaced = 2\
h_2 = Tile number 3 has a Manhattan distance of 1 plus tile number 4 has a Manhattan distance of 1 = 2\
h_g = Teleport tile number 4 to square plus teleport tile number 3 to square plus teleport tile number 4 to square = 3\

### h_g illustration

```8-puzzle
4 1 2
  3 5
6 7 8
```

```8-puzzle
4 1 2
3   5
6 7 8
```

```8-puzzle
  1 2
3 4 5
6 7 8
```

Gaschnig’s heuristic is more admissible since it is closer to the actual number of moves needed to solve the puzzle which is 26.

## 1.c

1. Check to see if the puzzle is in the final state (if true stop)
2. Check to see if a tile would be in its final state if swapped to the square (keep doing as long as applicable)
3. Check which tiles are misplaced and swap a random one of them with the square
4. Then repeat from step 2 until the puzzle is in the final state

## Exercise 2

### 2.a

g(n) = cost of reaching n
h(n) = estimated cost of reaching goal from state of n
f(n) = estimated cost of the cheapest path to a goal state that goes through path of n

The straight-line distance heuristic hSLD(n) to find a shortest path from Lugoj (L) to Bucharest (B) is 244.
<f, g, h, city>

| Iteration | Frontier | Note |
| --- | --- | --- |
| 0 | <244, 0, 244, L> | |
| 1 | **<311, 0 + 70, 241, M>** <440 ,0 + 111 , 329, T> | We choose M due to its `f` being lowest |
| 2 | <500, 111 + 70, 244, L> **<503, 111 + 75, 242, D>** | We choose D, because we have already visited L |
| 3 | <503, 186 + 75, 242, M> **<466, 186 + 120 , 160, C>** | We choose C, because we have already visited M |
| 4 | <668, 306 + 120, 242, D> <647, 306 + 148, 193, R> **<544, 306 + 138, 100, P>** | We choose P due to its `f` being lowest |
| 5 | <742, 444 + 138, 160, C> <734, 444 + 97, 193, R> **<545, 444 + 101, 0, B>** | We choose B due to its `f` being lowest |
| 6 | | We are in goal state and therefore stop execution |

### 2.b

**<545, 444 + 101, 0, B>** would be returned as it is the proof that we reached the goal state.

### 2.c

The path would be a set containing all the nodes we visited in order.

Path: `L -> M -> D -> C -> P -> B`

## Exercise 3

### 3.a

Initial state: S_0 = <[4,3,2,1], [], []>

Action function: {Move to left tower, Move to center tower, Move to right tower}

Result function:

1. Move to left tower: applicable if the left tower is empty or the current disk we are moving is smaller than the top disk on the left tower. The result must not be a previous visited state.
2. Move to center tower: applicable if the center tower is empty or the current disk we are moving is smaller than the top disk on the center tower. The result must not be a previous visited state.
3. Move to right tower: applicable if the right tower is empty or the current disk we are moving is smaller than the top disk on the right tower. The result must not be a previous visited state.

Is-goal function: {When the rightmost tower has been stacked with all disks}

Action-cost function: {Each disk movement costs one}

### 3.b

#### Towers of Hanoi search tree with n=2 using Christian  search problem

![Christian towers of hanoi search tree with n=2](/assignment_1/Towers_of_Hanoi_n=2.png)

### 3.c

The size of the space state of Chistian search problem is 18 when n = 2. 

I cannot find the exact number!!!! HELP!!!

## Mandatory assignment

### a

#### w = 1

If w = 1 we can shorten the function:

1. f(n) = (1-w)g(n) + wh(n)
2. f(n) = (1-1)g(n) + 1*h(n)
3. f(n) = 0*g(n) + h(n)
3. f(n) = h(n)

This means that the function will ignore the actual cost of reaching the goal state and only look at the current heuristic cost of reaching the goal state.

This therefore results in the algorithm corresponding to the greedy best-first search algorithm.

#### w = 0.5

If w = 0.5 we cannot shorten the function much:

1. f(n) = (1-w)g(n) + wh(n)
2. f(n) = (1-0.5)g(n) + 0.5*h(n)
3. f(n) = 0.5*g(n) + 0.5*h(n)

This means that the function will look at both the actual cost of reaching the goal state and the current heuristic cost of reaching the goal state.
Resulting in the algorithm corresponding to the A* algorithm.
