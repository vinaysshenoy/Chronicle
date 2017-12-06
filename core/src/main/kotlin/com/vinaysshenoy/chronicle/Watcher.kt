package com.vinaysshenoy.chronicle

interface Listener {

  fun onWatchTriggered(name: String)
}

class Watcher private constructor(private val watches: List<Watch>, private val listener: Listener) {

  class Builder(watch: Watch) {

    private val watches = mutableListOf<Watch>()

    init {
      watches.add(watch)
    }

    fun or(watch: Watch): Builder {
      watches.add(watch)
      return this
    }

    fun then(listener: Listener) = Watcher(watches.toList(), listener)
  }
}