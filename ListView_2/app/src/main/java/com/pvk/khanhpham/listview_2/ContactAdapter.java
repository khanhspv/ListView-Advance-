package com.pvk.khanhpham.listview_2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends BaseAdapter {

    private List<ContactModel> listContacts;
    private Context nContext;
    private IOnChildItemClick iOnChildItemClick;


    public ContactAdapter(Context nContext, List<ContactModel> listContacts) {
        this.listContacts = listContacts;
        this.nContext = nContext;
    }

    public void registerChildItemClick(IOnChildItemClick iOnChildItemClick){
        this.iOnChildItemClick = iOnChildItemClick;
    }

    public void unRegisterChildItemClick(){
        this.iOnChildItemClick = null;
    }

    @Override
    public int getCount(){
        return listContacts.size();
    }

    @Override
    public Object getItem(int i){
        return null;
    }
    @Override
    public long getItemId(int i){
        return 0;
    }
    @Override
    public View getView(final int i, View convertView, ViewGroup parent){
        View rowView = convertView;
        if (rowView == null)
        {
            LayoutInflater inflater = ((Activity)nContext).getLayoutInflater();
            rowView = inflater.inflate(R.layout.item_contact,null);

            ViewHolder holder = new  ViewHolder();
            holder.tvName = (TextView)rowView.findViewById(R.id.tvName);
            holder.tvPhone = (TextView)rowView.findViewById(R.id.tvPhone);
            holder.ivAvatar = (ImageView) rowView.findViewById(R.id.ivAvatar);
            holder.btCall =(ImageButton) rowView.findViewById(R.id.btCall);
            rowView.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.tvName.setText(listContacts.get(i).getName());
        holder.tvPhone.setText(listContacts.get(i).getPhone());
        holder.ivAvatar.setImageResource(listContacts.get(i).getImage());

        holder.btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCall(i);
            }
        });
        holder.btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnChildItemClick.onItemChildClick(i);
            }
        });
        return rowView;
    }

    private void onCall(int position){
        ContactModel contact = listContacts.get(position);
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel"+contact.getPhone()));
        if (ActivityCompat.checkSelfPermission(nContext, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            return;
        }
        nContext.startActivity(intent);
    }

    static class ViewHolder{
        ImageButton btCall;
        TextView tvName;
        TextView tvPhone;
        ImageView ivAvatar;

    }
}

