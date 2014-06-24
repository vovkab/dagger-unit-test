package vovkab.daggerexample.mock;

import android.content.Context;

import dagger.ObjectGraph;
import vovkab.daggerexample.Injectable;

public abstract class InjectableAppMock extends MockApplicationCompat implements Injectable {

    private ObjectGraph mObjectGraph;

    public InjectableAppMock(Context context) {
        super(context);
        mObjectGraph = ObjectGraph.create(getModules());
    }

    @Override
    public abstract Object[] getModules();

    @Override
    public void inject(Object o) {
        mObjectGraph.inject(o);
    }
}
