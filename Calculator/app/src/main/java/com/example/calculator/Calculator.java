package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Calculator extends AppCompatActivity {
    private ImageView one, two, three, four, five, six, seven, eight, nine, zero, dot, plus, minus, times, by, sqrt, pm, all_clear, percent, equals2;
    private TextView input;
    private String inputNumString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        dot = findViewById(R.id.dot);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        times = findViewById(R.id.times);
        by = findViewById(R.id.divide);
        sqrt = findViewById(R.id.sqrt);
        pm = findViewById(R.id.pm);
        all_clear = findViewById(R.id.all_clear);
        equals2 = findViewById(R.id.equals);
        percent = findViewById(R.id.percent);
        input = findViewById(R.id.numbers);
        inputNumString = "";

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumString += "1";
                input.setText(inputNumString);
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumString += "2";
                input.setText(inputNumString);
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumString += "3";
                input.setText(inputNumString);
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumString += "4";
                input.setText(inputNumString);
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumString += "5";
                input.setText(inputNumString);
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumString += "6";
                input.setText(inputNumString);
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumString += "7";
                input.setText(inputNumString);
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumString += "8";
                input.setText(inputNumString);
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumString += "9";
                input.setText(inputNumString);
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumString += "0";
                input.setText(inputNumString);
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumString += ".";
                input.setText(inputNumString);
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumString += "+";
                input.setText(inputNumString);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumString += "-";
                input.setText(inputNumString);
            }
        });

        times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumString += "x";
                input.setText(inputNumString);
            }
        });

        by.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumString += "/";
                input.setText(inputNumString);
            }
        });

        sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumString += "√"; // Assuming √ is for sqrt
                input.setText(inputNumString);
            }
        });

        pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleLastNumberSign();
                input.setText(inputNumString);
            }
        });


        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumString += "%";
                input.setText(inputNumString);
            }
        });


        all_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumString = "";
                input.setText(inputNumString);
            }
        });

        equals2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double result = 0;
                if (inputNumString.contains("%")) {
                    String[] splitPercent = new String[3];
                    splitPercent = inputNumString.split("%");
                    double number = Double.parseDouble(splitPercent[0]);
                    result = number/100.0;
                    input.setText(String.valueOf(result));
                } else {
                    result = evaluate(inputNumString);
                    if (Double.isNaN(result)) {
                        input.setText("Error");
                    } else {
                        input.setText(String.valueOf(result));
                    }
                    inputNumString = "";
                }
            }
        });


    }

    private double evaluate(String expression) {
        // Normalize the expression and handle common input corrections
        expression = expression.replaceAll("\\s+", "")
                .replaceAll("\\+-", "-")
                .replaceAll("--", "+")
                .replaceAll("\\*\\+", "*")
                .replaceAll("/\\+", "/");

        // Check for and handle invalid trailing operators
        if (expression.matches(".*[+*/]$")) {
            return Double.NaN; // Return NaN for invalid expressions
        }

        try {
            // Build and evaluate the expression using exp4j
            Expression e = new ExpressionBuilder(expression)
                    .build();
            return e.evaluate();
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN; // Return NaN to indicate an error
        }
    }

    private void toggleLastNumberSign() {
        if (inputNumString.isEmpty()) return;

        // Check if there's a number to toggle at the end
        int lastNonDigitCharIndex = -1;
        for (int i = inputNumString.length() - 1; i >= 0; i--) {
            if (!Character.isDigit(inputNumString.charAt(i)) && inputNumString.charAt(i) != '.') {
                lastNonDigitCharIndex = i;
                break;
            }
        }

        if (lastNonDigitCharIndex == -1) {  // Only one number in the input
            inputNumString = "-" + inputNumString;  // Toggle the sign of the number
        } else {
            String lastNumber = inputNumString.substring(lastNonDigitCharIndex + 1);
            if (lastNumber.startsWith("-")) {
                lastNumber = lastNumber.substring(1);  // Remove negative sign
            } else {
                lastNumber = "-" + lastNumber;  // Add negative sign
            }
            inputNumString = inputNumString.substring(0, lastNonDigitCharIndex + 1) + lastNumber;
        }
        input.setText(inputNumString);
    }



}
