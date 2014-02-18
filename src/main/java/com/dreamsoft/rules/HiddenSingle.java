package com.dreamsoft.rules;

import com.dreamsoft.data.Puzzle;

/**
 * Created by Dreamuth on 2/16/14.
 */
class HiddenSingle implements IRule
{
    private Puzzle puzzle = Puzzle.getInstance();;

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
        for (Integer candidate : puzzle.getCandidates(row, column))
        {
            boolean isFound = false;
            for (int j = 1; j <= 9; j++)
            {
                if (j == column)
                {
                    continue;
                }

                if (puzzle.getCandidates(row, j).contains(candidate))
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
                    break;
                }
            }
        }
    }

    private void solveColumnHidden(int row, int column)
    {
        for (Integer candidate : puzzle.getCandidates(row, column))
        {
            boolean isFound = false;
            for (int i = 1; i <= 9; i++)
            {
                if (i == row)
                {
                    continue;
                }
                if (puzzle.getCandidates(i, column).contains(candidate))
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
                    break;
                }
            }
        }
    }

    private void solveCubeHidden(int row, int column)
    {
        for (Integer candidate : puzzle.getCandidates(row, column))
        {
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
                    if (puzzle.getCandidates(i, j).contains(candidate))
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
                    break;
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
