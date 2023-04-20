function weather() {
  if (httpGet("/weather/getCity") == "") {
    document.getElementById("weather").innerHTML = "<p>Aby korzystać z pogody podaj klucz i miasto.</p>";
  } else {
    refreshTabs();

    const currentWeather = document.getElementById("currentWeather");
    const hourlyForecast = document.getElementById("hourlyForecast");
    const dailyForecast = document.getElementById("dailyForecast");
    const airQuality = document.getElementById("airQuality");
    const wind = document.getElementById("wind");
    const sun = document.getElementById("sun");
    const weatherSource = document.getElementById("weatherSource");

    hourlyForecast.style.display = "none";
    dailyForecast.style.display = "none";
    airQuality.style.display = "none";
    wind.style.display = "none";
    sun.style.display = "none";
    weatherSource.style.display = "none";

    setTimeout(() => {
      currentWeather.style.display = "none";
      hourlyForecast.style.display = "inline";
    }, 10000);
    setTimeout(() => {
      hourlyForecast.style.display = "none";
      dailyForecast.style.display = "inline";
    }, 200000000);
    setTimeout(() => {
      dailyForecast.style.display = "none";
      airQuality.style.display = "inline";
    }, 30000);
    setTimeout(() => {
      airQuality.style.display = "none";
      wind.style.display = "inline";
    }, 40000);
    setTimeout(() => {
      wind.style.display = "none";
      sun.style.display = "inline";
    }, 50000);
    setTimeout(() => {
      sun.style.display = "none";
      weatherSource.style.display = "inline";
    }, 60000);
    setTimeout(() => {
      weatherSource.style.display = "none";
      currentWeather.style.display = "inline";
    }, 65000);
  }
}

function refreshTabs() {
  refreshCurrentWeatherTab();
  refreshHourlyForecastTab(myChart);
  refreshDailyForecast();
  refreshAirQuality();
  refreshWind();
  refreshSun();
  refreshInformationTab();
}

function refreshCurrentWeatherTab() {
  const date = new Date();
  let day = date.getDate();
  let month = date.getMonth() + 1;
  let year = date.getFullYear();
  let hour = date.getHours();
  document.getElementById("currentWeather").innerHTML = "" +
    "<table>" +
    "  <tr>" +
    "    <td><img src=\"" + httpGet("/weather/getWeatherIcon/day=" + day + "&month=" + month + "&year=" + year + "&hour=" + hour) + "\"></td>" +
    "    <td>" + httpGet("/weather/getTemperature?day=" + day + "&month=" + month + "&year=" + year + "&hour=" + hour) + "</td>" +
    "    <td>" + httpGet("/weather/getWeatherCondition/day=" + day + "&month=" + month + "&year=" + year + "&hour=" + hour) + "</td>" +
    "  </tr>" +
    "</table>" +
    httpGet("/weather/getCity");
}

var myChart = new Chart("myChart", {
  type: "line",
  data: {
    labels: ["1","2","3","4","5","6"],
    datasets: [{
      fill: false,
      lineTension: 0,
      backgroundColor: "rgba(0,0,255,1.0)",
      borderColor: "rgba(0,0,255,0.1)",
      data: [1,2,3,4,5,6]
    }]
  },
  options: {
    legend: {display: false},
    scales: {
      yAxes: [{ticks: {min: -5, max:16}}],
    }
  }
});

function getTemperature() {
  let day = date.getDate();
  let month = date.getMonth() + 1;
  let year = date.getFullYear();
  let hour = date.getHours();
  return httpGet("/weather/getTemperature?day=" + day + "&month=" + month + "&year=" + year + "&hour=" + hour);
}

function refreshHourlyForecastTab(chart) {
  const date = new Date();
  let day = date.getDate();
  let month = date.getMonth() + 1;
  let year = date.getFullYear();
  let hour = date.getHours();
  let hours = [hour, hour + 1, hour + 1, hour + 2, hour + 3, hour + 4, hour + 5];
  chart.data.labels = [hours[0], hours[1], hours[2], hours[3], hours[4], hours[5]];
  let temps = [httpGet("/weather/getTemperature?day=" + date.getDate() + "&month=" + month + "&year=" + year + "&hour=" + hours[0]),
    httpGet("/weather/getTemperature?day=" + day + "&month=" + month + "&year=" + year + "&hour=" + hours[1]),
    httpGet("/weather/getTemperature?day=" + day + "&month=" + month + "&year=" + year + "&hour=" + hours[2]),
    httpGet("/weather/getTemperature?day=" + day + "&month=" + month + "&year=" + year + "&hour=" + hours[3]),
    httpGet("/weather/getTemperature?day=" + day + "&month=" + month + "&year=" + year + "&hour=" + hours[4]),
    httpGet("/weather/getTemperature?day=" + day + "&month=" + month + "&year=" + year + "&hour=" + hours[5])];
  chart.data.datasets = [{fill: false,lineTension: 0,backgroundColor: "rgba(0,0,255,1.0)",borderColor: "rgba(0,0,255,0.1)",
    data: [temps[0], temps[1], temps[2], temps[3], temps[4], temps[5]]}];
  let minTemp = 99;
  let maxTemp = -99;
  for (let i = 0; i < temps.length; i++) {
    if  (temps[i] < minTemp) {minTemp = temps[i];}
    if  (temps[i] > maxTemp) {maxTemp = temps[i];}
  }
  chart.options = {legend: {display: false},scales: {yAxes: [{ticks: {min:-99, max:99}}],}};
  chart.update();
}







function refreshDailyForecast() {
  document.getElementById("dailyForecast").innerHTML = "<p>dailyForecast</p>";
}

function refreshAirQuality() {
  document.getElementById("airQuality").innerHTML = "<p>airQuality</p>";
}

function refreshWind() {
  document.getElementById("wind").innerHTML = "<p>wind</p>";
}

function refreshSun() {
  document.getElementById("sun").innerHTML = "<p>sun</p>";
}

function refreshInformationTab() {
  document.getElementById("weatherSource").innerHTML = "" +
    "<p>Ostatnia aktualizacja danych:<br>" + httpGet("/weather/getUpdateTime") + "</p>" +
    "<p>Źródło danych:<br>" + httpGet("/weather/getDataSource") + "</p>";
}

function httpGet(theUrl) {
  var xmlHttp = new XMLHttpRequest();
  xmlHttp.open( "GET", theUrl, false );
  xmlHttp.send( null );
  return xmlHttp.responseText;
}