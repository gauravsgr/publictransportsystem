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
		<TITLE>New User Creation Page</TITLE>
		
<script language="JavaScript">
<!--

var b = 0 ;
var i = 0 ;
var errmsg = "" ;
var punct = "" ;
var min = 0 ;
var max = 0 ;

function formbreeze_email(field) {

	if (b && (field.value.length == 0)) return true ;


	if (! emailCheck(field.value))
	  {
		  field.focus();
		  if (field.type == "text") field.select();
		  return false ;
	  }

   return true ;
}

function formbreeze_filledin(field) {

if (b && (field.value.length == 0)) return true;

if (field.value.length < min) {
alert(errmsg);
field.focus();
if (field.type == "text") field.select();
return false ;
   }

if ((max > 0) && (field.value.length > max)) {
alert(errmsg);
field.focus();
if (field.type == "text") field.select();
return false ;
   }

return true ;
}

function formbreeze_number(field) {

if (b && (field.value.length == 0)) return true ; ;

if (i)
 var valid = "0123456789"
else
 var valid = ".,0123456789"

var pass = 1;
var temp;
for (var i=0; i<field.value.length; i++) {
temp = "" + field.value.substring(i, i+1);
if (valid.indexOf(temp) == "-1") pass = 0;

}

if (!pass) {
alert(errmsg);
field.focus();
if (field.type == "text") field.select();
return false;
 }

if (field.value < min) {
alert(errmsg);
field.focus();
if (field.type == "text") field.select();
return false;
   }


if ((max > 0) && (field.value > max)) {
alert(errmsg);
field.focus();
if (field.type == "text") field.select();
return false;
   }

return true ;
}


function formbreeze_numseq(field) {


if (b && (field.value.length == 0)) return true ;

var valid = punct + "0123456789"

var pass = 1;
var digits = 0
var temp;
for (var i=0; i<field.value.length; i++) {
temp = "" + field.value.substring(i, i+1);
if (valid.indexOf(temp) == "-1") pass = 0;
if (valid.indexOf(temp) > (punct.length-1) ) digits++ ;

}

if (!pass) {
alert(errmsg);
field.focus();
if (field.type == "text") field.select();
return false ; ;
   }

if (digits < min) {
alert(errmsg);
field.focus();
if (field.type == "text") field.select();
return false;
   }

if ((max > 0) && (digits > max)) {
alert(errmsg);
field.focus();
if (field.type == "text") field.select();
return false;
   }

return true ;
}

function emailCheck (emailStr) {

var checkTLD=1;
var knownDomsPat=/^(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum|ws)$/;
var emailPat=/^(.+)@(.+)$/;
var specialChars="\\(\\)><@,;:\\\\\\\"\\.\\[\\]";
var validChars="\[^\\s" + specialChars + "\]";
var quotedUser="(\"[^\"]*\")";
var atom=validChars + '+';
var word="(" + atom + "|" + quotedUser + ")";
var userPat=new RegExp("^" + word + "(\\." + word + ")*$");
var domainPat=new RegExp("^" + atom + "(\\." + atom +")*$");
var matchArray=emailStr.match(emailPat);

if (matchArray==null) {
alert(errmsg);
return false;
}
var user=matchArray[1];
var domain=matchArray[2];

for (i=0; i<user.length; i++) {
if (user.charCodeAt(i)>127) {
alert(errmsg);
return false;
   }
}
for (i=0; i<domain.length; i++) {
if (domain.charCodeAt(i)>127) {
alert(errmsg);
return false;
   }
}

if (user.match(userPat)==null) {
alert(errmsg);
return false;
}

var atomPat=new RegExp("^" + atom + "$");
var domArr=domain.split(".");
var len=domArr.length;
for (i=0;i<len;i++) {
if (domArr[i].search(atomPat)==-1) {
alert(errmsg);
return false;
   }
}

if (checkTLD && domArr[domArr.length-1].length!=2 &&
domArr[domArr.length-1].search(knownDomsPat)==-1) {
alert(errmsg);
return false;
}

if (len<2) {
alert(errmsg);
return false;
}

return true;
}

