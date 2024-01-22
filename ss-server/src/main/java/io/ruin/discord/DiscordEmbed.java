package io.ruin.discord;

import io.ruin.discord.embed.EmbedAuthor;
import io.ruin.discord.embed.EmbedField;
import io.ruin.discord.embed.EmbedFooter;
import io.ruin.discord.embed.EmbedMedia;
import io.ruin.discord.payload.Payload;
import io.ruin.discord.payload.PayloadMap;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;

@Setter
@Getter
public class DiscordEmbed extends Payload {

    private String title, description, url;
    private Color color;
    private EmbedAuthor author;
    private EmbedMedia thumbnail, video;
    private EmbedFooter footer;
    private ArrayList<EmbedField> fields;

    @Override
    public void save(PayloadMap map) {
        map.putIfExists("title", title);
        map.putIfExists("description", description);
        map.putIfExists("url", url);
        map.putIfExists("color", getIntColor());
        map.putIfExists("author", author);
        map.putIfExists("thumbnail", thumbnail);
        map.putIfExists("video", video);
        map.putIfExists("footer", footer);
        if (fields != null && fields.size() > 0) {
            map.put("fields", fields);
        }
    }

    public Integer getIntColor() {
        if (color != null) {
            return 65536 * color.getRed() + 256 * color.getGreen() + color.getBlue();
        }
        return null;
    }

    public static class Builder {
        private final DiscordEmbed embed;

        public Builder() {
            this.embed = new DiscordEmbed();
            this.embed.setFields(new ArrayList<EmbedField>());
        }

        public Builder withTitle(String title) {
            embed.setTitle(title);
            return this;
        }

        public Builder withDescription(String description) {
            embed.setDescription(description);
            return this;
        }

        public Builder withURL(String url) {
            embed.setUrl(url);
            return this;
        }

        public Builder withColor(Color color) {
            embed.setColor(color);
            return this;
        }

        public Builder withAuthor(EmbedAuthor author) {
            embed.setAuthor(author);
            return this;
        }

        public Builder withThumbnail(EmbedMedia thumbnail) {
            embed.setThumbnail(thumbnail);
            return this;
        }

        public Builder withVideo(EmbedMedia video) {
            embed.setVideo(video);
            return this;
        }

        public Builder withFooter(EmbedFooter footer) {
            embed.setFooter(footer);
            return this;
        }

        public Builder withField(EmbedField field) {
            embed.getFields().add(field);
            return this;
        }

        public DiscordEmbed build() {
            return embed;
        }
    }
}
