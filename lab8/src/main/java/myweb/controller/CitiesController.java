package myweb.controller;

import myweb.domain.City;
import myweb.model.DBManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CitiesController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if (action.equals("getAll")) {
            response.setContentType("application/json");
            DBManager dbManager = new DBManager();
            ArrayList<City> cities = dbManager.getCities();
            JSONArray citiesJson = new JSONArray();
            cities.forEach(city -> {
                JSONObject cityJson = new JSONObject();
                cityJson.put("id", city.getId());
                cityJson.put("name", city.getName());
                citiesJson.add(cityJson);
            });
            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(citiesJson.toJSONString());
            out.flush();
        }

        if (action.equals("getNeighbours")) {
            response.setContentType("application/json");
            int cityID = Integer.parseInt(request.getParameter("cityId"));
            DBManager dbManager = new DBManager();
            ArrayList<City> cities = dbManager.getNeighboursCities(cityID);
            JSONArray citiesJson =  new JSONArray();
            cities.forEach(city -> {
                JSONObject cityJson = new JSONObject();
                cityJson.put("id", city.getId());
                cityJson.put("name", city.getName());
                citiesJson.add(cityJson);
            });
            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(citiesJson.toJSONString());
            out.flush();

        }
    }

}
