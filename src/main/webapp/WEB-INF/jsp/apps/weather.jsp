function weather() {
  if (httpGet("/weather/getCity") == "") {
    document.getElementById("weather").innerHTML = "<p>Aby korzystać z pogody podaj klucz i miasto.";
  } else {
    currentWeatherTab();
    setTimeout(10000);
    informationTab();
  }
}

function currentWeatherTab() {
  document.getElementById("weather").innerHTML = "" +
    "<table>" +
    "  <tr>" +
    "    <td><img src=\"" + httpGet("/weather/getCurrentWeatherConditionIcon") + "\"></td>" +
    "    <td>" + httpGet("/weather/getCurrentTemperature") + "</td>" +
    "    <td>" + httpGet("/weather/getCurrentWeatherCondition") + "</td>" +
    "  </tr>" +
    "</table>" +
    httpGet("/weather/getCity");
}

function informationTab() {
  document.getElementById("weather").innerHTML = "" +
    "<p>Ostatnia aktualizacja danych:<br>" + httpGet("/weather/getUpdateTime") + "</p>" +
    "<p>Źródło danych:<br>" + httpGet("/weather/getDataSource") + "</p>";
}

function httpGet(theUrl) {
  var xmlHttp = new XMLHttpRequest();
  xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
  xmlHttp.send( null );
  return xmlHttp.responseText;
}