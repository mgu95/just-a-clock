<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>

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
        right: 10px;
        height: 150px;
        width: 250px;
        background-color:red;
      }

    </style>

  </head>

  <body onload="initialization()">

    <div class="gadget-bar">
      <div class="weather" id="weather"></div>
    </div>

    <br><br><br><br><br><br><br><br>
    <div id="clock" class="clock"></div>

    <a href="/settings">USTAWIENIA</a>

    <script>

      <!--setInterval(weather, 50000);-->

      function initialization() {
        weather();
        clock();
      }

      <jsp:include page="apps/clock.jsp" />
      <jsp:include page="apps/weather.jsp" />

    </script>

  </body>

</html>
