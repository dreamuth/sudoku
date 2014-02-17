package com.dreamsoft.data;

import com.google.common.collect.ArrayTable;
import java.util.concurrent.atomic.AtomicInteger;

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

//    public ArrayTable<Integer, Integer, Cell> getTable()
//    {
//        return table;
//    }

    public Integer getCandidateValue(int row, int column, int index)
    {
        return table.get(row, column).getCandidates().get(index).get();
    }

    public void removeCandidateValue(int row, int column, Integer value)
    {
        table.get(row, column).removeCandidateValue(value);
    }
    public void removeAllCandidateValue(int row, int column)
    {
        table.get(row, column).removeAllCandidateValue();
    }
    public boolean containsCandidate(int row, int column, Integer value)
    {
        if (value < 1 || value > 9)
        {
            System.out.println("ErrorLog" + row + " " + column + " " + value);
        }
        return table.get(row, column).getCandidates().get(value -1).get() != 0;
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
            table.get(row, column).setValue(value);
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
            if (getValue(row, column) == value)
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
            if (getValue(row, column) == value)
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
                if (getValue(i, j) == value)
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

    public int getCandidatesCount()
    {
        int count = 0;
        for (int row=1; row<=9; row++)
        {
            for (int column=1; column<=9; column++)
            {
                for (AtomicInteger candidate : table.get(row, column).getCandidates())
                {
                    if (candidate.get() != 0)
                    {
                        count++;
                    }
                }
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
                if (table.get(row, column).getValue() != null)
                {
                    count ++;
                }
            }
        }
        return count;
    }
}

