<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
<link rel="icon" href="https://i.imgur.com/CjvIMZT.png">
<style>
.listado {
 	width: 100%;
  	margin-bottom: 1.5rem;
  	background-color: transparent;}
  
  	.listado th,
  	.listado td {
    	padding: 0.75rem;
    	vertical-align: top;
    	border-bottom: 1px solid lightgrey; 
  	}
    
  	.listado thead th {
    	vertical-align: bottom;
   	 	border-bottom: 2px solid black; 
 	}
    
  	.listado tbody + tbody {
    	border-top: 2px solid lightgrey; 
   	}
    
  	.listado .listado {
    	background-color: white; 
   	}
   	.perfil {
 	width: 100%;
  	margin-bottom: 1.5rem;
  	background-color: transparent; 
    }
  
  	.perfil th,
  	.perfil td {
    	padding: 0.75rem;
    	vertical-align: top; 
  	}
    
  	.perfil thead th {
    	vertical-align: bottom;
   	 	border-bottom: 2px solid black; 
 	}
    
  	.perfil .perfil {
    	background-color: white; 
   	}
   	
.subrayadoGordo {
    	vertical-align: bottom;
   	 	border-bottom: 2px solid black; 
}

.subrayadoFino {
    	vertical-align: bottom;
   	 	border-bottom: 1px solid lightgrey; 
   	 	margin-bottom: 20px;
}
   	
   	
/* width */
::-webkit-scrollbar {
    width: 5px;
    heigth: 5px;
}

/* Track */
::-webkit-scrollbar-track {
    border-radius: 1px;
}
 
/* Handle */
::-webkit-scrollbar-thumb {
    background: black; 
    border-radius: 1px;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
    background: #555555; 
}


/* Button used to open the contact form - fixed at the bottom of the page */
.open-button {
  background-color: #555;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  opacity: 0.8;
  bottom: 23px;
  right: 28px;
  width: 280px;
}

/* The popup form - hidden by default */
.form-popup {
  display: none;
  width: 400px;
  position: absolute;
  top: 0px;
  left: 400px;
  border: 3px solid #f1f1f1;
  z-index: 100;
}

/* Add styles to the form container */
.form-container {
  max-width: 500px;
  padding: 7px;
  background-color: white;
}

/* Full-width input fields */
.form-container input[type=text], .form-container input[type=password] {
  width: inherit;
  padding: 5px;
  margin: 20px 10px 10px 10px;
  border: none;
  background: #f1f1f1;
}

/* When the inputs get focus, do something */
.form-container input[type=text]:focus, .form-container input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for the submit/login button */
.form-container .btn {
  background-color: #4CAF50;
  color: white;
  padding: 10px 10px;
  border: none;
  cursor: pointer;
  width: 187px;
}

/* Add a red background color to the cancel button */
.form-container .cancel {
  background-color: red;
}

/* Add some hover effects to buttons */
.form-container .btn:hover, .open-button:hover {
  opacity: 1;
}



.rightAlign {
    text-align: right;
    padding: 22px 0;
}

div.stars {
  width: 300px;
  display: inline-block;
  align: center;
}

input.star { display: none; }

label.star {
  float: right;
  padding: 10px;
  font-size: 36px;
  color: #444;
  transition: all .2s;
}

input.star:checked ~ label.star:before {
  content: '\f005';
  color: #FD4;
  transition: all .25s;
}

input.star-5:checked ~ label.star:before {
  color: #FE7;
  text-shadow: 0 0 20px #952;
}

input.star-1:checked ~ label.star:before { color: #F62; }

label.star:hover { transform: rotate(-15deg) scale(1.3); }

label.star:before {
  content: '\f006';
  font-family: FontAwesome;
}

/*Texto cnetrado inicio*/
.center {
	text-align:center;
}
/*Texto cnetrado fin*/
</style>
</head>
<body>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
	<link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.1.3.css" style="">
<jsp:include page="barrasuperior.jsp" flush="true" />
	<div class="center">
		<img src="https://i.imgur.com/Yiay52m.png" width = 256 title="source: imgur.com"/>
		<h4>${profesional.nombre} ${profesional.apellidos}</h4>
	</div>
	<form action="#"  method="post" class="text-left center">
		<div class="center">
			<div class="stars">
				<!--<input class="star star-${5} center" id="star-${5}" value="${5}" type="radio" name="star" required/>
				<label class="star star-${5} center" for="star-${5}"></label>
				<p>5</p>
				<input class="star star-${4} center" id="star-${4}" value="${4}" type="radio" name="star" required/>
				<label class="star star-${4} center" for="star-${4}"></label>
				<p>4</p>
				<input class="star star-${3} center" id="star-${3}" value="${3}" type="radio" name="star" required/>
				<label class="star star-${3} center" for="star-${3}"></label>
				<p>3</p>
				<input class="star star-${2} center" id="star-${2}" value="${2}" type="radio" name="star" required/>
				<label class="star star-${2} center" for="star-${2}"></label>
				<p>2</p>
				<input class="star star-${1} center" id="star-${1}" value="${1}" type="radio" name="star" required/>
				<label class="star star-${1} center" for="star-${1}"></label>
				<p>1</p>-->
				<c:forEach begin="1" end="5" varStatus="loop" var="i">
					<input class="star star-${6-i} center" id="star-${6-i}" value="${6-i}" type="radio" name="star" required/>
			    	<label class="star star-${6-i} center" for="star-${6-i}"></label>
				</c:forEach>
			</div>
		</div>
			<br/>
		<div class="center">
		
				<button type="submit" class="bg-primary center" name="votarProfesional">Votar profesional</button>
		</div>
		
	</form>
	

</body>
</html>