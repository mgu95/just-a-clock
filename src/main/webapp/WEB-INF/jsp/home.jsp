<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>

<%@ page import="pl.mgu95.justaclock.weather.ExampleWeather" %>
<%@ page import="pl.mgu95.justaclock.weather.Weather" %>

<!DOCTYPE html>

<html>

  <head>

    <title>Just a clock</title>

    <style>

      body {
        background-image: url('https://bing.biturl.top/?resolution=3840&format=image&index=0&mkt=random');
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-position: center;
        background-size: cover;
       }

      .clock {
        font-family: impact, sans-serif;
        font-size: 35vh;
        color: white;
        opacity: 0.7;
        text-align: center;
        text-shadow: 2px 2px 40px black;
      }

      .gadget-bar {
        background-color: #333;
      }

      .weather {
        position: absolute;
        right: 0px;
      }

    </style>

  </head>

  <body onload="initialization()">

    <div class="gadget-bar">
      <div class="weather">
        <table style="background-color:red;">
          <tr>
            <td id="weatherConditionIcon">[image]</td>
            <td id="temperature">[XX℃]</td>
            <td id="weatherCondition">[weather-condition]</td>
          </tr>
          <tr>
            <td id="city">[localization]</td>
          </tr>
          <tr>
            <td id="updateTime">[updateTime]</td>
          </tr>
        </table>
      </div>
    </div>

    <div id="clock" class="clock"></div>

    <a href="/settings">USTAWIENIA</a>

    <script>

      setInterval(updateWeather, 10000);

      function initialization() {
        startTime();
      }

      function updateWeather() {
        document.getElementById("temperature").innerHTML = httpGet("/weather/getTemperature");
        document.getElementById("city").innerHTML = httpGet("/weather/getCity");
        document.getElementById("weatherCondition").innerHTML = httpGet("/weather/getCondition");
        document.getElementById("weatherConditionIcon").innerHTML = "<img src=\"" + httpGet("/weather/getConditionIcon") + "\">";
        document.getElementById("updateTime").innerHTML = httpGet("/weather/getUpdateTime");
      }



      function startTime() {
        const today = new Date();
        let h = today.getHours();
        let m = today.getMinutes();
        m = checkTime(m);
        document.getElementById('clock').innerHTML =  h + "<br>" + m;
        setTimeout(startTime, 1000);
      }

      function checkTime(i) {
        if (i < 10) {i = "0" + i};
        return i;
      }

      function httpGet(theUrl) {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
        xmlHttp.send( null );
        return xmlHttp.responseText;
      }

    </script>

  </body>

</html>
