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
                                        <li><a href="user_details.jsp" accesskey="1" title="">Provide Your Details</a></li>
                                        
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
                                    function(group)
                                    {
                                        
                                    }
                                    
                                </script>
                                
                                <form name="formcheck" id="formcheck" onsubmit="javascript:return check();" action="register_action.php" method="post">
		
		
		<div id="left-side">	
			
			<p>Name</p>
			<input name="name" id="name"type="text" value="" />
		
			<p>Date of Birth</p>
            <select  name="dd" style="width:auto">
              <% for(int i=1; i<=31; i++) { %>
              <option value="<%=i %>"  ><%=i%> %> </option>
              <%} %>
            </select>
            <select name="dm" style="width:auto">
              <%
                for(int i=1; i<=12; i++)
                { %>
              <option value="<%=i%>"><%=i %></option>
              <%}%>
            </select>
            <select name="dy" style="width:auto">
                <?php
                for($i=date("Y"); $i>=date("Y")-90; $i--)
                { ?>
                <option value="<?php echo $i;?>"><?php echo $i;?></option>
                <?php } ?>
            </select>

			<p>Gender &nbsp;&nbsp;&nbsp;Blood Group&nbsp;&nbsp;Weight</p>
            <select name="sex" style="width:auto">
                <option value="" >Select</option>
                <option value="1">Male</option>
                <option value="0">Female</option>
            </select>&nbsp;&nbsp;
            <select name="bloodgroup" style="width:auto">
                <option value="">Select</option>
                <?php
                    $sql = "SELECT DISTINCT BloodGroup FROM bloodgroup;";
                    $result = mysql_query($sql);
                    while ($row1 = mysql_fetch_array($result)) {
                ?>
                    <option><?php echo $row1["BloodGroup"];?></option>
                <?php } ?>
            </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input name="weight" style="width:50px"/>
		
            <p>Year &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Branch&nbsp;&nbsp; Batch</p>
            <select name="admnyear" style="width:auto">
                <?php for($i = (int)date('Y'); $i > ((int)date('Y') - 20); $i--) {?>
                <option><?php echo (string)$i;?></option>
                <?php }?>
            </select>&nbsp;&nbsp;
            <select name="branch" style="width:auto">
                <?php
                    $sql = "SELECT DISTINCT course FROM course;";
                    $result = mysql_query($sql);
                    while ($row1 = mysql_fetch_array($result)) {
                ?> <option><?php echo $row1["course"];?></option>
                <?php } ?>
            </select>&nbsp;&nbsp;
            <select name="batch" style="width:auto">
                <?php
                    $sql = "SELECT DISTINCT Batch FROM batch_list;";
                    $result = mysql_query($sql);
                    while ($row1 = mysql_fetch_array($result)) {
                ?> <option><?php echo $row1["Batch"];?></option>
                <?php } ?>
            </select>
			<p>Date of Last Donation</p>
            <select  name="ld" style="width:auto">
              <?php for($i=1; $i<=31; $i++) { ?>
              <option value="<?php echo $i;?>"  >
              <?php  echo $i;?>
              </option>
              <?php } ?>
            </select>
            <select name="lm" style="width:auto">
              <?php
                for($i=1; $i<=12; $i++)
                { ?>
              <option value="<?php echo $i;?>"> <?php echo  substr(date("F", strtotime("".$i."/1/1") ),0,9) ; ?> </option>
              <?php } ?>
            </select>
            <select name="ly" style="width:auto">
                <?php
                for($i=date("Y"); $i>=date("Y")-90; $i--)
                { ?>
                <option value="<?php echo $i;?>"><?php echo $i;?></option>
                <?php } ?>
            </select>
            <p>District</p>
            <select name="address3" style="width:auto">
                <?php
                    $sql = "SELECT * FROM `district`;";
                    $result = mysql_query($sql);
                    while ($row1 = mysql_fetch_array($result)) {
                ?> <option><?php echo $row1["Name"];?></option>
                <?php } ?>
            </select>
		</div><!--end user-details-->
		
		<div id="right-side">		
			<p>Phone number&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Publish</p>
			<input type="text" name="phone" value="" style="width:150px;" />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="checkbox" name="publish" checked="false" />
			<p>Email ID (This will be your username for login)</p>
			<input type="text" name="email" id="email" value=""/>
			<p>Address</p>
            <textarea cols="" rows=""  name="address"></textarea>
            <p>&nbsp;</p><p>&nbsp;</p>
            <input type="reset" value="Reset" name="reset" class="reset"/>
		</div><!-- end user-message -->
        <div id="submit-button">
            <input type="submit" value="Submit" name="submit" class="submit" />
        </div>
</form> 
                                
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
