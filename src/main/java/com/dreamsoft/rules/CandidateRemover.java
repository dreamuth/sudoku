package com.dreamsoft.rules;

import com.dreamsoft.data.Puzzle;

/**
 * Created by Dreamuth on 2/15/14.
 */
public class CandidateRemover implements IRule
{
    private final Puzzle puzzle = Puzzle.getInstance();

    @Override
    public void solve()
    {
        removeCubeCandidates();
        removeRowColumnCandidates();
    }

    private void removeCubeCandidates()
    {
        for (int row = 1; row <= 9; row+=3)
        {
            for (int column = 1; column <= 9; column+=3)
            {
                removeCubeCandidate(row, column);
            }
        }
    }

    private void removeCubeCandidate(int srcRow, int srcColumn)
    {
        for (int targetRow = srcRow; targetRow < srcRow+3; targetRow++)
        {
            for (int targetColumn = srcColumn; targetColumn < srcColumn+3; targetColumn++)
            {
                Integer value = puzzle.getValue(targetRow, targetColumn);
                if (value != null)
                {
                    removeCubeCandidate(srcRow, srcColumn, value);
                }
            }
        }
    }

    private void removeCubeCandidate(int srcRow, int srcColumn, Integer value)
    {
        for (int targetRow = srcRow; targetRow < srcRow+3; targetRow++)
        {
            for (int targetColumn = srcColumn; targetColumn < srcColumn+3; targetColumn++)
            {
                puzzle.getCandidates(targetRow, targetColumn).remove(value);
            }
        }
    }

    private void removeRowColumnCandidates()
    {
        for (int row = 1; row <= 9; row++)
        {
            for (int column = 1; column <= 9; column++)
            {
                Integer value = puzzle.getValue(row, column);
                if (value != null)
                {
                    removeRowCandidate(row, value);
                    removeColumnCandidate(column, value);
                }
            }
        }
    }

    private void removeRowCandidate(int row, Integer value)
    {
        for (int column = 1; column <=9; column++)
        {
            puzzle.getCandidates(row, column).remove(value);
        }
    }

    private void removeColumnCandidate(int column, Integer value)
    {
        for (int row = 1; row <=9; row++)
        {
            puzzle.getCandidates(row, column).remove(value);
        }
    }
}
