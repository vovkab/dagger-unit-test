package vovkab.daggerexample;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;

public class MyApp extends Application implements Injectable {

    private ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        mObjectGraph = ObjectGraph.create(getModules());
    }

    @Override
    public Object[] getModules() {
        return Modules.list();
    }

    @Override
    public void inject(Object o) {
        mObjectGraph.inject(o);
    }

    public static Injectable getInjectable(Context context) {
        return (Injectable) context.getApplicationContext();
    }
}
