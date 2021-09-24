# **Bowling**

**Problem Description**

Create a program, which, given a valid sequence of rolls for one line of American Ten-Pin Bowling, produces the total score for the game. Here are some things that the program will do:

- We will check for valid rolls.
- We will check for correct number of rolls and frames.
- We will provide scores for intermediate frames.

I think you&#39;ll see that improvements like those above would go in readily if they were needed for real.

We can briefly summarize the scoring for this form of bowling:

- Each game, or &quot;line&quot; of bowling, includes ten turns, or &quot;frames&quot; for the bowler.
- In each frame, the bowler gets up to two tries to knock down all the pins.
- If in two tries, he fails to knock them all down, his score for that frame is the total number of pins knocked down in his two tries.
- If in two tries he knocks them all down, this is called a &quot;spare&quot; and his score for the frame is ten plus the number of pins knocked down on his next throw (in his next turn).
- If on his first try in the frame he knocks down all the pins, this is called a &quot;strike&quot;. His turn is over, and his score for the frame is ten plus the simple total of the pins knocked down in his next two rolls.
- If he gets a spare or strike in the last (tenth) frame, the bowler gets to throw one or two more bonus balls, respectively. These bonus throws are taken as part of the same turn. If the bonus throws knock down all the pins, the process does not repeat: the bonus throws are only used to calculate the score of the final frame.
- The game score is the total of all frame scores.

More info on the rules at: [How to Score for Bowling](http://www.topendsports.com/sport/tenpin/scoring.htm)

**Clues**

What makes this game interesting to score is the lookahead in the scoring for strike and spare. At the time we throw a strike or spare, we cannot calculate the frame score: we have to wait one or two frames to find out what the bonus is.

**Suggested Test Cases**

(When scoring &quot;X&quot; indicates a strike, &quot;/&quot; indicates a spare, &quot;-&quot; indicates a miss)

- X X X X X X X X X X X X (12 rolls: 12 strikes) = 10 frames \* 30 points = 300
- 9- 9- 9- 9- 9- 9- 9- 9- 9- 9- (20 rolls: 10 pairs of 9 and miss) = 10 frames \* 9 points = 90
- 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5 (21 rolls: 10 pairs of 5 and spare, with a final 5) = 10 frames \* 15 points = 150

# **Solution**

**Main Classes**

- Game class purpose is to process every roll input (int format) and to calculate the score of the game (core class)
- GameScorer class goal is to process the input string, to catch any invalid input and to provide the input rolls to the Game class

**Other Classes**

- A lot of customs Exception classes for any invalid string input format
- A constant reference class with all the bowling rules

**Tests**

- Almost every method has been unit tested
- A lot of custom valid test cases have been designed for both Game and GameScorer classes
- Every custom Exception has been tested
- Main class and Junit tests contain the 3 test cases of the kata
