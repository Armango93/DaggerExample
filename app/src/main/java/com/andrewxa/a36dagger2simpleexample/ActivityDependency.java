package com.andrewxa.a36dagger2simpleexample;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.Component;

@PerActivity
public final class ActivityDependency {
    Context context;

    @Inject
    ActivityDependency(Context context) {
        this.context = context;
    }

    public void helloMethod() {
        System.out.println(context.getApplicationContext());
        Toast.makeText(context, "Hello!", Toast.LENGTH_LONG).show();
    }
}