package com.me.heartattack.core;

import dev.brachtendorf.jimagehash.hash.Hash;
import dev.brachtendorf.jimagehash.hashAlgorithms.HashingAlgorithm;
import lombok.RequiredArgsConstructor;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class LikeStatusDetector {

    private final HashingAlgorithm hasher;

    public boolean isLiked(final String actualImage, final String expectedImagePath) {
        final byte[] imageBytes = Base64.getDecoder().decode(actualImage.replace("\n", ""));

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(expectedImagePath)) {
            final Hash actual = hasher.hash(ImageIO.read(new ByteArrayInputStream(imageBytes)));
            final Hash expected = hasher.hash(ImageIO.read(is));
            return actual.normalizedHammingDistance(expected) < 0.2;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
