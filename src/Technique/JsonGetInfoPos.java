/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Technique;

/**
 *
 * @author FATHLLAH Wael
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class JsonGetInfoPos {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }

  public static void main(String[] args) throws IOException, JSONException {
    JSONObject json = readJsonFromUrl("http://maps.googleapis.com/maps/api/geocode/json?latlng=37.215217,10.127766&sensor=false");
    
    
try {
  
  JSONArray jArr = json.getJSONArray("results");
  JSONObject json1 = jArr.getJSONObject(0);
    System.out.println((json1.getString("formatted_address")));
} catch (JSONException e) {
  e.printStackTrace();
}
  }
  
  public String getInfo(String pos) throws IOException, JSONException{
      JSONObject json = readJsonFromUrl("http://maps.googleapis.com/maps/api/geocode/json?latlng="+pos+"&sensor=false");
      try {
  
  JSONArray jArr = json.getJSONArray("results");
  JSONObject json1 = jArr.getJSONObject(0);
    System.out.println((json1.getString("formatted_address")));
    return json1.getString("formatted_address");
    } catch (JSONException e) {
      e.printStackTrace();
    }
      return "";
  }
}