package config;

import lombok.Getter;

@Getter
public enum Environment {
    BROWSERSTACK("browserstack"),
    EMULATOR("emulator");

    private final String displayName;

    Environment(String displayName) {
        this.displayName = displayName;
    }
}
