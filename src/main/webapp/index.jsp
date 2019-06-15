<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Educacion IT :: Java Web</title>

    <script language="JavaScript">

        function doRegister () {

            window.location = 'register.jsp';
        }
        
    </script>

</head>
<body>

<div class="jumbotron text-center">
    <h1>Delivery De Comida</h1>
    <p>Sistema de envio de comidas!</p>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm"></div>
        <div class="col-sm">
            <form action="login" method="post">
                <div class="form-group">
                    <label for="username">Correo Electronico</label>
                    <input type="text" class="form-control" id="username" name="username" aria-describedby="usernameHelp" placeholder="Ingrese su usuario">
                    <small id="usernameHelp" class="form-text text-muted">No permita que su clave sea vista por otros.</small>
                </div>
                <div class="form-group">
                    <label for="pw">Clave</label>
                    <input type="password" class="form-control" id="pw" name="pw" placeholder="Ingrese su clave">
                </div>
                <div class="form-group">
                    <%
                        if (request.getParameter ("err") != null &&
                                request.getParameter("err").equals ("001")) {
                    %>
                    <div class="alert alert-danger">
                        <strong>Error</strong> Usuario y password no validos !!!</a>.
                    </div>
                    <%
                        }
                    %>
                    <%
                        if (request.getParameter ("err") != null &&
                                request.getParameter("err").equals ("000")) {
                    %>
                    <div class="alert alert-danger">
                        <strong>Error</strong> Problemas ejecutando su peticion !!!</a>.
                    </div>
                    <%
                        }
                    %>
                </div>

                <button type="submit" class="btn btn-primary">Ingresar</button>

                <div class="form-group">
                    <br>
                    <button onclick="doRegister();" type="button" class="btn btn-link">Registrate</button>
                </div>
            </form>
        </div>
        <div class="col-sm"></div>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>