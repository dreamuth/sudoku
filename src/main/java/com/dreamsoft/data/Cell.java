package com.dreamsoft.data;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Dreamuth on 2/14/14.
 */
class Cell
{
    @Getter @Setter
    private Integer value;
    @Getter
    private List<Integer> candidateValues = new ArrayList<>(9);

    public Cell(String value)
    {
        if (!value.equals("."))
        {
            this.value = Integer.valueOf(value);
        }
        else
        {
            for (Integer candidate = 1; candidate <=9; candidate++)
            {
                candidateValues.add(candidate);
            }
        }
    }
}