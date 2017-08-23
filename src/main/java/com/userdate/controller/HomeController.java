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

import javax.net.ssl.SSLException;
import java.io.*;
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

        @RequestMapping("/department")
        public ModelAndView department () {
            try {
                //java object that is going to talk across the internet for us.
                // This HttpClient will make requests from the other server
                HttpClient http = HttpClientBuilder.create().build();
                //HttpHost holds connection info
                HttpHost host = new HttpHost("transit.land", 443, "https");

                //HttpGet will actually retrieves the information from the specific URI
                HttpGet getPage = new HttpGet("/api/v1/operators/o-dpsc-detroitdepartmentoftransportation");
                //this actually returns a JSON object

                //actually making the HttpGet happen and pulling in the response
                HttpResponse resp = http.execute(host, getPage);
                //response has status code within it to tell us success, failure, 505, etc

                //get actual content (JSON string) and turn it into object
                //"entity" is the meat of the response
                String jsonString = EntityUtils.toString(resp.getEntity());
                //turn the string into an actual JSON object
                JSONObject json = new JSONObject(jsonString);
                String department = json.get("name").toString();

                //get the response code and some info from JSON
                int status = resp.getStatusLine().getStatusCode();


                System.out.println(status);
                //create JSON data
//                JSONString department = json.get("name");


                //put into web application (ModelAndView)
                ModelAndView moo = new ModelAndView("department");
                moo.addObject("department", department);
                moo.addObject("title", "Department");

                return moo;
            }
            catch (Exception x) {
                x.printStackTrace();
            }

            //cue to read the log during debugging process, make the null into a user friendly message
        return null;
        }

    }
