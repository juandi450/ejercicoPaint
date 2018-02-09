package databinding.android.vogella.com.paint;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import databinding.android.vogella.com.paint.Pojo.Circulo;
import databinding.android.vogella.com.paint.Pojo.Linea;
import databinding.android.vogella.com.paint.Pojo.Cuadrado;

public class VistaPintada extends View {
    private Bitmap mapaDeBits;
    private Canvas canvasFondo;
    private String color = "#B576AD";
    private float xi, yi, xf, yf;
    private float radio;
    private boolean pintando = false;
    private Path rectaPoligonal = new Path();
    private String queEs = "";
    private ArrayList<Object> dibujos = new ArrayList<>();

    public VistaPintada(Context context) {
        super(context);
    }
    public VistaPintada(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void circle(Paint pincel, Canvas canvas){
        pincel.setColor(Color.parseColor(getColor()));
        pincel.setStrokeWidth(10);
        pincel.setStyle(Paint.Style.STROKE);
        if (pintando) {
            canvas.drawCircle(xi, yi, radio, pincel);
        } else {
            Circulo circulo = new Circulo(xi, yi, radio, pincel);
            canvasFondo.drawCircle(xi, yi, radio, pincel);
            dibujos.add(circulo);
        }

    }

    private void square(Paint pincel, Canvas canvas){
        pincel.setColor(Color.parseColor(getColor()));
        pincel.setStrokeWidth(10);
        pincel.setStyle(Paint.Style.STROKE);
        if (pintando) {
            canvas.drawRect(xi, yi, xf, yf ,pincel);
            Cuadrado square = new Cuadrado(xi, yi, xf, yf ,pincel);
            dibujos.add(square);
        } else {
            canvasFondo.drawRect(xi, yi, xf, yf ,pincel);
        }
    }

    private void line(Paint pincel, Canvas canvas){
        pincel.setColor(Color.parseColor(getColor()));
        pincel.setStrokeWidth(10);
        pincel.setStyle(Paint.Style.STROKE);
        if (pintando) {
            canvas.drawLine(xi, yi, xf, yf, pincel);
        } else{
            Linea lines = new Linea(xi, yi, xf, yf ,pincel);
            dibujos.add(lines);
            canvasFondo.drawLine(xi, yi, xf, yf, pincel);
        }
    }

    private void deleteAll(){
        dibujos.clear();
        canvasFondo.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint pincel = new Paint();
        canvas.drawBitmap(mapaDeBits, 0, 0, null);
        switch (queEs){
            case "circle":
                circle(pincel, canvas);
                break;
            case "square":
                square(pincel, canvas);
                break;
            case "line":
                line(pincel, canvas);
                break;
            case "deleteAll":
                deleteAll();
                break;
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mapaDeBits = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvasFondo = new Canvas(mapaDeBits);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.v("TAG", "onTouchEvent");
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // Pulsar el dedo
                pintando = true;
                xf = xi = x;
                yf = yi = y;
                //rectaPoligonal.reset();
                //rectaPoligonal.moveTo(xi, yi);
                break;
            case MotionEvent.ACTION_MOVE: // Mover el dedo
                //xi = xf;
                //yi = yf;
                xf = x;
                yf = y;
                //rectaPoligonal.quadTo(xi, yi, (x + xi) / 2, (y + yi) / 2);
                break;
            case MotionEvent.ACTION_UP: // Quitar el dedo
                pintando = false;
                //i = xf;
                //yi = yf;
                xf = x;
                yf = y;
                break;
        }
        radio = (float) Math.sqrt(Math.pow(xf - xi, 2) + Math.pow(yf - yi, 2));

        invalidate(); // Le dices a la vista que se tiene que redibujar
        return true;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setQueEs(String queEs) {
        this.queEs = queEs;
    }

    public String getColor() {
        return color;
    }
}
