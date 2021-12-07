var map;
var place;
var autocomplete;
var infowindow = new google.maps.InfoWindow();

function initialization() {
    showAllRaces();
    //initAutocomplete();
}

function showAllRaces() {
    $.ajax({
        url: 'HttpServlet',
        type: 'POST',
        data: { "tab_id": "1"},
        success: function(races) {
            mapInitialization(races);
        },
        error: function(xhr, status, error) {
            alert("An AJAX error occured: " + status + "\nError: " + error);
        }
    });
}

function mapInitialization(races) {
    console.log("Date is:" + Date.now())

    var mapOptions = {
        mapTypeId : google.maps.MapTypeId.ROADMAP, // Set the type of Map
    };

    // Render the map within the empty div
    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

    var bounds = new google.maps.LatLngBounds ();

    $.each(races, function(i, e) {
        var long = Number(e['longitude']);
        var lat = Number(e['latitude']);
        var latlng = new google.maps.LatLng(lat, long);

        bounds.extend(latlng);

        // Create the infoWindow content
        var contentStr = '<h4>Race Details</h4><hr>';
        contentStr += '<p><b>' + 'Race Name' + ':</b>&nbsp' + e['race_name'] +'</p>';
        contentStr += '<p><b>' + 'Surface Type' + ':</b>&nbsp' + e['surface'] +
            '</p>';
        contentStr += '<p><b>' + 'Distance' + ':</b>&nbsp' + e['distance'] +
            '</p>';

//         //Start of custom marker code
//         if (e['surface'] == 'Track') {
//             var url = "img/track.png";
//         }
//
//         if (e['surface'] == 'Street') {
//             var url = "img/bandage.png";
//         }
//
//         if (e['surface'] == 'Trail') {
//             var url = "img/solidarity.png";
//         }
//
//         var icon = {
//             url: url,
//             scaledSize: new google.maps.Size(40, 40), // scaled size
//             origin: new google.maps.Point(0,0), // origin
//             anchor: new google.maps.Point(0, 0) // anchor
//         };
//
// // Create the marker
//         var marker = new google.maps.Marker({ // Set the marker
//             position: latlng, // Position marker to coordinates
//             map: map, // assign the market to our map variable
//             customInfo: contentStr,
//             icon: icon
//         });

        //this is the original  default marker code from the lab
        // Create the marker
        var marker = new google.maps.Marker({ // Set the marker
            position : latlng, // Position marker to coordinates
            map : map, // assign the market to our map variable
            customInfo: contentStr,
        });
//end of default marker

        // Add a Click Listener to the marker
        google.maps.event.addListener(marker, 'click', function() {
            // use 'customInfo' to customize infoWindow
            infowindow.setContent(marker['customInfo']);
            infowindow.open(map, marker); // Open InfoWindow
        });

    });

    map.fitBounds (bounds);

}

// function initAutocomplete() {
//     // Create the autocomplete object
//     autocomplete = new google.maps.places.Autocomplete(document
//         .getElementById('autocomplete'));
//
//     // When the user selects an address from the dropdown, show the place selected
//     autocomplete.addListener('place_changed', onPlaceChanged);
// }
//
// function onPlaceChanged() {
//     const marker = new google.maps.Marker({
//         map,
//         anchorPoint: new google.maps.Point(0, -29),
//     });
//     place = autocomplete.getPlace();
//     if (!place.geometry || !place.geometry.location) {
//         // User entered the name of a Place that was not suggested and
//         // pressed the Enter key, or the Place Details request failed.
//         window.alert("No details available for input: '" + place.name + "'");
//         return;
//     }
//
//     // If the place has a geometry, then present it on a map.
//     if (place.geometry.viewport) {
//         marker.setVisible(false);
//         map.fitBounds(place.geometry.viewport);
//     } else {
//         map.setCenter(place.geometry.location);
//         map.setZoom(17);
//     }
//     marker.setPosition(place.geometry.location);
//     marker.setVisible(true);
// }

//Execute our 'initialization' function once the page has loaded.
google.maps.event.addDomListener(window, 'load', initialization);