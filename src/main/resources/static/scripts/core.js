function getCountriesRequest() {
    var DEFAULT_COUNTRY = "Russia";
    $.get("countries", function (response, status) {
        if (status === "success") {
            $.each(response, function (i, country) {
                $("#countrySelect").append($("<option>", {
                    value: country,
                    text: country
                }));
            });
            if (response.includes(DEFAULT_COUNTRY)) {
                $("#countrySelect").val(DEFAULT_COUNTRY);
                getAirportsRequest(DEFAULT_COUNTRY);
            }
        } else {
            alert("Error getting countries list");
        }
    });
}

function getAirportsRequest(country) {
    $.get("airports", {country: country}, function (response, status) {
        reloadTable(response);
    });
}

function reloadTable(airports) {
    var table = $("#airportsTable");
    var tbody = table.find("tbody");
    tbody.find("tr").remove();
    airports.forEach(function (airport) {
        var iata = airport.iata === null ? "" : airport.iata;
        var icao = airport.icao === null ? "" : airport.icao;
        var timezoneOffset = airport.timezoneOffset === null ? "" : airport.timezoneOffset;
        var dst = airport.dst === null ? "" : airport.dst;
        var timezoneName = airport.timezoneName === null ? "" : airport.timezoneName;
        tbody.append("<tr><td>" + airport.id + "</td><td>" + airport.name + "</td><td>" + airport.city + "</td><td>" +
            airport.country + "</td><td>" + iata + "</td><td>" + icao + "</td><td>" + airport.latitude +
            "</td><td>" + airport.longitude + "</td><td>" + airport.altitude + "</td><td>" + timezoneOffset +
            "</td><td>" + dst + "</td><td>" + timezoneName + "</td><td>" + airport.type + "</td><td>" +
            airport.source + "</td></tr>");
    });
}

$("#countrySelect").on("change", function () {
    getAirportsRequest(this.value);
});

$("#noFilterCheckbox").on("click", function () {
    var countrySelect = $("#countrySelect");
    if ($("#noFilterCheckbox").is(':checked')) {
        countrySelect.prop("disabled", true);
        getAirportsRequest();
    } else {
        countrySelect.prop("disabled", false);
        getAirportsRequest(countrySelect.val());
    }
});

getCountriesRequest();
