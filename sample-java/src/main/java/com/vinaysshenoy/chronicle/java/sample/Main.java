package com.vinaysshenoy.chronicle.java.sample;

import com.vinaysshenoy.chronicle.Chronicle;
import com.vinaysshenoy.chronicle.EventRecord;
import com.vinaysshenoy.chronicle.Store;
import com.vinaysshenoy.chronicle.Watcher;
import com.vinaysshenoy.chronicle.expr.GreaterThanOrEqualTo;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Main {

  private static final Logger LOGGER = Logger.getLogger("Main");

  private final Chronicle chronicle;

  private final Store store = new Store() {
    @Override public void put(@NotNull EventRecord eventRecord) throws IOException {

    }

    @Override public long countOf(@NotNull String event) throws IOException {
      return 0;
    }

    @Override public long countOfSince(@NotNull String event, long eventTimeMillis)
        throws IOException {
      return 0;
    }

    @Nullable @Override public EventRecord latestOf(@NotNull String event) throws IOException {
      return null;
    }
  };

  private Main() {
    chronicle = new Chronicle(store);
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
        .then(watchName -> LOGGER.info(String.format(Locale.US, "Triggered watch: %s", watchName)));
  }

  public static void main(String[] args) {
    new Main().run();
  }
}
