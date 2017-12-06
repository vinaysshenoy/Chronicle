package com.vinaysshenoy.chronicle.expr

class NotEqual(private val value: Long): Expression {

  override fun evaluate(test: Long) = test != value
}