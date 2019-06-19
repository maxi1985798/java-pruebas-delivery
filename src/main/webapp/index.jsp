<%@ include file="/WEB-INF/includes/header.jsp" %>
<body>
<script language="JavaScript">

    function doRegister () {

        window.location = 'register.jsp';
    }

</script>
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

<%@ include file="/WEB-INF/includes/footer.jsp" %>