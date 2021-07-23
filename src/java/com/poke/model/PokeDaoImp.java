/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poke.model;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author USER
 */
public class PokeDaoImp implements PokeDao {

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    
    JdbcTemplate template;
    
    @Override
    public List<PokeModel> getAllPokeData() {
        List<PokeModel> pokelist = new ArrayList<PokeModel>();
        String[] props = new String[3];
        Map<Integer, String> pokeId = new HashMap<Integer, String>();
        Map<Integer, String> pokeName = new HashMap<Integer, String>();
        Map<Integer, String> pokeImg = new HashMap<Integer, String>();
        Map<Integer, String> pokeType = new HashMap<Integer, String>();
        Map<Integer, String> pokeColorType = new HashMap<Integer, String>();
        try {
            URL url = new URL("https://pokeapi.co/api/v2/pokemon?limit=9&offse=100");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestMethod("GET");
            conn.connect();
            
            InputStream input = conn.getInputStream();
            JSONParser json = new JSONParser();
            JSONObject obj = (JSONObject)json.parse(new InputStreamReader(input, "UTF-8"));
            JSONArray arr = (JSONArray)obj.get("results");
            JSONObject tempt = null;
            for(int i=0; i<arr.size(); i++) {
                tempt = (JSONObject) arr.get(i);
                pokeName.put(i, tempt.get("name").toString());
                props = getPokeProps(tempt.get("name").toString());
                pokeId.put(i, props[0]);
                pokeImg.put(i, props[1]);
                pokeType.put(i, props[2]);
                pokeColorType.put(i, props[3]);
            }
            
            conn.disconnect();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        for(int i=0; i<pokeName.size(); i++) {
            PokeModel model = new PokeModel();
            model.setId(pokeId.get(i));
            model.setName(pokeName.get(i));
            model.setImg(pokeImg.get(i));
            model.setType(pokeType.get(i));
            model.setColorType(pokeColorType.get(i));
            pokelist.add(model);
        }
        return pokelist;
    }   

    @Override
    public String[] getPokeProps(String name) {
        String[] props = new String[4];
        try {
            URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + name);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("GET");
            conn.connect();
            
            InputStream input = conn.getInputStream();
            JSONParser json = new JSONParser();
            JSONObject obj = (JSONObject) json.parse(new InputStreamReader(input, "UTF-8"));
            JSONObject temptImg = (JSONObject) obj.get("sprites");
            JSONArray temptType = (JSONArray) obj.get("types");
            JSONObject objType = (JSONObject) temptType.get(0);
            JSONObject objType2 = (JSONObject ) objType.get("type");
            
            int id = Integer.parseInt(obj.get("id").toString());
            props[0] = formatPokeId(id);
            props[1] = temptImg.get("front_default").toString();
            props[2] = objType2.get("name").toString();
            props[3] = getPokeColorType(id + "");
            
            conn.disconnect();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return props;
    }
    
    public String getPokeColorType(String id) {
        String color = "";
        try {
            URL url = new URL("https://pokeapi.co/api/v2/pokemon-species/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("GET");
            conn.connect();
            
            InputStream input = conn.getInputStream();
            JSONParser json = new JSONParser();
            JSONObject obj = (JSONObject) json.parse(new InputStreamReader(input, "UTF-8"));
            JSONObject temptColor = (JSONObject) obj.get("color");
            color = temptColor.get("name").toString();
            
            conn.disconnect();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return color;
    }
    
    public PokeModel getPokeSearch(String name) {
        PokeModel pm = new PokeModel();
        try {
            URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + name);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("GET");
            conn.connect();
            
            InputStream input = conn.getInputStream();
            JSONParser json = new JSONParser();
            JSONObject obj = (JSONObject) json.parse(new InputStreamReader(input, "UTF-8"));
            JSONObject temptImg = (JSONObject) obj.get("sprites");
            JSONArray temptType = (JSONArray) obj.get("types");
            JSONObject objType = (JSONObject) temptType.get(0);
            JSONObject objType2 = (JSONObject ) objType.get("type");
            
            int id = Integer.parseInt(obj.get("id").toString());
            pm.setId(formatPokeId(id));
            pm.setName(name);
            pm.setImg(temptImg.get("front_default").toString());
            pm.setType(objType2.get("name").toString());
            pm.setColorType(getPokeColorType(id + ""));
            pm.setDesc(getPokeCharacteristic(id));
//            getPokeCharacteristic(id);
            conn.disconnect();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return pm;
    }
    
    public String getPokeCharacteristic(int id) {
//        PokeModel pm = new PokeModel();
        String desc = "";
        try {
            URL url = new URL("https://pokeapi.co/api/v2/characteristic/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("GET");
            conn.connect();
            
            InputStream input = conn.getInputStream();
            JSONParser json = new JSONParser();
            JSONObject obj = (JSONObject) json.parse(new InputStreamReader(input, "UTF-8"));
            JSONArray detail = (JSONArray) obj.get("descriptions");
            JSONObject descTemp = (JSONObject) detail.get(0);
            desc = (String) descTemp.get("description");
            System.out.println();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return desc;
    }
    
    public String formatPokeId(int id) {
        if(id < 10) return "#00" + id;
        if(id >= 10) return "#0" + id;
        else return "#" + id;
    }
    
    public String formatName(String name) {
        return name.substring(1, name.length()-1);
    }
}

