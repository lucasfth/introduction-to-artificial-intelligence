# Mandatory assignment Week 4

Made by Christian Bank Lauridsen [chbl@itu.dk](mailto:chbl@itu.dk).\
Worked together with Lucas Frey Torres Hanson [luha@itu.dk](mailto:luha@itu.dk)

## Mandatory exercise - Markov decision process

### Part a

#### State space

$$
S:\{(l_1,l_2) | 0 ≤ l_1 ≤ 20, 0 ≤ l_2 ≤ 20\}
$$

Where $l_1$ is the number of cars at location 1 and $l_2$ is the number of cars at location 2.

#### Action space
<!-- $$
A: \{(m_1,m_2, r_1, r_2) | 0 ≤ m_1 ≤ l_1, 0 ≤ m_2 ≤ l_2, 0 ≤ r_1 ≤ l_1, 0 ≤ r_2 ≤ l_2\}
$$

Where $m_1$ is move a car from location 1 to location 2 and $m_2$ is move a car from location 2 to location 1. $r_1$ is renting a car from location 1, $r_2$ is renting a car from location 2. -->

$$
A: \{(m_1,m_2) | 0 ≤ m_1 ≤ l_1, 0 ≤ m_2 ≤ l_2\}
$$

Where $m_1$ is the number of cars moved from location 1 to location 2 and $m_2$ is the number of cars moved from location 1 to location 2.

### Part b

#### Transition function

The amount of available cars at location $l_1'$, with a daily number of rentals (-3) and a daily number oof returns (+3):
$$
l_1' = l_1 - 3 + 3 + m_2
$$
The amount of available cars at location $l_2'$, with a daily number of rentals (-4) and a daily number oof returns (+2)
$$
l_2' = l_2 - 4 + 2 + m_1
$$

Then the transition function would be defined like this:
$$
P((l_1,l_2)| a) = (l_1', l_2')
$$

#### Reward function

### Part c
