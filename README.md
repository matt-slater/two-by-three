#Two By Three

This program attempts to solve puzzles from the NYT called*Two By Three.*
The puzzle rules follow:

 >...every one of the 35 answers is a familiar phrase or name in which two different letters of the alphabet each appear exactly three times. The repetitions can appear in any order. Fill in the blanks to complete the answers. The respective answer lengths are indicated in parentheses.

Usage:

Compile and run with the puzzle as an argument.

     javac TwoByThree.java
     java TwoByThree mi___l__k_

Some test inputs:

* b___ba___l *beerbarrel*
* cha_t__m_mb__ *chartermember*
* \_me__ing_a_t_ *smellingsalts*
* _uh____d_li *muhammadali*


TODO:

- [ ] Make sure no duplicate letter combinations are tried e.g. 'a and b' and 'b and a'.
- [ ] Make user input case insensitive.
- [ ] Check each answer against a dictionary library and output only those that match.
- [ ] Make output more pretty.