# Mandatory assignment Week 4

Made by Christian Bank Lauridsen [chbl@itu.dk](mailto:chbl@itu.dk).\
Worked together with Lucas Frey Torres Hanson [luha@itu.dk](mailto:luha@itu.dk)

## Part 1

```pseudo
function EXPECTIMINIMAX-SEARCH(state) returns an action 
  value, move ← MAX-VALUE(state)
  return move

function MAX-VALUE(state) returns a (utility, move) pair
  if IS-TERMINAL(state) then 
    return UTILITY(state, MAX), null 
  v ← −∞
  for each a in ACTIONS(state) do
      v2, a2 ← CHANCE-VALUE(RESULT(state, a), MIN) //next action
      if v2 > v then
        v, move ← v2, a 
  return v, move

function MIN-VALUE(state) returns a (utility, move) pair
  if IS-TERMINAL(state) then 
    return UTILITY(state, MIN), null
  v ← +∞
  for each a in ACTIONS(state) do
    v2, a2 ← CHANCE-VALUE(RESULT(state, a), MAX) //next action
    if v2 < v then
        v, move ← v2, a
  return v, move

function CHANCE-VALUE(state, player) returns (utility, move) pair
  if IS-TERMINAL(state) then
    return UTILITY(state, player), null
  v ← 0
  for each a in ACTIONS(state) do
    if player is MAX then
      v2, a2 ← MAX-VALUE(RESULT(state, a))
    else:
      v2, a2 ← MIN-VALUE(RESULT(state, a))
    v ← v + v2 * P(0.5)
  return v, null
```

## Part 2

The blue color nodes in the tree MAXs  actions.\
The non blue color nodes in the tree are MINs actions.\
The dark blue color node is the initial state of the game.

### Part 2.a

``` mermaid
flowchart TD
  max(0 0
  0 0)
  max -- H --> max0
  max -- H --> max1
  max -- H --> max2
  style max fill:#100099,stroke:#333,stroke-width:2px;
  style max0 fill:#006699,stroke:#333,stroke-width:2px;
  style max00 fill:#006699,stroke:#333,stroke-width:2px;
  style max01 fill:#006699,stroke:#333,stroke-width:2px;
  style max02 fill:#006699,stroke:#333,stroke-width:2px;
  max0(
  H 0
  0 0
  0.375
  )
  
  min00(
  H T
  0 0
  -0.25
  )
  max0 -- T --> min00
  max0 -- H --> min01
  max0 -- H --> min02

  min01(
  H 0
  H 0
  0.25
  )

  min01 -- T --> max011(
    H T
    H 0
    +1 
  )
  style max011 fill:#006699,stroke:#333,stroke-width:2px;

  min01 -- H --> max012(
    H 0
    H H
    -0.5
  )
  style max012 fill:#006699,stroke:#333,stroke-width:2px;

  max012 -- T --> min01200(
    H T
    H H
    -1 
    
  )
  max012 -- H --> min0121(
    H 0
    H H
    0
  )

  min02(
  H 0
  0 H
  1
  )
  min02 -- T --> max002(
    H T
    0 H
    +1
  )
  style max002 fill:#006699,stroke:#333,stroke-width:2px;
  min02 -- H --> max003(
    H 0
    H H
    +1
  )
  style max003 fill:#006699,stroke:#333,stroke-width:2px;
  max00(
  H T
  0 0
  0
  
  )
  max01(
  H T
  H 0 
  +1
  )
  max02(
  H T
  0 H
  -0.5
  )
  min000(
  H T
  0 H
  0
  )
  min001(
   H T
  H H
  -1
  )
  
  min00 -- T --> max00
  min00 -- H --> max01
  min00 -- H --> max02
  max02 -- T --> min000
  max02 -- H --> min001
  

  style max1 fill:#006699,stroke:#333,stroke-width:2px;
  style max10 fill:#006699,stroke:#333,stroke-width:2px;
  style max11 fill:#006699,stroke:#333,stroke-width:2px;
  style max12 fill:#006699,stroke:#333,stroke-width:2px;
  style max110 fill:#006699,stroke:#333,stroke-width:2px;
  style max111 fill:#006699,stroke:#333,stroke-width:2px;
  style max120 fill:#006699,stroke:#333,stroke-width:2px;
  style max121 fill:#006699,stroke:#333,stroke-width:2px;
  max1(
  0 0
  H 0
  0.75
  )
  min10(
  0 T
  H 0
  0.5
  )
  min11(
  H 0
  H 0 
  1
  )
  min12(
  0 0
  H H
  1
  )
  max1 -- T --> min10
  max1 -- H --> min11
  max1 -- H --> min12

  max10(
  0 T
  H 0 
  0
  )
  max11(
  H T
  H 0 
  +1
  )
  max12(
  0 T
  H H 
  +1
  )

  min10 -- T --> max10
  min10 -- H --> max11
  min10 -- H --> max12

  max110(
  H T
  H 0  
  +1
  )
  max111(
  H 0
  H H 
  +1 
  )
  min11 -- T --> max110
  min11 -- H --> max111

  max120(
  0 T
  H H
  +1
  )
  max121(
  H 0
  H H
  +1
  )
  min12 -- T --> max120
  min12 -- H --> max121

  style max2 fill:#006699,stroke:#333,stroke-width:2px;
  style max200 fill:#006699,stroke:#333,stroke-width:2px;
  style max201 fill:#006699,stroke:#333,stroke-width:2px;
  style max202 fill:#006699,stroke:#333,stroke-width:2px;
  style max220 fill:#006699,stroke:#333,stroke-width:2px;
  style max221 fill:#006699,stroke:#333,stroke-width:2px;
  max2(
  0 0
  0 H
  0.375
  )
  min20(
  0 T
  0 H
  -0.25
  )
  min21(
  0 0
  H H
  0.25
  )

  min21 -- T --> max2100(
    0 T
    H H
    +1
  )
   style max2100 fill:#006699,stroke:#333,stroke-width:2px;

  min21 -- H --> max2101(
    H 0
    H H
    -0.5
  )
   style max2101 fill:#006699,stroke:#333,stroke-width:2px;

  max2101 -- T --> min21010(
    H T
    H H
    -1
  )

  max2101 -- H --> min21011(
    H 0
    H H
    0
  )

  min22(
  H 0
  0 H
  1
  )

  max2 -- T --> min20
  max2 -- H --> min21
  max2 -- H --> min22
  
  max200(
  0 T
  0 H 
  0
  )
  max201(
  0 T
  H H
  +1
  )
  max202(
  H T
  0 H
  -0.5
  )
  min20 -- T --> max200
  min20 -- H --> max201
  min20 -- H --> max202

  min2020(
  H T
  0 H
  0
  )
  min2021(
  H T
  H H
  -1
  )
  max202 -- T --> min2020
  max202 -- H --> min2021

  max220(
  H T
  0 H
  +1)
  max221(
  H 0
  H H
  +1
  )
  min22 -- T --> max220
  min22 -- H --> max221
```

