/**
 * 
 */
package com.algorithm.naivebayes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.database.DBQuery;

public class NaiveBayes extends DBQuery implements Constants{
	private HashMap<String, HashSet<String>> Data = new HashMap<String,HashSet<String>>();
	private ResultSet rs = null;
	private String Team1=null;
	private String Team2=null;
	private ArrayList<FeatureTable> playersInfo = new ArrayList<FeatureTable>();
	private HashMap<String, ArrayList<Integer>> TotalData = new HashMap<String,ArrayList<Integer>>();
	private ArrayList<FeatureProbability> probability = new ArrayList<FeatureProbability>();
	private FeatureProbability prob = null;
	
	public NaiveBayes(String forTeam,String againstTeam) throws SQLException {
		this.Team1 = forTeam;
		this.Team2 = againstTeam;
	init();
	fetchTotalData();
	FeatureExtraction();
	calculateProbability();
	Collections.sort(probability);
	System.out.println("--------------------------------------------------PROBABILITY Calculation-------------------------------------------");
	
	}
	
	private void init() throws SQLException{
		HashSet<String> Players_1 = new HashSet<String>();
		HashSet<String> Players_2 = new HashSet<String>();
	if(rs == null)	{
		String[] col_name = {"teams","teams"};
		String[] data_names = {Team1,Team2};
		String tbl_name = "match_analysis";
		rs = DB_SELECT_WITH_LIKE(data_names, col_name, tbl_name);
		while(rs.next()){
			String db_team = rs.getString("team");
			
			if(db_team.equalsIgnoreCase(Team1)){
				Players_1.add(rs.getString("batsman"));
				Players_2.add(rs.getString("bowler"));
			}
			else if(db_team.equalsIgnoreCase(Team2)){
				Players_2.add(rs.getString("batsman"));
				Players_1.add(rs.getString("bowler"));
			}
		}
		Data.put(Team1, Players_1);
		Data.put(Team2, Players_2);
		System.out.println(Data);
	}
	}
	
