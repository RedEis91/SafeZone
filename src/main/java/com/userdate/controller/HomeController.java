package com.userdate.controller;

import com.userdate.model.DAO;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class HomeController {

    @RequestMapping("/")
    public ModelAndView safeZone()
    {

        ModelAndView mv = new
                ModelAndView("safezone");
        mv.addObject("message","You are NOT alone");
        mv.addObject("title", "Safe Zone");

        return mv;
    }

    @RequestMapping("/resourcelist")
    public ModelAndView viewresourceList () {
        ArrayList<Resources> resourceList = DAO.getResourceList();


        //TODO: make error.jsp
        if (resourceList == null) {
            return new ModelAndView("error", "errmsg", "No more resources - null");
        }

        return new ModelAndView("resourceview","rList", resourceList);

    }


    @RequestMapping("/register")
    public ModelAndView register () {
        return new ModelAndView("register", "inst",
                "Please enter info: ");
    }

    //action that gets called>
    @RequestMapping("/formhandler")
        public ModelAndView formhandler(
                @RequestParam("firstname") String firstname)
        {
            ArrayList<Resources> resourceList = DAO.getResourceList();


            //TODO: make error.jsp
            if (resourceList == null) {
                return new ModelAndView("error", "errmsg", "No more resources - null");
            }

            ModelAndView mv = new ModelAndView("resourceview");
            mv.addObject("firstname", firstname);
            mv.addObject("rList", resourceList);

            return mv;
        }

        @RequestMapping("/weather")
        public ModelAndView weather () {
            try {
                //java object that is going to talk across the internet for us.
                // This HttpClient will make requests from the other server
                HttpClient http = HttpClientBuilder.create().build();
                //HttpHost holds connection info
                HttpHost host = new HttpHost("forecast.weather.gov", 80, "http");

                //HttpGet will actually retrieves the information from the specific URI
                HttpGet getPage = new HttpGet("/MapClick.php?lat=42.331427&lon=-83.045754&FcstType=json");
                //this actually returns a JSON object

                //actually making the HttpGet happen and pulling in the response
                HttpResponse resp = http.execute(host, getPage);
                //response has status code within it to tell us success, failure, 505, etc

                //get actual content (JSON string) and turn it into object
                //"entity" is the meat of the response
                String jsonString = EntityUtils.toString(resp.getEntity());
                //turn the string into an actual JSON object
                JSONObject json = new JSONObject(jsonString);


                //get the response code and some info from JSON
                int status = resp.getStatusLine().getStatusCode();
                String prodCenter = json.get("productionCenter").toString();

                //create JSON data
                JSONArray days = json.getJSONObject("time").getJSONArray("startPeriodName");
                JSONArray temps = json.getJSONObject("data").getJSONArray("temperature");


                //put into web application (ModelAndView)
                ModelAndView mv = new ModelAndView("weather");
                mv.addObject("status", status);
                mv.addObject("prodCenter", prodCenter);
                mv.addObject("day1", days.getString(0));
                mv.addObject("day2", days.getString(1));
                mv.addObject("day3", days.getString(2));
                mv.addObject("day4", days.getString(3));
                mv.addObject("day5", days.getString(4));

                mv.addObject("temp1", temps.getString(0));
                mv.addObject("temp2", temps.getString(1));
                mv.addObject("temp3", temps.getString(2));
                mv.addObject("temp4", temps.getString(3));
                mv.addObject("temp5", temps.getString(4));

                return mv;
            }
            catch (IOException e)
                {
                    e.printStackTrace();
                }

            catch (JSONException e)
                {
                    e.printStackTrace();
                }
        //cue to read the log during debugging process, make the null into a user friendly message
        return null;
        }

    }