### Part 2.b

The following nodes should be read from the left to right in the tree. The EXPECTIMINIMAX values can also be seen in the drawing of the game tree.
The following will show the calculations for the EXPECTIMINIMAX value for each node.

The values of the bottom nodes in the tree:

- 0
- -1
- -1
- 0
- 0
- -1
- -1
- 0

The values of the second lowest nodes in the tree:

- 0
- +1
- (0.5\*0 + 0.5\*(-1)) = -0.5
- +1
- (0.5\*(-1) + 0.5\*0) = -0.5
- +1
- +1
- 0
- +1
- +1
- +1
- +1
- +1
- +1
- 0
- +1
- (0.5\*0 + 0.5\*(-1)) = -0.5
- +1
- +1
- +1
- (0.5\*(-1) + 0.5\*0) = -0.5

The values of the third lowest nodes in the tree:

- (0.5\*0 + 0.5\*(-0.5)) = -0.25
- (0.5\*1 + 0.5\*(-0.5)) = 0.25
- (0.5\*1 + 0.5\*1) = 1
- (0.5\*0 + 0.5\*1) = 0.5
- (0.5\*1 + 0.5\*1) = 1
- (0.5\*1 + 0.5\*1) = 1
- (0.5\*0 + 0.5\*(-0.5)) = -0.25
- (0.5\*1 + 0.5\*1) = 1
- (0.5\*1 + 0.5\*(-0.5)) = 0.25

The values of the top (second layer) layer:

- (0.5\*(-0.25) + 0.5\*1) = 0.375
- (0.5\*0.5 + 0.5\*1) = 0.75
- (0.5\*(-0.25) + 0.5\*1) = 0.375

### Part 2.c

The EXPECTIMINIMAX decision for MAX is to go down the middle node in the tree since it has the highest EXPECTIMINIMAX value of 0.75 and therefore has the highest chance of winning.
