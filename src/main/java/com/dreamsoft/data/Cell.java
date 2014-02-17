package com.dreamsoft.data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Dreamuth on 2/14/14.
 */
class Cell
{
    private AtomicInteger value;
    private List<AtomicInteger> candidateValues;



    public Cell(String value)
    {
        this.value = new AtomicInteger(0);
        candidateValues = new ArrayList<>(9);
        candidateValues.add(new AtomicInteger(1));
        candidateValues.add(new AtomicInteger(2));
        candidateValues.add(new AtomicInteger(3));
        candidateValues.add(new AtomicInteger(4));
        candidateValues.add(new AtomicInteger(5));
        candidateValues.add(new AtomicInteger(6));
        candidateValues.add(new AtomicInteger(7));
        candidateValues.add(new AtomicInteger(8));
        candidateValues.add(new AtomicInteger(9));
        if (!value.equals("."))
        {
            this.value.compareAndSet(0, Integer.valueOf(value));
            removeAllCandidateValue();
        }
    }

    Integer getValue()
    {
        return value.intValue() == 0 ? null : value.intValue();
    }

    void setValue(Integer value)
    {
        this.value.compareAndSet(0, value);
        this.removeAllCandidateValue();
    }

    public void removeCandidateValue(Integer value)
    {
        candidateValues.get(value - 1).compareAndSet(value, 0);
    }

    public void removeAllCandidateValue()
    {
        for (int index = 1; index <=9; index++)
        {
            removeCandidateValue(index);
        }
    }

    public List<AtomicInteger> getCandidates()
    {
        return candidateValues;
    }

    public boolean containsCandidate(int value)
    {
        return candidateValues.get(value -1).get() != 0;
    }
}