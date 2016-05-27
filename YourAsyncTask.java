package com.utilities.dhananjayan.sampleasnktaskexample;

import android.os.AsyncTask;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dhananjayan on 3/30/2016.
 */
public class YourAsyncTask extends AsyncTask<ArrayList, Void, ArrayList<HashMap<String, String>>> {
    private String userName;
    private String passWord;
    private String AuthToken;
    private int serviceCallMethod;
    // URL to get Login JSON Response
    private static String url;
    // Hashmap for ListView
    private static ArrayList loginResponse = new ArrayList<>();
    private MainActivity taskCompleted;
    public YourAsyncTask(MainActivity activityContext,int txtServiceCallMethod, String txtUserName, String txtPassWord, String txtAuthToken, String txtURL) {
        this.taskCompleted = activityContext;
        this.serviceCallMethod = txtServiceCallMethod;
        this.userName = txtUserName;
        this.passWord = txtPassWord;
        this.AuthToken = txtAuthToken;
        this.url = txtURL;
    }
    @Override
     protected void onPreExecute() {
    }
    @Override
    protected ArrayList doInBackground(ArrayList... arg0) {
        // Creating service handler class instance
        ServiceHandler sh = new ServiceHandler();
        // Making a request to url and getting response
        Log.d("TAG", "in make Service doInBackground");
        String jsonStr = sh.makeServiceCall(url,serviceCallMethod,userName,passWord,AuthToken);
        Log.d("TAG", "in make Service Response"+jsonStr);
        loginResponse.clear();
        if (jsonStr != null) {
                loginResponse.add(jsonStr);
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }
        return loginResponse;
    }

    protected void onPostExecute(ArrayList response) {
        taskCompleted.responseReceived(response);
    }
}

