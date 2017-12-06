package com.vinaysshenoy.chronicle.java.sample;

import com.vinaysshenoy.chronicle.EventRecord;
import com.vinaysshenoy.chronicle.Store;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ListBasedStore implements Store {

  private final List<EventRecord> events;

  public ListBasedStore() {
    events = new ArrayList<>(50);
  }

  @Override public void put(@NotNull EventRecord eventRecord) throws IOException {
    events.add(eventRecord);
  }

  @Override public long countOf(@NotNull String event) throws IOException {
    return events
        .stream()
        .filter(eventRecord -> eventRecord.getEvent().equals(event))
        .count();
  }

  @Override public long countOfSince(@NotNull String event, long eventTimeMillis)
      throws IOException {
    return events
        .stream()
        .filter(eventRecord -> eventRecord.getEvent().equals(event))
        .filter(eventRecord -> eventRecord.getEventTimeMillis() >= eventTimeMillis)
        .count();
  }

  @Nullable @Override public EventRecord latestOf(@NotNull String event) throws IOException {
    return events
        .stream()
        .filter(eventRecord -> eventRecord.getEvent().equals(event))
        .max((first, next) -> (int) (first.getEventTimeMillis() - next.getEventTimeMillis()))
        .orElse(null);
  }

  @Override public void clear() throws IOException {
    events.clear();
  }
}
