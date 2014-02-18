package com.dreamsoft.rules;

import com.dreamsoft.data.Puzzle;
import java.util.List;

/**
 * Created by Dreamuth on 2/16/14.
 */
class NakedPair extends AbstractRule implements IRule
{
    private Puzzle puzzle;

    public void solve()
    {
        puzzle = Puzzle.getInstance();
        for (int row = 1; row <= 9; row++)
        {
            for (int column = 1; column <= 9; column++)
            {
                solveRowNakedPair(row, column);
                solveColumnNakedPair(row, column);
                solveCubeHidden(row, column);
            }
        }
    }

    private void solveRowNakedPair(int row, int column)
    {
        boolean isFound = false;
        int pairColumn = 0;
        List<Integer> srcCandidates = puzzle.getCandidates(row, column);
        for (int targetColumn = 1; targetColumn <=9 && srcCandidates.size() == 2; targetColumn++)
        {
            if (column == targetColumn)
            {
                continue;
            }
            List<Integer> targetCandidates = puzzle.getCandidates(row, targetColumn);
            if (srcCandidates.size() == targetCandidates.size()
                && srcCandidates.containsAll(targetCandidates))
            {
                if (!isFound)
                {
                    isFound = true;
                    pairColumn = targetColumn;
                }
                else
                {
                    isFound = false;
                    break;
                }
            }
        }

        if (isFound)
        {
            System.out.println("RowPair: " + row + column + pairColumn + srcCandidates);
            for (int targetColumn = 1; targetColumn <=9; targetColumn++)
            {
                if (targetColumn != column && pairColumn != targetColumn)
                {
                    puzzle.getCandidates(row, targetColumn).removeAll(srcCandidates);
                }
            }
        }
    }

    private void solveColumnNakedPair(int row, int column)
    {
        boolean isFound = false;
        int pairRow = 0;
        List<Integer> srcCandidates = puzzle.getCandidates(row, column);
        for (int targetRow = 1; targetRow <=9 && srcCandidates.size() == 2; targetRow++)
        {
            if (row == targetRow)
            {
                continue;
            }
            List<Integer> targetCandidates = puzzle.getCandidates(targetRow, column);
            if (srcCandidates.size() == targetCandidates.size()
                && srcCandidates.containsAll(targetCandidates))
            {
                if (!isFound)
                {
                    isFound = true;
                    pairRow = targetRow;
                }
                else
                {
                    isFound = false;
                    break;
                }
            }
        }

        if (isFound)
        {
            System.out.println("ColumnPair: " + row + column + pairRow + srcCandidates);
            for (int targetRow = 1; targetRow <=9; targetRow++)
            {
                if (targetRow != row && pairRow != targetRow)
                {
                    puzzle.getCandidates(targetRow, column).removeAll(srcCandidates);
                }
            }
        }
    }

    private void solveCubeHidden(int row, int column)
    {
    }
}
