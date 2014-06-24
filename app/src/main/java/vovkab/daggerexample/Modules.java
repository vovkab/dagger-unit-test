package vovkab.daggerexample;

public final class Modules {
    public static Object[] list() {
        return new Object[]{
            new ControllerModule()
        };
    }

    private Modules() {
    }
}
