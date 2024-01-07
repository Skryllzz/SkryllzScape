package io.ruin.api.protocol.world;

public enum WorldType {
    ECO("SkryllzScape", "http://127.0.0.1"),
    BETA("SkryllzScape", "http://127.0.0.1"),
    PVP("SkryllzScape", "http://127.0.0.1"),
    DEV("SkryllzScape", "http://127.0.0.1");

    WorldType(String worldName, String websiteUrl) {
        this.worldName = worldName;
        this.websiteUrl = websiteUrl;
    }

    private String worldName, websiteUrl;

    public String getWorldName() {
        return worldName;
    }

    public String getWebsiteUrl() { return websiteUrl; }
}