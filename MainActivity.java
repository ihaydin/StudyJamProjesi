package com.ihaydin.x_o_x;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {


    int oyuncu = 0;

    boolean oyunDurumu = true;


    int[] oyunAlani = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] kazanmaPozisyonlari = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void tiklama(View view) {

        ImageView taslar = (ImageView) view;

        int basilanTas = Integer.parseInt(taslar.getTag().toString());

        if (oyunAlani[basilanTas] == 2 && oyunDurumu) {

            oyunAlani[basilanTas] = oyuncu;

            taslar.setTranslationY(-1000f);

            if (oyuncu == 0) {

                taslar.setImageResource(R.drawable.yellow);

                oyuncu = 1;

            } else {

                taslar.setImageResource(R.drawable.red);

                oyuncu = 0;

            }

            taslar.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for (int[] kazananPozisyon : kazanmaPozisyonlari) {

                if (oyunAlani[kazananPozisyon[0]] == oyunAlani[kazananPozisyon[1]] &&
                        oyunAlani[kazananPozisyon[1]] == oyunAlani[kazananPozisyon[2]] &&
                        oyunAlani[kazananPozisyon[0]] != 2) {


                    oyunDurumu = false;

                    String kazanan = "Kırmızı Taraf";

                    if (oyunAlani[kazananPozisyon[0]] == 0) {

                        kazanan = "Sarı Taraf";

                    }

                    TextView mesaj = (TextView) findViewById(R.id.kazananMesaj);

                    mesaj.setText(kazanan + " Kazandı!");

                    LinearLayout layout = (LinearLayout)findViewById(R.id.tekrarOynaZemini);

                    layout.setVisibility(View.VISIBLE);

                } else {

                    boolean oyunBitti = true;

                    for (int siralama : oyunAlani) {

                        if (siralama == 2) oyunBitti = false;

                    }

                    if (oyunBitti) {

                        TextView mesaj = (TextView) findViewById(R.id.kazananMesaj);

                        mesaj.setText("Berabere");

                        LinearLayout layout = (LinearLayout)findViewById(R.id.tekrarOynaZemini);

                        layout.setVisibility(View.VISIBLE);

                    }

                }

            }
        }


    }

    public void tekrarOyna(View view) {

        oyunDurumu = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.tekrarOynaZemini);

        layout.setVisibility(View.INVISIBLE);

        oyuncu = 0;

        for (int i = 0; i < oyunAlani.length; i++) {

            oyunAlani[i] = 2;

        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for (int i = 0; i< gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
