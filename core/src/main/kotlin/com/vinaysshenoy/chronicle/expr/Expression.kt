package com.vinaysshenoy.chronicle.expr

interface Expression {

  fun evaluate(test: Long): Boolean
}