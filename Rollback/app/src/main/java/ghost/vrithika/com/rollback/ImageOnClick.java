package ghost.vrithika.com.rollback;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

/**
 * Created by Vrithika on 23-06-2016.
 */
public class ImageOnClick implements View.OnClickListener {

    int number;
    Image imageObject;
    Activity activity;

    public ImageOnClick(int num, Image image, Activity activity){
        this.number = num;
        this.imageObject = image;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        Stack<Integer> stack = Main_Rollback.getStack();
        if(stack.pop()!=number){
            for(Image imageObject : Main_Rollback.imageList){
                imageObject.image.setClickable(false);
            }
            Toast.makeText(activity,"Game Over", Toast.LENGTH_LONG).show();
            Toast.makeText(activity,"Your Score is : "+Integer.toString(Main_Rollback.score), Toast.LENGTH_LONG).show();
            while(!stack.isEmpty()){
                stack.pop();

            }
        }
        else {
            imageObject.image.setAlpha(0f);
            imageObject.image.setClickable(false);

            if (stack.isEmpty()) {
                Button start = (Button) activity.findViewById(R.id.button2);
                start.setEnabled(true);
                start.setAlpha(1f);
                start.setText("Next");
                Toast.makeText(activity, "You Cleared this Level", Toast.LENGTH_LONG).show();
                int lvl;
                lvl=Main_Rollback.level%4;
                if(lvl==0)
                    lvl=4;
                int time;
                TextView text6 = (TextView) activity.findViewById(R.id.textView6);
                time=Integer.parseInt(text6.getText().toString());
                Main_Rollback.score=(Main_Rollback.score+(time*lvl));
                TextView text4 = (TextView) activity.findViewById(R.id.textView4);
                text4.setText(Integer.toString(Main_Rollback.score));
            }
        }
    }

}
