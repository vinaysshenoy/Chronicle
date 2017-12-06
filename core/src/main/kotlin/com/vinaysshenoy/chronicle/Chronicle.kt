package com.vinaysshenoy.chronicle

class Chronicle(val store: Store) {

  @JvmOverloads
  fun did(event: String, eventTimeMillis: Long = System.currentTimeMillis()) {

  }

  fun watch(watch: Watch) = Watcher.Builder(watch)

  fun query(name: String = "") = Watch.Builder(store, name)
}