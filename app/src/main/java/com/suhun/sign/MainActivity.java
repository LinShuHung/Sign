package com.suhun.sign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public String tag = MainActivity.class.getSimpleName();
    private SignView signView;
    private Button clearBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signView = findViewById(R.id.sign);
    }

    public void clear(View view){
        signView.clearCanvas();
    }

    public void undo(View view){
        signView.undo();
    }

    public void redo(View view){
        signView.redo();
    }
}