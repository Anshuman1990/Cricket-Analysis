

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.impl.Connector;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connector connect = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException{
		 response.setContentType("text/html;charset=UTF-8");
	        connect = new Connector(request, response, this);
	        
	       String task = request.getParameter("task");
	       System.out.println("Task="+task);
	       if(task.equalsIgnoreCase("register"))
	       {
	           String fname = request.getParameter("fname");
	           String lname = request.getParameter("lname");
	           String email = request.getParameter("email");
	           String phone = request.getParameter("phone");
	           String pass = request.getParameter("pass");
	           
	           connect.register(fname, lname, email, phone, pass);
	       }
	       
	       else if(task.equalsIgnoreCase("login"))
	       {
	           String id = request.getParameter("id");
	           String pass = request.getParameter("pass");
	           System.out.println("login");
	           connect.login(id, pass);
	       }
	       else if(task.equalsIgnoreCase("activate"))
	       {
	           String id = request.getParameter("id");
	           connect.update_status(id, "active");
	       }
	       else if(task.equalsIgnoreCase("deactivate"))
	       {
	           String id = request.getParameter("id");
	           connect.update_status(id, "inactive");
	       }
	       else if(task.equalsIgnoreCase("view_user"))
	       {
	           connect.view_user();
	       }
	       else if(task.equalsIgnoreCase("get_score")){
	    	   String t1 = request.getParameter("forteam");
	    	   String t2 = request.getParameter("againstteam");
	    	   connect.get_score(t1, t2);
	    	   System.out.println(t1+">>>"+t2);
	       }
	       else if(task.equalsIgnoreCase("get_new_score")){
	    	   String players = request.getParameter("p");
	    	   System.out.println(players);
	    	   try {
				connect.get_new_score( players);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
	       }
	}
}
