package com.vinaysshenoy.chronicle.expr

interface Expression<in T: Any> {

  fun evaluate(test: T): Boolean
}