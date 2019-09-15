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
    <script type="text/javascript">  
            function validate() {                    
                
                
                
                var fname= document.getElementById("fname").value; 
                var letters = /^[A-Za-z]+$/;  
                if (!letters.test(fname))
                {  
                    alert('Please input alphanumeric characters only');  
                    return false;  
                        
                }  var utype= document.getElementById("utype").value; 
                
                
                
                var lname= document.getElementById("lname").value;  
                var letters = /^[A-Za-z]+$/;  
                if (!letters.test(lname))
                {  
                    alert('Please input alphanumeric characters only');  
                    return false;  
                        
                }  
                var pass1 = document.getElementById("pass").value;
             
                var pass2 = document.getElementById("cpass").value;
                    
                if (pass1 != pass2 ) {
                    alert("Please provide correct Passwords");
                    return false;
                }  
                
                var email= document.getElementById("email").value; 
                var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
                
                if (!filter.test(email)){
                    alert('Please provide a valid email address');
                    return false;
                }
                var age= document.getElementById("age").value; 
                var numbers = /^[0-9]+$/;  
                if (!numbers.test(age)){
                    alert('Please provide correct age');
                    return false;
                }  
                var mobno = document.getElementById("mobno").value;
                var phoneNum = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/; 
                if (!phoneNum.test(mobno)){
                    alert('Please provide correct Mobile no.');
                    return false;
                }
                               
//                var Select = "Select";
                var utype= document.getElementById("utype").value; 
               if (utype == "select")
                {  
                    alert('Please Select Account type');  
                    return false;  
                        
                }  
                
            }
        </script>
<div id="wrapper1">
	<div id="header-wrapper">
		<div id="header" class="container">
			<div id="logo"> <span class="icon icon-cogs"></span>
				<h1><a href="#">Cricket Analysis</a></h1>
				<span>Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a></span> </div>
			<div id="menu">
				<ul>
					<li><a href="index.jsp" accesskey="1" title="">Homepage</a></li>
					<li class="current_page_item"><a href="register.jsp" accesskey="2" title="">Registration</a></li>
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
			<p>Register Yourself</p>
                        <script>
                            function check()
                            {
                                var pass = document.getElementById("pass").value;
                                var repass = document.getElementById("repass").value;
                                
                                if(pass != repass)
                                    {
                                        alert("Password Mismatch!!!");
                                        return false;       
                                    }
                                else{
                                	validate();
                                }
                            }
                            
                        </script>
                        <form action="./Controller?task=register" method="post" onsubmit="return check();">
                            <table>
                                <tr>
                                    <td>First Name:-</td><td><input type="text" name="fname" id="fname" required=""</td>
                                </tr>
                                <tr>
                                    <td>Last Name:-</td><td><input type="text" name="lname" id="lname" required=""</td>
                                </tr>
                                <tr>
                                    <td>Email Address:-</td><td><input type="text" name="email" id="email" required=""</td>
                                </tr>
                                <tr>
                                    <td>Phone Number:-</td><td><input type="text" name="phone" id="phone" required=""</td>
                                </tr>
                                <tr>
                                    <td>Password:-</td><td><input type="password" name="pass" id="pass" required=""</td>
                                </tr>
                                <tr>
                                    <td>Retype Password:-</td><td><input type="password" name="repass" id="repass" required=""</td>
                                </tr>
                                <tr>
                                    <td><input type="submit" value="Submit"/></td><td><input type="reset" value="Clear"/></td>
                                </tr>
                            </table>
                        </form>
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
