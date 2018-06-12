# The Epic Race

## Summary:

The classic hare and tortoise race drawn using JavaFX and JFrames

## Specifications:

- JavaFX Application
- Netbeans IDE

## Installation:

- Download the project and run on Netbeans or similar IDE.
- Run main() in _src/race/RaceJFrame.java_

## Description:

### Simulation: The Tortoise and the Hare

In this problem, we recreate one of the truly great moments in history, namely the classic race of the tortoise and the hare. I used random number generation to develop a simulation of this memorable event.

Our contenders begin the race at “square 1” of 300 squares. Each square represents a possible position along the race course. The finish line is at square 300. The first contender to reach or pass square 300 is rewarded with a pail of fresh carrots and lettuce. The course weaves its way up the side of a slippery mountain, so occasionally the contenders lose ground.

The mountain is simulated by drawing a line that extends from the bottom-left of the window to the top-right of the window. The tortoise and the hare race up the mountain.

There is a clock that ticks once per second. With each tick of the clock, the program adjusts the position of the animals according to the rules (given below). Used variables to keep track of the positions of the animals (i.e., position numbers are 1–300).

Each animal starts at position 1 (i.e., the “starting gate”). If an animal slips left before square 1, the animal is moved back to square 1.

### Rules and Instructions

![rules](https://github.com/PrateekAdhikaree/TheEpicRace/blob/master/screens/simulation_rules.jpg "Simulation Rules")

Generate the percentages in the preceding table by producing a random integer, **i**, in the range 1 ≤ i ≤ 10.

For the tortoise, perform
a “fast plod” when 1 ≤ i ≤ 5,
a “slip” when 6 ≤ i ≤ 7 or
a “slow plod” when 8 ≤ i ≤ 10. 

Use a similar technique to move the hare.

Then, for each tick of the clock (i.e., each repetition of a loop), print a 300-position line showing the letter T in the position of the tortoise and the letter H in the position of the hare (or display images).

Occasionally, the contenders will land on the same square. In this case, the __tortoise bites the hare__, and your program should print 'OUCH!!!' beginning at that position.

All print positions other than the T, the H or the OUCH!!! (in case of a tie) should be blank.

After each line is printed, test for whether either animal has reached or passed square 300. If so, print the winner and terminate the simulation.

If the tortoise wins, print __TORTOISE WINS!!! YAY!!!__ If the hare wins, print __Hare wins. Yuch.__ If both animals win on the same tick of the clock, you may want to favor the turtle (the “underdog”) or you may want to print It's a tie. If neither animal wins, perform the loop again to simulate the next tick of the clock.

### Additional features

- **Sun:** There is a sun at the top left of the window and it keeps moving towards the right as the program executes
- **Audio:** Few more audio clips, like when
  * the race starts
  * tortoise bites the hare (they are both on the same tile)
  * tortoise wins
  * hare wins

## Screenshots:

![screen1](https://github.com/PrateekAdhikaree/TheEpicRace/blob/master/screens/screen1.jpg "Screenshot 1")

![screen2](https://github.com/PrateekAdhikaree/TheEpicRace/blob/master/screens/screen2.jpg "Screenshot 2")

## Video link:

[![](https://img.youtube.com/vi/OtuA6vl1jFU/0.jpg)](https://www.youtube.com/watch?v=OtuA6vl1jFU "Click to play on YouTube.com")

## Thanks!