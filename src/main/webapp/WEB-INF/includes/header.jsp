<%!
    String getUserName (HttpSession session) {

        User s = (User) session.getAttribute ("user");
        return s.getName () + " " + s.getLastName ();
    }
%>
<%@ include file="commons.jsp" %>
<!doctype html>
<html lang="en">
<head>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Educacion IT :: Java Web</title>

</head>

<body>

    <div class="jumbotron text-center">
        <h1>Delivery De Comida</h1>
        <p>Sistema de envio de comidas!</p>
        <p>Bienvenido : <b><%=getUserName (session)%></b></p>
        <p><a href="logout">Salir</a></p>
    </div>