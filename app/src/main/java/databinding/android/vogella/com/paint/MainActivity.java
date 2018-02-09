package databinding.android.vogella.com.paint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private VistaPintada vistaPintada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        setContentView(R.layout.activity_main);

        Button change = findViewById(R.id.changeColor);
        Button circle = findViewById(R.id.circle);
        Button square = findViewById(R.id.square);
        Button line = findViewById(R.id.Line);
        Button purple = findViewById(R.id.purple);
        Button deleteAll = findViewById(R.id.deleteAll);
        Button undo = findViewById(R.id.undo);
        vistaPintada = findViewById(R.id.view2);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vistaPintada.setColor("#FF0000");
            }
        });
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vistaPintada.setQueEs("circle");
            }
        });
        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vistaPintada.setQueEs("square");
            }
        });
        line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vistaPintada.setQueEs("line");
            }
        });
        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vistaPintada.setColor("#B576AD");
            }
        });
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vistaPintada.setQueEs("deleteAll");
            }
        });

    }
}
