package com.vinaysshenoy.chronicle

import java.io.IOException

class Chronicle(val store: Store) {

  private var eventListeners = emptyList<(String, Long) -> Unit>()

  @JvmOverloads
  @Throws(IOException::class)
  fun did(event: String, eventTimeMillis: Long = System.currentTimeMillis()) {
    store.put(EventRecord(event, eventTimeMillis))
    eventListeners.forEach { it(event, eventTimeMillis) }
  }

  fun watch(watch: Watch) = Watcher.Builder(watch)

  fun query(name: String = "") = Watch.Builder(this, name)

  internal fun addEventListener(listener: (String, Long) -> Unit) {
    eventListeners += listener
  }

  internal fun removeEventListener(listener: (String, Long) -> Unit) {
    eventListeners -= listener
  }
}