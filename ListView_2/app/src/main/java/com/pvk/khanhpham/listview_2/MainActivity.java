package com.pvk.khanhpham.listview_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IOnChildItemClick{

    private ListView lvContact;
    private List<ContactModel> listContacts = new ArrayList<>();
    private ContactAdapter nAdapter;
    private ImageView ivUser;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();

        nAdapter = new ContactAdapter(this,listContacts);
        nAdapter.registerChildItemClick(this);
        lvContact.setAdapter(nAdapter);
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                ContactModel model = listContacts.get(i);
                Toast.makeText(MainActivity.this,model.getName()+":"+model.getPhone(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initData(){
       listContacts.add(new ContactModel("Tran Van A","0123456789",R.drawable.ic_u3));
        listContacts.add(new ContactModel("Tran Van B","0123456789",R.drawable.ic_u3));
        listContacts.add(new ContactModel("Tran Van C","0123456789",R.drawable.ic_u3));
        listContacts.add(new ContactModel("Tran Van D","0123456789",R.drawable.ic_u3));
        listContacts.add(new ContactModel("Tran Van E","0123456789",R.drawable.ic_u3));
        listContacts.add(new ContactModel("Tran Van R","0123456789",R.drawable.ic_u3));
        listContacts.add(new ContactModel("Tran Van F","0123456789",R.drawable.ic_u3));
        listContacts.add(new ContactModel("Tran Van D","0123456789",R.drawable.ic_u3));listContacts.add(new ContactModel("Tran Van A","0123456789",R.drawable.ic_u3));
        listContacts.add(new ContactModel("Tran Van G","0123456789",R.drawable.ic_u3));
        listContacts.add(new ContactModel("Tran Van H","0123456789",R.drawable.ic_u3));

    }

    private void initView(){
        lvContact = (ListView)findViewById(R.id.lvContact);
        ivUser =(ImageView)findViewById(R.id.ivUser);
        tvName=(TextView)findViewById(R.id.tvName);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nAdapter.unRegisterChildItemClick();
    }

    @Override
    public void onItemChildClick(int position) {
        ContactModel contact= listContacts.get(position);
        ivUser.setImageResource(contact.getImage());
        tvName.setText(contact.getName());
    }
}
