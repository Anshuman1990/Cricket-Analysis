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
				 </div>
			<div id="menu">
				<ul>
					<li class="current_page_item"><a href="index.jsp" accesskey="1" title="">Homepage</a></li>
					<li><a href="register.jsp" accesskey="2" title="">Registration</a></li>
					<li><a href="login.jsp" accesskey="3" title="">Login</a></li>
					<li><a href="#" accesskey="5" title="">Contact Us</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="wrapper2">
		<div id="welcome" class="container">
			<div class="title">
                            <h2>Welcome</h2>
			</div>
                    <%
                    String msg = "";
if(session.getAttribute("msg")!=null)
{
    msg = session.getAttribute("msg").toString();
}
else
{
                msg = "";        
}
session.removeAttribute("msg");                                                                
%>
			<p><%=msg%></p>
		</div>
	</div>
	
</div>
<div id="footer" class="container">
	
</div>
<div id="copyright" class="container">
	
</div>
</body>
</html>
