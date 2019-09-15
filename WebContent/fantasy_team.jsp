<!--
Design by TEMPLATED
http://templated.co
Released for free under the Creative Commons Attribution License

Name       : Embellished 
Description: A two-column, fixed-width design with dark color scheme.
Version    : 1.0
Released   : 20140207

-->
<%@page import="com.algorithm.naivebayes.NaiveBayes"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.database.DBQuery"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="default.css" rel="stylesheet" type="text/css" media="all" />
<link href="fonts.css" rel="stylesheet" type="text/css" media="all" />
<script src="js/jquery.js"></script>
        <script src="js/jquery-migrate-1.2.1.js"></script>
        <script src="js/jquery-1.10.2.js"></script>
        <script src='js/device.min.js'></script>
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>

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
			
			<script>
				 function score(){

					 var o = $('#sc').val;
					 console.log(o);
					 $.ajax({
						  url:'Controller',
						  type:'POST',
						  data:'task=get_new_score&p='+o+'',
						  success: function(response){
							  if(response!=null){
								  console.log(console);
								  $('#finalscore').empty();
									 $('#finalScore').append('<h3>This curresnt score is '+console+'</h3>');
							  }
						  }
					  })
				 }	  
			
				 
				 
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
                          String team1 = request.getParameter("team1");
                          String team2 = request.getParameter("team2");
                          String runscored = request.getParameter("score");
                         	NaiveBayes Nb = new NaiveBayes(team1,team2);
                         	ArrayList<String[]> arr = Nb.getOutput(team1);
                          %>
                          
                          <table border="1">
                          <th>Player Name</th><th>Team Name</th><th>Feature Probability</th>
                          <%
                          for(int i=0;i<arr.size();i++){
                        	  String[] data = arr.get(i);
                        	  %>
                        	  <tr><td id="pname"><%=data[0] %></td><td><%=data[1] %></td><td><%=data[2] %></td></tr>
                        	  <%
                          }
                          %>
                          </table>
                       <!--    <button id="score" name="score" onclick="score();">Calculate Score Predictor</button> -->
                          <input type="hidden" id="sc" value="<%=runscored%>" name="sc"/>
                          <div id="finalScore"></div>
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
