package com.example.tungvu.wardriving;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tungvu.wardriving.Objects.AccessPoint;
import com.example.tungvu.wardriving.Objects.SendToServer;
import com.example.tungvu.wardriving.Objects.Sample;
import com.example.tungvu.wardriving.modules.GrantPermission;
import com.example.tungvu.wardriving.modules.Wifi;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public MainActivity(){
        Log.i("main", "constructor");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("main", "onCreate");
        init();

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestToServer r = new RequestToServer();
                r.execute("");
            }
        });

        button_measure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        button_change_server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SERVER = editText_change_server.getText().toString();
                Toast.makeText(MainActivity.this, "Changed server to "+SERVER, Toast.LENGTH_SHORT).show();
            }
        });

    }

    void getData() {
        id_sample = 0;
        x = Float.parseFloat(editText_x.getText().toString());
        y = Float.parseFloat(editText_y.getText().toString());
        if (radioButton_survey.isChecked()) isSurvey = true;
        else if (radioButton_test.isChecked()) isSurvey = false;
        listSample = new ArrayList<Sample>();
        listWifi = new ArrayList<>();
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                id_sample++;
                progressBar.setProgress(id_sample);
                listWifi = wifi.getListWifi();
                textView_percentage.setText(id_sample * 100 / SAMPLES + "%");
                for (int i = 0; i < listWifi.size(); i++) {
                    sample = new Sample(listWifi.get(i).getMAC(),
                            listWifi.get(i).getSSID(), listWifi.get(i).getLevel(), id_sample);
                    listSample.add(sample);
                }
                if (id_sample >= SAMPLES) {
                    sendToServer = new SendToServer(listSample, x, y, isSurvey);
                    handler.removeCallbacks(this);
                    return;
                }
                handler.postDelayed(this, TIME_DELAY_SAMPLING);
            }
        };
        handler.post(runnable);

    }

    class RequestToServer extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                Socket socket = new Socket(SERVER, 1357);

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                DataInputStream dis = new DataInputStream(socket.getInputStream());

                oos.writeObject(sendToServer);
                message_from_server = dis.readUTF();

                oos.close();
                dis.close();
                socket.close();

            } catch (Exception e) {
                Log.i("main", e.toString());
                return "Error";

            }
            return message_from_server;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(MainActivity.this, "Message from server: " + message_from_server, Toast.LENGTH_SHORT).show();
        }
    }

    void init() {
        wifi = new Wifi(this);
        new GrantPermission(this);
        button_add = (Button) findViewById(R.id.button_add);
        button_measure = (Button) findViewById(R.id.button_measure);
        editText_x = (EditText) findViewById(R.id.editText_setX);
        editText_y = (EditText) findViewById(R.id.editText_setY);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        sb = new StringBuilder();
        radioButton_survey = (RadioButton) findViewById(R.id.radioButton_survey);
        radioButton_test = (RadioButton) findViewById(R.id.radioButton_test);
        textView_percentage = (TextView) findViewById(R.id.textView_percentage);
        progressBar.setMax(SAMPLES);
        radioButton_survey.setChecked(true);
        editText_change_server = (EditText) findViewById(R.id.editText_change_server);
        button_change_server = (Button) findViewById(R.id.button_change_server);
        editText_change_server.setText(SERVER);
    }

    private static final int TIME_MEASUREMENT = 10000; //mini second
    private static final int TIME_DELAY_SAMPLING = 100; //mini second
    private static final int SAMPLES = TIME_MEASUREMENT / TIME_DELAY_SAMPLING;
    public String SERVER = "192.168.7.200";

    float x = 0;
    float y = 0;
    Boolean isSurvey = true;
    int id_sample = 0;
    int count = 0;

    Button button_measure, button_add, button_change_server;
    EditText editText_x, editText_y, editText_change_server;
    ProgressBar progressBar;
    StringBuilder sb;
    RadioButton radioButton_survey, radioButton_test;
    TextView textView_percentage;

    Sample sample = null;
    SendToServer sendToServer = null;
    String message_from_server = null;
    Handler handler;
    Runnable runnable;
    ArrayList<AccessPoint> listWifi;
    Wifi wifi;
    ArrayList<Sample> listSample;
}
