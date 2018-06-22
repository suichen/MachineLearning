package com.suichen.hmm;

/**
 * Created by Think on 2016/1/6.
 */
public class Viterbi {
    public static int[] compute(int[] obs, int[] states, double[] start_p, double[][] trans_p, double[][] emit_p)
    {
        double[][] V = new double[obs.length][states.length];
        int[][] path = new int[states.length][obs.length];

        for (int y : states)
        {
            V[0][y] = start_p[y] * emit_p[y][obs[0]];
        }

        for (int t = 1; t < obs.length; ++t)
        {
            for (int y : states)
            {
                double prob = -1, tmp=-1;
                int state = -1;
                for (int y0 : states)
                {
                    double nprob = V[t - 1][y0] * trans_p[y0][y] * emit_p[y][obs[t]];
                    double nstate = V[t-1][y0]*trans_p[y0][y];
                    if (nprob > prob)
                    {
                        prob = nprob;
                    }

                    if(nstate > tmp) {
                        state = y0;
                        tmp = nstate;
                    }

                }

                V[t][y] = prob;
                path[y][t-1] = state;
            }

        }

        double prob = -1;
        int state = 0;
        for (int y : states)
        {
            if (V[obs.length - 1][y] > prob)
            {
                prob = V[obs.length - 1][y];
                state = y;
            }
        }

        int[] ans = new int[obs.length];
        int cnt = obs.length-1;
        ans[cnt--] = state;

        while (cnt >= 0) {
            state = path[state][cnt];
            ans[cnt--] = state;
        }
        return ans;
    }
}
