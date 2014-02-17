package com.dreamsoft.app;

import com.dreamsoft.candidate.CandidateRemover;
import com.dreamsoft.data.Puzzle;
import com.dreamsoft.io.Printer;
import com.dreamsoft.rules.HiddenSingles;
import com.dreamsoft.rules.NakedSingles;

/**
 * Created by Dreamuth on 2/15/14.
 */
public class Solver
{

    public Solver solve() throws InterruptedException
    {
        long maxWait = 5000;
        long startTime = System.currentTimeMillis();
        SolverThreadManager threadManager = initializeThreadManager();
        Puzzle puzzle = Puzzle.getInstance();
        System.out.println("Input Count : " + puzzle.getValueCount());
        threadManager.start();

        int repeatCount = 0;
        int valueCount = 0;
        Thread.sleep(100);
        do
        {
            Thread.sleep(100);
            System.out.println("Solving Count : " + puzzle.getValueCount());
            if (valueCount != puzzle.getCandidatesCount())
            {
                valueCount = puzzle.getCandidatesCount();
                repeatCount = 0;
            }
            else
            {
                repeatCount++;
            }
        } while (repeatCount != 5);
        threadManager.interrupt();
        return this;
    }

    private SolverThreadManager initializeThreadManager()
    {
        SolverThreadManager threadManager =  new SolverThreadManager();
        threadManager.register(new CandidateRemover());
        threadManager.register(new NakedSingles());
        threadManager.register(new HiddenSingles());
        return threadManager;
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
