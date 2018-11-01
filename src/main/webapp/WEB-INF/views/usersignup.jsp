<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
<link rel="icon" href="https://i.imgur.com/CjvIMZT.png">
<style>

.col-md-6 {
  margin: 0 auto;
}

</style>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="theme.css" type="text/css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
	integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz"
	crossorigin="anonymous">
</head>
<body>
<jsp:include page="barrasuperior.jsp" flush="true" />
<div class="py-5">
		<div class="container">		
		 <div class="col-md-6">
          <h2 style="text-align: center;">Introduzca sus datos</h2>
          <form action="#" method="post" class="text-center">
           <div class="form-group">
							<label for="form16">Nombre de usuario</label> <input type="text"
								name="username" class="form-control" id="UN"
								placeholder="Elija un nombre de usuario, p. ej. 'usuario123' " 								
								maxlength="10"
								required  /> ${err.err}
						</div>
			
			<div class="form-group">
							<label for="form16">Contraseña</label> <input type="text"
								name="password" class="form-control" id="PSW"								
								maxlength="10"
								required  />
						</div>
						
			<div class="form-group">
							<label for="form16">Nombre</label> <input type="text"
								name="nombre" class="form-control" id="nombre"
								placeholder="Introduzca su nombre real"
								maxlength="20"
								required />
						</div>
						
			<div class="form-group">
							<label for="form16">Apellido</label> <input type="text"
								name="apellido" class="form-control" id="apellido"
								placeholder="Introduzca su apellido real" 
								maxlength="20"
								required  />
						</div>			
						
			<div class="form-group">
							<label for="form16">Email</label> <input type="email"
								name="email" class="form-control" id="email"
								placeholder="Introduzca su correo electrónico" 
								required 
								maxlength="25" />
						</div>			
			
			<div class="form-group">
							<label for="form16">Teléfono</label> <input type="text"
								name="tlf" class="form-control" id="tlf"
								placeholder="Introduzca su número de teléfono móvil/fijo)"
								pattern = "[69][0-9]{8}"
								title = "número de teléfono móvil o fijo de 9 cifras"
								maxlength="9"
								required  />
						</div>
						
			<div class="form-group">
							<label for="form16">DNI/NIF</label> <input type="text"
								name="dninif" class="form-control" id="dninif"
								placeholder="Introduzca su DNI/NIF" 
								pattern="(([X-Z]{1})([-]?)(\d{7})([-]?)([A-Z]{1}))|((\d{8})([-]?)([A-Z]{1}))"
								title = "12345678A, o X-1234567-B si tiene NIE"
								maxlength="13"
								required />
						</div>		
			
			<input type="radio" name="rol" value="cliente" checked>   Cuenta Cliente &nbsp; &nbsp;  
			<input type="radio" name="rol" value="profesional">   Cuenta Profesional<br> 
			
			<div class="row">
				<div class="col-md-6"> 
					<a class="nav-link" href="hello.htm">Cancelar</a>
		    	</div>
		    	
				<div class="col-md-6">
           			<button type="submit" class="btn btn-primary m-2">Registrarse</button>
            	</div>
			</div>
			</form>	
           
            </div>
        </div>
      </div>
</body>
</html>