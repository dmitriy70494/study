<%@ page import="ru.job4j.persist.CarStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>


        $(document).ready(initData());

        function initData() {
            $.ajax("${'/items/all_parts.do'}", {
                method: 'POST',
                complete: function (data) {
                    var parts = [];
                    var objects = JSON.parse(data.responseText);
                    for (var i = 0; i < objects.length; i++) {
                        var result = '';
                        for (var index = 0; index < objects[i].length; index++) {
                            result += "<option value=" + objects[i][index].id + ">" + objects[i][index].name + "</option>";
                        }
                        parts[i] = result;
                    }
                    $("#motor").html("Тип двигателя: <select name='motor' id='motorSel' >" + parts[0] + "</select></br>");
                    $("#transmission").html("Передача: <select name='transmission' id='transSel'>" + parts[1] + "</select></br>");
                    $("#bodywork").html("Кузов: <select name='bodywork' id='bodySel'>" + parts[2] + "</select></br>");
                }
            });
        }

    </script>
</head>
<body>

<form action="/items/create.do" method="post" enctype="multipart/form-data" accept-charset='utf-8'>
    Название: <input type="text" name="name" id="name" required/></br>
    <div id="motor">

    </div>
    <div id="transmission">

    </div>
    <div id="bodywork">

    </div>
    Прикрепить фотoграфию:</br>
    Добавить файл: <input type="file" id="foto" name="upfile"/><br/>
    Notes about the file: <input type="text" name="note"><br/>
    <button type="submit" class="btn btn-default" action="">Добавить объявление</button>
</form>

</body>
