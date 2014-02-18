package com.dreamsoft.app;

import com.dreamsoft.data.Puzzle;
import com.dreamsoft.io.Printer;
import com.dreamsoft.rules.RuleManager;

/**
 * Created by Dreamuth on 2/15/14.
 */
public class Solver
{

    public Solver solve() throws InterruptedException
    {
        RuleManager ruleManager = new RuleManager();
        Puzzle puzzle = Puzzle.getInstance();
        System.out.println("Input Count : " + puzzle.getValueCount());

        int repeatCount = 0;
        int valueCount = 0;
        do
        {
            ruleManager.solve();
            if (valueCount != puzzle.getAllCandidatesCount())
            {
                System.out.println("Solving Count : " + puzzle.getValueCount());
                valueCount = puzzle.getAllCandidatesCount();
                repeatCount = 0;
            }
            else
            {
                repeatCount++;
            }
        } while (repeatCount != 5);
        return this;
    }

    public Solver printCandidates()
    {
        new Printer().printCandidates();
        return this;
    }

    public Solver print()
    {
        new Printer().printResult();
        return this;
    }
}
