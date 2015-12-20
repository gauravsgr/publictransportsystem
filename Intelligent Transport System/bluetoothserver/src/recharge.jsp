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
		<TITLE>Recharge Page</TITLE>
		<style type="text/css">
			body 
			{
				font-family: Arial;
				font-size: 20px;
			}			
		</style>
	</HEAD>
	<BODY>
		<H1>&nbsp;&nbsp;&nbsp;&nbsp;--<U>Recharge Page</U>--</H1>
    	<fieldset><legend>Recharge with Email Id</legend>
			<FORM METHOD="POST" ACTION="BalanceRecharge.do" >
				<TABLE>
					<TR>
						<TD> Email Id:</TD>  <TD><INPUT TYPE="TEXT" NAME="email" VALUE="" SIZE="15" MAXLENGTH="50"></TD> <BR>
					</TR>
					<TR>
						<TD>Amount:</TD> <TD><INPUT TYPE="TEXT" NAME="amount" VALUE="" SIZE="9" MAXLENGTH="50"></TD><BR>
					</TR>
				</TABLE>
				&nbsp;&nbsp;&nbsp;<INPUT TYPE="SUBMIT" NAME="submit" VALUE="Recharge"><BR>
			</FORM>
		</fieldset>
		<br>
		
		<fieldset><legend>Recharge with Imei No.</legend>
			<FORM METHOD="POST" ACTION="BalanceRecharge.do" >
				<TABLE>
					<TR>
						<TD> Imei No.:</TD>  <TD><INPUT TYPE="TEXT" NAME="emei" VALUE="" SIZE="15" MAXLENGTH="50"></TD> <BR>
					</TR>
					<TR>
						<TD>Amount:</TD> <TD><INPUT TYPE="TEXT" NAME="amount" VALUE="" SIZE="9" MAXLENGTH="50"></TD><BR>
					</TR>
				</TABLE>
				&nbsp;&nbsp;&nbsp;<INPUT TYPE="SUBMIT" NAME="submit" VALUE="Recharge"><BR>
			</FORM>
		</fieldset>
		<br>
		
		<fieldset><legend>Recharge with Bluetooth No.</legend>
			<FORM METHOD="POST" ACTION="BalanceRecharge.do" >
				<TABLE>
					<TR>
						<TD>Bluetooth No.:</TD>  <TD><INPUT TYPE="TEXT" NAME="bluetooth_adr" VALUE="" SIZE="15" MAXLENGTH="50"></TD> <BR>
					</TR>
					<TR>
						<TD>Amount:</TD> <TD><INPUT TYPE="TEXT" NAME="amount" VALUE="" SIZE="9" MAXLENGTH="50"></TD><BR>
					</TR>
				</TABLE>
				&nbsp;&nbsp;&nbsp;<INPUT TYPE="SUBMIT" NAME="submit" VALUE="Recharge"><BR>
			</FORM>
		</fieldset>
		<br>
	</BODY>
</HTML>
