package com.vinaysshenoy.chronicle

import java.io.IOException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicInteger

private class ChronicleThreadFactory(val threadPrefix: String) : ThreadFactory {

  private val threadNumber = AtomicInteger(1)

  override fun newThread(r: Runnable) = Thread(r, threadPrefix + "-thread-${threadNumber.getAndIncrement()}")

}

class Chronicle @JvmOverloads constructor(
    val store: Store,
    private val writeExecutor: ExecutorService = Executors.newSingleThreadExecutor(ChronicleThreadFactory("chronicle-write")),
    private val evaluationExecutor: ExecutorService = Executors.newSingleThreadExecutor(ChronicleThreadFactory("chronicle-evaluate"))) {

  private var eventListeners = emptyList<(String, Long) -> Unit>()

  @JvmOverloads
  @Throws(IOException::class)
  fun did(event: String, eventTimeMillis: Long = System.currentTimeMillis()) {

    writeExecutor.submit {
      store.put(EventRecord(event, eventTimeMillis))
      dispatchListeners(event, eventTimeMillis)
    }
  }

  private fun dispatchListeners(event: String, eventTimeMillis: Long) {
    eventListeners.forEach { it(event, eventTimeMillis) }
  }

  fun watch(watch: Watch) = Watcher.Builder(watch)

  fun query(name: String = "") = Watch.Builder(this, evaluationExecutor, name)

  fun dispose() {
    writeExecutor.shutdown()
    evaluationExecutor.shutdown()
  }

  internal fun addEventListener(listener: (String, Long) -> Unit) {
    eventListeners += listener
  }

  internal fun removeEventListener(listener: (String, Long) -> Unit) {
    eventListeners -= listener
  }
}