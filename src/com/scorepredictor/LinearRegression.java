package com.scorepredictor;

public class LinearRegression implements Constants{
private String forTeam;
private String againstTeam;
private int TotRunScored;
public String getForTeam() {
	return forTeam;
}
public void setForTeam(String forTeam) {
	this.forTeam = forTeam;
}
public String getAgainstTeam() {
	return againstTeam;
}
public void setAgainstTeam(String againstTeam) {
	this.againstTeam = againstTeam;
}
public int getTotRunScored() {
	return TotRunScored;
}
public void setTotRunScored(int totRunScored) {
	TotRunScored = totRunScored;
}
public void calculation(){
	int MAXN = 1000;
    int n = 0;
    double[] x = new double[MAXN];
    double[] y = new double[MAXN];

    // first pass: read in data, compute xbar and ybar
    double sumx = 0.0, sumy = 0.0, sumx2 = 0.0;
    while(getTotRunScored()!=0) {
        x[n] = StdIn.nextDouble();
        y[n] = StdIn.nextDouble();
        sumx  += x[n];
        sumx2 += x[n] * x[n];
        sumy  += y[n];
        n++;
    }
    double xbar = sumx / n;
    double ybar = sumy / n;

    // second pass: compute summary statistics
    double xxbar = 0.0, yybar = 0.0, xybar = 0.0;
    for (int i = 0; i < n; i++) {
        xxbar += (x[i] - xbar) * (x[i] - xbar);
        yybar += (y[i] - ybar) * (y[i] - ybar);
        xybar += (x[i] - xbar) * (y[i] - ybar);
    }
    double beta1 = xybar / xxbar;
    double beta0 = ybar - beta1 * xbar;

    // print results

    // analyze results
    int df = n - 2;
    double rss = 0.0;      // residual sum of squares
    double ssr = 0.0;      // regression sum of squares
    for (int i = 0; i < n; i++) {
        double fit = beta1*x[i] + beta0;
        rss += (fit - y[i]) * (fit - y[i]);
        ssr += (fit - ybar) * (fit - ybar);
    }
    double R2    = ssr / yybar;
    double svar  = rss / df;
    double svar1 = svar / xxbar;
    double svar0 = svar/n + xbar*xbar*svar1;
}
}
