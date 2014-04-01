package ch.jmanagr.dal;

import java.util.prefs.Preferences;

public class Settings {
    private Preferences preferences;

    public Settings() {
        preferences = Preferences.userRoot().node(this.getClass().getName());
    }
}
