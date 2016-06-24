package ghost.vrithika.com.rollback;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Main_Rollback extends AppCompatActivity {

    static int level, score;
    private static Stack<Integer> stack = new Stack<>();
    static List<Image> imageList = new LinkedList<>();
    CountDownTimer timer;
    //private Bitmap imageBitmap = null;
    //private Bundle_view bundles;
    //private RelativeLayout container;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public static Stack<Integer> getStack(){
        return stack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__rollback);
        score = 0;
        level = 0;

//        container = (RelativeLayout) findViewById(R.id.rollback_container);
  //      bundles = new Bundle_view(this);

        // Some setup of the view.
        //bundles.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        //container.addView(bundles);

        Button start = (Button) findViewById(R.id.button2);
        assert start!=null;
        start.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        if(level==0) {
                            TextView text = (TextView) findViewById(R.id.textView);
                            text.setText("LEVEL");
                            TextView text2 = (TextView) findViewById(R.id.textView2);
                            text2.setText("1");
                            TextView text3 = (TextView) findViewById(R.id.textView3);
                            text3.setText("Score: ");
                            TextView text4 = (TextView) findViewById(R.id.textView4);
                            text4.setText("0");
                            TextView text5 = (TextView) findViewById(R.id.textView5);
                            text5.setText("Timer: ");
                            TextView text6 = (TextView) findViewById(R.id.textView6);
                            text6.setText("45");
                            TextView text7 = (TextView) findViewById(R.id.textView7);
                            text7.setText("");
                            TextView text8 = (TextView) findViewById(R.id.textView8);
                            text8.setText("");
                            level=1;
                        }
                        else{
                            TextView text2 = (TextView) findViewById(R.id.textView2);
                            level++;
                            text2.setText(Integer.toString(level));

                        }
                        int t=17000-(level*1000);
                        if(t<6000)
                            t=6000;

                        final TextView text6 = (TextView) findViewById(R.id.textView6);
                        timer=new CountDownTimer(t, 100) {

                            public void onTick(long millisUntilFinished) {
                                text6.setText(Long.toString(millisUntilFinished / 1000));
                                if(stack.isEmpty()){
                                    cancel();
                                }
                            }

                            public void onFinish() {
                                text6.setText("0");
                                while(!stack.isEmpty()){
                                    stack.pop();
                                }
                                Toast.makeText(getApplicationContext(),"Game Over", Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(),"Your Score is : "+Integer.toString(Main_Rollback.score), Toast.LENGTH_LONG).show();
                                text6.setText("0");

                                for(Image imageFromList : imageList){
                                    imageFromList.image.setClickable(false);
                                }
                                while(!stack.isEmpty()){
                                    stack.pop();
                                }
                                cancel();
                            }
                        }.start();

                        for(Image imageObject : imageList){
                            imageObject.image.setAlpha(1f);
                            imageObject.image.setClickable(true);
                        }

                     //   imageBitmap  = BitmapFactory.decodeResource(getResources(), R.drawable.pb);
                   //     bundles.initialize(imageBitmap, container);
                        Button start = (Button) findViewById(R.id.button2);
                        start.setEnabled(false);

                        shuffle();
                    }
                }
        );
        Button reset = (Button) findViewById(R.id.button);
        assert reset!=null;
        reset.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        TextView text = (TextView) findViewById(R.id.textView);
                        text.setText("");
                        TextView text2 = (TextView) findViewById(R.id.textView2);
                        text2.setText("");
                        TextView text3 = (TextView) findViewById(R.id.textView3);
                        text3.setText("");
                        TextView text4 = (TextView) findViewById(R.id.textView4);
                        text4.setText("");
                        TextView text5 = (TextView) findViewById(R.id.textView5);
                        text5.setText("");
                        TextView text6 = (TextView) findViewById(R.id.textView6);
                        text6.setText("");
                        TextView text7 = (TextView) findViewById(R.id.textView7);
                        text7.setText("PRESS START!");
                        TextView text8 = (TextView) findViewById(R.id.textView8);
                        text8.setText("(Click in Descending Order)");
                        Button start = (Button) findViewById(R.id.button2);
                        start.setEnabled(true);
                        start.setText("Start");
                        level=0;
                        score=0;
                        //bundles.reset();
                        int i;
                        String a[]=new String[10];
                        for(i=0;i<9;i++){
                            a[i]="w";
                        }
                        display(a);
                        timer.cancel();
                    }
                }
        );
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void shuffle(){
        Random random=new Random();
        int r,i;
        int chk[]=new int[10];

        int c[]=new int[10];

        for(i=0;i<9;i++)
            chk[i]=0;
        int lvl;
        lvl=level%4;
        if(lvl==0)
            lvl=4;
        if(lvl>1) {
            for (i=0;i<9;i++) {
                c[i] = random.nextInt(lvl) + (i * lvl + 1);
            }
        }
        else{
            for(i=0;i<9;i++){
                c[i]=i+1;
            }
        }

        String a[]= new String[10];
        for(i=1;i<=9;i++){
            r=random.nextInt(9);
            if(chk[r]==1){
                i--;
            }
            else{
                chk[r]=1;
                a[r]=Integer.toString(c[i-1]);
                stack.push(c[i-1]);
            }
        }
        display(a);
    }


    void display(String a[]){
        int d;
        ImageView image;
        imageList.clear();

        d = getResources().getIdentifier("p"+a[0], "drawable", getPackageName());
        image = (ImageView) findViewById(R.id.imageView1);
        imageList.add(new Image(image, "p" + a[0]));
        image.setImageResource(d);

        d = getResources().getIdentifier("p"+a[1], "drawable", getPackageName());
        image = (ImageView) findViewById(R.id.imageView2);
        imageList.add(new Image(image, "p" + a[1]));
        image.setImageResource(d);

        d = getResources().getIdentifier("p"+a[2], "drawable", getPackageName());
        image = (ImageView) findViewById(R.id.imageView3);
        imageList.add(new Image(image, "p" + a[2]));
        image.setImageResource(d);

        d = getResources().getIdentifier("p"+a[3], "drawable", getPackageName());
        image = (ImageView) findViewById(R.id.imageView4);
        imageList.add(new Image(image, "p" + a[3]));
        image.setImageResource(d);

        d = getResources().getIdentifier("p"+a[4], "drawable", getPackageName());
        image = (ImageView) findViewById(R.id.imageView5);
        imageList.add(new Image(image, "p" + a[4]));
        image.setImageResource(d);

        d = getResources().getIdentifier("p"+a[5], "drawable", getPackageName());
        image = (ImageView) findViewById(R.id.imageView6);
        imageList.add(new Image(image, "p" + a[5]));
        image.setImageResource(d);

        d = getResources().getIdentifier("p"+a[6], "drawable", getPackageName());
        image = (ImageView) findViewById(R.id.imageView7);
        imageList.add(new Image(image, "p" + a[6]));
        image.setImageResource(d);

        d = getResources().getIdentifier("p"+a[7], "drawable", getPackageName());
        image = (ImageView) findViewById(R.id.imageView8);
        imageList.add(new Image(image, "p"+a[7]));
        image.setImageResource(d);

        d = getResources().getIdentifier("p"+a[8], "drawable", getPackageName());
        image = (ImageView) findViewById(R.id.imageView9);
        imageList.add(new Image(image, "p"+a[8]));
        image.setImageResource(d);

        for(Image imageFromList : imageList){
            try{
                int imageNumber = Integer.parseInt(imageFromList.fileName.substring(1));

                //To do only for images that are numbers
                imageFromList.image.setClickable(true);
                imageFromList.image.setOnClickListener(new ImageOnClick(imageNumber,imageFromList,this));
            } catch(Exception e){}
        }
    }


/*    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("hi","Action was DOWN");
        }
        return super.onTouchEvent(event);
    }
*/
    @Override
    public void onStart() {
        super.onStart();
        //INITIALLY BLANK SCREEN

        int i;
        String a[]=new String[10];
        for(i=0;i<9;i++){
            a[i]="w";
        }
        display(a);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main_Rollback Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://ghost.vrithika.com.rollback/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main_Rollback Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://ghost.vrithika.com.rollback/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}