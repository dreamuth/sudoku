package com.dreamsoft.data;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.ImmutableList;

/**
 * Created by Dreamuth on 2/15/14.
 */
public class InputReader
{
    public ArrayTable<Integer, Integer, Cell> getInput()
    {
        ArrayTable<Integer, Integer, Cell> table = ArrayTable.create(
                ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
                ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
//        String input = "579......618.5.3.4423..9.5.2641....7.5..7..8.8....4.2..3.6.....9.2.8..7........1.";
//        String input = "57941.86261875239442386.7512641985373512764898975.412613.62794894238167578694521.";
//        String input = ".8..2.4........37.974......6..91....75.....91....85..2......956.36........1.4..37";
//        String input = "37.5.....8....7........4.37.6.8.2.1...8...2...4.9.3.6.21.6........4....1.....9.58";
//        String input = "....9.7...5.....3...1..8...9..7....6..4......2....3..1.7.6..9...3.....5...814..2.";
        String input = ".6...23..1....78...723...5.5.9....8...........4....5.1.2...374...87....3..75...6.";
        int index = 0;
        for (Integer i=1; i<=9; i++)
        {
            for (Integer j=1; j<=9; j++)
            {
                table.put(i, j, new Cell(String.valueOf(input.charAt(index++))));
            }
        }
        return table;
    }
}
