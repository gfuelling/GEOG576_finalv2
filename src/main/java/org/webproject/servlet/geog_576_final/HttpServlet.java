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


        sql = "insert into races (race_name, surface, city, state, distance, elevation, geom)" +
                " values ('" + race_name + "','" + surface + "','" + city + "','" + state + "','" + distance + "','" + elevation + "', ST_GeomFromText('POINT(" + lon + " " + lat + ")', 4326))";

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
        String sql = "select race_name, surface, city, state, distance, ST_X(geom) as " +
                "longitude, ST_Y(geom) as latitude, elevation from races";
        queryReportHelper(sql,list);

        response.getWriter().write(list.toString());
    }

    public static void queryReportHelper(String sql, JSONArray list) throws SQLException {
        DBUtility dbutil = new DBUtility();

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
            list.put(m);
        }
    }

    public void main() throws JSONException {
    }
}
