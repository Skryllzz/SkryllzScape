package net.runelite.standalone;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor @Getter
public enum CustomWorldType {

    PVP("KronosPK", "https://kronos.rip", "31.220.96.84", "31.220.96.84"),
    ECO("SkryllzScape", "https://kronos.rip", "31.220.96.84", "31.220.96.84"),
    BETA("BETA", "https://kronos.rip", "31.220.96.84", "31.220.96.84"),
    DEV("DEV", "https://kronos.rip", "127.0.0.1", "127.0.0.1");  //SERVER IP

    private final String name;
    private final String url;
    private final String gameServerAddress;
    private final String fileServerAddress;

}
