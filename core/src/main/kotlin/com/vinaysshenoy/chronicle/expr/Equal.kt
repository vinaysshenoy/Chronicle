package com.vinaysshenoy.chronicle.expr

class Equal(private val value: Long) : Expression {

  override fun evaluate(test: Long) = test == value
}