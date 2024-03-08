# Mandatory assignment Week 4

Made by Christian Bank Lauridsen [chbl@itu.dk](mailto:chbl@itu.dk).\
Worked together with Lucas Frey Torres Hanson [luha@itu.dk](mailto:luha@itu.dk)

## Mandatory exercise - Markov decision process

### Part a

#### State space

$$
S:\{(l_1,l_2) | 0 ≤ l_1 ≤ 20, 0 ≤ l_2 ≤ 20\}
$$

Where $l_1$ is the number of availbable cars at location 1 and $l_2$ is the number of availbable cars at location 2.

#### Action space

$$
A: \{m1, m2\}
$$
Where $m1$ is the action of moving a car from location 1 to location 2,
$m2$ is the action of moving a car from location 2 to location 1. 

### Part b

#### Transition function

For an action of moving a car from location 1 to location 2 $(m1)$
$$
s_1' = (l_1-1, l_2+1)
$$

For an action of moving a car from location 2 to location 1 $(m2)$
$$
s_2' = (l_1+1, l_2-1)
$$

Then the transition function would be defined like this:
$$
P(s_1'|(l_1,l_2), m1)
$$
$$
P(s_2'|(l_1,l_2), m2)
$$
This gives the probaility of transitioning from state $s = (l_1,l_2)$ to either state $s_1'$ or $s_2'$ using action $a$ which is either $m1$ or $m2$,


#### Reward function

It cost the owner 2$ to move a car. Therefore at any action in any state will give a reward of -2$.

The amount of available cars at location $l_1'$, with a daily number of rentals (-3) and a daily number of returns (+3):
$$
l_1' = l_1 - 3 + 3 + m_2
$$
The amount of available cars at location $l_2'$, with a daily number of rentals (-4) and a daily number of returns (+2)
$$
l_2' = l_2 - 4 + 2 + m_1
$$

<!-- 
The amount of daily rentals at location 1 can be defined like this:
$$\min(l_1, 3) = \begin{cases} l_1 & \text{if } l_1 < 3 \\ 3 & \text{else} \end{cases}$$

The amount of daily rentals at location 2 can be defined like this:
$$\min(l_2, 4) = \begin{cases} l_2 & \text{if } l_2 < 4 \\ 4 & \text{else} \end{cases}$$

$$
R((l_1, l_2), a) =
\begin{cases}
\min(l_1, 3) * 10 + \min(l_2, 4) * 10 & \text{if } a = \text{rentCar} \\
-2 & \text{if } a = \text{m1 or } a = \text{m2}
\end{cases}
$$ -->

### Part c
