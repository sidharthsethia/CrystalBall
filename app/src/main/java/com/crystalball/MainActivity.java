package com.crystalball;

import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private CrystalBall mCrystalBall  = new CrystalBall();
	private TextView mAnswerLabel;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
	private ImageView mCrystalBallImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAnswerLabel = (TextView)findViewById(R.id.textView1);//to assign a view
        mCrystalBallImage = (ImageView) findViewById(R.id.imageView1);//to assign an image view for animation
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                findNewAnswer();
            }
        });

    }

    @Override
    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(mShakeDetector);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(mShakeDetector,mAccelerometer,SensorManager.SENSOR_DELAY_UI);
    }

    private void findNewAnswer() {
        String answer = mCrystalBall.getAnswer();
        mAnswerLabel.setText(answer);
        animateCrystalBall();
        playSound();
        animateAnswer();
    }

    private void animateCrystalBall(){
    	mCrystalBallImage.setImageResource(R.drawable.ball_animation);
    	AnimationDrawable ballAnimation = (AnimationDrawable) mCrystalBallImage.getDrawable();
    	if(ballAnimation.isRunning())
    		ballAnimation.stop();
    	ballAnimation.start();
    }
    
    private void animateAnswer(){
    	AlphaAnimation fadeInAnimation = new AlphaAnimation(0,1);
    	fadeInAnimation.setDuration(1500);
    	fadeInAnimation.setFillAfter(true);
    	mAnswerLabel.setAnimation(fadeInAnimation);
    }
    
    private void playSound()
    {
    	MediaPlayer player = MediaPlayer.create(this, R.raw.crystal_ball);
    	player.start();
    	player.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				
				mp.release();
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    }
