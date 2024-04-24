package ru.myitschool.lab23;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import ru.myitschool.lab23.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    EditText url2, query;
    TextView result;
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         url2 = (EditText) findViewById(R.id.url_text);
        query = (EditText) findViewById(R.id.query_parameter);
      result = (TextView) findViewById(R.id.result_text);
        btn = (Button) findViewById(R.id.send_button);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Thread thread = new Thread() {
                    public void run() {
                        try {

                            URL url = new URL(url2.getText().toString()+"?cta="+query.getText().toString());

                            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
                            try {

                                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                                final StringBuilder stringBuilder = new StringBuilder();
                                String line;
                                while ((line = bufferedReader.readLine()) != null) {
                                    stringBuilder.append(line).append("\\n");
                                }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        result.setText(stringBuilder.toString());
                                    }
                                });
                            } finally {
                                urlConnection.disconnect();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
            }
        });
    }
}
