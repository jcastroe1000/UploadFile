<%-- 
    Document   : index
    Created on : 27/02/2016, 07:25:21 PM
    Author     : tono
--%>

 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>File Upload</title>
    </head>
 
    <body> 
        <div>
            <h3> Choose File to Upload in Server </h3>
            <form action="upload" method="post" enctype="multipart/form-data" >
                <div class="form-group">
                    <label for="InputName">Nombre del Platillo</label>
                        <div class="input-group">
                            <input type="text" class="form-control" name="name_dish"
                                   id="name_dish" placeholder="Agrega un Nombre" value="">
                        </div>
                </div>
                <div class="form-group">
                    <label for="InputMessage">Ingredientes</label>
                    <div class="input-group">
                        <textarea name="ingredients" id="ingredients"
                        placeholder="Agrega los ingredientes" class="form-control" value=""
                        rows="5" ></textarea>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <input type="file" name="file" />
                <br>
                <br>
                <input type="submit" value="upload" />
            </form>          
        </div>
      
    </body>
</html>