	private void FeatureExtraction(){
		try{
		String[] teams = {Team1,Team2};
		Set<String> keySet = Data.keySet();
		Iterator<String> itr = keySet.iterator();
		while(itr.hasNext()){
			String teamName = itr.next();
			HashSet<String> players = Data.get(teamName);
			Iterator<String> itr_1 = players.iterator();
			while(itr_1.hasNext()){
				String pname = itr_1.next();
				int runsScored = getRunScored(teams, pname);
				int wicketTaken = getWicketTaken(teams, pname);
				int catches = getCatches(teams, pname);
				FeatureTable fTable = new FeatureTable(pname, teamName);
				fTable.setRuns(runsScored);
				fTable.setWicket(wicketTaken);
				fTable.setCatches(catches);
				playersInfo.add(fTable);
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void fetchTotalData() throws SQLException{
		String[] teams = {Team1,Team2};
		ArrayList<Integer> dteam1 = new ArrayList<>();
		dteam1.add(getTotalRunscored(teams, Team1));
		dteam1.add(getTotalCatchesTaken(teams, Team1));
		dteam1.add(getTotalWicketTaken(teams, Team1));
		TotalData.put(Team1, dteam1);
		
		ArrayList<Integer> dteam2 = new ArrayList<>();
		dteam2.add(getTotalRunscored(teams, Team2));
		dteam2.add(getTotalCatchesTaken(teams, Team2));
		dteam2.add(getTotalWicketTaken(teams, Team2));
		TotalData.put(Team2, dteam2);
	}
	
	private void calculateProbability(){
		Iterator<FeatureTable> itr = playersInfo.iterator();
		while(itr.hasNext()){
			FeatureTable f = itr.next();
			String pname = f.getPlayerName();
			String team = f.getTeam();
			int runScored = f.getRuns();
			int wicketTaken = f.getWicket();
			int catchesTaken = f.getCatches();
			Iterator<String> teams = TotalData.keySet().iterator();
			while(teams.hasNext()){
				String T = teams.next();
				if(team.equals(T) && TotalData.containsKey(T)){
					ArrayList<Integer> datas = TotalData.get(T);
					int totRunScored = datas.get(0);
					int totCatchesTaken = datas.get(1);
					int totwicketTaken = datas.get(2);
					double p = (probaliltyFunction(runScored, totRunScored)+probaliltyFunction(wicketTaken, totwicketTaken)+probaliltyFunction(catchesTaken, totCatchesTaken));
					prob = new FeatureProbability();
					prob.setPlayerName(pname);
					prob.setTeamName(team);
					prob.setProbability(p);
					probability.add(prob);
				}
			}
		}
	}
	
	private <T> void display(T value){
		
		if(value instanceof List)
		{
			List list = (List) value;
			Iterator itr = list.iterator();
			while(itr.hasNext()){
				System.out.println(itr.next());
			}
		}
		
		else if(value instanceof Set){
			Set set = (Set)value;
			Iterator itr = set.iterator();
			while(itr.hasNext()){
				System.out.println(itr.next());
			}
		}
		
		else if(value instanceof Map){
			Map map = (Map) value;
			Set keys = map.keySet();
			Iterator itr_key = keys.iterator();
			while(itr_key.hasNext()){
				Object k = itr_key.next();
				Object v = null;
				if(map.containsKey(k)){
					v = map.get(k);
				}
				System.out.println("Keys = "+k);
				System.out.println("Value = "+v);
			}
		}
		else if(value instanceof String[]){
			String[] str = (String[]) value;
			for(int i=0;i<str.length;i++){
				System.out.println(str[i]);
			}
		}
		
		else if(value instanceof int[]){
			int[] I = (int[]) value;
			for(int i=0;i<I.length;i++){
				System.out.println(I[i]);
			}
		}
		
		else if(value instanceof char[]){
			char[] I = (char[]) value;
			for(int i=0;i<I.length;i++){
				System.out.println(I[i]);
			}
		}
		
		else if(value instanceof Object[]){
			Object[] I = (Object[]) value;
			for(int i=0;i<I.length;i++){
				System.out.println(I[i]);
			}
		}
		
	}

	@Override
	public <T extends Object> T cleanData(T data) throws Exception {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = null;
		String type = "";
		T output = null;
		if(data.getClass().isArray()){
			type = data.getClass().getSimpleName();
			type = type.replace("[","");
			type = type.replace("]", "").trim().toUpperCase();
			System.out.println("Type= "+type);
			
			switch (type) {
			
			case "STRING":
				String[] S = (String[]) data;
				String S_data = Arrays.toString(S);
				S_data = S_data.replace("[", "");
				S_data = S_data.replace("]","").trim();
					matcher = pattern.matcher(S_data);
					if(matcher.find()){
						System.out.println("needs to be clean");
						String S_temp = matcher.replaceAll("");
						T S_split = (T) S_temp.split(",");
						output = S_split;
					}
					else{
						output = data;
					}
				break;
				
			case "INT":
				int[] I = (int[])data;
				String I_data = Arrays.toString(I);
				I_data = I_data.replace("[", "");
				I_data = I_data.replace("]","").trim();
					matcher = pattern.matcher(I_data);
					System.out.println(I_data+">>>"+matcher.find());
					if(matcher.find()){
						System.out.println("needs to be clean");
						String I_temp = matcher.replaceAll("");
						T I_split = (T) I_temp.split(",");
						output = I_split;
					}
					else{
						output = data;
					}
				break;
				
				case "CHAR":
					char[] C = (char[])data;
					String C_data = Arrays.toString(C);
					C_data = C_data.replace("[", "");
					C_data = C_data.replace("]","").trim();
						matcher = pattern.matcher(C_data);
						System.out.println(C_data+">>>"+matcher.find());
						if(matcher.find()){
							System.out.println("needs to be clean");
							String C_temp = matcher.replaceAll("");
							T C_split = (T) C_temp.split(",");
							output = C_split;
						}
						else{
							output = data;
						}
					break;
					
				case "OBJECT":
					Object[] O = (Object[])data;
					String O_data = Arrays.toString(O);
					O_data = O_data.replace("[", "");
					O_data = O_data.replace("]","").trim();
						matcher = pattern.matcher(O_data);
						System.out.println(O_data+">>>"+matcher.find());
						if(matcher.find()){
							System.out.println("needs to be clean");
							String O_temp = matcher.replaceAll("");
							T O_split = (T) O_temp.split(",");
							output = O_split;
						}
						else{
							output = data;
						}
						break;
						
			default:
				System.err.println("Incompatible Data Type");
				break;
			}
		}
		else{
			throw new Exception("Incompatible type Found");
		}
		return output;
	}

	private <T> void cleanup(T data){
		
	}
	
	private double probaliltyFunction(int matchedData,int totData){
		return ((double)matchedData/totData);
	}
	
	public ArrayList<String[]> getOutput(String teamName){
		ArrayList<String[]> arr = new ArrayList<String[]>();
		Iterator<FeatureProbability> itr = probability.iterator();
		int count = 0;
		while(itr.hasNext()){
			FeatureProbability fprob = itr.next();
			if(fprob.getTeamName().equalsIgnoreCase(teamName) && count<11){
				arr.add(new String[]{fprob.getPlayerName(),fprob.getTeamName(),fprob.getProbability()+""});
				count++;
			}
		}
		
		return arr;
	}
	
	public static void main(String[] args) {
		try {
			new NaiveBayes("australia", "new zealand");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}