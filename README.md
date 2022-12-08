# euler54
- How the solution works (at a high level)  
  Input hands are classified by construction attempt from highest combination grades to lowest  
  Then as we know the hands grades we can compare them to find the winner or tie
- Which object-oriented programming ideas did you apply to solve the problem;  
  Inheritance, polymorphism
- What you like and do not like about your solution;
  - Negatives:
    - Didn't cover with tests properly as have little time for this, but added couple of tests for example
    - Combination classification is not optimal in terms of performance. For example isFlush is calculated both
      for StraightFlush and Flush. This allows cleaner code on the other hand as we don't need to juggle with lots of
      variables
  - Positives:
    - Classification and comparison logic is separated to specific classes for each combination grade
    - Common logic is extracted to a superclass and can be reused