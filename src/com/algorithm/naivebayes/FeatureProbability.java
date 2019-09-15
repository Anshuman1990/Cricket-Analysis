package com.algorithm.naivebayes;

public class FeatureProbability implements Comparable<FeatureProbability>{
private String playerName;
private String teamName;
private double probability;


public String getPlayerName() {
	return playerName;
}
public void setPlayerName(String playerName) {
	this.playerName = playerName;
}
public String getTeamName() {
	return teamName;
}
public void setTeamName(String teamName) {
	this.teamName = teamName;
}
public double getProbability() {
	return probability;
}
public void setProbability(double probability) {
	this.probability = probability;
}

@Override
	public String toString() {
	StringBuffer s_buf = new StringBuffer();
	s_buf.append("Player Name= "+getPlayerName()+"\n");
	s_buf.append("Team Name= "+getTeamName()+"\n");
	s_buf.append("Probability= "+getProbability()+"\n");
		return s_buf.toString();
	}

@Override
	public int compareTo(FeatureProbability o) {
	if(o.getTeamName().equalsIgnoreCase(this.getTeamName())){
	return Double.compare(o.getProbability(), this.getProbability());
	}
	else{
		return 0;
	}
	}
}
