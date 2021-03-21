package aimsmart.memead.aimad.assignment;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class getdata extends AsyncTask<String,String,Void> {

    public List<senddata> listsend = new ArrayList<senddata>();
    BufferedInputStream inputStream;
    JSONArray jsonArray;
    String result;
    JSONArray jsonArray1;
    Activity activity;
    Context context;
    private String Link = "https://restcountries.eu/rest/v2/region/asia?fields=name;flag;capital;region;subregion;population;borders;languages";

    public getdata(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... strings) {
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(Link);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            result = readStream();
            result = result.substring(result.indexOf("["),result.lastIndexOf("]")+1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String readStream() throws IOException {
         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
         StringBuilder stringBuilder = new StringBuilder();
         String line;
         try{
             while((line=bufferedReader.readLine())!=null) {
                 stringBuilder.append(line);
             }
         }
         catch(Exception e){
              e.printStackTrace();
             }
             return stringBuilder.toString();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        try {

            jsonArray = new JSONArray(result);

            if (jsonArray!=null){
                for(int i=0 ;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    senddata senddata = new senddata();
                    senddata.setTitle(jsonObject.getString("name"));
                    senddata.setImageurl(jsonObject.getString("flag"));
                    senddata.setCapital(jsonObject.getString("capital"));
                    senddata.setPopulation(jsonObject.getString("population"));
                    senddata.setRegion(jsonObject.getString("region"));
                    senddata.setSubregion(jsonObject.getString("subregion"));
                    jsonArray1 = jsonObject.getJSONArray("languages");
                    ArrayList<String> list = new ArrayList<String>();
                    if(jsonArray1!=null) {
                        for (int y = 0; y < jsonArray1.length(); y++) {
                            list.add(jsonArray1.getJSONObject(y).getString("name"));
                        }
                        StringBuilder listString = new StringBuilder();
                        for (String s : list) {
                            listString.append(s).append("\t, ");
                        }
                        senddata.setLanguages(listString.toString().substring(0,listString.length()-2));
                    }
                    JSONArray jsonArray2 = jsonObject.getJSONArray("borders");
                    ArrayList<String> list2 = new ArrayList<String>();
                    if (jsonArray2!=null) {
                        for (int j = 0; j < jsonArray2.length(); j++) {
                            list2.add(jsonArray2.getString(j));
                        }
                        StringBuilder listString1 = new StringBuilder();
                        for (String s : list2) {
                            listString1.append(s).append("\t, ");
                        }
                        senddata.setBorder(listString1.toString().substring(0, listString1.length()));
                    }
                    listsend.add(senddata);
                }
            }
            RecyclerView recyclerView;
            recyclerView = this.activity.findViewById(R.id.list1);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            recycler adapter = new recycler(listsend, context);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
