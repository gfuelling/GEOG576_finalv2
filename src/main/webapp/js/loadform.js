var place;


//we don't need this anymore, correct?
// function onSelectSurfaceType(ele){
//     console.log("Starting OnSelectSurfaceType")
//     var form = $(ele).parent().parent();
//     var label = $(form).find(".additional_msg");
//     var select = $(form).find(".additional_msg_select");
//
//     switch (ele.value) {
//         case "Track":
//         case "Road":
//             label.text("Surface Type:");
//             select.find('option').remove();
//             select.append($("<option></option>")
//                 .attr("value","")
//                 .text("Choose the resource type"));
//             selectValues = ['water', 'food', 'money', 'medicine', 'cloth',
//                 'rescue/volunteer'];
//             $.each(selectValues, function(index,value) {
//                 select.append($("<option></option>")
//                     .attr("value",value)
//                     .text(value));
//             });
//             break;
//         case "Trail":
//             label.text("Trail Surface Type:");
//             select.find('option').remove();
//             select.append($("<option></option>")
//                 .attr("value","")
//                 .text("Choose the damage type"));
//             selectValues = ['pollution', 'building damage', 'road damage', 'casualty',
//                 'other'];
//             $.each(selectValues, function(index,value) {
//                 select.append($("<option></option>")
//                     .attr("value",value)
//                     .text(value));
//             });
//             break;
//         default:
//             $(form).find(".additional_msg_div").css("visibility", "hidden");
//             return;
//     }
//     $(form).find(".additional_msg_div").css("visibility", "visible");
// }

function queryRace(event) {
    console.log("starting query function")
    event.preventDefault(); // stop form from submitting normally

    var a = $("#query_race_form").serializeArray();
    a.push({ name: "tab_id", value: "1" });
    console.log(a +"this is variable a")
    a = a.filter(function(item){return item.value != '';});
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
    //adding location
    a = a.filter(function(item){return item.value != '';});
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