package com.example.careermitra;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.*;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IntroScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intro_screen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        ImageView logo = findViewById(R.id.logo);
        TextView appName = findViewById(R.id.app_name);

        // Bounce effect for logo
        AnimationSet logoAnimation = new AnimationSet(true);

        ScaleAnimation scaleAnimation = new ScaleAnimation(
                0.3f, 1.2f, 0.3f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(800);

        RotateAnimation rotateAnimation = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        BounceInterpolator bounceInterpolator = new BounceInterpolator();
        scaleAnimation.setInterpolator(bounceInterpolator);

        logoAnimation.addAnimation(scaleAnimation);
        logoAnimation.addAnimation(rotateAnimation);
        logo.startAnimation(logoAnimation);

        // Typewriter effect for text
        String fullText = "CareerMitra";
        Handler handler = new Handler();
        appName.setText("");

        for (int i = 0; i < fullText.length(); i++) {
            final int index = i;
            handler.postDelayed(() -> appName.append(String.valueOf(fullText.charAt(index))), 150 * index);
        }

        // Fade in effect for text after typing
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(1000);
        fadeIn.setStartOffset(fullText.length() * 150);
        fadeIn.setFillAfter(true);

        appName.startAnimation(fadeIn);
    }
}
