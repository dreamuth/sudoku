package com.dreamsoft.rules;

/**
 * Created by Dreamuth
 * on 2/17/14.
 */
public class AbstractRule
{
    protected int getStartIndex(int row)
    {
        while ((row-1) % 3 != 0)
        {
            row--;
        }
        return row;
    }

}
