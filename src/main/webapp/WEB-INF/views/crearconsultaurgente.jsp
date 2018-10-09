<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.1.3.css">
</head>

<body>
  <nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container"> <button class="navbar-toggler navbar-toggler-right border-0" type="button" data-toggle="collapse" data-target="#navbar13">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbar13"> <a class="navbar-brand d-none d-md-block" href="#">
          <i class="fa d-inline fa-lg fa-stop-circle-o"></i>
          <b> CogniSpatium</b>
        </a>
        <ul class="navbar-nav mx-auto">
          <li class="nav-item"> <a class="nav-link" href="#">Features</a> </li>
          <li class="nav-item"> <a class="nav-link" href="#">Pricing</a> </li>
          <li class="nav-item"> <a class="nav-link" href="#">About</a> </li>
        </ul>
        <ul class="navbar-nav">
          <li class="nav-item"> <a class="nav-link" href="#">
              <i class="fa fa-twitter fa-fw"></i>
            </a> </li>
          <li class="nav-item"> <a class="nav-link" href="#">
              <i class="fa fa-facebook fa-fw"></i>
            </a> </li>
          <li class="nav-item"> <a class="nav-link" href="#">
              <i class="fa fa-slack fa-fw"></i>
            </a> </li>
        </ul>
      </div>
    </div>
  </nav>
  <div class="py-5 text-center" style="">
    <div class="container">
      <div class="row" style="">
        <div class="mx-auto col-lg-6 col-10">
          <h1>Crear una consulta urgente</h1>
          <p class="mb-3">En esta pantalla podrá introducir los datos para crear su consulta urgente.</p>
          <form class="text-left">
            <div class="form-group"> <label for="form16">Servicio</label>
              <br>
              <select name="programa">
                <option value="Windows" selected="selected">Windows</option>
                <option value="Machintosh">Mac</option>
                <option value="Linux">Linux</option>
              </select></div>
            <div class="form-group"> <label for="form16">Tiempo máximo de espera</label> <input type="time" class="form-control" id="form16" placeholder="Tiempo de espera"> </div>
            <div class="form-group"> <label for="form16">Resumen</label> <input type="text" class="form-control" id="form16" placeholder="Resumen de la consulta"> </div>
            <div class="form-group"> <label for="form18">Descripción</label> <input type="text" class="form-control" id="form18" style="" placeholder="Introduzca una descripcion de la consulta"> </div>
            <button type="submit" class="btn btn-primary">Crear consulta<br></button>
          </form>
        </div>
      </div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  <pingendo onclick="window.open('https://pingendo.com/', '_blank')" style="cursor:pointer;position: fixed;bottom: 20px;right:20px;padding:4px;background-color: #00b0eb;border-radius: 8px; width:220px;display:flex;flex-direction:row;align-items:center;justify-content:center;font-size:14px;color:white">Made with Pingendo Free&nbsp;&nbsp;<img src="https://pingendo.com/site-assets/Pingendo_logo_big.png" class="d-block" alt="Pingendo logo" height="16"></pingendo>
</body>

</html>