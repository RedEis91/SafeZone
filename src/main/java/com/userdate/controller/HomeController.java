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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriUtils;
import com.userdate.model.Location;

import javax.net.ssl.SSLException;
import java.io.*;
import java.net.*;
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
                "Please fill in the form below to register!");
    }

    //action that gets called>
    @RequestMapping("/formhandler")
        public ModelAndView formhandler(
                @RequestParam("firstname") String firstName,
                @RequestParam("lastname") String lastName,
                @RequestParam("phonenum") long phoneNum,
                @RequestParam("gender") String gender,
                @RequestParam("birthday") String birthDay,
                @RequestParam("email") String email
    )
        {
            boolean result = DAO.addUser(firstName,lastName,phoneNum,gender,birthDay,email);

            if (result == false) {
                //still have to write this view
                return new ModelAndView("error", "errmsg", "User add failed");
            }

            ArrayList<Resources> resourceList = DAO.getResourceList();

            //TODO: make error.jsp
            if (resourceList == null) {
                return new ModelAndView("error", "errmsg", "No more resources - null");
            }

            ModelAndView mv = new ModelAndView("resourceview");
            mv.addObject("firstname", firstName);
            mv.addObject("rList", resourceList);

            return mv;
        }

//        @RequestMapping("/department")
//        public ModelAndView department () {
//            try {
//                //java object that is going to talk across the internet for us.
//                // This HttpClient will make requests from the other server
//                HttpClient http = HttpClientBuilder.create().build();
//                //HttpHost holds connection info
//                HttpHost host = new HttpHost("transit.land", 443, "https");
//
//                //HttpGet will actually retrieves the information from the specific URI
//                HttpGet getPage = new HttpGet("/api/v1/operators/o-dpsc-detroitdepartmentoftransportation");
//                //this actually returns a JSON object
//
//                //actually making the HttpGet happen and pulling in the response
//                HttpResponse resp = http.execute(host, getPage);
//                //response has status code within it to tell us success, failure, 505, etc
//
//                //get actual content (JSON string) and turn it into object
//                //"entity" is the meat of the response
//                String jsonString = EntityUtils.toString(resp.getEntity());
//                //turn the string into an actual JSON object
//                JSONObject json = new JSONObject(jsonString);
//                String department = json.get("name").toString();
//
//                //get the response code and some info from JSON
//                int status = resp.getStatusLine().getStatusCode();
//
//
//                System.out.println(status);
//                //create JSON data
////                JSONString department = json.get("name");
//
//
//                //put into web application (ModelAndView)
//                ModelAndView moo = new ModelAndView("department");
//                moo.addObject("department", department);
//                moo.addObject("title", "Department");
//
//                return moo;
//            }
//            catch (Exception x) {
//                x.printStackTrace();
//            }
//
//            //cue to read the log during debugging process, make the null into a user friendly message
//        return null;
//        }

    @RequestMapping("/route")
    public ModelAndView route () {
        try {

            HttpClient http = HttpClientBuilder.create().build();

//            String myURL = "https://valhalla.mapzen.com/route?json={%22locations%22:[{%22lat%22:42.331674,%22lon%22:-83.052636,%22type%22:%22break%22,%22street%22:%22Cass%22},{%22lat%22:42.375675,%22lon%22:-83.07691,%22type%22:%22break%22,%22street%22:%22Woodward%22}],%22costing%22:%22multimodal%22,%22costing_options%22:{%22transit%22:{%22use_bus%22:%221.0%22,%22use_transfers%22:%220.001%22},%22pedestrian%22:{%22walking_speed%22:%224.1%22}}}&api_key=mapzen-HeGbynW";
//
//                URL url = new URL(myURL);
//                String nullFragment = null;
//                URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), nullFragment);
//                System.out.println("URI " + uri.toString() + " is OK");


            String startURI = "https://valhalla.mapzen.com/route?json={\"locations\":[{\"lat\":42.331674,\"lon\":-83.052636,\"type\":\"break\",\"street\":\"Cass\"},{\"lat\":42.375675,\"lon\":-83.07691,\"type\":\"break\",\"street\":\"Woodward\"}],\"costing\":\"multimodal\",\"costing_options\":{\"transit\":{\"use_bus\":\"1.0\",\"use_transfers\":\"0.001\"},\"pedestrian\":{\"walking_speed\":\"4.1\"}}}&api_key=mapzen-HeGbynW";
            String uri = UriUtils.encodeQuery(startURI, "UTF-8");

            HttpGet getPage = new HttpGet(uri);

            //this actually returns a JSON object
            //actually making the HttpGet happen and pulling in the response
            HttpResponse resp = http.execute(getPage);
            //response has status code within it to tell us success, failure, 505, etc

            //get actual content (JSON string) and turn it into object
            //"entity" is the meat of the response
            String jsonString = EntityUtils.toString(resp.getEntity());
            //turn the string into an actual JSON object
            JSONObject json = new JSONObject(jsonString);
            JSONArray maneuvers = json.getJSONObject("trip").getJSONArray("legs").getJSONObject(0).getJSONArray("maneuvers");
            ArrayList<String> instructions = new ArrayList<String>();
            for (int i = 0; i < maneuvers.length(); i++) {
                String instruction = maneuvers.getJSONObject(i).getString("verbal_pre_transition_instruction");
                instructions.add(instruction);
            }
            //get the response code and some info from JSON
            int status = resp.getStatusLine().getStatusCode();

            System.out.println(status);
            //create JSON data
//                JSONString department = json.get("name");


            //put into web application (ModelAndView)
            ModelAndView moo = new ModelAndView("route");
//            moo.addObject("instructions", instructions);

            moo.addObject("status", status);
            moo.addObject("instructions", instructions);
            return moo;
        }
        catch (JSONException x) {
            x.printStackTrace();
            System.out.println("JSON!!!");
        } catch (Exception x) {
            x.printStackTrace();
        }

        //cue to read the log during debugging process, make the null into a user friendly message
        return null;
    }

    }
