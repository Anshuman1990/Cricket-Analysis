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
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet" />
<link href="default.css" rel="stylesheet" type="text/css" media="all" />
<link href="fonts.css" rel="stylesheet" type="text/css" media="all" />

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
                            <%
String type = "";
if(session.getAttribute("type")!=null)
{
    type = session.getAttribute("type").toString();
}
session.setAttribute("type", type);                             
%>
				<h2>Welcome <%=type%></h2>
                                <script>
                                   
                                    
                                </script>
                                
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
