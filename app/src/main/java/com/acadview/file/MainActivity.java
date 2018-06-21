package com.acadview.file;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button readButton,writeButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        writeButton = findViewById(R.id.write);
        readButton = findViewById(R.id.read);
        textView = findViewById(R.id.textView);

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String data = editText.getText().toString();

                if (TextUtils.isEmpty(data)){
                    editText.setError("required field");
                    return;
                }
               //write operation
                FileOutputStream fos;

                try {
                    fos = openFileOutput("userdata",MODE_PRIVATE);
                    fos.write(data.getBytes());
                    fos.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }



                readButton.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);


            }
        });

        //data read
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BufferedReader input = null;
                File file = null;

                StringBuilder builder = new StringBuilder();

                try{
                    file = getBaseContext().getFileStreamPath("userdata");



                    FileReader reader = new FileReader(file.getPath());
                    BufferedReader bufferedReader = new BufferedReader(reader);

                    String line;
                    while ((line = bufferedReader.readLine())!=null){
                        builder.append(line);
                    }

                    reader.close();

                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();

                }
                catch (IOException e) {
                    e.printStackTrace();
                }


                textView.setText(builder.toString());

            }
        });

    }
}