function formbreeze_sub()
{

/*
//FBDATA:btadr^0^1^0^0^Field "btadr" must be filled in.:;Emei^0^1^15^0^Field "Emei" must be filled in.:;name^0^3^30^0^Field "name" must be filled in.:;cell^0^10^10^0^Field "cell" must be filled in.:;state^0^4^50^0^Field "state" must be filled in.:;zip^2^5^9^-^0^Please enter a valid zip code.:;street^0^4^50^0^Field "street" must be filled in.:;country^0^1^0^0^Field "country" must be filled in.:;city^0^1^0^0^Field "city" must be filled in.:;balance^0^1^0^0^Field "balance" must be filled in.:;
*/
b=0;
errmsg="Field \"btadr\" must be filled in.";
min=1;
max=0;
if (! formbreeze_filledin(document.userData.btadr) ) return false ;

b=0;
errmsg="Field \"Emei\" must be filled in.";
min=1;
max=15;
if (! formbreeze_filledin(document.userData.Emei) ) return false ;

b=0;
errmsg="Field \"name\" must be filled in.";
min=3;
max=30;
if (! formbreeze_filledin(document.userData.name) ) return false ;

b=0;
errmsg="Field \"cell\" must be filled in.";
min=10;
max=10;
if (! formbreeze_filledin(document.userData.cell) ) return false ;

b=0;
errmsg="Field \"state\" must be filled in.";
min=4;
max=50;
if (! formbreeze_filledin(document.userData.state) ) return false ;

b=0;
errmsg="Please enter a valid zip code.";
punct="-";
min=5;
max=9;
if (! formbreeze_numseq(document.userData.zip) ) return false ;

b=0;
errmsg="Field \"street\" must be filled in.";
min=4;
max=50;
if (! formbreeze_filledin(document.userData.street) ) return false ;

b=0;
errmsg="Field \"country\" must be filled in.";
min=1;
max=0;
if (! formbreeze_filledin(document.userData.country) ) return false ;

b=0;
errmsg="Field \"city\" must be filled in.";
min=1;
max=0;
if (! formbreeze_filledin(document.userData.city) ) return false ;

b=0;
errmsg="Field \"balance\" must be filled in.";
min=1;
max=0;
if (! formbreeze_filledin(document.userData.balance) ) return false ;


}
-->
</script>
	</HEAD>
	<BODY>
				<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--Customer Details--</h1>
                <form name="userData" action="customerAdd.do" method=GET onsubmit=" return formbreeze_sub()">
				  	<p><fieldset><legend>CellPhone Info</legend></p>
						<table>
							<tr>
								<td><font face="Tahoma"><strong>Bluetooth Address &nbsp; </strong> </font></td>
								<td><input maxlength="12" name=btadr size="12"></td>
							</tr>
							<tr>
								<td><font face="Tahoma"><strong>Imei No </strong></font></td>
								<td><input maxlength="20" name="Emei"></td>
							</tr>
							<tr>
								<td><font face="Tahoma"><strong>CellPhone No </strong></font></td> 
								<td><input maxlength="10" name="cell" size="10"><td>
							</tr>
						</table>
					</fieldset>
					<p><fieldset><legend>Customer's Info</legend></p>
						<table>
							<tr>
								<td><strong><font face=Tahoma>Name </font></strong></td>
								<td><input maxlength="30" name="name" size="15"></td>
							<tr>
							<tr>
								<td><strong><font face="Tahoma">Email Id </font></strong></td> 
								<td><input maxlength="40" name="email" size="15"></td>
							</tr>
							<tr>
								<td><font face=Tahoma><strong>Gender &nbsp;</td>
								<td><font face=Tahoma><input type="radio" value=Male name=gender> Male <input type="radio" value="Female" name=gender> Female <input type="radio" value="Other" name="gender"> Other</strong></font></td>
							</tr>
							<tr>	
								<td><font face=Tahoma><strong>D.O.B.</strong></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								<td>
								<font face="Tahoma">Date </font>
								<select size="1" name="date">
								<option value="1" selected>1</option> 
								<option value="2">2</option> 
								<option value=3>3</option>
								<option value="4">4</option> 
								<option value=5>5</option>
								<option value="6">6</option>
								<option value=7>7</option> 
								<option value="8">8</option> 
								<option value=9>9</option>
								<option value="10">10</option>
								<option value=11>11</option>
								<option value="12">12</option> 
								<option value=13>13</option> 
								<option value="14">14</option>
								<option value=15>15</option>
								<option value="16">16</option> 
								<option value=17>17</option>
								<option value="18">18</option> 
								<option value=19>19</option> 
								<option value="20">20</option> 
								<option value=21>21</option> 
								<option value="22">22</option> 
								<option value=23>23</option>
								<option value="24">24</option> 
								<option value=25>25</option>
								<option value="26">26</option>
								<option value=27>27</option>
								<option value="28">28</option>
								<option value=29>29</option> 
								<option value="20">30</option>
								<option value=31>31</option> &lt;\SELECT&gt;
								</select>
								<font face=Tahoma>Month </font>
								<select size="1" name="month">
								<option value="Jan" selected>Jan</option>
								<option value="Feb">Feb</option> 
								<option value="Mar">Mar</option> 
								<option value="April">April</option>
								<option value="May">May</option>
								<option value="Jun">Jun</option> 
								<option value=" Jul">Jul</option> 
								<option value="Aug">Aug</option> 
								<option value="Sep">Sep</option> 
								<option value=" Oct">Oct</option> 
								<option  value="Nov">Nov</option>
								<option value="Dec">Dec</option> &lt;\SELECT&gt;
								</select>
								<font face="Tahoma">Year </font>
								<select size="1" name=year>
									<option value="1960" selected>1960</option>
									<option value="1961">1961</option> 
									<option value="1962">1962</option> 
									<option value="1963">1963</option> 
									<option value="1964">1964</option> 
									<option value="1965">1965</option> 
									<option value="1966">1966</option> 
									<option value="1967">1967</option> 
									<option value="1968">1968</option> 
									<option value="1969">1969</option> 
									<option value="1970">1970</option>
									<option value="1971">1971</option> 
									<option value="1972">1972</option> 
									<option value="1973">1973</option> 
									<option value="1974">1974</option> 
									<option value="1975">1975</option> 
									<option value="1976">1976</option> 
									<option value="1977">1977</option>
									<option value="1978">1978</option>
									<option value="1979">1979</option> 
									<option value="1980">1980</option> 
									<option value="1981">1981</option>
									<option value="1982">1982</option>
									<option value="1983">1983</option>
									<option value="1984">1984</option>
									<option value="1985">1985</option>
									<option value="1986">1986</option>
									<option value="1987">1987</option>
									<option value="1988">1988</option>
									<option value="1989">1989</option>
									<option value="1990">1990</option>
									<option value="1991">1991</option>
									<option value="1992">1992</option>
									<option value="1993">1993</option>
									<option value="1994">1994</option>
									<option value="1995">1995</option>
									<option value="1996">1996</option>
									<option value="1997">1997</option>
									<option value="1998">1998</option>
									<option value="1999">1999</option>
									<option value="2000">2000</option> &lt;\SELECT&gt;
								</select>
								</td>
							</tr>
						</table>
				</fieldset>
					<p><fieldset><legend>Customer's Address</legend></p>
					<table>
						<tr>
							<td><strong><font face="Tahoma">Street </font></strong></td> 
							<td><input maxlength="100" name=street></td>
						</tr>
						<tr>
							<td><strong><font face="Tahoma">City </font></strong></td> 
							<td><input maxlength="20" size="15" name=city></td>
						</tr>
						<tr>
							<td><strong><font face="Tahoma">State </font></strong></td> 
							<td><input maxlength="20" size="15" name=state></td>
						</tr>
						<tr>
							<td><strong><font face="Tahoma">Country </font></strong></td> 
							<td><input maxlength="20" size="15" name=country></td>
						</tr>
							<td><strong><font face="Tahoma">Zip Code &nbsp;</font></strong></td>
							<td><input maxlength="6" size="6" name=zip></td>
						</tr>
					</table>
				</fieldset>
					<p><strong><font face="Tahoma" color=#000000>&nbsp;&nbsp;&nbsp; Initial Balance </font></strong> <input maxlength="10" size="7" name=balance>&nbsp; INR</p>
					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" value="Submit" name="submit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="Reset" name="reset"></p> 
				</form>
	</BODY>
</HTML>