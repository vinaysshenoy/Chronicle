package com.vinaysshenoy.chronicle.specs.expressions

import com.vinaysshenoy.chronicle.expr.Equal
import org.amshove.kluent.`should be false`
import org.amshove.kluent.`should be true`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object EqualSpec: Spek({

  describe("An equal spek set to a particular value") {

    val equal = Equal(5L)

    describe("A value equal to the set value") {
      val test = 5L

      on("evaluating with the test value") {
        it("should return true") {
          equal.evaluate(test).`should be true`()
        }
      }
    }

    describe("A value not equal to the set value") {
      val test = -1L

      it("should return false") {
        equal.evaluate(test).`should be false`()
      }
    }
  }
})