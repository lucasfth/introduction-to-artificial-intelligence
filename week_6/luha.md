# Mandatory assignment Week 6

Made by Lucas Frey Torres Hanson [luha@itu.dk](mailto:luha@itu.dk).\
Worked together with Christian Bank Lauridsen [chbl@itu.dk](mailto:chbl@itu.dk).

## Part 1

Just to summarize the differences.
Backward chaining uses depth-first search, while forward chaining uses breadth-first search.

But backward chaining is often more efficient when answering specific questions (example given by the book was "Where are my keys"), due to only exploring relevant facts of the knowledge base.

Forward chaining is often more efficient when trying to find all possible conclusions, due to the breadth-first search.

## Part 2

Below I will go through the first part of the algorithm and then slowly move over to a more compact version to evaluate the query.

1. PL-BC-ENTAILS(KB, q) is called with q set as A
2. We then check if A is known to be entailed in the KB, which it is not
3. We then start the loop which checks each part of the clause by calling CHECK-ALL(KB, c) with As' clause (D⋀B⋀C) which means that the c will first be set to D, then B, and then C
    1. We start the loop which looks at each part of the clause, which is only D and call PL-BC-ENTAILS(KB, p) with p set to D
        1. We then check if D is known to be entailed in the KB, which it is not
        2. This continues and since D is not proven we then use the clause F⋀E which then by themselves can be proven to be entailed in the KB
    2. Then B is checked and can not be proven. Then its clause E is checked which can be proven to be entailed in the KB
    3. Then C is checked and can not be proven. Then its clause B⋀E⋀G. This will lead to B and E being checked and proven entailed whilst we have to check Gs' clause which leads back to C and this will continue forever.

This means the algorithm will never terminate and therefore it can not answer that KB ⊨ A since not all clauses can be proven to be entailed.

To fix the algorithm we could during the run update the KB to include that C bi-implies G. 
This could make the algorithm terminate and answer that KB ⊨ A.
