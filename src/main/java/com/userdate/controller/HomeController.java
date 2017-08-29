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
//    private String rLon;

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
        //Generates ArrayList made of Resource objects from database via the DAO (Document Access Object)
        //DAO uses method called getResourceList to pull every row from the Resources table in DB
        ArrayList<Resource> resourceList = DAO.getResourceList();
        //Generates ArrayList made of Resource objects from database via the DAO (Document Access Object)
        //based on what category(s) of resource(s) user selects, DAO.getUserResourceList method
        //pulls every applicable row from the Resources table in DB and stores in ArrayList userResourceList
        ArrayList<Resource> userResourceList = DAO.getUserResourceList();

        //TODO: make error.jsp
        if (resourceList == null) {
            return new ModelAndView("error", "errmsg", "No resource list - null");
        }
        if (userResourceList == null){
            return new ModelAndView("error","errmsg","No user resource list - null");
        }

       ModelAndView mv = new ModelAndView("resourceview");
       mv.addObject("rList",resourceList);
       mv.addObject("usList",userResourceList);
       return mv;
    }



    @RequestMapping("/register")
    public ModelAndView register () {
        return new ModelAndView("register", "inst",
                "Please fill in the form below to register!");
    }

    //action that gets called when user registers
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

            ArrayList<Resource> resourceList = DAO.getResourceList();

            //TODO: make error.jsp
            if (resourceList == null) {
                return new ModelAndView("error", "errmsg", "No more resources - null");
            }

            ModelAndView mv = new ModelAndView("resourceview");
            mv.addObject("firstname", firstName);
            mv.addObject("rList", resourceList);

            return mv;
        }

    @RequestMapping("/route")
    public ModelAndView route (@RequestParam("lat") String userLat,
                               @RequestParam("lon") String userLon,
                               @RequestParam("rLat") String resourceLat,
                                @RequestParam("rLon") String resourceLon)
    {
//       TODO- resourceview.jsp : form of hidden fields. javascript fills in hidden view forms with userLat and userLon
// & jsp page receives resourceLat and resourceLon from Controller via the DAO
        try {


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
            JSONObject json = new JSONObject(jsonString);
            JSONArray maneuvers = json.getJSONObject("trip").getJSONArray("legs").getJSONObject(0).getJSONArray("maneuvers");
            ArrayList<String> instructions = new ArrayList<String>();
            for (int i = 0; i < maneuvers.length(); i++) {
//                String instruction = maneuvers.getJSONObject(i).getString("verbal_pre_transition_instruction");
//                instructions.add(instruction);
                if (maneuvers.getJSONObject(i).has("arrive_instruction") ){
                    String arriveInstruction = maneuvers.getJSONObject(i).getString("arrive_instruction");
                    instructions.add(arriveInstruction);}

                if (maneuvers.getJSONObject(i).has("verbal_pre_transition_instruction")){
                    String preTransitionInstruction = maneuvers.getJSONObject(i).getString("verbal_pre_transition_instruction");
                    instructions.add(preTransitionInstruction);}

                if (maneuvers.getJSONObject(i).has("verbal_transition_alert_instruction")){
                    String transitionAlertInstruction = maneuvers.getJSONObject(i).getString("verbal_transition_alert_instruction");
                    instructions.add(transitionAlertInstruction);}

                if (maneuvers.getJSONObject(i).has("instruction")){
                    String instruction = maneuvers.getJSONObject(i).getString("instruction");
                    instructions.add(instruction);}

                if (maneuvers.getJSONObject(i).has("verbal_post_transition_instruction")){
                    String postTransitionInstruction = maneuvers.getJSONObject(i).getString("verbal_post_transition_instruction");
                    instructions.add(postTransitionInstruction);}

                if (maneuvers.getJSONObject(i).has("verbal_depart_instruction")){
                    String verbalDepartInstruction = maneuvers.getJSONObject(i).getString("verbal_depart_instruction");
                    instructions.add(verbalDepartInstruction);}

                if (maneuvers.getJSONObject(i).has("verbal_arrive_instruction")){
                    String verbalArriveInstruction = maneuvers.getJSONObject(i).getString("arrive_instruction");
                    instructions.add(verbalArriveInstruction);}

                if (maneuvers.getJSONObject(i).has("depart_instruction")){
                    String departInstruction = maneuvers.getJSONObject(i).getString("depart_instruction");
                    instructions.add(departInstruction);}


//
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
