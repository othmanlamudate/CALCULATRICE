package com.example.CALCULATRICE;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    private TextView ecran,resl;
    private Button[] numbers=new  Button[10];
    private Button[] op=new Button[6];
    private Boolean isSetop=false;
    private Button egale;
    private Button sup;


    private ArrayList<Double> num=new ArrayList<Double>();
    private ArrayList<String> operateur=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sup=this.findViewById(R.id.b);
        ecran=this.findViewById(R.id.ecran);
        numbers[0]=this.findViewById(R.id.b0);
        numbers[1]=this.findViewById(R.id.b1);
        numbers[2]=this.findViewById(R.id.b2);
        numbers[3]=this.findViewById(R.id.b3);
        numbers[4]=this.findViewById(R.id.b4);
        numbers[5]=this.findViewById(R.id.b5);
        numbers[6]=this.findViewById(R.id.b6);
        numbers[7]=this.findViewById(R.id.b7);
        numbers[8]=this.findViewById(R.id.b8);
        numbers[9]=this.findViewById(R.id.b9);
        resl=this.findViewById(R.id.res);
        for (int i=0;i<10;i++){
            Onclick(numbers[i]);
        }

        op[0]=this.findViewById(R.id.sus);
        op[1]=this.findViewById(R.id.div);
        op[2]=this.findViewById(R.id.add);
        op[3]=this.findViewById(R.id.b10);
        op[4]=this.findViewById(R.id.b11);
        op[5]=this.findViewById(R.id.b12);

        for (int i=0;i<6;i++){
            Onclickop(op[i]);
        }


        egale=this.findViewById(R.id.egal);
        egale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    resl.setText(calculate(ecran.getText().toString()));
                    ecran.setText(resl.getText());

                }
                catch (Exception e){
                    e.printStackTrace();
                }




            }
        });
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String[] temp;
                    temp=ecran.getText().toString().split("");
                    String s="";
                    for (int i=0;i<temp.length-1;i++){
                        s+=temp[i];
                    }

                    ecran.setText(s);

                }
                catch (Exception e){
                    e.printStackTrace();
                }




            }
        });
    }

    private void Onclick(Button b ){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ecran.setText(ecran.getText().toString()+b.getText().toString());
                isSetop=true;
            }
        });

    }
    private void Onclickop(Button b ){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSetop) {
                    ecran.setText(ecran.getText().toString() + b.getText().toString());
                    isSetop=false;
                }else{
                    String[] T;
                    T=ecran.getText().toString().split("");
                    T[T.length-1]=b.getText().toString();
                    String str="";
                    for(int i=0;i<T.length;i++){
                        str+=T[i];
                    }
                    ecran.setText(str);
                }
            }
        });

    }







    protected String calculate(String s){
            String[] T=s.split("");
            ArrayList<String> T1=new ArrayList<String>();
            String[] T2=s.split("");
            int i;
            double temp;
            String x="";
            for(i=0;i<T.length;i++){
                if(T[i].equals("*") ||  T[i].equals("/")  ||  T[i].equals("-") ||  T[i].equals("+") ||  T[i].equals("%") ||  T[i].equals("D")  )
                {
                    if(!x.equals("")) {
                        T1.add(x);
                        T1.add(T[i]);
                        x = "";
                    }
                }
                else{
                    x+=T[i];
                }

                if(i==T.length-1 ){
                    T1.add(x);
                }
            }


            if(T[1].equals("-")) {
                   T1.set(0, "-" + T1.get(0));
            }
           


            for(i=0;i<T1.size();i++){
                if(T1.get(i).equals("*"))
                {
                    temp=Double.valueOf(T1.get(i-1))*Double.valueOf(T1.get(i+1));
                    T1.set(i-1,String.valueOf(temp));
                    T1.remove(i);
                    T1.remove(i);
                    i--;
                }
                if(T1.get(i).equals("%"))
                {
                    temp=Double.valueOf(T1.get(i-1))%Double.valueOf(T1.get(i+1));
                    T1.set(i-1,String.valueOf(temp));
                    T1.remove(i);
                    T1.remove(i);
                    i--;
                }
                if(T1.get(i).equals("D"))
                {
                    temp=Double.valueOf(T1.get(i-1))/Double.valueOf(T1.get(i+1));
                    T1.set(i-1,String.valueOf(Integer.valueOf((int) temp)));
                    T1.remove(i);
                    T1.remove(i);
                    i--;
                }
                if(T1.get(i).equals("/"))
                {
                    temp=Double.valueOf(T1.get(i-1))/Double.valueOf(T1.get(i+1));
                    T1.set(i-1,String.valueOf(temp));
                    T1.remove(i);
                    T1.remove(i);
                    i--;
                }
            }
            for(i=0;i<T1.size();i++){
                if(T1.get(i).equals("+"))
                {
                    temp=Double.valueOf(T1.get(i-1))+Double.valueOf(T1.get(i+1));
                    T1.set(i-1,String.valueOf(temp));
                    T1.remove(i);
                    T1.remove(i);
                    i--;
                }
                if(T1.get(i).equals("-"))
                {
                    temp=Double.valueOf(T1.get(i-1))-Double.valueOf(T1.get(i+1));
                    T1.set(i-1,String.valueOf(temp));
                    T1.remove(i);
                    T1.remove(i);
                    i--;
                }
            }

            return T1.get(0);
    }
}