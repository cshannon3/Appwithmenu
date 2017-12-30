package com.ratingrocker.appwithmenu;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class Fx extends Service {
    public Fx() {}
        //...
        /**
         *
         * @param ctx
         * @param v
         */
        public static void slide_down(Context ctx, View v){
            Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_down);
            if(a != null){
                a.reset();
                if(v != null){
                    v.clearAnimation();
                    v.startAnimation(a);
                }
            }
        }
    public static void slide_up(Context ctx, View v){
        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_up);
        if(a != null){
            a.reset();
            if(v != null){
                v.clearAnimation();
                v.startAnimation(a);
            }
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
