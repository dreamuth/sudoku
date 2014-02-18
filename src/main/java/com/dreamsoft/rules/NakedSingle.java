package com.dreamsoft.rules;

import com.dreamsoft.data.Puzzle;

/**
 * Created by Dreamuth on 2/15/14.
 */
class NakedSingle implements IRule
{
    private Puzzle puzzle = Puzzle.getInstance();

    public void solve()
    {
        for (int row = 1; row <= 9; row++)
        {
            for (int column = 1; column <= 9; column++)
            {
                if (puzzle.getCandidates(row, column).size() == 1)
                {
                    Integer nakedValue = puzzle.getCandidates(row, column).get(0);
                    puzzle.setValue(row, column, nakedValue);
                    System.out.println(row + " " + column + " = " + nakedValue + " Naked Single");
                }
            }
        }
    }
}
