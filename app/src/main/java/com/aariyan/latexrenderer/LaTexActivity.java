package com.aariyan.latexrenderer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.aariyan.mathjax.MathJaxView;


public class LaTexActivity extends AppCompatActivity
        implements View.OnClickListener {
    MathJaxView mMathJaxView;

    private int exampleIndex = 0;

    private String getExample(int index) {
        return getResources().getStringArray(R.array.tex_examples)[index];
    }

    public void onClick(@NonNull View v) {
        int id = v.getId();
    }

    private void showMathJax(String value) {
        mMathJaxView.setInputText(value);
    }

    private Button showBtn;
    private Button clearBtn;
    private Button exampleBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMathJaxView = (MathJaxView) findViewById(R.id.laTexView);
        setupMathJaxListener();
        EditText e = (EditText) findViewById(R.id.edit);
        e.setBackgroundColor(Color.LTGRAY);
        e.setTextColor(Color.BLACK);
        e.setText("");
        showBtn = findViewById(R.id.showBtn);
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMathJax(e.getText().toString());
            }
        });
        clearBtn = findViewById(R.id.clearBtn);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e.setText("");
                showMathJax("");
            }
        });

        exampleBtn = findViewById(R.id.exampleBtn);
        exampleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e.setText(getExample(exampleIndex++));
                if (exampleIndex > getResources().getStringArray(R.array.tex_examples).length - 1)
                    exampleIndex = 0;
                showMathJax(e.getText().toString());
            }
        });
        //initial load the first example
        onClick(exampleBtn);

        TextView t = (TextView) findViewById(R.id.textview3);
        t.setMovementMethod(LinkMovementMethod.getInstance());
        t.setText(Html.fromHtml(t.getText().toString()));


    }

    private void setupMathJaxListener() {
        mMathJaxView.setRenderListener(new MathJaxView.OnMathJaxRenderListener() {
            @Override
            public void onRendered() {
                showToast();
            }
        });
    }

    private void showToast() {
        Toast.makeText(this, "Render complete", Toast.LENGTH_SHORT).show();
    }
}

