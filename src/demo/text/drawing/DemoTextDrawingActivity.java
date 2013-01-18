package demo.text.drawing;

import java.io.IOException;
import java.io.InputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class DemoTextDrawingActivity extends Activity {
	
    private static final TextPaint TEXT_PAINT = new TextPaint();
    {
    	TEXT_PAINT.setTextSize(24);
    	TEXT_PAINT.setAntiAlias(true);
    }
	private static final LayoutParams LAYOUT_PARAMS = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
	//private static final String LONG_STRING = "Please try this is a long text to fix into this view. Thanks!";
	private static final String LONG_STRING = "Start\n안녕하세요! 다시 만나서 반가워요! 이쪽으로 오세요 안녕하세요! 다시 만나서 반가워요! 이쪽으로 오세요 안녕하세요! 다시 만나서 반가워요! 이쪽으로 오세요 안녕하세요! 다시 만나서 반가워요! 이쪽으로 오세요 안녕하세요! 다시 만나서 반가워요! 이쪽으로 오세요 안녕하세요! 다시 만나서 반가워요! 이쪽으로 오세요 안녕하세요! 다시 만나서 반가워요! 이쪽으로 오세요 안녕하세요! 다시 만나서 반가워요! 이쪽으로 오세요 안녕하세요! 다시 만나서 반가워요! 이쪽으로 오세요 안녕하세요! 다시 만나서 반가워요! 이쪽으로 오세요 ";
     
	/** Called when the activity is first created. */
    @SuppressLint("DrawAllocation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        final String content;
        
        try {
        	InputStream is = getAssets().open("1-1.txt");
            
            // We guarantee that the available method returns the total
            // size of the asset...  of course, this does mean that a single
            // asset can't be more than 2 gigs.
            int size = is.available();
            
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            
         // Convert the buffer into a string.
            content = new String(buffer);
    	} catch (IOException e) {
    		// Should never happen!
    		throw new RuntimeException(e);
    	}
        
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        
        View view02 = new View(this){
        	@Override
        	protected void onDraw(Canvas canvas) {
        		super.onDraw(canvas);
        		TEXT_PAINT.setColor(Color.WHITE);
        		//Create a static layout to draw 
				StaticLayout staticLayout = new StaticLayout(
						content, 0, content.length(),
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
					
					String lineString = content.substring(start, end);
					
					TEXT_PAINT.setColor(Color.WHITE);
					canvas.drawText(lineString, staticLayout.getLineLeft(i), staticLayout.getLineBottom(i), TEXT_PAINT);
				}
				
        	}
        };
        view02.setBackgroundColor(Color.DKGRAY);
		linearLayout.addView(view02, LAYOUT_PARAMS);

        setContentView(linearLayout);
    }
}