#Two By Three

This program attempts to solve puzzles from the NYT called *Two By Three.*
The puzzle rules follow:

 >...every one of the 35 answers is a familiar phrase or name in which two different letters of the alphabet each appear exactly three times. The repetitions can appear in any order. Fill in the blanks to complete the answers. The respective answer lengths are indicated in parentheses.

##Usage:

Compile and run with the puzzle as argument 1 and size of individual words as argument 2.

     javac TwoByThree.java
     java TwoByThree mi___l__k_ 4,6 

##Some test inputs:

* b___ba___l 4,6 *beer barrel*
* cha_t__m_mb__ 7,6 *charter member*
* \_me__ing_a_t_ 8,5 *smelling salts*
* _uh____d_li 8,3 *muhammad ali*
* f_rst_____g 4,6 *first inning*
* _oph___nd__ils 3,3,3,5 *top hat and tails*


###TODO:

- [ ] Check each answer against a dictionary library and output only those that match.
- [ ] Make output more pretty.