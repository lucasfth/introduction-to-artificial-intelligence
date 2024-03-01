# Exercises for week 5

## Exercise 1 - Bellman equation

![bellman equation](./bellman_equation.png)

Bellman equation

$$
U(s)=\max_{a \in A(s)} \sum_{s'}P(s'|s,a)[R(s,a,s')+\gamma U(s')]
$$

Policy

$$
U^{\pi}(s)= E[\sum_{t=0}^{\infty}\gamma^tR(S_t,\pi(S_t),S_{t+1}]
$$

## Exercise 2 - Grid World
