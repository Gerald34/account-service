package labworx.io.services.account.enums;

import lombok.Getter;

@Getter
public enum Roles {
    SUPER_USER("Application super user"),
    USER("Single application user"),
    GROUP("Group account");

    private final String label;
    Roles(String label) {
        this.label = label;
    }
}
