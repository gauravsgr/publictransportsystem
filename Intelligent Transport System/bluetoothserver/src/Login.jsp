<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2//EN">
<style type="text/css">
body {
font-family: Arial;
font-size: 15px;
}
form#userData {
border: 2px dotted #F60;
background-color: #ede8a1;
}
input, select, textarea {
border: 1px solid #F60;
}
textarea {
overflow: auto;
padding: 5px;
}
td {
vertical-align: top;
}
input#submit {
color: #F60;
font-weight: bold;
}
</style>

<HTML>
	<HEAD>
		<TITLE>Login Page</TITLE>
		<style type="text/css">
			body 
			{	
				background-image: url("images/mytheme.jpg");	
				background-attachment: fixed;
				background-repeat: no-repeat;
				font-family: Arial;
				font-size: 20px;
			}			
		</style>
	</HEAD>
	<BODY>
		<% 
			if(session.getAttribute("name")!=null) request.getRequestDispatcher("Account.jsp").forward(request,response);
		%>
    	<fieldset><legend>Login Information</legend>
			<FORM METHOD="POST" ACTION="LoginUser.do" >
				<TABLE>
					<TR>
						<TD>Username:</TD>  <TD><INPUT TYPE="TEXT" NAME="username" VALUE="" SIZE="15" MAXLENGTH="50"></TD> <BR>
					</TR>
					<TR>
						<TD>Password:</TD> <TD><INPUT TYPE="PASSWORD" NAME="password" VALUE="" SIZE="15" MAXLENGTH="50"></TD><BR>
					</TR>
				</TABLE>
				&nbsp;&nbsp;&nbsp;<INPUT TYPE="SUBMIT" NAME="submit" VALUE="Login"><BR>
			</FORM>
		</fieldset>
	</BODY>
</HTML>
