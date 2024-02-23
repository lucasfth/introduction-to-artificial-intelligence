# Exercises for week 4

## Exercise 1

### 1.a

```mermaid
flowchart TD
  s0(
  b 0 0 0 0
  w 0 0 0 0
  0 0 0 0 0
  0 0 0 0 0)

  sb1(
  b b 0 0 0
  w 0 0 0 0
  0 0 0 0 0
  0 0 0 0 0)

  sw11(
  b b w 0 0
  w 0 0 0 0
  0 0 0 0 0
  0 0 0 0 0)
  
  sw12(
  b b 0 0 0
  w w 0 0 0
  0 0 0 0 0
  0 0 0 0 0)

  sw13(
  b b 0 0 0
  w 0 0 0 0
  w 0 0 0 0
  0 0 0 0 0)

  sb2(
  b 0 0 0 0
  w b 0 0 0
  0 0 0 0 0
  0 0 0 0 0)

  sw21(
  b w 0 0 0
  w b 0 0 0
  0 0 0 0 0
  0 0 0 0 0)

  sw22(
  b 0 0 0 0
  w w w 0 0
  0 0 0 0 0
  0 0 0 0 0)
  
  sw23(
  b 0 0 0 0
  w b 0 0 0
  0 w 0 0 0
  0 0 0 0 0)
  
  sw24(
  b 0 0 0 0
  w b 0 0 0
  w 0 0 0 0
  0 0 0 0 0)

  sb3(
  b 0 0 0 0
  b 0 0 0 0
  b 0 0 0 0
  0 0 0 0 0)

  sw31(
  b w 0 0 0
  b 0 0 0 0
  b 0 0 0 0
  0 0 0 0 0)

  sw32(
  b 0 0 0 0
  b w 0 0 0
  b 0 0 0 0
  0 0 0 0 0)

  sw33(
  b 0 0 0 0
  b 0 0 0 0
  b w 0 0 0
  0 0 0 0 0)

  sw34(
  b 0 0 0 0
  b 0 0 0 0
  b 0 0 0 0
  w 0 0 0 0)

  s0 -- val:0 --> sb1
  sb1 -- val:0 --> sw11
  sb1 -- val:0 --> sw12
  sb1 -- val:0 --> sw13

  s0 -- val:-2 --> sb2
  sb2 -- val:0 --> sw21
  sb2 -- val:-2 --> sw22
  sb2 -- val:0 --> sw23
  sb2 -- val:0 --> sw24

  s0 -- val:+2 --> sb3
  sb3 -- val:+2 --> sw31
  sb3 -- val:+2 --> sw32
  sb3 -- val:+2 --> sw33
  sb3 -- val:+2 --> sw34
```

### 1.b

Looking at the minimax value it is first the best for black to go to the right branch (black move piece to a3).\
Then white the minimal value option is between three threes.
Therefore white has no option than to choose one of the threes (white moves piece either to b5, b4, b3, or a2)

The minimax value is **+2**.

### 1.c

The root action that black has to move is a3. 
Since it gives us the minimax value of +2.

## Exercise 2

### 2.a

Max will choose the path a (right path) since it will give 7 points as seen on the picture below:

![minmax tree](./minmax_tree.png)

### 2.b

Given the following picture we do not need to examine the nodes:

- O
- I
  - T
  - U
- K
  - X
  - Y

![alpha-beta tree](./alpha_beta_tree.png)

## Exercise 3

If Min uses a suboptimal stategy then Max will never underestimate the value of the root node.
The reason is due to the fact that Max will always choose the highest value of the children nodes based upon that Min will choose the lowest value of the children nodes.
Therefore Max will never underestimate the value of the root node.

```mermaid
flowchart TD
  s0(Max) --> s01(Min)
  s0 --> s02(Min)

  s01 --> s11(Max) --> 7
  s11 --> 2
  s01 --> s12(Max) --> 10
  s12 --> 11

  s02 --> s21(Max) --> 4
  s21 --> 3
  s02 --> s22(Max) --> 22
  s22 --> 50
```

If Max goes for the global biggest root.\
When it is Min's turn he will go for a random state.

This results in that Max first will go for the right path.\
Then Min goes for the right path.\
Then Max goes for the right path resulting in getting 50 points.

If the Minmax algorithm had been used the result would have been 7 points.
