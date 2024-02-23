# Mandatory assignment Week 4

Made by Christian Bank Lauridsen [chbl@itu.dk](mailto:chbl@itu.dk) and Lucas Frey Torres Hanson [luha@itu.dk](mailto:luha@itu.dk)

## Part 1

```pseudo
function EXPECTIMINIMAX-SEARCH(game, state) returns an action 
    player←game.TO-MOVE(state)
    value, move←MAX-VALUE(game, state)
    return move

function MAX-VALUE(game, state) returns a (utility, move) pair
    if game.IS-TERMINAL(state) then return game.UTILITY(state, player), null 
    v, move←−∞
    for each a in game.ACTIONS(state) do
        v2, a2←MIN-VALUE(game, game.RESULT(state, a)) if v2 > v then
        v, move←v2, a 
    return v, move

function MIN-VALUE(game, state) returns a (utility, move) pair
    if game.IS-TERMINAL(state) then return game.UTILITY(state, player), null 
    v, move←+∞
    for each a in game.ACTIONS(state) do
        v2, a2←MAX-VALUE(game, game.RESULT(state, a)) 
        if v2 < v then
            v, move←v2, a 
    return v, move

function CHANCE-VALUE(is_heads) returns a (boolean) value
    is_heads ← random boolean value
    return is_heads
    
```

Ruleset:
intial state:
0 0
0 0

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
