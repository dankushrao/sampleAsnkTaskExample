package com.utilities.dhananjayan.sampleasnktaskexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends Activity implements CaptureResponseListener {

    private String txtUId;
    private String txtUpassword;
    private int txtMethod;
    private String txtAuthToken;
    private  String txtURL;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sampleui);
        txtUId = "2558";
        txtUpassword = "123456";
        txtMethod = 1;
        txtAuthToken = "bearer fd3a6a7b-6a25-4aed-a4db-e385e265a68b";
        // Post Method URL:
       // txtURL = "https://amway-india-uat.smartstack.io/lynxcommercewebservices/v2/amway/loginOCC/users/loginuser";
       // Get Method URL:
        txtURL = "https://amway-india-uat.smartstack.io/lynxcommercewebservices/oauth/token?client_id=trusted_client&client_secret=$tore@123&grant_type=client_credentials";
    }

    @Override
    public void responseReceived(ArrayList response) {
        Toast.makeText(getApplicationContext(),"Response: "+response,Toast.LENGTH_LONG).show();
    }

     public void invokeCaptureAcitivity(View v) {
        YourAsyncTask task = new YourAsyncTask(MainActivity.this,txtMethod,txtUId,txtUpassword,txtAuthToken,txtURL);
        task.execute();
    }
}
