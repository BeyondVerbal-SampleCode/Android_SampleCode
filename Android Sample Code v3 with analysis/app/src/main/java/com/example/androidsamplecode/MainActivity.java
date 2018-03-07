package com.example.androidsamplecode;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final String RECORDING_URL = "https://apiv5.beyondverbal.com/v5/recording/";
    private static final String Auth_URL = "https://token.beyondverbal.com/token";
    private static final String APIKey = "API KEY";
    ResponseHandler responseHand;
    private Header access_token;
    private String recordingid;
    private Button upstreamButton;
    private Button sendFileButton;
    private TextView statusContent;
    private TextView responseContentTextView;
    private TextView txtWait;
    private ProgressDialog progressDialog;
    private ResponseHolder responseHolder;
    private long FromMs = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseHolder = new ResponseHolder();
        initViews();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);

        }
    }

    /**
     * Initialize all views from xml resource
     */
    private void initViews() {
        upstreamButton = initButton(R.id.get_upstream_button);
        sendFileButton = initButton(R.id.send_file_button);
        statusContent = initTextView(R.id.status_content_textView);

        txtWait = initTextView(R.id.txtWait);
        responseContentTextView = initTextView(R.id.response_content_textView);
        upstreamButton.setOnClickListener(this);
        sendFileButton.setOnClickListener(this);
        //sendFileButton.setEnabled(false);
    }

    private Button initButton(int buttonId) {
        return (Button) findViewById(buttonId);
    }

    private SeekBar initSeekBar(int progressId) {
        return (SeekBar) findViewById(progressId);
    }

    private TextView initTextView(int textViewId) {
        return (TextView) findViewById(textViewId);
    }

    /**
     * Post to server according to which button was pressed
     *
     * @param buttonId the id of the button pressed
     * @return ResponseHolder
     */

    private ResponseHolder postByAction(int buttonId) {

        HttpActivity httpa = new HttpActivity();

        switch (buttonId) {
            case R.id.get_upstream_button:
                getToken();
                responseHolder = httpa.doPost(RECORDING_URL + "start", access_token, getEntityForUpstream());

                if (responseHolder.content != null) {
                    recordingid = getRecordingid(responseHolder.content);
                    GoStream();

                }
                break;
            case R.id.send_file_button:
                getToken();
                HttpEntity entity = getEntityForUpstream();
                responseHolder = httpa.doPost(RECORDING_URL + "start", access_token, entity);

                if (responseHolder.content != null) {
                    recordingid = getRecordingid(responseHolder.content);
                    responseHolder = httpa.doPost(RECORDING_URL + recordingid, access_token, getEntityForSendFile());
                }


        }
        return responseHolder;
    }

    /******
     *
     * Create Asyncronic Post(Stream file)
     * **/
    public void GoStream() {


        txtWait.post(new Runnable() {
            public void run() {
                txtWait.setText("Wait");
            }
        });

        Thread stream = new Thread(new Runnable() {
            @Override
            public void run() {

                HttpActivity httpac = new HttpActivity();
                final ResponseHolder hol = httpac.doPost(RECORDING_URL + recordingid, access_token, getEntityForSendFile());


                responseContentTextView.post(new Runnable() {
                    public void run() {
                        CharSequence text = responseContentTextView.getText();
                        responseContentTextView.setText("Full analysis::::" + hol.content + text);
                    }
                });

            }
        });

        stream.start();

        Analyze();
    }

    public void Analyze() {

        final Timer myTimer = new Timer();
        long delay = 0;
        long period = 5000;

        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                HttpActivity httpac = new HttpActivity();
                try {

                    txtWait.post(new Runnable() {
                        public void run() {
                            txtWait.append(".");
                        }
                    });

                    final ResponseHolder hol = httpac.doGet(RECORDING_URL + recordingid + "/analysis?fromMs=" + FromMs, access_token);

                    String status = getJName(hol.content, "status");
                    String sesionStatus = getsesionStatus(hol.content);
                    String f = getDuration(hol.content);
                    if (status.equals("success")) {
                        FromMs = Long.parseLong(f.replace(".0", ""));
                    }
                    if (sesionStatus != null && sesionStatus.equals("Done")) {
                        myTimer.cancel();
                        txtWait.post(new Runnable() {
                            public void run() {
                                txtWait.setText("");
                            }
                        });
                    }

                    responseContentTextView.post(new Runnable() {
                        public void run() {
                            responseContentTextView.append("\n....\n" + hol.content);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, delay, period);


    }

    protected void ResponseResult(ResponseHolder rs) {

        statusContent.setText(rs.responseString);
        if (rs.content != null) {
            responseContentTextView.append("\n-----------------\n" + rs.content);
        }

    }

    private String getsesionStatus(String response) {
        if (response == null)
            return null;
        try {
            JSONObject json = new JSONObject(response);
            String duration = json.getJSONObject("result").getString("sessionStatus");


            String res = json.getString("result");
            JSONObject obj = new JSONObject(res);
            JSONArray jsonArray = obj.getJSONArray("analysisSegments");
            JSONArray ssaa = new JSONArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject value = jsonArray.getJSONObject(i);

                JSONObject value2 = value.getJSONObject("analysis");
                try {
                    ssaa.put(i, value2);


                    String response = value2.getJSONObject("Mood").getJSONObject("Composite").getJSONObject("Primary").getString("Phrase");

                } catch (JSONException e) {

                }

            }


          

            return duration;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getDuration(String response) {
        if (response == null)
            return null;
        try {
            JSONObject json = new JSONObject(response);
            String duration = json.getJSONObject("result").getString("duration");

            return duration;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getJName(String response, String name) {
        if (response == null)
            return null;
        try {
            JSONObject json = new JSONObject(response);
            String recordingid = json.getString(name);

            return recordingid;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }

    private String getRecordingid(String response) {
        if (response == null)
            return null;
        try {
            JSONObject json = new JSONObject(response);
            String recordingid = json.getString("recordingId");

            return recordingid;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Called when one of the buttons is pressed getUpstream or SendFile
     *
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        new ServerConnection().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, v.getId());
    }

    /**
     * @return the WAV file from local resource
     */
    private HttpEntity getEntityForSendFile() {

        // Fetches file from local resources.
        InputStream raw = getResources().openRawResource(R.raw.sample2);
        InputStreamEntity reqEntity = new InputStreamEntity(raw, -1);
        return reqEntity;
    }



    /**
     * @return the configuration data for get upstream url
     */
    private HttpEntity getEntityForUpstream() {
        StringEntity se = null;
        try {
            se = new StringEntity(getConfigData());
            se.setContentType("application/json; charset=UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return se;
    }

    /**
     * Create the https client with all configuration settings
     *
     * @return
     */

    private void getToken() {
        if (access_token != null)
            return;
        HttpActivity httpa = new HttpActivity();
        String jsonToken = httpa.doPost(Auth_URL, null, getEntityForAccessToken()).content;
        if (jsonToken == null)
            return;
        JSONObject jsonObject;
        Header header = null;
        try {
            jsonObject = new JSONObject(jsonToken);
            header = new BasicHeader("Authorization",
                    jsonObject.getString("token_type") + " " + jsonObject.getString("access_token"));
            Log.i("header", header.getName() + "  " + header.getValue());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        access_token = header;
    }

    private HttpEntity getEntityForAccessToken() {

        String body = String.format("apikey=%s&grant_type=%s", APIKey, "client_credentials");

        StringEntity se = null;
        try {
            se = new StringEntity(body);
            se.setContentType("Content-Type:application/x-www-form-urlencoded");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return se;

    }

    /**
     * Sets configuration data for POST to server to receive upstream url
     *
     * @return
     */
    private String getConfigData() {
        try {

            JSONObject inner_json = new JSONObject();
            inner_json.put("type", "WAV");
            inner_json.put("channels", 1);
            inner_json.put("sample_rate", 0);
            inner_json.put("bits_per_sample", 0);
            inner_json.put("auto_detect", true);
            JSONObject json = new JSONObject();
            json.put("data_format", inner_json);

            return json.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates background thread to connect to server in order to avoid blocking
     * UI thread
     */
    private class ServerConnection extends AsyncTask<Integer, Void, ResponseHolder> {

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("please wait...");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected ResponseHolder doInBackground(Integer... params) {
            int buttonId = params[0];
            responseHolder.actionId = buttonId;
            return postByAction(buttonId);
        }

        @Override
        protected void onPostExecute(ResponseHolder responseHolder) {
            statusContent.setText(responseHolder.responseString);
            if (responseHolder.content != null) {
                CharSequence text = responseContentTextView.getText();
                responseContentTextView.setText(text + responseHolder.content);
            }

            progressDialog.dismiss();
            super.onPostExecute(responseHolder);
        }
    }


}
