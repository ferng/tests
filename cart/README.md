## Cart item selection.

# Notes
Oh dear Spanish hindered me :-) I misread a crucial mutually exclusive bit of logic around the 
"specials".

# Process
I spent way too long playing with getting the data read from the CSV files.

# Dependency versions:
* The project was created using a maven archetype so I've left some defaults in there.

# Design decisions
* These are commented throughout the code.
* I have chosen to optimise time over flexibility. For instance returning a HashMap 
  when an Arraylist would have been less specific. However, when I have done that the main id is 
  always what the item is most commonly retrieved by.
* The filter process is specifically targeted for this exercise.
* Although iterating through the main "sizes" collection several times appears wasteful, I 
  wanted to break up the rules into individual logical chunks.

# Things I would have changed
* More time to refactor things, logic is split across Filter and RuleProcessor in a way that is 
  ugly.