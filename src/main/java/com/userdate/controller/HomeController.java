package com.userdate.controller;

import com.userdate.model.DAO;
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
import org.springframework.web.util.UriUtils;

import java.util.ArrayList;

@Controller
public class HomeController {
    @RequestMapping("/")
    public ModelAndView safeZone()
    {
        ModelAndView mv = new ModelAndView("safezone");
        mv.addObject("message","You are NOT alone");
        mv.addObject("title", "Safe Zone");
        return mv;
    }

    @RequestMapping("/resourceview")
    public ModelAndView resourceView () {
        ArrayList<Resource> resourceList = DAO.getResourceList();
        if (resourceList == null) {
            return new ModelAndView("error", "errmsg", "No resource list - null");
        }
       ModelAndView mv = new ModelAndView("resourceview");
       mv.addObject("rList",resourceList);
       return mv;
    }

    @RequestMapping("/resourceselector")
    public ModelAndView resourceSelector () {
       ModelAndView mv = new ModelAndView("resourceselector");
       return mv;
    }

    @RequestMapping("/foodResources")
    public ModelAndView foodResources () {
        ArrayList<Resource> foodResourceList = DAO.getFoodResourceList();
        if (foodResourceList == null){
            return new ModelAndView("error","errmsg","No food resource list - null");
        }
        ModelAndView mv = new ModelAndView("foodresources");
        mv.addObject("foodList",foodResourceList);
        return mv;
    }

    @RequestMapping("/shelterResources")
    public ModelAndView shelterResources () {
        ArrayList<Resource> shelterResourceList = DAO.getShelterResourceList();
        if (shelterResourceList == null){
            return new ModelAndView("error","errmsg","No shelter resource list - null");
        }
        ModelAndView mv = new ModelAndView("shelterresources");
        mv.addObject("shelterList",shelterResourceList);
        return mv;
    }

    @RequestMapping("/clothingResources")
    public ModelAndView clothingResources () {
        ArrayList<Resource> clothingResourceList = DAO.getClothingResourceList();
        if (clothingResourceList == null){
            return new ModelAndView("error","errmsg","No clothing resource list - null");
        }
        ModelAndView mv = new ModelAndView("clothingresources");
        mv.addObject("clothingList",clothingResourceList);
        return mv;
    }

    @RequestMapping("/counselingResources")
    public ModelAndView counselingResources () {
        ArrayList<Resource> counselingResourceList = DAO.getCounselingResourceList();
        if (counselingResourceList == null){
            return new ModelAndView("error","errmsg","No counseling resource list - null");
        }
        ModelAndView mv = new ModelAndView("counselingresources");
        mv.addObject("counselingList",counselingResourceList);
        return mv;
    }

    @RequestMapping("/healthcareResources")
    public ModelAndView healthcareResources () {
        ArrayList<Resource> healthcareResourceList = DAO.getHealthcareResourceList();
        if (healthcareResourceList == null){
            return new ModelAndView("error","errmsg","No healthcare resource list - null");
        }
        ModelAndView mv = new ModelAndView("healthcareresources");
        mv.addObject("healthcareList",healthcareResourceList);
        return mv;
    }

    @RequestMapping("/educationResources")
    public ModelAndView educationResources () {
        ArrayList<Resource> educationResourceList = DAO.getEducationResourceList();
        if (educationResourceList == null){
            return new ModelAndView("error","errmsg","No education resource list - null");
        }
        ModelAndView mv = new ModelAndView("educationresources");
        mv.addObject("educationList",educationResourceList);
        return mv;
    }

    @RequestMapping("/jobResources")
    public ModelAndView jobResources () {
        ArrayList<Resource> jobResourceList = DAO.getJobResourceList();
        if (jobResourceList == null){
            return new ModelAndView("error","errmsg","No job resource list - null");
        }
        ModelAndView mv = new ModelAndView("jobresources");
        mv.addObject("jobList",jobResourceList);
        return mv;
    }











    @RequestMapping("/register")
    public ModelAndView register () {
        return new ModelAndView("register", "inst",
                "Please fill in the form below to register!");
    }

    @RequestMapping("/formhandler")
        public ModelAndView formhandler(
                @RequestParam("firstname") String firstName,
                @RequestParam("lastname") String lastName,
                @RequestParam("phonenum") long phoneNum,
                @RequestParam("email") String email)

