<%@ page session="false" %>
<%@ include file="/WEB-INF/includes/commons.jsp" %>
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

            if (document.getElementById("userName").value == '') {

                alert ("Nombre de usuario es obligatorio !!!");
                return;
            }

            if (document.getElementById("name").value == '') {

                alert ("Primer nombre es obligatorio !!!");
                return;
            }

            if (document.getElementById("lastName").value == '') {

                alert ("Apellido es obligatorio !!!");
                return;
            }

            if (document.getElementById("mobile").value == '') {

                alert ("Celular es obligatorio !!!");
                return;
            }

            if (document.getElementById("address").value == '') {

                alert ("La direccion es obligatoria !!!");
                return;
            }

            if (document.getElementById("email").value == '') {

                alert ("Direccion de email es obligatorio !!!");
                return;
            }

            if (document.getElementById("pw").value == '') {

                alert ("La clave es obligatoria !!!");
                return;
            }

            if (document.getElementById("pw").value != document.getElementById("pw2").value) {

                alert ("Clave y confirmacion diferentes !!!");
                return;
            }

            document.getElementById ("data").submit();
        }

    </script>

</head>
<body>

<div class="jumbotron text-center">
    <h1>Delivery De Comida</h1>
    <p>Sistema de envio de comidas!</p>
    <p><b>>>> Nuevo Usuario</b></p>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm"></div>
        <div class="col-sm">
            <form id="data" name="data" action="register" method="post">
                <div class="form-group">
                    <label for="userName">Nombre de usuario</label>
                    <input type="text" class="form-control" id="userName" name="userName" aria-describedby="userNameHelp" placeholder="Nombre de usuario">
                    <small id="userNameHelp" class="form-text text-muted">Nombre de usuario, no se permiten caracteres especiales ni numeros, solo letras.</small>
                </div>
                <div class="form-group">
                    <label for="name">Nombre</label>
                    <input type="text" class="form-control" id="name" name="name" aria-describedby="nameHelp" placeholder="Nombre">
                    <small id="nameHelp" class="form-text text-muted">Primer nombre.</small>
                </div>
                <div class="form-group">
                    <label for="lastName">Apellido</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" aria-describedby="lastNameHelp" placeholder="Apellido">
                    <small id="lastNameHelp" class="form-text text-muted">Primer apellido.</small>
                </div>
                <div class="form-group">
                    <label for="mobile">Celular</label>
                    <input type="text" class="form-control" id="mobile" name="mobile" aria-describedby="mobileHelp" placeholder="Telefono">
                    <small id="mobileHelp" class="form-text text-muted">Telefono celular.</small>
                </div>
                <div class="form-group">
                    <label for="address">Direccion</label>
                    <textarea class="form-control" id="address" name="address" aria-describedby="addressHelp" placeholder="Direccion">
                    </textarea>
                    <small id="addressHelp" class="form-text text-muted">Direccion de casa.</small>
                </div>
                <div class="form-group">
                    <label for="email">Correo Electronico</label>
                    <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" placeholder="Ingrese su correo">
                    <small id="emailHelp" class="form-text text-muted">No permita que su clave sea vista por otros.</small>
                </div>
                <div class="form-group">
                    <label for="pw">Clave</label>
                    <input type="password" class="form-control" id="pw" name="pw" aria-describedby="pwHelp" placeholder="Ingrese su clave">
                    <small id="pwHelp" class="form-text text-muted">No se permiten caracteres especiales, solo numeros y letras.</small>
                </div>
                <div class="form-group">
                    <label for="pw2">Repita su clave</label>
                    <input type="password" class="form-control" id="pw2" name="pw2" placeholder="Repita su clave">
                </div>

                <div class="form-group">
                    <%
                        if (request.getParameter ("err") != null &&
                                request.getParameter("err").equals ("002")) {
                    %>
                    <div class="alert alert-danger">
                        <strong>Error:</strong> Falta ingresar alguna informacion sobre su registro !!!</a>.
                    </div>
                    <%
                        }
                    %>
                    <%
                        if (request.getParameter ("err") != null &&
                                request.getParameter("err").equals ("003")) {
                    %>
                    <div class="alert alert-danger">
                        <strong>Error:</strong> Clave y confirmacion no coinciden !!!</a>.
                    </div>
                    <%
                        }
                    %>
                    <%
                        if (request.getParameter ("err") != null &&
                                request.getParameter("err").equals ("000")) {
                    %>
                    <div class="alert alert-danger">
                        <strong>Error:</strong> Problemas ejecutando su peticion, intente nuevamente</a>.
                    </div>
                    <%
                        }
                    %>
                </div>

                <button type="button" onclick="doRegister();" class="btn btn-primary">Registrarme</button>

                <div class="form-group">
                    <br>
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