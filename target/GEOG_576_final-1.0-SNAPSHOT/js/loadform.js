function queryRace(event) {
    console.log("starting query function")
    event.preventDefault(); // stop form from submitting normally

    var a = $("#query_race_form").serializeArray();
    a.push({ name: "tab_id", value: "1" });

    a = a.filter(function(item){return item.value != '';});

    console.log(JSON.stringify(a));
    $.ajax({
        url: 'HttpServlet',
        type: 'POST',
        data: a,
        success: function(races) {
            console.log("starting successful query")
            mapInitialization(races);
        },
        error: function(xhr, status, error) {
            alert("Status: " + status + "\nError: " + error);
        }
    });
}

function createRace(event){
    console.log("starting create race function")

    event.preventDefault(); // stop form from submitting normally

    var a = $("#create_race_form").serializeArray();
    a.push({ name: "tab_id", value: "0" });

    a = a.filter(function(item){return item.value != '';});
    console.log(a.toString())
    console.log("starting ajax call")
    $.ajax({
        url: 'HttpServlet',
        type: 'POST',
        data: a,
        success: function(races) {

            //popup window saying there was a successful call
            window.alert("Race successfully submitted")
            document.getElementById("create_race_form").reset();
            showAllRaces();

        },
        error: function(xhr, status, error) {
            alert("Status: " + status + "\nError: " + error);
        }
    });
}

$("#query_race_form").on("submit",queryRace);
$("#create_race_form").on("submit",createRace);