<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>

<%@ page import="pl.mgu95.justaclock.weather.ExampleWeather" %>
<%@ page import="pl.mgu95.justaclock.weather.Weather" %>

<!DOCTYPE html>

<html>

  <head>

    <title>Settings</title>

  </head>

  <body>

    <a href="/">HOME</a>

    <h1>Pogoda</h1>

    <form method="post" action="/settings/weatherInitialization">
      <label for="key">API key:</label><br>
      <input type="text" name="key"><br>
      <label for="city">Miasto (bez PL znaków):</label><br>
      <input type="text" name="city"><br><br>
      <input type="submit" value="Submit">
    </form>

  </body>

</html>
