<%-- 
    Document   : index
    Created on : Sep 23, 2013, 7:45:43 PM
    Author     : Wolverine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="jquery-1.6.2.min.js" type="text/javascript"></script>
        <script type="text/javascript">
    /*    history.replaceState("manoj", "manoj", "/Testyourcode");*/
        </script>
        <style>
body{
	margin:0;
	padding:0;
	font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
	}
fieldset{
	border:0;}
#container{
	width:1000px;
	margin:auto;
 }
#mainwindow{
		width:1000px;
	margin:auto;
	margin-top:70px;
	}
select,
input[type="text"],
input[type="password"],
input[type="datetime"],
input[type="datetime-local"],
input[type="date"],
input[type="month"],
input[type="time"],
input[type="week"],
input[type="number"],
input[type="email"],
input[type="url"],
input[type="search"],
input[type="tel"],
input[type="color"],
.uneditable-input {
  display: inline-block;
  height: 20px;
  padding: 4px 6px;
  margin-bottom: 10px;
  font-size: 14px;
  line-height: 20px;
  color: #555555;
  vertical-align: middle;
  -webkit-border-radius: 4px;
     -moz-border-radius: 4px;
          border-radius: 4px;
		    width: 206px;
			  font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
  font-size:16px;
			
}
textarea {
  height: auto;
    -webkit-border-radius: 2px;
     -moz-border-radius: 2px;
          border-radius: 2px;
		  padding:10px 10px 10px 10px;
        display: block;
  overflow-x: auto;
  padding: 0.5em;
  background: #fdf6e3;
  color: #657b83;
  -webkit-text-size-adjust: none;
  font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
  font-size:16px;
  width:570px;
} 
select,
textarea,
input[type="text"],
input[type="password"],
input[type="datetime"],
input[type="datetime-local"],
input[type="date"],
input[type="month"],
input[type="time"],
input[type="week"],
input[type="number"],
input[type="email"],
input[type="url"],
input[type="search"],
input[type="tel"],
input[type="color"],
.uneditable-input {
  background-color: #ffffff;
  border: 1px solid #cccccc;
  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
     -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
          box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
  -webkit-transition: border linear 0.2s, box-shadow linear 0.2s;
     -moz-transition: border linear 0.2s, box-shadow linear 0.2s;
       -o-transition: border linear 0.2s, box-shadow linear 0.2s;
          transition: border linear 0.2s, box-shadow linear 0.2s;
}
select:focus,
option:focus,
textarea:focus,
input[type="text"]:focus,
input[type="password"]:focus,
input[type="datetime"]:focus,
input[type="datetime-local"]:focus,
input[type="date"]:focus,
input[type="month"]:focus,
input[type="time"]:focus,
input[type="week"]:focus,
input[type="number"]:focus,
input[type="email"]:focus,
input[type="url"]:focus,
input[type="search"]:focus,
input[type="tel"]:focus,
input[type="color"]:focus,
.uneditable-input:focus {
  border-color: rgba(82, 168, 236, 0.8);
  outline: 0;
  outline: thin dotted \9;
  /* IE6-9 */

  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
     -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
          box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
}
#hai{
	font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
	font-size:16px;
	text-align:justify;
	text-wrap:normal;
	line-height:2;
	padding:15px 15px 15px 15px;
	height:700px;
	overflow:auto;
	}
 #output{
	 float:right;
	 width:390px;
	 height:600px;
	 margin-top:80px;
	 margin-left:15px;
	 padding:5px 5px 5px 5px;
	 }
	#editor{
		  float:left;
		  width:580px;
	}
	.red{
		border-color:#C00 !important;
		  -webkit-box-shadow: inset 0 1px 1px #C00;
     -moz-box-shadow: inset 0 1px 3px #C00;
          box-shadow: inset 0 1px 3px #C00;
  -webkit-transition: border linear 0.2s, box-shadow linear 0.2s;
     -moz-transition: border linear 0.2s, box-shadow linear 0.2s;
       -o-transition: border linear 0.2s, box-shadow linear 0.2s;
          transition: border linear 0.2s, box-shadow linear 0.2s;
		}
        </style>
    </head>
    <body>
        
  <div id="container">
  <section id="mainwindow">   
<form name="manoj" method="post" action="./run" id="editor">
<fieldset>
    <label>Enter Your Class Name:</label><input id="cln" type="text" name="classname"/><div id="err1"></div><br>
    <p>Your Code</p>
        <textarea id="prg" name="program" cols="100" rows="20"></textarea>
</fieldset>
<fieldset>
<input type="button" id="sub" value="Run">
</fieldset>
  </form>
<div id="output">  
  <h3 align="center">Output:</h3>
  <div id="hai"></div>
</div>

</section>

  </div>
    <script type="text/javascript">
	$('#cln').blur(function(e) {
		var cls=$("#cln").val();
       				if(cls=="")
				{
					$("#cln").removeClass().addClass("red");
					$("#err1").html("<span style='color:#F00; margin-left:150px;'>enter the class name</span>");
                }
				else{
					$("#cln").removeClass();
					$("#err1").html("<span style='color:#F00; margin-left:150px;'></span>");
					} 
    });
	$('#prg').focus(function(e) {
        $("#prg").removeClass();
    });

            $("#sub").click(function(e) {
				
                var prg= document.getElementById("prg").value;
				var cls=$("#cln").val();
				if(cls=="")
				{
					$("#cln").removeClass().addClass("red");
					$("#err1").html("<span style='color:#F00; margin-left:150px;'>enter the class name</span>");
                }
				else if(prg==""){
					$("#prg").removeClass().addClass("red");
				}
				else{
				datastring="program="+String(prg)+"&classname="+cls;
	$.post("./run",{program:prg,classname:cls},function(data){	
		document.getElementById('hai').innerHTML=data;
			});
				}				
            });
  
            
            
        </script>
    </body>
</html>
