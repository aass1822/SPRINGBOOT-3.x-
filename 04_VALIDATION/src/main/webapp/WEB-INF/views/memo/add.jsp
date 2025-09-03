<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
    <h1>/memo/add PAGE</h1>

    <form action="/memo/add" method="post">
        <div>
            <label>ID :</label> <span> </span> <br/>
            <input name="id" />
        </div>
        <div>
            <label>TEXT :</label> <span> </span> <br/>
            <textarea name="text"></textarea>
        </div>
        <div>
            <label>WRITER :</label> <span> </span> <br/>
             <input name="writer" />
        </div>
       <div>
            <label>CREATEAT :</label> <span> </span> <br/>
            <input Type="datetime-local" name="createat" />
       </div>
       <div>
            <button>메모추가</button>
       </div>

    </form>

</body>
</html>
