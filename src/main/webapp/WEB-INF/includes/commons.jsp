<%@ page import="java.util.ResourceBundle" %>

<%!
    String getMessage (ServletContext ctx, String key) {

        ResourceBundle bundle = (ResourceBundle)ctx.getAttribute ("lang");
        return bundle.getString (key);
    }
%>