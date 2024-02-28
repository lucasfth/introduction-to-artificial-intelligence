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

### Part 2.b

### Part 2.c
