<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
<link rel="icon" href="https://i.imgur.com/CjvIMZT.png">
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
      <div class="row">
        <div class="col-md-12 text-center">
          <h1 class="">Log In</h1>
        </div>
      </div>
      <div class="row p-5">
        <div class="col-md-12">
          <div class="row">
            <div class="col-md-12">
              <form action="#" method="post" class="text-center">
                <div class="form-group"> <label>Username</label> <input type="text" id="username" name="username" class="form-control w-25 text-left mx-auto" placeholder="Enter username" style="" required="required"> <small class="form-text text-muted">We'll never share your information with anyone else.</small> </div>
                <div class="form-group text-center"> <label>Password</label> <input type="password" id="password" name="password" class="form-control w-25 text-left mx-auto" placeholder="Password" required="required" style="opacity: 0.5;"> </div> 
                	<button class="bg-primary">
					<i class="far fa-comments"></i>
					</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>