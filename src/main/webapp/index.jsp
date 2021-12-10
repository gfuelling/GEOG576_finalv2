<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Final Project</title>

    <!-- Custom styles -->
    <link rel="stylesheet" href="css/style.css">

    <!-- jQuery -->
    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <!-- Google Maps -->
    <script src="https://maps.googleapis.com/maps/api/js?&key=AIzaSyCqOT5Skhiltm2TrMrtHWO7enL6tt2Vf-Y&libraries=places,visualization"></script>

</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <a class="navbar-brand">US Race Portal</a>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="sidebar col-xs-3">

            <!-- Tab Navis-->
            <ul class="nav nav-tabs">
                <li class="active"><a href="#add_race" data-toggle="tab">Add Race</a></li>
                <li><a href="#query_race" data-toggle="tab">Query Race</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content ">
                <!-- Add Race Tab Panel -->
                <div class="tab-pane active" id="create_race">
                    <form id = "create_race_form">
                        <div><label>Race Name:&nbsp</label><input placeholder="Race Name" name="race_name"></div>
                        <div><label>Surface Type:</label>
                            <select name="surface">
                                <option value="">Choose the surface type</option>
                                <option value="Track">Track</option>
                                <option value="Street">Road</option>
                                <option value="Trail">Trail</option>
                            </select>
                        </div>
                        <div><label>Distance:</label><input placeholder="Number of Miles" name="distance"></div>
                        <div><label>Elevation Gain:</label><input placeholder="Elevation Gain" name="elevation"></div>
                        <div><label>City:</label><input placeholder="City" name="city"></div>
                        <div><label>State:</label>
                            <select name="state">
                                <option value="AL">AL</option>
                                <option value="AK">AK</option>
                                <option value="AR">AR</option>
                                <option value="AZ">AZ</option>
                                <option value="CA">CA</option>
                                <option value="CO">CO</option>
                                <option value="CT">CT</option>
                                <option value="DC">DC</option>
                                <option value="DE">DE</option>
                                <option value="FL">FL</option>
                                <option value="GA">GA</option>
                                <option value="HI">HI</option>
                                <option value="IA">IA</option>
                                <option value="ID">ID</option>
                                <option value="IL">IL</option>
                                <option value="IN">IN</option>
                                <option value="KS">KS</option>
                                <option value="KY">KY</option>
                                <option value="LA">LA</option>
                                <option value="MA">MA</option>
                                <option value="MD">MD</option>
                                <option value="ME">ME</option>
                                <option value="MI">MI</option>
                                <option value="MN">MN</option>
                                <option value="MO">MO</option>
                                <option value="MS">MS</option>
                                <option value="MT">MT</option>
                                <option value="NC">NC</option>
                                <option value="NE">NE</option>
                                <option value="NH">NH</option>
                                <option value="NJ">NJ</option>
                                <option value="NM">NM</option>
                                <option value="NV">NV</option>
                                <option value="NY">NY</option>
                                <option value="ND">ND</option>
                                <option value="OH">OH</option>
                                <option value="OK">OK</option>
                                <option value="OR">OR</option>
                                <option value="PA">PA</option>
                                <option value="RI">RI</option>
                                <option value="SC">SC</option>
                                <option value="SD">SD</option>
                                <option value="TN">TN</option>
                                <option value="TX">TX</option>
                                <option value="UT">UT</option>
                                <option value="VT">VT</option>
                                <option value="VA">VA</option>
                                <option value="WA">WA</option>
                                <option value="WI">WI</option>
                                <option value="WV">WV</option>
                                <option value="WY">WY</option>
                            </select>
                        </div>
                        <div><label>Race Company:</label><input placeholder="Race Company" name="race_company"></div>
                        <div><label>Latitude:</label><input placeholder="Latitude" name ="latitude"></div>
                        <div><label>Longitude:</label><input placeholder="Longitude" name ="longitude"></div>
                        <button type="submit" class="btn btn-default" id="report_submit_btn">
                            <span class="glyphicon glyphicon-star"></span> Submit
                        </button>
                    </form>
                </div>

                <!-- Query Race Tab Panel -->
                <div class="tab-pane" id="query_race">
                    <form id = "query_race_form">
                        <div><label>Surface Type:</label>
                            <select name="surface">
                                <option value="">Choose the surface type</option>
                                <option value="Track">Track</option>
                                <option value="Street">Road</option>
                                <option value="Trail">Trail</option>
                            </select>
                        </div>
                        <div><label>Distance:</label><input placeholder="Number of Miles" name = "distance"></div>
<%--                        <div><label>City:</label><input placeholder="Nearest City" name = "city"></div>--%>
                        <div><label>Race Company:</label><input placeholder="Race Company" name = "race_company"></div>
                        <button type="submit" class="btn btn-default">
                            <span class="glyphicon glyphicon-star"></span> Submit the query
                        </button>
                    </form>
                </div>
            </div>
        </div>

        <div id="map-canvas" class="col-xs-9"></div>

    </div>
</div>

<script src="js/loadform.js"></script>
<script src="js/loadmap.js"></script>
</body> </html>
