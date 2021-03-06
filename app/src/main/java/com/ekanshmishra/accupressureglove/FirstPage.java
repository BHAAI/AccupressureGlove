package com.ekanshmishra.accupressureglove;

/**
 * Created by ekanshmishra on 09/05/17.
 */

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@EActivity(R.layout.first_page)
public class FirstPage extends AppCompatActivity {

    public static final String EXTRA_ADDRESS = "address";

    @ViewById(R.id.lv_newDevices) ListView listView;

    Context context;
    ArrayAdapter<String> listAdapter;
    BluetoothAdapter bluetoothAdapter = null;
    Set pairedDevices;

    @Click(R.id.b_scan)
    protected void buttonScanClicked() {
        pairedDevicesList();
    }

    @AfterViews
    protected void afterViews() {
        context = this;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null){
            //Show a mensag. that thedevice has no bluetooth adapter
            Toast.makeText(getApplicationContext(), "Bluetooth Device Not Available", Toast.LENGTH_LONG).show();
        } else if (bluetoothAdapter.isEnabled()){

        } else {
            Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnBTon,1);
        }
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                //discovery starts, we can show progress dialog or perform other tasks

            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                //discovery finishes, dismis progress dialog

            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //bluetooth device found

                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Toast.makeText(context, "Found device " + device.getName(), Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);

        super.onDestroy();
    }


    private void pairedDevicesList()
    {
        pairedDevices = bluetoothAdapter.getBondedDevices();
        ArrayList list = new ArrayList();

        if (pairedDevices.size()>0) {
            List<BluetoothDevice> devices = new ArrayList<>();
            devices.addAll(pairedDevices);

            for (BluetoothDevice device : devices) {
                list.add(device.getName() + "\n" + device.getAddress());
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices Found.", Toast.LENGTH_LONG).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the device MAC address, the last 17 chars in the View
                String info = ((TextView) view).getText().toString();
                String address = info.substring(info.length() - 17);
                // Make an intent to start next activity.
                Intent intent = new Intent(context, MainActivity_.class);
                //Change the activity.
                intent.putExtra(EXTRA_ADDRESS, address);
                startActivity(intent);
            }
        }); //Method called when the device from the list is clicked

    }
}