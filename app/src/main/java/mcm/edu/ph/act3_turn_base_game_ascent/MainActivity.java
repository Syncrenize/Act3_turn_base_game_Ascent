package mcm.edu.ph.act3_turn_base_game_ascent;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    int heroHP =        1985;
    int heroMinDPT =    60;
    int heroMaxDPT =    111;
    String heroName  =  "Shen";

    int monsHP =        2029;
    int monsMinDPT =    63;
    int monsMaxDPT =    121;
    String monsName  =  "Zed";

    int turnNumber = 1;



    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_main);

        Button nextTurn = findViewById(R.id.btnNextTurn);

        TextView txtMonsName = findViewById(R.id.txtEnemy_name);
        TextView txtHeroName = findViewById(R.id.txtPlayer_name);
        TextView txtMonsHP = findViewById(R.id.txtEnemy_hp);
        TextView txtHeroHP = findViewById(R.id.txtPlayer_hp);
        TextView txtHeroDPT = findViewById(R.id.txtPlayerDPT);
        TextView txtMonsDPT = findViewById(R.id.txtEnemyDPT);

        txtHeroName.setText(heroName);
        txtMonsName.setText(monsName);
        txtHeroHP.setText(String.valueOf(heroHP));
        txtMonsHP.setText(String.valueOf(monsHP));
        txtHeroDPT.setText(heroMinDPT+ " ~ "+heroMaxDPT);
        txtMonsDPT.setText(monsMinDPT+ " ~ "+monsMaxDPT);
        nextTurn.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v){

        Random randomizer = new Random();

        Button nextTurn =       findViewById(R.id.btnNextTurn);
        TextView txtMonsHP =    findViewById(R.id.txtEnemy_hp);
        TextView txtHeroHP =    findViewById(R.id.txtPlayer_hp);
        TextView txtCombatLog = findViewById(R.id.txtTurnLog);

        txtMonsHP.setText(String.valueOf(monsHP));
        txtHeroHP.setText(String.valueOf(heroHP));


        int heroDPT = randomizer.nextInt(heroMaxDPT - heroMinDPT) + heroMinDPT;
        int monsDPT = randomizer.nextInt(monsMaxDPT - monsMinDPT) + monsMinDPT;

        switch (v.getId()){
            case R.id.btnNextTurn:

                if(turnNumber%2 == 1){
                    monsHP = monsHP - heroDPT;
                    turnNumber++;
                    txtCombatLog.setText("The Player dealt " +heroDPT+ " damage to the Enemy");
                    txtCombatLog.setText("The Monster dealt" +monsDPT+ "damage to the Player");
                    txtMonsHP.setText(String.valueOf(monsHP));
                    txtHeroHP.setText(String.valueOf(heroHP));
                    nextTurn.setText("Enemy's Turn ("+turnNumber+ ")");
                    if (monsHP < 0){
                        txtCombatLog.setText("The Player dealt " +heroDPT+ " damage to the Enemy. The Player was Victorious!");
                        heroHP = 1500;
                        monsHP = 1000;
                        turnNumber = 1;
                        nextTurn.setText("Reset Game");
                    }
                }
                else if(turnNumber%2 != 1){
                    heroHP = heroHP - monsDPT;
                    turnNumber++;
                    txtCombatLog.setText("The Monster dealt " +monsDPT+ " damage to the Player");
                    txtHeroHP.setText(String.valueOf(heroHP));
                    nextTurn.setText("Player's Turn ("+turnNumber+ ")");
                    if (heroHP < 0){
                        txtCombatLog.setText("The Monster dealt " +monsDPT+ " damage to the Player. The Player Died");
                        heroHP = 1500;
                        monsHP = 1000;
                        turnNumber = 1;
                        nextTurn.setText("Reset Game");
                    }//
                } //
                break;
        }
    }
}