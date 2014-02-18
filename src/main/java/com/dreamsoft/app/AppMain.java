package com.dreamsoft.app;

/**
 * Created by Dreamuth on 2/14/14.
 */
public class AppMain
{
    public static void main(String[] args)
    {
        try
        {
            new Solver().solve().print().printCandidates();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
