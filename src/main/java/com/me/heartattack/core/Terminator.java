package com.me.heartattack.core;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.queue.CircularFifoQueue;

import static com.me.heartattack.config.Config.NONE_STOP_MODE;
import static com.me.heartattack.config.Config.TERMINATION_AFTER_CONTINUES_NUM_OF_LIKED;

public class Terminator {

    private final CircularFifoQueue<Boolean> queue = new CircularFifoQueue<>(TERMINATION_AFTER_CONTINUES_NUM_OF_LIKED);

    public boolean shouldContinue(final boolean liked) {
        if (NONE_STOP_MODE) {
            return true;
        }

        queue.offer(liked);
        boolean shouldDrop = queue.isAtFullCapacity() && queue.stream().noneMatch(Boolean::booleanValue);
        return !shouldDrop;
    }
}
