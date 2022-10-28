package com.me.heartattack;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.me.heartattack.core.Terminator;
import com.me.heartattack.core.*;
import com.me.heartattack.model.Moment;
import com.me.heartattack.model.MomentType;
import com.me.heartattack.module.AppModules;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import one.util.streamex.StreamEx;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Log4j2
public class HeartAttack {

    private final ScreenRecorder screenRecorder;
    private final MomentMatcher momentMatcher;
    private final LikeActionOrchestrator likeActionOrchestrator;
    private final Set<String> visited = new HashSet<>();
    private final Terminator terminator;
    private final Scroller scroller;

    public void run() {
        screenRecorder.start();
        while (true) {
            final List<Moment> moments = Arrays.stream(MomentType.values())
                    .flatMap(momentMatcher::match)
                    .filter(moment -> visited.add(moment.getTagName()))
                    .collect(Collectors.toList());

            if (moments.isEmpty()) {
                log.info("No more new posts");
                break;
            }

            boolean shouldContinue = StreamEx.of(moments)
                    .sorted(Comparator.comparingInt(m -> m.getBound().getYCenter()))
                    .peek(m -> log.info(m.getTagName()))
                    .map(likeActionOrchestrator::like)
                    .map(terminator::shouldContinue)
                    .takeWhileInclusive(Boolean::booleanValue)
                    .reduce(true, Boolean::logicalAnd);

            if (!shouldContinue) {
                log.info("Termination conditions hit");
                break;
            }

            scroller.scrollDownABit();
        }
        screenRecorder.stopAndSave();
    }

    public static void main(String[] args) {
        final Injector injector = Guice.createInjector(new AppModules());
        final HeartAttack app = injector.getInstance(HeartAttack.class);
        app.run();
    }
}
