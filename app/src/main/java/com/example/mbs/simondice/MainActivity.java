package com.example.mbs.simondice;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity{
    Button botonesColor[];
    TextView textoLvl;
    TextView textoPuntuacion;
    ImageButton  btnStart;
    ArrayList<Integer> acertados = new ArrayList<>();
    ArrayList<Integer> combinacion = new ArrayList<>();

    boolean aux;
    int nivel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textoLvl = (TextView)findViewById(R.id.textoLvl);
        textoPuntuacion = (TextView)findViewById(R.id.textoPuntuacion);
        botonesColor = new Button[]{
                (Button)findViewById(R.id.btn1),
                (Button)findViewById(R.id.btn2),
                (Button)findViewById(R.id.btn3),
                (Button)findViewById(R.id.btn4)


        };


        btnStart = findViewById(R.id.btnStart);

        botonesColor[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobar(0);
            }
        });

        botonesColor[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobar(1);
            }
        });

        botonesColor[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobar(2);
            }
        });
        botonesColor[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobar(3);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


           combinacionColores();

            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   public void combinacionColores() {
     Handler handler1 = new Handler();
     final Handler  handler2 = new Handler();
     final int resultado = (int) (Math.random()*4);
     int time =1000;
     combinacion.add(resultado);

     for(int i=0;i<combinacion.size();i++) {
         final int fin =i;
         handler1.postDelayed(new Runnable() {
             @Override
             public void run() {
                 botonesColor[combinacion.get(fin)].setPressed(true);

                 handler2.postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         botonesColor[combinacion.get(fin)].setPressed(false);

                     }

                 }, 500);

             }
         }, time*i+500);

     }
    textoLvl.setText(String.valueOf(combinacion.size()));
    textoPuntuacion.setText(String.valueOf(combinacion.size()));
   }





public void comprobar(int comprobarColor){
    final Toast txtPerdedor = Toast.makeText(getApplicationContext(),"HAS PERDIDO",Toast.LENGTH_SHORT);
    if(comprobarColor==0){
        acertados.add(0);
    }else if(comprobarColor==1){

        acertados.add(1);
    }else if(comprobarColor==2){
        acertados.add(2);
    }else{

        acertados.add(3);
    }

    if(combinacion.size()==acertados.size()){
        for(int i=0;i<combinacion.size();i++){
            if(combinacion.get(i).equals(acertados.get(i))){

            }else{
                txtPerdedor.show();
                acertados.clear();
                combinacion.clear();
            }
        }
    }

}

}
