package com.vinaysshenoy.chronicle

import java.io.IOException

interface Store {

  @Throws(IOException::class)
  fun put(eventRecord: EventRecord)

  @Throws(IOException::class)
  fun countOf(event: String): Long

  @Throws(IOException::class)
  fun countOfSince(event: String, eventTimeMillis: Long): Long

  @Throws(IOException::class)
  fun latestOf(event: String): EventRecord?

}