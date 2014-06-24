package vovkab.daggerexample;

public interface Injectable {

    public Object[] getModules();

    public void inject(Object o);
}
