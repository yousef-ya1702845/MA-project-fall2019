package qa.edu.qu.cmps312.todolistapplication.activities;


import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import qa.edu.qu.cmps312.todolistapplication.R;


public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter adapter;
    private String collges[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.collogeList);
        collges=getResources().getStringArray(R.array.collges);

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,collges);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Intent intent = new Intent(MainActivity.this,Engineering.class);
                    startActivity(intent);
                }else if(i==1){
                   Intent Mintent = new Intent(MainActivity.this,MediaColloge.class);
                   startActivity(Mintent);
                }else if(i==2){
                    Intent intent = new Intent(MainActivity.this,Medical.class);
                    startActivity(intent);
                }
                else if(i==3){
                    Intent intent = new Intent(MainActivity.this,Bussiness.class);
                    startActivity(intent);
                }

            }
        });
    }
}
