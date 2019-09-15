package com.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.DBQuery;
import com.scorepredictor.Score_predictor;


public class Connector extends DBQuery{
   HttpServletRequest request = null;
   HttpServletResponse response = null;
   RequestDispatcher rd = null;
   GenericServlet generic = null;
   HttpSession session = null;
   String redirect_page="";

    public Connector(HttpServletRequest req,HttpServletResponse resp,GenericServlet gen) throws SQLException {
        this.request = req;
        this.response = resp;
        this.generic = gen;
        this.session = req.getSession();
    }
   
    
    public void register(String fname,String lname,String email,String phone,String pass) throws SQLException, ServletException, IOException
    {
        String[] arr = {fname,lname,email,phone,pass,"true"};
        String tbl1 = "register";
        
        String[] arr1 = {email,pass,"active","user"};
        String tb2 = "login";
        
        String redirect_page = generic.getServletConfig().getInitParameter("index");
        int i = DB_INSERT(arr, tbl1);
        int j = DB_INSERT(arr1, tb2);
        
        if(i!=0 && j!=0)
        {
        	session.setAttribute("msg", "Sucessfully Registered!!!");
            rd = request.getRequestDispatcher(redirect_page);
            rd.forward(request, response);
        }
    }
   
    public void login(String id,String pass) throws SQLException, ServletException, IOException
    {
        String[] data = {id,pass};
        String[] column_name = {"email","password"};
        String tb1 = "login";
        String con_type = "with condition";
        
        ResultSet rs = DB_SELECT(data, column_name, tb1, con_type);
        if(rs.next())
        {
            String type = rs.getString("utype");
            String Name= null;
            System.out.println("utype= "+type);
                if(type.equalsIgnoreCase("admin"))
                {
                    redirect_page = generic.getServletConfig().getInitParameter("admin_home");
                    session.setAttribute("type", type);
                    rd = request.getRequestDispatcher(redirect_page);
                    rd.forward(request, response);
                }
                else if(type.equalsIgnoreCase("user"))
                {
                    String sta = rs.getString("status");
                    if(sta.equalsIgnoreCase("active"))
                    {	
                    	
                        redirect_page = generic.getServletConfig().getInitParameter("user_home");
                        System.out.println(redirect_page);
                        session.setAttribute("type", id);
                        rd = request.getRequestDispatcher(redirect_page);
                        rd.forward(request, response);
                    }
                    else if(sta.equalsIgnoreCase("inactive"))
                    {
                        session.setAttribute("msg", "Account not yet activated!!!");
                        redirect_page = generic.getServletConfig().getInitParameter("index");
                        rd = request.getRequestDispatcher(redirect_page);
                        rd.forward(request, response);
                    }
                }
        }
        else
                {
                    session.setAttribute("msg", "Invalid username or password");
                    redirect_page = generic.getServletConfig().getInitParameter("index");
                    rd = request.getRequestDispatcher(redirect_page);
                    rd.forward(request, response);
                }
    }
    
    public void view_user() throws SQLException, ServletException, IOException
    {
        ArrayList<String[]> arr = new ArrayList<String[]>();
        ResultSet rs = DB_SELECT(new String[]{"user"}, new String[]{"utype"}, "login", "with condition");
        while(rs.next())
        {
            arr.add(new String[]{rs.getString("email"),rs.getString("status")});
        }
        
        session.setAttribute("arr", arr);
        redirect_page = generic.getServletConfig().getInitParameter("admin_view_user");
        rd = request.getRequestDispatcher(redirect_page);
        rd.forward(request, response);
    }
    
    public void update_status(String id, String type) throws SQLException, IOException
    {
        int val = 0;
        val = DB_UPDATE(new String[]{type}, new String[]{"status"}, new String[]{id}, new String[]{"email"}, "login");
       if(val !=0)
       {
         response.getWriter().write("done");
       }
    }
    
    
    public void add_data(String inc_id,String c_loc,String c_place,String c_type) throws SQLException, SQLException, ServletException, IOException
    {
        String[] arr = {inc_id,c_loc,c_place,c_type};
        String tbl = "dataset";
        int i = DB_INSERT(arr, tbl);
        if(i!=0)
        {
            session.setAttribute("msg", "Sucessfully Inserted");
           redirect_page = generic.getServletConfig().getInitParameter("admin_home");
        rd = request.getRequestDispatcher(redirect_page);
        rd.forward(request, response);
        }
    }
    
    public void get_score(String team1,String team2){
    	try {
    		ArrayList<String> arr = new ArrayList<String>();
			Score_predictor predict1 = new Score_predictor(team1, team2);
			
			System.out.println("Team 1 score "+predict1.getPredictScoreteam1());
			System.out.println("Team 2 score "+predict1.getPredictScoreteam2());
			arr.add(team1+","+(int)predict1.getPredictScoreteam1());
			arr.add(team2+","+(int)predict1.getPredictScoreteam2());
			session.setAttribute("teams", new String[]{team1,team2});
			session.setAttribute("scores", arr);
			redirect_page = generic.getServletConfig().getInitParameter("cricket_select");
			 rd = request.getRequestDispatcher(redirect_page);
		     rd.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    public void get_new_score(String players) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
    	System.out.println(players);
    	
    	String g = getData();
    	System.out.println(g);
    	int v = 300;
    	if(v!=0){
    		response.getWriter().write(v);
    	}
    }
}
