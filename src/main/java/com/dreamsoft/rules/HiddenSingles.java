package com.dreamsoft.rules;

import com.dreamsoft.data.Puzzle;

/**
 * Created by Dreamuth on 2/16/14.
 */
public class HiddenSingles implements Runnable
{

    private Puzzle puzzle;

    @Override
    public void run()
    {
        puzzle = Puzzle.getInstance();
        while (!Thread.interrupted())
        {
            solve();
        }
    }

    public void solve()
    {
        for (int row = 1; row <= 9; row++)
        {
            for (int column = 1; column <= 9; column++)
            {
                solveRowHidden(row, column);
                solveColumnHidden(row, column);
                solveCubeHidden(row, column);
            }
        }
    }

    private void solveRowHidden(int row, int column)
    {
        for (int index=0; index<9; index++)
        {
            int candidate = Puzzle.getInstance().getCandidateValue(row, column, index);
            if (candidate == 0)
            {
                continue;
            }
            boolean isFound = false;
            for (int j = 1; j <= 9; j++)
            {
                if (j == column)
                {
                    continue;
                }

                if (puzzle.containsCandidate(row, j, candidate))
                {
                    isFound = true;
                    break;
                }
            }
            if (!isFound)
            {
                if (puzzle.setValue(row, column, candidate))
                {
                    System.out.println(row + " " + column + " = " + candidate + " solveRowHidden");
                }
            }
        }
    }

    private void solveColumnHidden(int row, int column)
    {
        for (int index=0; index<9; index++)
        {
            int candidate = Puzzle.getInstance().getCandidateValue(row, column, index);
            if (candidate == 0)
            {
                continue;
            }
            boolean isFound = false;
            for (int i = 1; i <= 9; i++)
            {
                if (i == row)
                {
                    continue;
                }
                if (puzzle.containsCandidate(i, column, candidate))
                {
                    isFound = true;
                    break;
                }
            }
            if (!isFound)
            {
                if (puzzle.setValue(row, column, candidate))
                {
                    System.out.println(row + " " + column + " = " + candidate +" solveColumnHidden");
                }
            }
        }
    }

    private void solveCubeHidden(int row, int column)
    {
        for (int index=0; index<9; index++)
        {
            int candidate = Puzzle.getInstance().getCandidateValue(row, column, index);
            if (candidate == 0)
            {
                continue;
            }
            boolean isFound = false;
            int startRow = getStartIndex(row);
            int startColumn = getStartIndex(column);
            for (int i=startRow; i<startRow+3; i++)
            {
                for (int j=startColumn; j<startColumn+3; j++)
                {
                    if (row == i && column == j)
                    {
                        continue;
                    }
                    if (puzzle.containsCandidate(i, j, candidate))
                    {
                        isFound = true;
                        break;
                    }
                }
            }
            if (!isFound)
            {
                if (puzzle.setValue(row, column, candidate))
                {
                    System.out.println(row + " " + column + " = " + candidate + " solveCubeHidden");
                }
            }
        }
    }

    private int getStartIndex(int row)
    {
        while ((row-1) % 3 != 0)
        {
            row--;
        }
        return row;
    }
}
