# Mandatory assignment Week 4

Made by Lucas Frey Torres Hanson [luha@itu.dk](mailto:luha@itu.dk).\
Worked together with Christian Bank Lauridsen [chbl@itu.dk](mailto:chbl@itu.dk).

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
      v2, a2 ← CHANCE-VALUE(RESULT(state, a), MAX) //next action
      if v2 > v then
        v, move ← v2, a 
  return v, move

function MIN-VALUE(state) returns a (utility, move) pair
  if IS-TERMINAL(state) then 
    return UTILITY(state, MIN), null
  v ← +∞
  for each a in ACTIONS(state) do
    v2, a2 ← CHANCE-VALUE(RESULT(state, a), MIN) //next action
    if v2 < v then
        v, move ← v2, a
  return v, move

function CHANCE-VALUE(state, player) returns (utility, move) pair
  sum ← 0
  for each a in ACTIONS(state) do
    // Will for each action simulate the next players action
    // to calculate the expected value of the action
    if player is Max then
      v, a2 ← MIN-VALUE(RESULT(state, a))
    else
      v, a2 ← MAX-VALUE(RESULT(state, a))
    sum ← sum + P(a)*v
  return sum, null
```

## Part 2

### Part 2.a and 2.b

The below both shows the game tree and what each node would be evaluated to.

<!-- ![minmax tree](./luha-gametree.png) -->

```mermaid
flowchart TD
s0(
    0 0
    0 0
) -- Heads --> b10(
    Black turn

    B 0
    0 0
    Val: +0,375
) -- Tails --> w20(
    White turn

    B W
    0 0
    Val: -0,25
) -- Tails --> b30(
    Black turn
    Illegal move

    B W
    0 0
    Val: 0
)
w20 -- Heads --> b31(
    Black turn
    Gets two in a row

    B W
    B 0
    Val: +1
)
w20 -- Heads --> b32(
    Black turn

    B W
    0 B
    Val: -0,5
) -- Tails --> w40(
    White turn
    Illegal move

    B W
    0 B
    Val: 0
)
b32 -- Heads --> w41(
    White turn
    Gets two in a row

    B W
    W B
    Val: -1
)
b10 -- Heads --> w21(
    White turn

    B 0
    W 0
    Val: 0,25
) -- Tails --> b21(
    Black turn
    Gets two in a row

    B B
    W 0
    Val: +1
)
w21 -- Heads --> b22(
    Black turn

    B 0
    W B
    Val: -0,5
) -- Tails --> w31(
    White turn
    Gets two in a row

    B W
    W B
    Val: -1
)
b22 -- Heads --> w32(
    White turn
    Illegal move

    B 0
    W B
    Val: 0
)
b10 -- Heads --> w23(
    White turn

    B 0
    0 W
    Val: +1
) -- Tails --> b33(
    Black turn
    Gets two in a row

    B B
    0 W
    Val: +1
)
w23 -- Heads --> b34(
    Black turn
    Gets two in a row

    B 0
    B W
    Val: +1
)
s0 -- Heads --> b11(
    Black turn
    
    0 0
    B 0
    Val: +0,75
) -- Tails --> w24(
    White turn

    0 W
    B 0
    Val: 0,5
) -- Tails --> b35(
    Black turn
    Illegal move

    0 W
    B 0
    Val: 0
)
w24 -- Heads --> b36(
    Black turn
    Gets two in a row

    B W
    B 0
    Val: +1
)
w24 -- Heads --> b37(
    Black turn
    Gets two in a row

    0 W
    B B
    Val: +1
)
b11 -- Heads --> w25(
    White turn

    W 0
    B 0
    Val: +1
) -- Tails --> b38(
    Black turn
    Gets two in a row

    W B
    B 0
    Val: +1
)
w25 -- Heads --> b39(
    Black turn
    Gets two in a row

    W 0
    B B
    Val: +1
)
b11 -- Heads --> w26(
    White turn

    0 0
    B W
    Val: +1
) -- Tails --> b40(
    Black turn
    Gets two in a row

    0 B
    B W
    Val: +1
)
w26 -- Heads --> b41(
    Black turn
    Gets two in a row

    B 0
    B W
    Val: +1
)
s0 -- Heads --> b12(
    Black turn

    0 0
    0 B
    Val: +0,375
) -- Tails --> w27(
    0 W
    0 B
    Val: -0,25
) -- Tails --> b42(
    Black turn
    Illegal move

    0 W
    0 B
    Val: 0
)
w27 -- Heads --> b43(
    Black turn
    Gets two in a row

    0 W
    B B
    Val: +1
)
w27 -- Heads --> b44(
    Black turn

    B W
    0 B
    Val: -0,5
) -- Tails --> w33(
    White turn
    Illegal move

    B W
    0 B
    Val: 0
)
b44 -- Heads --> w34(
    White turn
    Gets two in a row

    B W
    W B
    Val: -1
)
b12 -- Heads --> w28(
    White turn

    W 0
    0 B
    Val: +1
) -- Tails --> b45(
    Black turn
    Gets two in a row

    W B
    0 B
    Val: +1
)
w28 -- Heads --> b46(
    Black turn
    Gets two in a row

    W 0
    B B
    Val: +1
)
b12 -- Heads --> w29(
    White turn

    0 0
    W B
    Val: +0,25
) -- Tails --> b47(
    Black turn
    Gets two in a row

    0 B
    W B
    Val: +1
)
w29 -- Heads --> b48(
    Black turn

    B 0
    W B
    Val: -0,5
) -- Tails --> w35(
    White turn
    Gets two in a row

    B W
    W B
    Val: -1
)
b48 -- Heads --> w36(
    White turn
    Illegal move

    B 0
    W B
    Val: 0
)
```

All the values have been calculated so by the following.

- The node which is reached given tails was multiplied by 0,5.
- Then the node which gave the optimal value given tails was multiplied by 0,5 (of course optimal depends on if we are at a max (black) or a min (white) node).
- These two values where then added together to give the value of the node.

### Part 2.c

The expected minimax decision for Max would be to choose the middle node with the value of 0.75.
It would give him the best odds of winning the game.
