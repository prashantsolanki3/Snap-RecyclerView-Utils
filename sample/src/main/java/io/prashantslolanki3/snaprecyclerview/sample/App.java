package io.prashantslolanki3.snaprecyclerview.sample;

import android.app.Application;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import io.github.prashantsolanki3.utiloid.Utiloid;

/**
 * Created by Prashant on 1/9/2016.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Iconify.with(new FontAwesomeModule());
        Utiloid.init(getApplicationContext());
    }
}
