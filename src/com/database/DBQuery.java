/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.impl.Constants;
import com.impl.propertyImpl;


/**
 *
 * @author Anspro
 */
public class DBQuery extends DBFormat implements DBConnection {
    private Connection con = null;
    private Statement st = null;
    private ResultSet  rs = null;

    public DBQuery() throws SQLException {
        this.con = getConnection();
        this.st = this.con.createStatement();
    }

    @Override
    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(propertyImpl.getproperty(Constants.db_driver));
            String db_info = propertyImpl.getproperty(Constants.db_url)+propertyImpl.getproperty(Constants.db_ip)+":"+propertyImpl.getproperty(Constants.db_port)+"/"+propertyImpl.getproperty(Constants.db_name);
            con = DriverManager.getConnection(db_info,propertyImpl.getproperty(Constants.db_username),propertyImpl.getproperty(Constants.db_password));
            
        } catch (SQLException ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    public int DB_INSERT(String[] arr,String table_name) throws SQLException
    {
        int i=0;
        try{
        String q = INSERT(arr, table_name);
         i = st.executeUpdate(q);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return i;
    }
    
    public ResultSet DB_SELECT(String[] data,String[] column_name,String table_name,String select_type) throws SQLException
    {
        String q = SELECT(data, column_name, table_name,select_type);
//        System.out.println("q= "+q);
        rs = st.executeQuery(q);
        return rs;
    }
    
    
    public int DB_UPDATE(String[] data,String[] column_name,String[] condition_data,String[] condition_column,String table_name) throws SQLException
    {
        int i=0;
        String q = UPDATE(data, column_name, condition_data, condition_column, table_name);
//        System.out.println(">>> "+q);
        i = st.executeUpdate(q);
        return i;
    }
    
    public int DB_ALTER(String[] column_details,String table_name,String alter_type) throws SQLException
    {
        String q = ALTER(table_name, column_details, alter_type);
        int i = st.executeUpdate(q);
        return i;
    }
    
    public int DB_DELETE(String[] data,String[] column_name,String table_name,String delete_type) throws SQLException
    {
        String q = DELETE(data, column_name, table_name, delete_type);
        int i = st.executeUpdate(q);
        return i;
    }
    
    public ResultSet DB_SELECT_WITH_LIKE(String[] data,String[] column_name,String table_name) throws SQLException{
    	String q = SELECT_WITH_LIKE(data, column_name, table_name);
        System.out.println("q= "+q);
        rs = st.executeQuery(q);
        return rs;
    }
    
    public int getRunScored(String[] teams,String playerName) throws SQLException{
    	String q = "select sum(runs_scored) from match_analysis where (teams LIKE '%"+teams[0]+"%' AND teams LIKE '%"+teams[1]+"%') AND batsman = '"+playerName+"'";
    	int out = 0;
    	rs = st.executeQuery(q);
    	if(rs.next()){
    	out =  rs.getInt(1);
    	}
    	return out;
    }
    
    public int getWicketTaken(String[] teams,String playerName) throws SQLException{
    	String q = "select count(batsman) from match_analysis where (teams LIKE '%"+teams[0]+"%' AND teams LIKE '%"+teams[1]+"%') AND bowler = '"+playerName+"' AND player_out <> 'no data'";
    	int out = 0;
    	rs = st.executeQuery(q);
    	if(rs.next()){
    	out =  rs.getInt(1);
    	}
    	return out;
    }
    
    public int getCatches(String[] teams,String playerName) throws SQLException{
    	String q = "select count(batsman) from match_analysis where (teams LIKE '%"+teams[0]+"%' AND teams LIKE '%"+teams[1]+"%') AND fielder = '"+playerName+"' AND kind = 'caught'";
    	int out = 0;
    	rs = st.executeQuery(q);
    	if(rs.next()){
    	out =  rs.getInt(1);
    	}
    	return out;
    }
    
    public int getTotalRunscored(String[] teams,String teamName) throws SQLException{
    	String q = "select sum(runs_scored) from match_analysis where (teams LIKE '%"+teams[0]+"%' AND teams LIKE '%"+teams[1]+"%') AND team = '"+teamName+"'";
    	int out = 0;
    	rs = st.executeQuery(q);
    	if(rs.next()){
    	out =  rs.getInt(1);
    	}
    	return out;
    }
    
    public int getTotalWicketTaken(String[] teams,String teamName) throws SQLException{
    	String q = "select count(*) from match_analysis where (teams LIKE '%"+teams[0]+"%' AND teams LIKE '%"+teams[1]+"%') AND team = '"+teamName+"' AND player_out <> 'no data'";
    	int wicket = 0;
    	rs = st.executeQuery(q);
    	if(rs.next()){
    		wicket =  rs.getInt(1);
    	}
    	return wicket;
    }
    
    public int getTotalCatchesTaken(String[] teams,String teamName) throws SQLException{
    	String q = "select count(*) from match_analysis where (teams LIKE '%"+teams[0]+"%' AND teams LIKE '%"+teams[1]+"%') AND team = '"+teamName+"' AND player_out <> 'no data' AND kind = 'caught'";
    	int catches = 0;
    	rs = st.executeQuery(q);
    	if(rs.next()){
    		catches =  rs.getInt(1);
    	}
    	return catches;
    }
    
    public static void main(String[] args){
		DBQuery dbq;
		try {
			dbq = new DBQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
    
    public ArrayList<String> getTeams() throws SQLException{
    	ArrayList<String> out = new ArrayList<String>();
    	String q = "select distinct(team) from match_analysis";
    	rs = st.executeQuery(q);
    	while(rs.next()){
    		out.add(rs.getString(1));
    	}
    	return out;
    }
    
    public int getNumberOfTimesPlayed(String[] teams) throws SQLException{
    	String q = "select count(distinct(City)) from match_analysis where teams LIKE '%"+teams[0]+"%' AND teams LIKE '%"+teams[1]+"%'";
//    	System.out.println(q);
    	rs = st.executeQuery(q);
    	if(rs.next()){
    		return rs.getInt(1);
    	}
    	return 0;
    }
    
    public int get_range_score(String[] teams,String team,String pname,int v) throws SQLException{
    	String q = "";
    	switch (v) {
		case 5:
			q = "SELECT sum(runs_scored) FROM match_analysis where teams LIKE '%"+teams[0]+"%' AND teams LIKE '%"+teams[1]+"%' AND team = '"+team+"' and batsman = '"+pname+"' AND over between 0 and 5";
			break;
			
		case 10:
			q = "SELECT sum(runs_scored) FROM match_analysis where teams LIKE '%"+teams[0]+"%' AND teams LIKE '%"+teams[1]+"%' AND team = '"+team+"' and batsman = '"+pname+"' AND over between 6 and 10";
			break;
			
		case 15:
			q = "SELECT sum(runs_scored) FROM match_analysis where teams LIKE '%"+teams[0]+"%' AND teams LIKE '%"+teams[1]+"%' AND team = '"+team+"' and batsman = '"+pname+"' AND over between 11 and 15";
			break;
	
		case 20:
			q = "SELECT sum(runs_scored) FROM match_analysis where teams LIKE '%"+teams[0]+"%' AND teams LIKE '%"+teams[1]+"%' AND team = '"+team+"' and batsman = '"+pname+"' AND over between 16 and 20";
			break;
	
		case 25:
			q = "SELECT sum(runs_scored) FROM match_analysis where teams LIKE '%"+teams[0]+"%' AND teams LIKE '%"+teams[1]+"%' AND team = '"+team+"' and batsman = '"+pname+"' AND over between 21 and 25";
			break;
	
		case 30:
			q = "SELECT sum(runs_scored) FROM match_analysis where teams LIKE '%"+teams[0]+"%' AND teams LIKE '%"+teams[1]+"%' AND team = '"+team+"' and batsman = '"+pname+"' AND over between 26 and 30";
			break;
	
		case 35:
			q = "SELECT sum(runs_scored) FROM match_analysis where teams LIKE '%"+teams[0]+"%' AND teams LIKE '%"+teams[1]+"%' AND team = '"+team+"' and batsman = '"+pname+"' AND over between 31 and 35";
			break;
	
		case 40:
			q = "SELECT sum(runs_scored) FROM match_analysis where teams LIKE '%"+teams[0]+"%' AND teams LIKE '%"+teams[1]+"%' AND team = '"+team+"' and batsman = '"+pname+"' AND over between 36 and 40";
			break;
	
		case 45:
			q = "SELECT sum(runs_scored) FROM match_analysis where teams LIKE '%"+teams[0]+"%' AND teams LIKE '%"+teams[1]+"%' AND team = '"+team+"' and batsman = '"+pname+"' AND over between 41 and 45";
			break;
	
		case 50:
			q = "SELECT sum(runs_scored) FROM match_analysis where teams LIKE '%"+teams[0]+"%' AND teams LIKE '%"+teams[1]+"%' AND team = '"+team+"' and batsman = '"+pname+"' AND over between 46 and 50";
			break;

		default:
			break;
		}
    	rs = st.executeQuery(q);
    	if(rs.next()){
    		return rs.getInt(1);
    	}
    	return 0;
    }
    
    
}
