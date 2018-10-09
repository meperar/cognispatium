<%@ include file="/WEB-INF/views/include.jsp"%>
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
          <li class="nav-item"> <a class="nav-link" href="hello.jsp">Features</a> </li>
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
  <div class="py-3">
    <div class="container">
      <div class="row">
        <div class="col-md-8 p-4">
          <h1>Contestar a la consulta urgente&nbsp;</h1>
        </div>
      </div>
      <div class="row">
        <div class="col-md-5 p-4">
          <h3>Datos de la consulta</h3>
          <p>A continuación aparecerán los datos de la consulta que va a responder:</p>
          <p class="lead mt-3"> <b>Tema</b></p>
          <p> <a href="#">support@hello.com</a> </p>
          <p class="lead mt-3"> <b>Fecha de creación</b></p>
          <p> <a href="#">+1 234 567 89</a> </p>
          <p class="lead mt-3"> <b>Autor</b></p>
          <p> <a href="#">sales@hello.com</a> </p>
          <p class="lead mt-3"> <b>Descripción</b></p>
          <p> <a href="#">info@hello.com</a> </p>
        </div>
        <div class="col-md-7 p-4">
          <h3 class="mb-3">Respuesta</h3>
          <form>
            <div class="form-group"> <input type="text" class="form-control" id="form38" placeholder="Company"> </div>
            <div class="form-group"> <textarea class="form-control" id="form43" rows="3" placeholder="Tu respuesta a la consulta" style="margin-top: 0px; margin-bottom: 0px; height: 141px;"></textarea> </div> <button type="submit" class="btn btn-primary">Send</button>
          </form>
        </div>
      </div>
    </div>
  </div>
  <div class="py-3">
    <div class="container">
      <div class="row">
        <div class="col-md-12 text-center"> <i class="d-block fa fa-stop-circle mb-3 text-muted fa-3x"></i>
          <p> <a href="https://goo.gl/maps/AUq7b9W7yYJ2" target="_blank"> Fake street, 100 <br>NYC - 20179, USA</a> </p>
          <p> <a href="tel:+246 - 542 550 5462">+1 - 256 845 87 86</a> </p>
          <p class="mb-0"> <a href="mailto:info@pingendo.com">info@pingendo.com</a> </p>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12 d-flex align-items-center justify-content-center my-3"> <a href="#">
            <i class="d-block fa fa-facebook-official text-muted fa-lg mr-2"></i>
          </a> <a href="#">
            <i class="d-block fa fa-instagram text-muted fa-lg mx-2"></i>
          </a> <a href="#">
            <i class="d-block fa fa-google-plus-official text-muted fa-lg mx-2"></i>
          </a> <a href="#">
            <i class="d-block fa fa-pinterest-p text-muted fa-lg mx-2"></i>
          </a> <a href="#">
            <i class="d-block fa fa-reddit text-muted fa-lg mx-2"></i>
          </a> <a href="#">
            <i class="d-block fa fa-twitter text-muted fa-lg ml-2"></i>
          </a> </div>
      </div>
      <div class="row">
        <div class="col-md-12 text-center">
          <p class="mb-0">© 2014-2018 Pingendo. All rights reserved</p>
        </div>
      </div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous" style=""></script>
</body>

</html>