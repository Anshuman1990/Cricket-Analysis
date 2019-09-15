<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by TEMPLATED
http://templated.co
Released for free under the Creative Commons Attribution License

Name       : Embellished 
Description: A two-column, fixed-width design with dark color scheme.
Version    : 1.0
Released   : 20140207

-->
<%@page import="java.util.ArrayList"%>
<%@page import="com.database.DBQuery"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet" />
<link href="default.css" rel="stylesheet" type="text/css" media="all" />
<link href="fonts.css" rel="stylesheet" type="text/css" media="all" />
<script type="test/javascript" src="js/jquery-1.10.2.js"></script>
<script type="test/javascript" src="js/jquery-ui.min.js"></script>
<script type="test/javascript" src="js/jquery.min.js"></script>
<script type="test/javascript" src="js/jquery-3.2.1.min.js"></script>

<!--[if IE 6]><link href="default_ie6.css" rel="stylesheet" type="text/css" /><![endif]-->

</head>
<body>
<div id="wrapper1">
	<div id="header-wrapper">
		<div id="header" class="container">
			<div id="logo"> <span class="icon icon-cogs"></span>
				<h1><a href="#">Cricket Analysis</a></h1>
				<span>Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a></span> </div>
			<div id="menu">
				<ul>
					<li class="current_page_item"><a href="user_home.jsp" accesskey="1" title="">Homepage</a></li>
                                        <li><a href="cricket_select.jsp" accesskey="1" title="">Search Team</a></li>
                                        
					<li><a href="index.jsp" accesskey="5" title="">Logout</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="wrapper2">
		<div id="welcome" class="container">
			<div class="title">
			
			<script type="text/javascript"  src="js/jquery-3.2.1.min.js">

				  $("#S").onclick(function (){
					  var t1 = $("#forteam").find(":selected").text();
					  var t2 = $("#againstteam").find(":selected").text();
					  console.log(t1+">>>>>"+t2);
					  
				  });		  
			
			</script>
			
			
                            <%
String type = "";
if(session.getAttribute("type")!=null)
{
    type = session.getAttribute("type").toString();
}
session.setAttribute("type", type);                             
%>
				<h2>Welcome <%=type%></h2>
                           <%
                           DBQuery dbq =new DBQuery();
                           ArrayList<String> arr = dbq.getTeams();
                           %>    
                           
                           <form action="./Controller?task=get_score" method="post" id="analysis">
                           <table>
                           <tr><td>Select For Team</td>
                           <td><select id="forteam" name="forteam">
                           <%for(String s:arr){ %>
                           <option value="<%=s%>"><%=s %></option>
                           <%} %>
                           </select></td>
                           </tr>
                           
                           <tr><td>Select Against Team</td>
                           <td><select id="againstteam" name="againstteam">
                           <%for(String s:arr){ %>
                           <option value="<%=s%>"><%=s %></option>
                           <%} %>
                           </select></td>
                           </tr>
                           
                           <tr>
                           <td><input type="submit" value="Get Score" id="S"/></td>
                           <td><input type="reset" value="Clear"/></td>
                           </tr>
                           </table>
                           
                           </form>
                           <%
                           			if(session.getAttribute("scores")!=null && session.getAttribute("teams")!=null){
                           				String[] teams = (String[])session.getAttribute("teams");
                           				String team2 = "";
                           				ArrayList<String> scores = (ArrayList)session.getAttribute("scores");
                           				for(int i=0;i<scores.size();i++){
                           					String team = scores.get(i).toString().split(",")[0];
                           					String runscored = scores.get(i).toString().split(",")[1];
                           					for(int j=0;j<teams.length;j++){
                           						if(!team.equalsIgnoreCase(teams[j])){
                           							team2 = teams[j];
                           						}
                           					}
                           %>
                                <h3>The <%=team %> will score <%=runscored%></h3>
                                <a href="fantasy_team.jsp?team1=<%=team%>&team2=<%=team2%>&score=<%=runscored%>">Click Here for Fantasy team creation</a>
                                <%}
                           			session.removeAttribute("scores");	
                           			} %>
			</div>
					</div>
	</div>
	
</div>
<div id="footer" class="container">
	<div class="title">
		<h2>Get in touch</h2>
		<span class="byline">Phasellus nec erat sit amet nibh pellentesque congue</span> </div>
	<ul class="contact">
		<li><a href="#" class="icon icon-twitter"><span>Twitter</span></a></li>
		<li><a href="#" class="icon icon-facebook"><span></span></a></li>
		<li><a href="#" class="icon icon-dribbble"><span>Pinterest</span></a></li>
		<li><a href="#" class="icon icon-tumblr"><span>Google+</span></a></li>
		<li><a href="#" class="icon icon-rss"><span>Pinterest</span></a></li>
	</ul>
</div>
<div id="copyright" class="container">
	<p>&copy; Untitled. All rights reserved. | Photos by <a href="http://fotogrph.com/">Fotogrph</a> | Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>.</p>
</div>
</body>
</html> 
