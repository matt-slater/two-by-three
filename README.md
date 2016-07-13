#Two By Three

This program attempts to solve puzzles from the NYT called *Two By Three.*
The puzzle rules follow:

 >...every one of the 35 answers is a familiar phrase or name in which two different letters of the alphabet each appear exactly three times. The repetitions can appear in any order. Fill in the blanks to complete the answers. The respective answer lengths are indicated in parentheses.

##Usage:

Go to main directory with pom.xml. Maven install and run the .jar with the puzzle as argument 1 and size of individual words as argument 2.

     cd two-by-three
     mvn clean install
     cd target
     java -cp two-by-three-1.0-SNAPSHOT.jar TwoByThree b___ba___l 4,6

##Some test inputs:

* b___ba___l 4,6 *beer barrel*
* cha_t__m_mb__ 7,6 *charter member*
* \_me__ing_a_t_ 8,5 *smelling salts*
* _uh____d_li 8,3 *muhammad ali*
* f_rst_____g 5,6 *first inning*
* _oph___nd__ils 3,3,3,5 *top hat and tails*


###TODO:

- [x] Check each answer against a dictionary library and output only those that match.
- [ ] Make output more pretty.
- [x] Add support for more than 2 words.
- [ ] Add a manifest for an executable .jar file.
- [ ] Add user-input checking
- [ ] Add usage instructions in case of no CL Arguments