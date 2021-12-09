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
                        <div><label>Race Name:&nbsp</label><input placeholder="race name" name="race_name"></div>
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
                        <div><label>State:</label><input placeholder="State" name="state"></div>
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
                            <select onchange="onSelectSurfaceType(this)" name="surface">
                                <option value="">Choose the surface type</option>
                                <option value="Track">Track</option>
                                <option value="Street">Road</option>
                                <option value="Trail">Trail</option>
                            </select>
                        </div>
                        <div><label>Distance:</label><input placeholder="Number of Miles" name = "distance"></div>
<%--                        <div><label>City:</label><input placeholder="Nearest City" name = "city"></div>--%>
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
