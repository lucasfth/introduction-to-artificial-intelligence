# Mandatory assignment Week 4

Made by Christian Bank Lauridsen [chbl@itu.dk](mailto:chbl@itu.dk) and Lucas Frey Torres Hanson [luha@itu.dk](mailto:luha@itu.dk)

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

- If tails then tails position
  - If tail is not available then game.IS-TERMINAL(state)
- If heads then check
  - If you have tails position (if so then lay diagonally)
  - Else check if you have a heads position (if so then lay it adjacent)
  - Else then lay it low left if available
  - Else lay it where possible
  - Else game.IS-TERMINAL(state)

## Part 2

### Part 2.a



``` mermaid
flowchart TD
  max(0 0
  0 0)
  max --> max0
  max --> max1
  max --> max2
  style max0 fill:#006699,stroke:#333,stroke-width:2px;
  style max00 fill:#006699,stroke:#333,stroke-width:2px;
  style max01 fill:#006699,stroke:#333,stroke-width:2px;
  style max02 fill:#006699,stroke:#333,stroke-width:2px;
  max0(
  H 0
  0 0
  )
  
  min00(
  H T
  0 0
  
  )
  max0 -- 0.5 --> min00
  max0 -- 0.5 --> min01
  max0 -- 0.5 --> min02

  min01(
  H 0
  H 0
  )
  min02(
  H 0
  0 T
  )
  max00(
  H T
  0 0
  -1)
  max01(
  H T
  H 0 
  +1)
  max02(
  H T
  0 H
  )
  min000(H T
  0 H
  +1)
  min001(H T
  H H
  -1)
  
  min00 -- 0.5 --> max00
  min00 -- 0.5 --> max01
  min00 -- 0.5 --> max02
  max02 -- 0.5 --> min000
  max02 -- 0.5 --> min001
  

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
  H 0)
  min10(
  0 T
  H 0 
  )
  min11(
  H 0
  H 0 
  )
  min12(
  0 0
  H H
  )
  max1 -- 0.5 --> min10
  max1 -- 0.5 --> min11
  max1 -- 0.5 --> min12

  max10(
  0 T
  H 0 
  -1
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

  min10 -- 0.5 --> max10
  min10 -- 0.5 --> max11
  min10 -- 0.5 --> max12

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
  min11 -- 0.5 --> max110
  min11 -- 0.5 --> max111

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
  min12 -- 0.5 --> max120
  min12 -- 0.5 --> max121

  style max2 fill:#006699,stroke:#333,stroke-width:2px;
  style max200 fill:#006699,stroke:#333,stroke-width:2px;
  style max201 fill:#006699,stroke:#333,stroke-width:2px;
  style max202 fill:#006699,stroke:#333,stroke-width:2px;
  style max220 fill:#006699,stroke:#333,stroke-width:2px;
  style max221 fill:#006699,stroke:#333,stroke-width:2px;
  max2(
  0 0
  0 H)
  min20(
  0 T
  0 H)
  min21(
  0 0
  H H)
  min22(
  H 0
  0 H)

  max2 -- 0.5 --> min20
  max2 -- 0.5 --> min21
  max2 -- 0.5 --> min22
  
  max200(
  0 T
  0 H 
  -1)
  max201(
  0 T
  H H
  +1)
  max202(
  H T
  0 H)
  min20 -- 0.5 --> max200
  min20 -- 0.5 --> max201
  min20 -- 0.5 --> max202

  min2020(
  H T
  0 H
  +1)
  min2021(
  H T
  H H
  -1)
  max202 -- 0.5 --> min2020
  max202 -- 0.5 --> min2021

  max220(
  H T
  0 H
  +1)
  max221(
  H 0
  H H
  +1
  )
  min22 -- 0.5 --> max220
  min22 -- 0.5 --> max221


```


### Part 2.b

### Part 2.c
