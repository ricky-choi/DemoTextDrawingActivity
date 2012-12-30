package demo.text.drawing;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class DemoTextDrawingActivity extends Activity {
	
    private static final TextPaint TEXT_PAINT = new TextPaint();
    {
    	TEXT_PAINT.setTextSize(30);
    	TEXT_PAINT.setAntiAlias(true);
    }
	private static final LayoutParams LAYOUT_PARAMS = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
	//private static final String LONG_STRING = "Please try this is a long text to fix into this view. Thanks!";
	private static final String LONG_STRING = "Start\n안녕하세요! 다시 만나서 반가워요! 이쪽으로 오세요 안녕하세요! 다시 만나서 반가워요! 이쪽으로 오세요 안녕하세요! 다시 만나서 반가워요! 이쪽으로 오세요 안녕하세요! 다시 만나서 반가워요! 이쪽으로 오세요 안녕하세요! 다시 만나서 반가워요! 이쪽으로 오세요 안녕하세요! 다시 만나서 반가워요! 이쪽으로 오세요 안녕하세요! 다시 만나서 반가워요! 이쪽으로 오세요 안녕하세요! 다시 만나서 반가워요! 이쪽으로 오세요 안녕하세요! 다시 만나서 반가워요! 이쪽으로 오세요 안녕하세요! 다시 만나서 반가워요! 이쪽으로 오세요 ";

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        View view01 = new View(this){
        	@Override
        	protected void onDraw(Canvas canvas) {
        		super.onDraw(canvas);
        		TEXT_PAINT.setColor(Color.WHITE);
        		//Drawing the text at middle of the view
				canvas.drawText(LONG_STRING, 0, getHeight()/2, TEXT_PAINT);
				TEXT_PAINT.setColor(Color.RED);
				//Here we draw the middle line
				canvas.drawLine(0, getHeight()/2, getWidth(), getHeight()/2, TEXT_PAINT);

				Rect rect = new Rect();
				TEXT_PAINT.setColor(Color.BLUE);
				TEXT_PAINT.getTextBounds(LONG_STRING, 0, LONG_STRING.length(), rect);
				//Measure the height and draw line on top of text
				canvas.drawLine(0, getHeight()/2-rect.height(), getWidth(), getHeight()/2-rect.height(), TEXT_PAINT);
        	}
        };
        view01.setBackgroundColor(Color.LTGRAY);
		linearLayout.addView(view01, LAYOUT_PARAMS);
        
        View view02 = new View(this){
        	@Override
        	protected void onDraw(Canvas canvas) {
        		super.onDraw(canvas);
        		TEXT_PAINT.setColor(Color.WHITE);
        		//Create a static layout to draw 
				StaticLayout staticLayout = new StaticLayout(
						LONG_STRING, 0, LONG_STRING.length(),
						TEXT_PAINT, getWidth(),android.text.Layout.Alignment.ALIGN_NORMAL , 
						1.0f, 1.0f, false);
				//staticLayout.draw(canvas);
				
				
				for(int i = 0 ; i < staticLayout.getLineCount();i++){
					
					if (staticLayout.getLineBottom(i) > getHeight()) break;
					
					/*
					TEXT_PAINT.setColor(Color.BLUE);
					canvas.drawLine(0, staticLayout.getLineTop(i), getWidth(), staticLayout.getLineTop(i), TEXT_PAINT);
					
					TEXT_PAINT.setColor(0x7FFFFFFF&Color.YELLOW);
					canvas.drawRect(staticLayout.getLineLeft(i), staticLayout.getLineTop(i), staticLayout.getLineRight(i), staticLayout.getLineBottom(i), TEXT_PAINT);
					
					TEXT_PAINT.setColor(Color.RED);
					canvas.drawLine(0, staticLayout.getLineBaseline(i), getWidth(), staticLayout.getLineBaseline(i), TEXT_PAINT);
					
					TEXT_PAINT.setColor(Color.MAGENTA);
					canvas.drawLine(0, staticLayout.getLineBottom(i), getWidth(), staticLayout.getLineBottom(i), TEXT_PAINT);
					*/
					
					
					int start = staticLayout.getLineStart(i);
					int end = staticLayout.getLineEnd(i);
					
					String lineString = LONG_STRING.substring(start, end);
					
					TEXT_PAINT.setColor(Color.WHITE);
					canvas.drawText(lineString, staticLayout.getLineLeft(i), staticLayout.getLineBottom(i), TEXT_PAINT);
				}
				
        	}
        };
        view02.setBackgroundColor(Color.DKGRAY);
		linearLayout.addView(view02, LAYOUT_PARAMS);
		
        View view03 = new View(this){
        	@Override
        	protected void onDraw(Canvas canvas) {
        		super.onDraw(canvas);
        		TEXT_PAINT.setColor(Color.WHITE);
				StaticLayout staticLayout = new StaticLayout(
						LONG_STRING, 0, LONG_STRING.length(),
						TEXT_PAINT, getWidth(),android.text.Layout.Alignment.ALIGN_CENTER , 
						1.0f, 1.0f, false);
				
        		TEXT_PAINT.setColor(Color.WHITE);
				canvas.drawText(LONG_STRING, 0, getHeight()/2+(staticLayout.getLineBaseline(0)-staticLayout.getLineBottom(0)/2), TEXT_PAINT);
				TEXT_PAINT.setColor(Color.RED);
				canvas.drawLine(0, getHeight()/2, getWidth(), getHeight()/2, TEXT_PAINT);
				
				TEXT_PAINT.setColor(0x7FFFFFFF&Color.GREEN);
				canvas.drawRect(0, getHeight()/2-staticLayout.getLineBottom(0)/2, getWidth(), getHeight()/2+staticLayout.getLineBottom(0)/2, TEXT_PAINT);

        	}
        };
        view03.setBackgroundColor(Color.GRAY);
		linearLayout.addView(view03, LAYOUT_PARAMS);
        setContentView(linearLayout);
    }
}