package com.dreamsoft.app;

import com.dreamsoft.candidate.CandidateRemover;
import com.dreamsoft.io.Printer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dreamuth on 2/16/14.
 */
public class SolverThreadManager
{
    List<Thread> threads = new ArrayList<>();
    public void register(Runnable obj)
    {
        threads.add(new Thread(obj));
    }

    public void start()
    {
        CandidateRemover candidateRemover = new CandidateRemover();
        candidateRemover.solve();
        Printer printer = new Printer();
        printer.printCandidates();
        for (Thread thread : threads)
        {
            thread.start();
        }
    }

    public void interrupt()
    {
        for (Thread thread : threads)
        {
            thread.interrupt();
        }
    }
}
