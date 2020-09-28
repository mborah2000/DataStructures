# DataStructures
1. The Program reads the input files line by line
2. A 2-dimensional array is used to store the start time and end time of shifts of each of the guards. The string values of each array element is split and stored as integer into this 2 -dimensional array
3. Calculate the lone time of each of the guards. We start by taking the difference between end time and start time. 
4. We use a nested loop to check the following cases-
	- When the shifts of the two guards overlap such that the shift of second guard(j) starts before that of first guard(i) and ends before the shift of first guard 
	- When the shifts of the two guards overlap such that the shift of second guard(j) starts after that of first guard (i) and ends after the shift of first guard 
	- When the shift of the first guard(i)'s shift is a subset of the second guard(j)'s shift 
	- When the shift of the second guard(j)'s shift is a subset of the first guard(i)'s shift 
5. We remove the overlap of the shifts to get the lone time value
6. We also store the total overlap value in a variable
7. We calculate the total coverage of the shifts by adding the total lone time of all guards and the total overlap
8. We calculate the maximum coverage by finding the largest value of the total coverage minus lone time.
