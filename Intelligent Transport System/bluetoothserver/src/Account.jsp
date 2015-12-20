<HTML>
	<HEAD>
		<TITLE>ACCOUNT INFORMATION</TITLE>
	</HEAD>
	<BODY>
	
<h2>Welcome ${name}</h2>
<strong>CURRENT BALANCE:</strong> ${balance} INR

<form action="https://www.paypal.com/cgi-bin/webscr" method="post">
<input type="hidden" name="cmd" value="_xclick">
<input type="hidden" name="business" value="gaurav.sagar@hotmail.com">
<input type="hidden" name="lc" value="US">
<input type="hidden" name="button_subtype" value="services">
<input type="hidden" name="no_note" value="0">
<input type="hidden" name="currency_code" value="USD">
<input type="hidden" name="bn" value="PP-BuyNowBF:BTN_Recharge_with_PayPal.jpg:NonHostedGuest">
<table>
<tr><td><input type="hidden" name="on0" value="Recharge"><strong>Recharge Online</strong></td></tr><tr><td><select name="os0">
	<option value="Small">Small $5.00</option>
	<option value="Medium">Medium $10.00</option>
	<option value="Large">Large $20.00</option>
</select> </td></tr>
</table>
<input type="hidden" name="currency_code" value="USD">
<input type="hidden" name="option_select0" value="Small">
<input type="hidden" name="option_amount0" value="5.00">
<input type="hidden" name="option_select1" value="Medium">
<input type="hidden" name="option_amount1" value="10.00">
<input type="hidden" name="option_select2" value="Large">
<input type="hidden" name="option_amount2" value="20.00">
<input type="hidden" name="option_index" value="0">
<input type="image" src="http://personal.optus.com.au/web/ShowBinary/SCSRepository/assets/images/personal/mobile/prePaidMobile/BTN_Recharge_with_PayPal.jpg" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
<img alt="" border="0" src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1">
</form>

<BR>
<form name="dateGetter" action="TravelLogFetcher.do" method="post">
<fieldset><legend>ENTER TIME PERIOD TO SEE DETAILS OF YOUR TRAVEL LOG:-</legend>
<table>
	<tr>
<td><strong>DATE FROM:</strong>&nbsp;</td> <td>dd<select size="1" name="datefrm"><option value="1" 
  selected>1</option> <option value="2">2</option> <option value="3">3</option> 
  <option value="4">4</option> <option value="5">5</option> <option 
  value=6>6</option> <option value="7">7</option> <option value="8">8</option> 
  <option value="9">9</option> <option value="10">10</option> <option 
  value=11>11</option> <option value="12">12</option> <option value="13">13</option> 
  <option value="14">14</option> <option value="15">15</option> <option 
  value=16>16</option> <option value="17">17</option> <option value="18">18</option> 
  <option value="19">19</option> <option value="20">20</option> <option 
  value=21>21</option> <option value="22">22</option> <option value="23">23</option> 
  <option value="24">24</option> <option value="25">25</option> <option 
  value=26>26</option> <option value="27">27</option> <option value="28">28</option> 
  <option value="29">29</option> <option value="30">30</option> <option 
  value=31>31</option></select></td>&nbsp;<td>-mm<select size="1" 
name=monthfrm><option value="1" selected>Jan</option> <option 
  value=2>Feb</option> <option value="3">Mar</option> <option value="4">Apr</option> 
  <option value="5">May</option> <option value="6">Jun</option> <option 
  value=7>Jul</option> <option value="8">Aug</option> <option value="9">Sep</option> 
  <option value="10">Oct</option> <option value="11">Nov</option> <option 
  value=12>Dec</option> &lt;\SELECT&gt;</select></td><td>-yyyy<select size="1" 
  name=yearfrm><option value="2010" selected>2010</option> <option 
  value=2011>2011</option> <option value="2012">2012</option> <option 
  value=2013>2013</option> <option value="2014">2014</option> <option 
  value=2015>2015</option> <option value="2016">2016</option> <option 
  value=2017>2017</option>
	</tr>
</select></td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<tr><td><strong>DATE 
TO:</strong> &nbsp;</td><td>dd<select size="1" name="dateto"><option value="1" selected>1</option> 
  <option value="2">2</option> <option value="3">3</option> <option 
  value=4>4</option> <option value="5">5</option> <option value="6">6</option> 
  <option value="7">7</option> <option value="8">8</option> <option 
  value=9>9</option> <option value="10">10</option> <option value="11">11</option> 
  <option value="12">12</option> <option value="13">13</option> <option 
  value=14>14</option> <option value="15">15</option> <option value="16">16</option> 
  <option value="17">17</option> <option value="18">18</option> <option 
  value=19>19</option> <option value="20">20</option> <option value="21">21</option> 
  <option value="22">22</option> <option value="23">23</option> <option 
  value=24>24</option> <option value="25">25</option> <option value="26">26</option> 
  <option value="27">27</option> <option value="28">28</option> <option 
  value=29>29</option> <option value="30">30</option> <option value="31">31</option> 
  &lt;\SELECT&gt;</select></td><td>-mm<select size="1" name="monthto"><option value="1" 
  selected>Jan</option> <option value="2">Feb</option> <option 
  value=3>Mar</option> <option value="4">Apr</option> <option value="5">May</option> 
  <option value="6">Jun</option> <option value="7">Jul</option> <option 
  value=8>Aug</option> <option value="9">Sep</option> <option 
  value=10>Oct</option> <option value="11">Nov</option> <option 
  value=12>Dec</option> &lt;\SELECT&gt;</select></td><td>-yyyy<select size="1" 
  name=yearto><option value="2010" selected>2010</option> <option 
  value=2011>2011</option> <option value="2012">2012</option> <option 
  value=2013>2013</option> <option value="2014">2014</option> <option 
  value=2015>2015</option> <option value="2016">2016</option> <option 
  value=2017>2017</option> &lt;\SELECT&gt;</select></td></tr></table>
<p>&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="Submit" name="submit">&nbsp;&nbsp;&nbsp;<a href="LogOut.do">Log Out</a>
</fieldset>
</p></form>
<img src="images/paypal.png">
	</BODY>
</HTML>