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
A: \{m1, m2 | 0 ≤ m1 ≤ l_1,  0 ≤ m2 ≤ l_2\}
$$
Where $m1$ is the amount of moved cars from location 1 to location 2, and 
$m2$ is the amount of moved cars from location 2 to location 1.

### Part b

#### Transition function

$$
P(s' | (l_1,l_2), a) =
\begin{cases}
s' = (l_1-m1, l_2+m1) \text{ if }a = \text{m1} \\
s' = (l_1+m2, l_2-m2) \text{ if }a = \text{m2}
\end{cases}
$$
This gives the probaility of transitioning from state $s = (l_1,l_2)$ to either state $s'$ using action $a$ which is either $m1$ or $m2$,

#### Reward function

It cost the owner 2$ to move a car. 
And the owner gets 10$ per rented car.
The reward function can then be defined like this:
$$
R((l_1, l_2), a, (l_1', l_2')) =
((l_1 - l_1') * 10) + ((l_2 - l_2') * 10) - (m1 + m2) * 2
$$
The reward function consist of the amount of revenue from rented cars in location 1,plus the amount of revenue from rented cars in location 2, minus the cost of moved cars from location 1 to location 2 and the cost of moved cars from location 2 to location 1.

### Part c

At location 2, the car owner loses two cars, since 4 cars are rented out and 2 are returned.
The optimal policy for maximizing profit would be to always keep 3 cars a location 1, 