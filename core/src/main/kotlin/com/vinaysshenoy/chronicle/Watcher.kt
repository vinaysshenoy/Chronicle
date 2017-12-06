package com.vinaysshenoy.chronicle

interface Listener {

  fun onWatchTriggered(name: String)
}

class Watcher private constructor(private val watches: List<Watch>, private val listener: Listener) {

  private val evaluationListener = { watchName: String -> listener.onWatchTriggered(watchName) }

  init {
    watches.forEach { it.addEvaluationListener(evaluationListener) }
  }

  fun stopWatching() {
    watches.forEach { it.stop() }
  }

  fun evaluate() = watches.forEach { it.evaluate() }

  class Builder internal constructor(watch: Watch) {

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