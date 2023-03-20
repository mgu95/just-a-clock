<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>

<html>

  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>

  <head>
    <title>Just a clock</title>
    <style><jsp:include page="styles/homeStyle.jsp" /></style>
  </head>

  <body onload="initialization()">

    <div class="gadget-bar">
      <div class="weather" id="weather">
        <div id="currentWeather"></div>
        <div id="hourlyForecast">
          <canvas id="myChart" style="width:100%;max-width:250px;height:150px\"></canvas>
        </div>
        <div id="dailyForecast"></div>
        <div id="airQuality"></div>
        <div id="wind"></div>
        <div id="sun"></div>
        <div id="weatherSource"></div>
      </div>
    </div>

    <br><br><br><br><br><br><br><br>
    <div id="clock" class="clock"></div>

    <a href="/settings">USTAWIENIA</a>

    <script>

      setInterval(weather, 120000);

      function initialization() {
        weather();
        clock();
      }

      <jsp:include page="apps/clock.jsp" />
      <jsp:include page="apps/weather.jsp" />

    </script>

  </body>

</html>
