# Mandatory assignment Week 3

Made by Christian Bank Lauridsen (chbl@itu.dk) and Lucas Frey Torres Hanson

## Part a

### w = 1

If w = 1 we can shorten the function:

1. f(n) = (1-w)g(n) + wh(n)
2. f(n) = (1-1)g(n) + 1*h(n)
3. f(n) = 0*g(n) + h(n)
4. f(n) = h(n)

This means that the function will ignore the actual cost of reaching the goal state and only look at the current heuristic cost of reaching the goal state.

This therefore results in the algorithm corresponding to the greedy best-first search algorithm.

### w = 0.5

If w = 0.5 we cannot shorten the function much:

1. f(n) = (1-w)g(n) + wh(n)
2. f(n) = (1-0.5)g(n) + 0.5*h(n)
3. f(n) = 0.5*g(n) + 0.5*h(n)

This means that the function will look at both the actual cost of reaching the goal state and the current heuristic cost of reaching the goal state.
Resulting in the algorithm corresponding to the A* algorithm.

## Part b

If w is set to 1 the algorithm cannot guarantee that the solution is optimal.\
If w is set to be lower than 1 the algorithm can guarantee that the solution is optimal and therefore admissible.