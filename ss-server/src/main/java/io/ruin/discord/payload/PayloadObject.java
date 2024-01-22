package io.ruin.discord.payload;

import org.json.simple.JSONObject;

public class PayloadObject extends JSONObject {

    public PayloadObject(Payload payload) {
        PayloadMap map = new PayloadMap();
        payload.save(map);
        this.putAll(map);
    }
}