        {
            boolean result = DAO.addUser(firstName,lastName,phoneNum, email);
            if (result == false) {
                return new ModelAndView("error", "errmsg", "User add failed");
            }
            ArrayList<Resource> resourceList = DAO.getResourceList();
            if (resourceList == null) {
                return new ModelAndView("error", "errmsg", "No more resources - null");
            }
            ModelAndView mv = new ModelAndView("resourceview");
            mv.addObject("firstname", firstName);
            mv.addObject("rList", resourceList);
            return mv;
        }

    @RequestMapping("/directions")
    public ModelAndView route (@RequestParam("lat") String userLat,
                               @RequestParam("lon") String userLon,
                               @RequestParam("rLat") String resourceLat,
                                @RequestParam("rLon") String resourceLon)
    {
    //TODO- resourceview.jsp : form of hidden fields. javascript fills in hidden view forms with userLat and userLon
        try {
            //here we begin to form a connection to our Mapzen API
            //below we pass our user lat and lon plus their destination's lat and lon through the API URL which returns an object
            //that builds a route to that destination from their current location with detailed info on what the user needs to do
            //(in terms of travel directions/ instructions) to get there
            HttpClient http = HttpClientBuilder.create().build();
            String startURI = "https://valhalla.mapzen.com/route?json={\"locations\":[{\"lat\":"+userLat+",\"lon\":"+userLon+",\"type\":\"break\"},{\"lat\":"+resourceLat+",\"lon\":"+resourceLon+",\"type\":\"break\"}],\"costing\":\"multimodal\",\"costing_options\":{\"transit\":{\"use_bus\":\"1.0\",\"use_transfers\":\"0.001\"},\"pedestrian\":{\"walking_speed\":\"4.1\"}}}&api_key=mapzen-HeGbynW";
            String uri = UriUtils.encodeQuery(startURI, "UTF-8");
            System.out.println(startURI);
            HttpGet getPage = new HttpGet(uri);
            //this actually returns a JSON object
            //actually making the HttpGet happen and pulling in the response
            HttpResponse resp = http.execute(getPage);
            //response has status code within it to tell us success, failure, 505, etc
            //get actual content (JSON string) and turn it into object
            //"entity" is the meat of the response
            String jsonString = EntityUtils.toString(resp.getEntity());
            System.out.println(jsonString);
            //turn the string into an actual JSON object
            //for approx the next 40 lines of code, we are going through the JSON object that is returned from our URL above and grabbing
            //travel directions from the user's location to the destination and storing them in an Arraylist called "instructions"
            JSONObject json = new JSONObject(jsonString);
            JSONArray maneuvers = json.getJSONObject("trip").getJSONArray("legs").getJSONObject(0).getJSONArray("maneuvers");
            ArrayList<String> instructions = new ArrayList<String>();
            for (int i = 0; i < maneuvers.length(); i++) {

                if (maneuvers.getJSONObject(i).has("verbal_pre_transition_instruction")){
                    String verbalPreTransitionInstruction = maneuvers.getJSONObject(i).getString("verbal_pre_transition_instruction");
                    instructions.add(verbalPreTransitionInstruction);}
                if (maneuvers.getJSONObject(i).has("verbal_depart_instruction")){
                    String verbalDepartInstruction = maneuvers.getJSONObject(i).getString("verbal_depart_instruction");
                    instructions.add(verbalDepartInstruction);}
                if (maneuvers.getJSONObject(i).has("verbal_post_transition_instruction")){
                    String verbalPostTransitionInstruction = maneuvers.getJSONObject(i).getString("verbal_post_transition_instruction");
                    instructions.add(verbalPostTransitionInstruction);}
                if (maneuvers.getJSONObject(i).has("verbal_arrive_instruction")){
                    String verbalArriveInstruction = maneuvers.getJSONObject(i).getString("verbal_arrive_instruction");
                    instructions.add(verbalArriveInstruction);}
            }
            //get the response code and some info from JSON
            //200 means we made a successful connection
            int status = resp.getStatusLine().getStatusCode();
            System.out.println(status);
            //create JSON data
            //put into web application (ModelAndView)
            //here, on our route.jsp page, we return an ArrayList called "instructions" through a model called "instructions"
            //We also return the status code (status) through a model called 'status'
            ModelAndView moo = new ModelAndView("directions");
            moo.addObject("status", status);
            moo.addObject("instructions", instructions);
            return moo;
        }
        catch (JSONException x) {
            x.printStackTrace();
            System.out.println("JSON Exception!!!");
        } catch (Exception x) {
            x.printStackTrace();
        }
        //cue to read the log during debugging process, make the null into a user friendly message
        return null;
    }
    }
