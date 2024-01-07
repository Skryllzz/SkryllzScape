/*
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.game;

import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.IndexedSprite;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.util.ImageUtil;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.image.BufferedImage;
import java.util.Arrays;

@Singleton
public class ModIconManager
{

    private final Client client;
    private final SpriteManager spriteManager;

    private int offset;

    final private int modIconSpriteAmount = 100; // Max amount of child sprite ids being read from the main ID
    final private int modIconSprite =  2157; // The main sprite id

    @Inject
    private ModIconManager(
            final Client client,
            final SpriteManager spriteManager,
            final EventBus eventbus
    )
    {
        this.client = client;
        this.spriteManager = spriteManager;

        eventbus.subscribe(GameStateChanged.class, this, this::onGameStateChanged);
    }


    private void onGameStateChanged(GameStateChanged gameStateChanged)
    {
        if (gameStateChanged.getGameState() == GameState.LOGIN_SCREEN && offset == 0) {
            loadModIcons();
        }
    }

    private void loadModIcons()
    {
        {
            IndexedSprite[] modIcons = client.getModIcons();
            offset = modIcons.length;

            IndexedSprite blank = ImageUtil.getImageIndexedSprite(
                    new BufferedImage(13, 13, BufferedImage.TYPE_INT_ARGB),
                    client);

            modIcons = Arrays.copyOf(modIcons, offset + modIconSpriteAmount);
            Arrays.fill(modIcons, offset, modIcons.length, blank);

            client.setModIcons(modIcons);
        }

        for (int i = 0; i < modIconSpriteAmount; i++) {
            final int fi = i;
            try {
                BufferedImage img = spriteManager.getSprite(modIconSprite, i);
            } catch(Exception e) {
                return;
            }
            spriteManager.getSpriteAsync(modIconSprite, i, sprite ->
            {
                IndexedSprite[] modIcons = client.getModIcons();
                modIcons[offset + fi] = ImageUtil.getImageIndexedSprite(modIconImageFromSprite(sprite), client);
            });
        }
    }

    private static BufferedImage modIconImageFromSprite(final BufferedImage modIconSprite)
    {
        final BufferedImage modIconCanvas = ImageUtil.resizeCanvas(modIconSprite,13, 13);
        return modIconCanvas;
    }
}