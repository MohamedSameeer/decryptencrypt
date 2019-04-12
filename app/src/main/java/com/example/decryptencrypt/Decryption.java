package com.example.decryptencrypt;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Decryption extends Fragment {


    public Decryption() {
        // Required empty public constructor
    }
    View view;
    EditText mKey,mMessage;
    Button mEncrypt;
    TextView mEncryptMessage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view =inflater.inflate(R.layout.fragment_decryption, container, false);

        initi();
        mEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String k,m;
                k=mKey.getText().toString().trim();
                m=mMessage.getText().toString().trim();
                mEncryptMessage.setText(encrypt(k,m));
            }
        });
        return view;
    }

    void initi(){
        mEncrypt=view.findViewById(R.id.encrypt);
        mKey=view.findViewById(R.id.key);
        mMessage=view.findViewById(R.id.message);
        mEncryptMessage=view.findViewById(R.id.messageEncrypt);
    }
    String encrypt(String key,String message){

        char[]ans=new char[30];
        char[]alpha=new char[30];
        int count=0;
        for(int i=0;i<26;i++){
            alpha[i]= (char) ('a'+i);
        }
        for(int i=0;i<key.length();i++){
            char x=key.charAt(i);
            boolean flag=false;
            if(key.charAt(i)<'a'){
                x= (char) (key.charAt(i)-'A'+'a');
            }
            for(int j=0;j<i;j++){
                if (x==ans[j]){
                    flag=true;
                    break;
                }
            }
            if(flag)
                continue;
            ans[count]=x;
            count++;
        }


        for(int i=0;i<26;i++){
            boolean flag=true;

            for (int j=0;j<key.length();j++){
                if(alpha[i]==ans[j]){
                    flag=false;
                    break;
                }
            }
            if (flag){
                ans[count]=alpha[i];
                count++;
            }
        }

        StringBuilder m = new StringBuilder();

        boolean z=false;
        for(int i=0;i<message.length();i++){
            char x=message.charAt(i);
            if(x==' ' && !z){
                m.append("\n");
                z=true;
                continue;
            }
            else if(x==' '&& z){

                continue;
            }
            if(message.charAt(i)<'a'){
                x= (char) (message.charAt(i)-'A'+'a');
            }
            z=false;
            for(int j=0;j<26;j++){
                if(x==ans[j]){
                    Log.e("kkk",x+"");
                    x=alpha[j];
                    break;
                }
            }
            Log.e("kkk2",x+"");
            m.append(alpha[x -'a']);
        }
        return m.toString();
    }
}
