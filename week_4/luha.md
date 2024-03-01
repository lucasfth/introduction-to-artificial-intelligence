# Mandatory assignment Week 4

Made by Lucas Frey Torres Hanson [luha@itu.dk](mailto:luha@itu.dk).\
Worked together with Christian Bank Lauridsen [chbl@itu.dk](mailto:chbl@itu.dk).

## Part 1

## Part 2

### Part 2.a and 2.b

```mermaid
flowchart TD
s0(
    0 0
    0 0
) --> b10(
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
    B W
    W B
    Val: -1
)
b22 -- Heads --> w32(
    White turn
    Invalid move

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
    B B
    0 W
    Val: +1
)
w23 -- Heads --> b34(
    Black turn
    B 0
    B W
    Val: +1
)
```

### Part 2.c

The expected minimax decision for Max would be to choose the middle node with the value of 0.75.
It would give him the best odds of winning the game.
