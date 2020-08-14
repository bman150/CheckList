package com.example.checklist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    // Implement variables
    private EditText itemET; // for @id/item_EditText
    private Button addButton; // for @id/add_Button
    private ListView itemListView; // for @id/item_ListView
    private ArrayList<String> items; // to store items input from user to a database
    private ArrayAdapter<String> adapter; // to access and edit items in database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assign variables
        itemET = (EditText) findViewById(R.id.item_EditText);
        addButton = (Button) findViewById(R.id.add_Button);
        itemListView = (ListView) findViewById(R.id.item_ListView);
        items = FileHelper.readData(this);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        itemListView.setAdapter(adapter);
        addButton.setOnClickListener(this);
        itemListView.setOnItemClickListener(this);


    }

    // when the user clicks the addButton
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_Button:
                String itemEntered = itemET.getText().toString();
                adapter.add(itemEntered);
                itemET.setText("");
                FileHelper.writeData(items, this);
                break;
        }
    }

    // when the user clicks on a "to do" item in the checklist
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        items.remove(i); // remove a "to do" item from position 'i'
        adapter.notifyDataSetChanged();
    }
}