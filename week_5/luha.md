# Mandatory assignment Week 5

Made by Lucas Frey Torres Hanson [luha@itu.dk](mailto:luha@itu.dk).\
Worked together with Christian Bank Lauridsen [chbl@itu.dk](mailto:chbl@itu.dk).

## Part a

Set of states $S$:

$$
S: \{(loc_a,loc_b) | 0 ≤ a ≤ 20, 0 ≤ b ≤ 20\}
$$

$a$ and $b$ are the two rental locations and contains the number of cars each location has.

Action function $A$:

$$
A(s): \{move_{ab}, move_{ba} | 0 ≤ m_{ab} ≤ loc_a, 0 ≤ m_{ba} ≤ loc_b\}
$$

Where $move_{ab}$ means to move a car from location $a$ to $b$, $move_{ba}$ means to move a car from location b to $b$.

## Part b

<!-- had a diagram which is the rent-luha.md -->

### Transition function $P$

Move from location a to b with some number of cars: $move_{ab}: \{loc_a-move_{ab}, loc_b+move_{ab}\}$

Move from location b to a with some number of cars: $move_{ba}: \{loc_b-move_{ba}, loc_a+move_{ba}\}$

$$
P(s'|s,a) = \begin{cases} 
  s' = loc_a-move_{ab}, loc_b+move_{ab} \space if\space a = move_{ab}\\
  s' = loc_b-move_{ba}, loc_a+move_{ba} \space if\space a = move_{ba}
\end{cases}
$$

### Reward function $R$

To move a car from location a to b, and vice versa, it costs 2$.\
Therefore, the reward function is:

$$
R_{move}(s\{loc_a|loc_b\},a\{m_1|m_2\},s'\{loc_b|loc_a\}) = -2\$
$$

Furthermore at the start of the day location a gets three rented cars and b gets four cars rented, if the amount is available and each car gives 10$.\
Then at the end of the day location a gets three cars returned to location a and location b gets two cars returned.

Lets define the amount of available cars at location a as $loc_a'$ and at location b as $loc_b'$.

All this results in a reward function as below:

$$
R((loc_a, loc_b), (move_{ab},move_{ba}), (loc_a',loc_b')) = \\
(loc_a-loc_a')*10\$ + (loc_b-loc_b')*10\$ - (move_{ab}+move_{ba})*2\$
$$

## Part c

Due to location a getting an equal amount of cars rented and returned, this can keep running indefinitely and keep getting 30$ total per day.\
Location b is not as lucky, as it gets four cars rented and two cars returned.
This means for a while Jack will get 40$ per day, but then it will drop to 20$ per day (when he gets two cars returned and is the only ones he can then rent the following day).
After he will have no cars left and will not get any money.\
Then Jack has the option to move cars from location a to b, to help location b get revenue.
The problem is that it will costs Jack 2$ per car moved, which will make him lose money.
He will then also at some point run out of cars at location a\
The alternative is just not to get revenue from location b, which would be more profitable for Jack as he would then be able to keep running location a.
The problem with this is that Jack would have a big unnecessary buffer at location a.\
The best alternative would be to move all cars except three cars from location a to b (to ensure that there can be rented three and three can be the buffer for when they are returned), and then keep running location a.
Though we can only move two cars per night due to location b only being able to handle 20 cars and only loosing two cars per day.
This would shortly give a boost in revenue as location b can keep running for a while longer, and location a would still be able to keep running indefinitely.
Though it would only make a difference of $(170\$-34\$)=136\$$ by moving the 17 cars.
