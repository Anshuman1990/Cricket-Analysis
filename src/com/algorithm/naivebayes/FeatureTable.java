package com.algorithm.naivebayes;

public class FeatureTable {
private String PlayerName;
private String Team;
private int runs;
private int wicket;
private int catches;

public String getPlayerName() {
	return PlayerName;
}

public String getTeam() {
	return Team;
}

public FeatureTable(String pname,String team) {
this.PlayerName = pname;
this.Team = team;
}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}


	

	public int getWicket() {
		return wicket;
	}

	public void setWicket(int wicket) {
		this.wicket = wicket;
	}

	public int getCatches() {
		return catches;
	}

	public void setCatches(int catches) {
		this.catches = catches;
}
	
	@Override
		public String toString() {
		StringBuffer s_buf = new StringBuffer();
		s_buf.append("Player Name= "+getPlayerName()+"\n");
		s_buf.append("Team Name= "+getTeam()+"\n");
		s_buf.append("Runs Scored= "+getRuns()+"\n");
		s_buf.append("Wicket Taken= "+getWicket()+"\n");
		s_buf.append("Catches Taken= "+getCatches());
			return s_buf.toString();
		}
}
