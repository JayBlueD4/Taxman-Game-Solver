# Taxman-Game-Solver

This is a program designed to find the optimal solution for the Taxman Game with the number of cheques provided. A significant portion of this project focused not on the solution's implementation, but its derivation.

The solution process involved brainstorming different scenarios and sketching them out on paper, attempting various sequences to determine the universally optimal one. The game is set up with a specified number of sequential cheques (e.g. 5 cheques = 1, 2, 3, 4, 5). The goal of the game is to accumulate the greatest value in cheques, but the catch is that each cheque removed takes its factors with it. A cheque with no remaining factors cannot be taken. Finding a solution required extensive knowledge of factors.

After much analysis, the optimal solution always began with choosing the largest prime number. However, there is no universal second move for the optimal solution, so the remaining steps the program takes are to brute-force the selection of the remaining tiles. This works efficiently for numbers up to 25, which is well above the standard number of cheques of 12.

In retrospect, another possible optimization is to consider the least optimal solution. Common factors like 2, 3, and 4 are rarely ever chosen unless the cheque count is small, so the elimination of these choices in the solution algorithm would further optimize it. The second number chosen appears to have a pattern as well. Further analysis using the semi-brute force solution algorithm currently developed could lead to a further optimized solution.

A code block displaying the tested sequences is commented out for optimization, but can be uncommented for a deeper understanding of the algorithm.
