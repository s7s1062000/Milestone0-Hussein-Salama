package com.example.milestone0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    Socket s1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
    }

    public void connect(View view){
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            s1 = new Socket("172.20.10.4", 12348);
            System.out.print("connected");
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.print("exception");
        }
    }
    public void send(View view) throws Exception{
        TextView out = (TextView) findViewById(R.id.output);
        try {
            // get the output stream from the socket.
            // create a data output stream from the output stream so we can send data through it
            DataOutputStream dataOutputStream = new DataOutputStream(s1.getOutputStream());

            System.out.println("Sending string to the ServerSocket");

            // write the message we want to send
            dataOutputStream.writeUTF("Hello from the other side!");
            System.out.println("Text sent to server");
            DataInputStream input = new DataInputStream(s1.getInputStream());
            String x = input.readUTF();
            Toast.makeText(this, "HGHCGFGFG", Toast.LENGTH_LONG);
       //     dataOutputStream.flush(); // send the message

       //     dataOutputStream.close(); // close the output stream when we're done.

            out.setText(x);
            //System.out.println("Closing socket and terminating program.");
            s1.close();
        }
        catch(Exception i){
            i.printStackTrace();
            System.out.print("exception");
        }
    }
}