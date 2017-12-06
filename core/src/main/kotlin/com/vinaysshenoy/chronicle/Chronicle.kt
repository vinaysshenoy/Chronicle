package com.vinaysshenoy.chronicle

import java.io.IOException

class Chronicle(val store: Store) {

  @JvmOverloads
  @Throws(IOException::class)
  fun did(event: String, eventTimeMillis: Long = System.currentTimeMillis()) {
    store.put(EventRecord(event, eventTimeMillis))
  }

  fun watch(watch: Watch) = Watcher.Builder(watch)

  fun query(name: String = "") = Watch.Builder(store, name)
}