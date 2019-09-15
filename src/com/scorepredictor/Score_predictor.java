package com.scorepredictor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.algorithm.naivebayes.FeatureTable;
import com.database.DBQuery;

public class Score_predictor extends DBQuery{
private HashMap<String, HashSet<String>> Data = new HashMap<String,HashSet<String>>();
private SimpleRegression regressionT1 = null;
private SimpleRegression regressionT2 = null;
private String team1=null;
private String team2 = null;
private ResultSet rs = null;
private int th;
private String s = "";

public Score_predictor(String forTeam,String againstTeam) throws SQLException{
	super();
	this.team1 = forTeam;
	this.team2 = againstTeam;
	init();
	ExtractInfo();
}

private void init() throws SQLException{
	regressionT1 = new SimpleRegression();
	regressionT2 = new SimpleRegression();
	
	HashSet<String> Players_1 = new HashSet<String>();
	HashSet<String> Players_2 = new HashSet<String>();
	if(rs == null)	{
		String[] col_name = {"teams","teams"};
		String[] data_names = {team1,team2};
		String tbl_name = "match_analysis";
		rs = DB_SELECT_WITH_LIKE(data_names, col_name, tbl_name);
		while(rs.next()){
			String db_team = rs.getString("team");
			
			if(db_team.equalsIgnoreCase(team1)){
				Players_1.add(rs.getString("batsman"));
				Players_2.add(rs.getString("bowler"));
			}
			else if(db_team.equalsIgnoreCase(team2)){
				Players_2.add(rs.getString("batsman"));
				Players_1.add(rs.getString("bowler"));
			}
		}
		Data.put(team1, Players_1);
		Data.put(team2, Players_2);
		
		System.out.println(Data);
	}
}

private void ExtractInfo(){
	try{
	String[] teams = {team1,team2};
	Set<String> keySet = Data.keySet();
	Iterator<String> itr = keySet.iterator();
	while(itr.hasNext()){
		String teamName = itr.next();
		
		if(teamName.equalsIgnoreCase(team1)){
		HashSet<String> players = Data.get(teamName);
		int j=5;
		int runScored = 0;
		for(int i=0;i<50;i=i+5,j=j+5){
		for(String pname:players){
			runScored+=get_range_score(teams, teamName, pname, j);
		}
		
		System.out.println("------------------------------------"+runScored);
		regressionT1.addData(j, runScored);
		runScored = 0;
		}
		}
		else if(teamName.equalsIgnoreCase(team2)){
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			HashSet<String> players = Data.get(teamName);
			int j=5;
			int runScored = 0;
			for(int i=0;i<50;i=i+5,j=j+5){
			for(String pname:players){
				runScored+=get_range_score(teams, teamName, pname, j);
			}
			
			System.out.println("------------------------------------"+runScored);
			regressionT2.addData(j, runScored);
			runScored = 0;
			}
		}
	}
	
	
	}catch(Exception e){
		e.printStackTrace();
	}
}

public double getPredictScoreteam1() {
	if(regressionT1.predict(50)<200){
		return regressionT1.predict(50)+250;
	}
	return regressionT1.predict(50);
}

public double getPredictScoreteam2() {
	if(regressionT2.predict(50)<200){
		return regressionT2.predict(50)+250;
	}
	return regressionT2.predict(50);
}




public static void main(String[] args) {
	try {
		Score_predictor s = new Score_predictor("india", "bangladesh");
		System.out.println((int)s.getPredictScoreteam1());
		System.out.println((int)s.getPredictScoreteam2());
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}
