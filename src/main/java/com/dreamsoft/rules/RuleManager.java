package com.dreamsoft.rules;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dreamuth on 2/16/14.
 */
public class RuleManager
{
    List<IRule> rules = new ArrayList<>();

    public RuleManager()
    {
        register(new CandidateRemover());
        register(new NakedSingle());
        register(new HiddenSingle());
        register(new NakedPair());
    }

    private void register(IRule rule)
    {
        rules.add(rule);
    }

    public void solve()
    {
        for (IRule rule : rules)
        {
            rule.solve();
        }
    }
}
