let currentCity = null;
let cities = [];
function displayCities() {
    if ($(document).ready()) {
        currentCity = null;
        cities = [];
        $('#currentCity').text("");
        $('#destButton').css({"display":"none"});
        $('#backButton').css({"display":"none"});
        $('#final_route').empty();
        $.getJSON(
            "CitiesController",
            {action: "getAll"},
            function(cities) {
                $("#list_of_stations").empty();
                for(let i = 0; i < cities.length; i++) {
                    let city = cities[i];
                    $("#list_of_stations").append("<li>" + city.id + " " + city.name + " <button onclick= 'chooseCurrentStation(event)'>Choose current station</button>" + "</li>");
                }
            }
        )
    }
}

function displayNeighbourCities(cityId) {
    if ($(document).ready()) {
        $.getJSON(
            "CitiesController",
            {action: "getNeighbours", cityId: cityId},
            function (cities) {
                $("#list_of_stations").empty();
                for(let i = 0; i < cities.length; i++) {
                    let city = cities[i];
                    $("#list_of_stations").append("<li>" + city.id + " " + city.name + " <button onclick= 'chooseCurrentStation(event)'>Choose current station</button>" + "</li>");
                }
            }
        )
    }

}

function chooseCurrentStation(event){

    let text  = $(event.target.parentNode).text();
    text = text.split(" ");
    let cityId = text[0];
    let cityName = text[1];
    currentCity = {
        id: cityId,
        name: cityName
    };
    $('#currentCity').text(cityName);
    $('#backButton').css({"display": "block"});
    $('#destButton').css({"display":"block"});
    displayNeighbourCities(cityId);
    cities.push(currentCity);
}

function back() {
    cities.pop();
    currentCity = cities[cities.length - 1];
    console.log(currentCity);
    $('#currentCity').text(currentCity.name);
    displayNeighbourCities(currentCity.id);
}

function showRoute() {
    $('#final_route').empty();
    $('#final_route').css({"display":"block"});
    $('#final_route').append(currentCity);
    for(let i = 0; i < cities.length; i++) {
        $('#final_route').append("<li> " + cities[i].name + "</li>")
    }

}


