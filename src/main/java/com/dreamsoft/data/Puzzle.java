package com.dreamsoft.data;

import com.dreamsoft.rules.CandidateRemover;
import com.google.common.collect.ArrayTable;
import java.util.List;

/**
 * Created by Dreamuth on 2/15/14.
 */
public class Puzzle
{
    private static Puzzle instance;

    private ArrayTable<Integer, Integer, Cell> table;

    public static Puzzle getInstance()
    {
        if (instance == null)
        {
            synchronized (Puzzle.class)
            {
                instance = new Puzzle();
            }
        }
        return instance;
    }

    public Puzzle()
    {
        InputReader inputReader = new InputReader();
        table = inputReader.getInput();
    }

    public List<Integer> getCandidates(int row, int column)
    {
        return table.get(row, column).getCandidateValues();
    }

    public Integer getValue(int row, int column)
    {
        return table.get(row, column).getValue();
    }

    public synchronized boolean setValue(int row, int column, Integer value)
    {
        boolean isSuccess = false;
        if (isValidValue(row, column, value))
        {
//            new Printer().printSingleLineCandidates();
            Cell cell = table.get(row, column);
            cell.setValue(value);
            cell.getCandidateValues().clear();
            CandidateRemover candidateRemover = new CandidateRemover();
            candidateRemover.solve();
            isSuccess = true;
        }
        return isSuccess;
    }

    private boolean isValidValue(int row, int column, Integer value)
    {
        return isValidRowValue(row, value)
            && isValidColumnValue(column, value)
            && isValidCubeValue(row, column, value);
    }

    private boolean isValidRowValue(int row, Integer value)
    {
        boolean isValid = true;
        for (int column=1; column<=9; column++)
        {
            if (getValue(row, column).equals(value))
            {
                isValid = false;
            }
        }
        return isValid;
    }

    private boolean isValidColumnValue(int column, Integer value)
    {
        boolean isValid = true;
        for (int row=1; row<=9; row++)
        {
            if (getValue(row, column).equals(value))
            {
                isValid = false;
            }
        }
        return isValid;
    }

    private boolean isValidCubeValue(int row, int column, Integer value)
    {
        boolean isValid = true;
        int startRow = getStartIndex(row);
        int startColumn = getStartIndex(column);
        for (int i=startRow; i<startRow+3; i++)
        {
            for (int j=startColumn; j<startColumn+3; j++)
            {
                if (getValue(i, j).equals(value))
                {
                    isValid = false;
                }
            }
        }
        return isValid;
    }

    private int getStartIndex(int row)
    {
        while ((row-1) % 3 != 0)
        {
            row--;
        }
        return row;
    }

    public int getAllCandidatesCount()
    {
        int count = 0;
        for (int row=1; row<=9; row++)
        {
            for (int column=1; column<=9; column++)
            {
                count += table.get(row, column).getCandidateValues().size();
            }
        }
        return count;
    }

    public int getValueCount()
    {
        int count = 0;
        for (int row=1; row<=9; row++)
        {
            for (int column=1; column<=9; column++)
            {
                if (getValue(row, column) != null)
                {
                    count ++;
                }
            }
        }
        return count;
    }
}

