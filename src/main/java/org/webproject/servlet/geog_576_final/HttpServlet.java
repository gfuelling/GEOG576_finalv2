package org.webproject.servlet.geog_576_final;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class HttpServlet
 */
@WebServlet("/HttpServlet")
public class HttpServlet extends javax.servlet.http.HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public HttpServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String tab_id = request.getParameter("tab_id");

        // create a report
        if (tab_id.equals("0")) {
            System.out.println("A report is submitted!");
            try {
                createReport(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // query reports
        else if (tab_id.equals("1")) {
            try {
                queryReport(request, response);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    //changes here to match race table
    private void createReport(HttpServletRequest request, HttpServletResponse
            response) throws SQLException, IOException {
        DBUtility dbutil = new DBUtility();
        String sql;

        // Create race
        String race_name = request.getParameter("race_name");
        String surface = request.getParameter("surface");
        String city = request.getParameter("city");
        String lon = request.getParameter("longitude");
        String lat = request.getParameter("latitude");
        String state = request.getParameter("state");
        String distance = request.getParameter("distance");
        String elevation = request.getParameter("elevation");
        String race_company = request.getParameter("race_company");


        sql = "insert into races (race_name, surface, city, state, distance, elevation, race_company, geom)" +
                " values ('" + race_name + "','" + surface + "','" + city + "','" + state + "','" + distance + "','" + elevation + "','" + race_company + "', ST_GeomFromText('POINT(" + lon + " " + lat + ")', 4326))";

        dbutil.modifyDB(sql);


        // response that the report submission is successful
        JSONObject data = new JSONObject();
        try {
            data.put("status", "success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        response.getWriter().write(data.toString());

    }

    public void queryReport(HttpServletRequest request, HttpServletResponse
            response) throws JSONException, SQLException, IOException {
        JSONArray list = new JSONArray();

        String surface_type = request.getParameter("surface");

        String race_distance = request.getParameter("distance");

        System.out.println(surface_type);

        System.out.println(race_distance);

        String sql;
        if (surface_type == null && race_distance == null){
            System.out.println("starting if statement for null");
            sql = "select race_name, surface, elevation, city, state, distance, race_company, ST_X(geom) as " +
                    "longitude, ST_Y(geom) as latitude, elevation from races";
            queryReportHelper(sql,list,surface_type,race_distance);
        } else if (surface_type != null && race_distance ==null){
            sql = "select race_name, elevation, surface, city, state, distance, ST_X(geom) as " +
                    "longitude, ST_Y(geom) as latitude, elevation from races where surface = ";

            queryReportHelper(sql,list, surface_type, race_distance);
        } else if (race_distance != null && surface_type == null){
            sql = "select race_name, elevation, surface, city, state, distance, ST_X(geom) as " +
                    "longitude, ST_Y(geom) as latitude, elevation from races where distance = ";
            queryReportHelper(sql,list, surface_type, race_distance);
        } else if (race_distance != null && surface_type != null){
            sql = "select race_name, elevation, surface, city, state, distance, ST_X(geom) as " +
                    "longitude, ST_Y(geom) as latitude, elevation from races where ";
            queryReportHelper(sql,list, surface_type, race_distance);
        }


        response.getWriter().write(list.toString());
    }

    public static void queryReportHelper(String sql, JSONArray list,String surface_type, String race_distance) throws SQLException {
        DBUtility dbutil = new DBUtility();
        if (race_distance != null && surface_type != null) {
            sql += "surface = " + "'" + surface_type + "'" +" AND distance =" + "'" + race_distance + "'";
            System.out.println(sql);
        }
        else if (surface_type != null) {
            sql += "'" + surface_type + "'";
            System.out.println(sql);
        }

        else if (race_distance != null) {
            sql += "'" + race_distance + "'";
            System.out.println(sql);
        }

        ResultSet res = dbutil.queryDB(sql);
        while (res.next()) {
            // add to response
            HashMap<String, String> m = new HashMap<String,String>();
            m.put("race_name", res.getString("race_name"));
            m.put("surface", res.getString("surface"));
            m.put("city", res.getString("city"));
            m.put("state", res.getString("state"));
            m.put("distance", res.getString("distance"));
            m.put("longitude", res.getString("longitude"));
            m.put("latitude", res.getString("latitude"));
            m.put("elevation", res.getString("elevation"));
            m.put("race_company", res.getString("race_company"));
            list.put(m);
        }
    }

    public void main() throws JSONException {
    }
}
