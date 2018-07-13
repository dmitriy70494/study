<%--
  Created by IntelliJ IDEA.
  User: balandin
  Date: 12.07.2018
  Time: 7:29
  To change this template use File | Settings | File Templates.
--%>
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

        $(document).ready(drawHtml());

        function drawHtml() {
            drawUsersAd("${'/items/search.do'}", 2, '');
            drawSelect();
        }

        function drawUsersAd(linked, style, parametr) {
            $.ajax(linked, {
                    method: 'post',
                    data: {
                        parametr: parametr
                    },
                    beforeSend: function () {
                        $('#table').html('Получаем контент');
                    },
                    complete: function (data) {
                        var ad = JSON.parse(data.responseText);
                        var result = "<tr><th>Статус</th><th>Фото</th><th>Название</th><th>Тип двигателя</th><th>Передача</th><th>Кузов</th><th>Дата создания</th></tr>";
                        for (var index = 0; index < ad.length; index++) {
                            if (style == 1) {
                                if (ad[index].done == 'true') {
                                    result += "<tr><td><input type='button' id='check' name='check' onclick=\"updateAd(" + ad[index].id + ");\" value = 'снять'></td><td>";
                                } else {
                                    result += "<tr><td>снято с </br> продажи</td><td>";
                                }
                            } else {
                                result += "<tr><td>продается</td><td>"
                            }
                            result += "<img src=\"" + ad[index].foto + "\" width=\"100\" height=\"100\" alt=\"lorem\"></td><td>"
                                + ad[index].name + "</td><td>"
                                + ad[index].motor.name + ("</td><td>")
                                + ad[index].transmission.name + "</td><td>"
                                + ad[index].bodywork.name
                                + "</td><td>"
                                + ad[index].create
                                + "</td></tr>";
                        }
                        $('#table').html(result);
                    }
                }
            )
            ;
        }

        function drawUsersAdWithParametr(select) {
            drawUsersAd("${'/items/users_select.do'}", 2, select.options[select.selectedIndex].value);
        }

        function updateAd(id) {
            $.ajax("${'/items/change_status.do'}", {
                    method: 'post',
                    data: {
                        id: id
                    },
                    complete: drawUsersAd("${'/items/search.do'}", 2, '')
                }
            );
        }

        function drawSelect() {
            $.ajax("${'/items/search_named.do'}", {
                    method: 'post',
                    complete: function (data) {
                        var result = "<select id='sel' name='sel' onchange=\"drawUsersAdWithParametr(this)\" required>"
                            + "<option value=\"\">None</option>";
                        result += data.responseText;
                        result += "</select>";
                        $('#selectInterface').html(result);
                    }
                }
            );
        }

    </script>
</head>
<body>
<div class="main" id="main">
    <div class="container-fluid">
        <form action="cars" method="post">
            <input type="hidden" name="action" id="action" value="createAd">
            <a href="create.do">Добавить объявление</a>
            <button type="button" class="btn btn-default" onclick="drawUsersAd('${'/items/users_selects.do'}', 1, '')">Мои объявление
            </button>

        </form>
    </div>

    <br>
    <br>
    <br>
    <div class="interface">
        <label><input type="checkbox" id="lastDay" onchange="drawUsersAd('${'/items/last_day.do'}', 2, '')"/> за последний день</label>
        <label><input type="checkbox" id="withFoto" onchange="drawUsersAd('${'/items/with_foto.do'}', 2, '')"/>только с
            фотографией</label>
    </div>
    <div class="selectInterface" id="selectInterface">

    </div>
    <div class="user table">
        <h2>Объявления</h2>
        <table class="table table-hover" id="table">
        </table>
    </div>
</div>
</body>
</html>
