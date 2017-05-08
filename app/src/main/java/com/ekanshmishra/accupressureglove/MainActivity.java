package com.ekanshmishra.accupressureglove;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private String bluetoothClientAddress = "";

    protected String EXTRA_ADDRESS = "address";

    @ViewById(R.id.b_eye) Button button_eye;
    @ViewById(R.id.b_heart) Button button_heart;
    @ViewById(R.id.b_kidney) Button button_kidney;
    @ViewById(R.id.b_sinus) Button button_sinus;
    @ViewById(R.id.b_stomach) Button button_stomach;
    @ViewById(R.id.b_stop) Button button_stop;

    @AfterViews
    protected void afterViews() {
        bluetoothClientAddress = getIntent().getStringExtra(FirstPage.EXTRA_ADDRESS);
    }

    @Click(R.id.b_eye)
    protected void buttonEyeClicked() {
        Intent intent = new Intent(MainActivity.this, ConnectionActivity_.class);
        intent.putExtra("message", "e");
        intent.putExtra(FirstPage.EXTRA_ADDRESS, bluetoothClientAddress);
        startActivity(intent);
    }

    @Click(R.id.b_heart)
    protected void buttonHeartClicked() {
        Intent intent = new Intent(MainActivity.this, ConnectionActivity_.class);
        intent.putExtra("message", "h");
        intent.putExtra(EXTRA_ADDRESS, bluetoothClientAddress);
        startActivity(intent);
    }

    @Click(R.id.b_kidney)
    protected void buttonKidneyClicked() {
        Intent intent = new Intent(MainActivity.this, ConnectionActivity_.class);
        intent.putExtra("message", "k");
        intent.putExtra(EXTRA_ADDRESS, bluetoothClientAddress);
        startActivity(intent);
    }

    @Click(R.id.b_sinus)
    protected void buttonSinusClicked() {
        Intent intent = new Intent(MainActivity.this, ConnectionActivity_.class);
        intent.putExtra("message", "i");
        intent.putExtra(EXTRA_ADDRESS, bluetoothClientAddress);
        startActivity(intent);
    }

    @Click(R.id.b_stomach)
    protected void buttonStomachClicked() {
        Intent intent = new Intent(MainActivity.this, ConnectionActivity_.class);
        intent.putExtra("message", "s");
        intent.putExtra(EXTRA_ADDRESS, bluetoothClientAddress);
        startActivity(intent);
    }

    @Click(R.id.b_stop)
    protected void buttonStopClicked() {
        Intent intent = new Intent(MainActivity.this, ConnectionActivity_.class);
        intent.putExtra("message", "stop");
        intent.putExtra(EXTRA_ADDRESS, bluetoothClientAddress);
        startActivity(intent);
    }
}
