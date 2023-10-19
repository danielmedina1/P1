package es.riberadeltajo.crono;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout cl = findViewById(R.id.constraintLayout);
        EditText sNum = findViewById(R.id.sNum);
        EditText tNum = findViewById(R.id.tNum);
        EditText dNum = findViewById(R.id.dNum);
        TextView ses = findViewById(R.id.sesiones);
        TextView est = findViewById(R.id.estado);
        ImageButton b = findViewById(R.id.startCont);
        cl.setBackgroundColor(Color.YELLOW);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sNum != null && tNum !=null && dNum != null) {
                    int s = Integer.parseInt(sNum.getText().toString());
                    int t = Integer.parseInt(tNum.getText().toString()) *60000;
                    int d = Integer.parseInt(dNum.getText().toString()) *60000;
                    ses.setText(s+"");
                    cronometroT(t, d, s);
                }

            }
        });
        cl.setBackgroundColor(Color.YELLOW);
        est.setText("En Pausa");


    }
    protected void cronometroT(int time, int des, int ses) {
        ConstraintLayout cl = findViewById(R.id.constraintLayout);
        TextView cont = findViewById(R.id.timer);
        TextView est = findViewById(R.id.estado);
        TextView sesi = findViewById(R.id.sesiones);
        MediaPlayer mp = MediaPlayer.create(this, R.raw.angelicalpad143276);
        sesi.setText(""+ses);
        cl.setBackgroundColor(Color.GREEN);
        cont.setText(time/60000+"");
        est.setText("Hora de Trabajar");
        CountDownTimer c = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int tiempoMinutos = (int) (millisUntilFinished/60000)+1;
                cont.setText(tiempoMinutos+"");


            }

            @Override
            public void onFinish() {
                mp.start();
                cronometroD (des, ses, time);

            }
        };
        c.start();
    }

    protected void cronometroD (int des, int ses, int time) {
        ConstraintLayout cl = findViewById(R.id.constraintLayout);
        TextView cont = findViewById(R.id.timer);
        TextView est = findViewById(R.id.estado);
        TextView sesi = findViewById(R.id.sesiones);
        cl.setBackgroundColor(Color.RED);
        cont.setText(des/60000+"");
        est.setText("Hora de Descansar");
        MediaPlayer mp = MediaPlayer.create(this, R.raw.braindamage148577);
        CountDownTimer c = new CountDownTimer(des, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int tiempoMinutos = (int) (millisUntilFinished/60000)+1;
                cont.setText(tiempoMinutos+"");

            }

            @Override
            public void onFinish() {
                mp.start();
                int i = ses;
                i = i - 1;
                if (i !=0) {
                    cronometroT(time, des, i);
                } else {
                    cl.setBackgroundColor(Color.YELLOW);
                    est.setText("En Pausa");
                    cont.setText("");
                    sesi.setText("");

                }

            }
        };
        c.start();

    }
}