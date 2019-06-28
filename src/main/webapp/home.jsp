<%@ include file="/WEB-INF/includes/header.jsp" %>
    <div class="jumbotron text-center">
        <h1>Delivery De Comida</h1>
        <p>Sistema de envio de comidas!</p>
        <p>Bienvenido : <b><e:Profile /></b></p>
        <p><a href="logout">Salir</a></p>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-sm"></div>
            <div class="col-sm">
                <c:forEach items="${bean.getArray('foo')}" var="item">
                    ${item} <br />
                </c:forEach>
                
            </div>
            <div class="col-sm"></div>
        </div>
    </div>

<%@ include file="/WEB-INF/includes/footer.jsp" %>