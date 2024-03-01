# Exercises for week 5

## Exercise 1 - Bellman equation

Default Bellman equation

$$
U(s)=\max_{a \in A(s)} \sum_{s'}P(s'|s,a)[R(s,a,s')+\gamma U(s')]
$$

Pi Policy

$$
U^{\pi}(s)= E[\sum_{t=0}^{\infty}\gamma^tR(S_t,\pi(S_t),S_{t+1}]
$$

Bellman equation using pi policy

$$
U(s)=\max_{\pi(S') \in A(s)} \sum_{s'}P(s'|s,\pi(S'))[R(s,\pi(S'),s')+\gamma U(s')]
$$

## Exercise 2 - Grid World
