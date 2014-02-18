package com.dreamsoft.io;

import com.dreamsoft.data.Puzzle;

/**
 * Created by Dreamuth
 *
 * on 2/15/14.
 */
public class Printer
{
    private final Puzzle puzzle = Puzzle.getInstance();

    public void printResult()
    {
        System.out.println("----------------------");
        for (Integer i=1; i<=9; i++)
        {
            System.out.print("|");
            for (Integer j=1; j<=9; j++)
            {
                String val = " ";
                Integer value = puzzle.getValue(i, j);
                if (value != null)
                {
                    val = String.valueOf(value);
                }
                System.out.print(val + " " );
                if (j % 3 == 0)
                {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i % 3 == 0)
            {
                System.out.println("----------------------");
            }
        }
    }

    public void printCandidates()
    {
        System.out.println("=========================================");
        for (int row=1; row<=9; row++)
        {
            printRow(row);
            if (row ==3 || row==6 || row==9)
            {
                System.out.println("=========================================");
            }
        }
        System.out.println("Solved count: " + puzzle.getValueCount());
    }

    private void printRow(int row)
    {
        for (int subRow=0; subRow<9; subRow+=3)
        {
            printSubRow(row, subRow);
            System.out.println();
        }
        System.out.println("-----------------------------------------");
    }

    private void printSubRow(int row, int subRow)
    {
        System.out.print("||");
        for (int column=1; column<=9; column++)
        {
            printSubRowColumns(row, subRow, column);
            System.out.print("|");

            if (column == 3 || column == 6 || column == 9)
            {
                System.out.print("|");
            }
        }
    }

    private void printSubRowColumns(int row, int subRow, int column)
    {
        for (int index=subRow; index<subRow+3; index++)
        {
            if (puzzle.getCandidates(row, column).size() > index)
            {
                System.out.print(puzzle.getCandidates(row, column).get(index));
            }
            else
            {
                System.out.print(" ");
            }
        }
    }
}
