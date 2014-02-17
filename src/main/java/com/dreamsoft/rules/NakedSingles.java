package com.dreamsoft.rules;

import com.dreamsoft.data.Puzzle;

/**
 * Created by Dreamuth on 2/15/14.
 */
public class NakedSingles implements Runnable
{
    @Override
    public void run()
    {
        while (!Thread.interrupted())
        {
            solve();
        }
    }

    public void solve()
    {
        for (int i = 1; i <= 9; i+=3)
        {
            for (int j = 1; j <= 9; j+=3)
            {
                solveCube(i, j);
            }
        }
    }

    private void solveCube(int x, int y)
    {
        for (int i = x; i < x+3; i++)
        {
            for (int j = y; j < y+3; j++)
            {
                Integer nakedValue = getCandidatesCount(i, j);
                if (nakedValue != null)
                {
                    System.out.println("Naked Single " + i + " " + j + " = " + nakedValue);
                    Puzzle.getInstance().setValue(i, j, nakedValue);
                }
            }
        }
    }

    private Integer getCandidatesCount(int row, int column)
    {
        Integer nakedValue = null;
        for (int index=0; index<9; index++)
        {
            int candidate = Puzzle.getInstance().getCandidateValue(row, column, index);
            if (candidate != 0)
            {
                if (nakedValue != null)
                {
                    nakedValue = null;
                    break;
                }
                nakedValue = candidate;
            }
        }
        return nakedValue;
    }
}
