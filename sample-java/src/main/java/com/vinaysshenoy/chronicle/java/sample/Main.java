package com.vinaysshenoy.chronicle.java.sample;

import com.vinaysshenoy.chronicle.Chronicle;
import com.vinaysshenoy.chronicle.Watcher;
import com.vinaysshenoy.chronicle.expr.GreaterThanOrEqualTo;
import com.vinaysshenoy.chronicle.expr.LesserThan;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.logging.Logger;

public class Main {

  private static final Logger LOGGER = Logger.getLogger("Main");

  private final Chronicle chronicle;

  private Main() {
    chronicle = new Chronicle(new ListBasedStore());
  }

  private void run() {
    LOGGER.info("RUNNING!");

    final Watcher watcher = chronicle
        .watch(
            chronicle
                .query("query_1")
                .hasDone("event_1")
                .timesDone("event_2", 3)
                .timesDone("event_3", new GreaterThanOrEqualTo(5))
                .build()
        )
        .or(
            chronicle
                .query("query_2")
                .hasDone("event_4")
                .timesDone("event_5", new LesserThan(3))
                .build()
        )
        .then(watchName -> LOGGER.info(String.format(Locale.US, "Triggered watch: %s", watchName)));

    watcher.startWatching();

    new Thread(() ->
        Arrays.asList(
            "event_2", "event_2",
            "event_3", "event_3", "event_3",
            "event_5", "event_3", "event_2",
            "event_3", "event_5", "event_4",
            "event_1"

        ).forEach(event -> {
          LOGGER.info("Event: " + event + ", on thread: " + Thread.currentThread().getName());
          try {
            chronicle.did(event);
            Thread.sleep(500L);
          } catch (InterruptedException | IOException e) {
            e.printStackTrace();
          }
        })
    ).start();
  }

  public static void main(String[] args) {
    new Main().run();
  }
}
