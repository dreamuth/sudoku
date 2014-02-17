package com.dreamsoft.candidate;

import com.dreamsoft.data.Puzzle;

/**
 * Created by Dreamuth on 2/15/14.
 */
public class CandidateRemover implements Runnable
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
        removeCubeCandidates();
        removeRowColumnCandidates();
    }

    private void removeCubeCandidates()
    {
        for (int i = 1; i <= 9; i+=3)
        {
            for (int j = 1; j <= 9; j+=3)
            {
                removeCandidate(i, j);
            }
        }
    }
    private void removeCandidate(int x, int y)
    {
        for (int i = x; i < x+3; i++)
        {
            for (int j = y; j < y+3; j++)
            {
                Integer value = Puzzle.getInstance().getValue(i, j);
                if (value != null)
                {
                    removeCandidate(x, y, value);
                }
            }
        }
    }

    private void removeCandidate(int x, int y, int value)
    {
        for (int i = x; i < x+3; i++)
        {
            for (int j = y; j < y+3; j++)
            {
                Puzzle.getInstance().removeCandidateValue(i, j, value);
            }
        }
    }

    private void removeRowColumnCandidates()
    {
        for (int i = 1; i <= 9; i++)
        {
            for (int j = 1; j <= 9; j++)
            {
                Integer value = Puzzle.getInstance().getValue(i, j);
                if (value != null)
                {
                    removeRowCandidate(i, value);
                    removeColumnCandidate(j, value);
                }
            }
        }
    }

    private void removeRowCandidate(int row, Integer value)
    {
        for (int column = 1; column <=9; column++)
        {
            Puzzle.getInstance().removeCandidateValue(row, column, value);
        }
    }

    private void removeColumnCandidate(int column, Integer value)
    {
        for (int row = 1; row <=9; row++)
        {
            Puzzle.getInstance().removeCandidateValue(row, column, value);
        }
    }
}
