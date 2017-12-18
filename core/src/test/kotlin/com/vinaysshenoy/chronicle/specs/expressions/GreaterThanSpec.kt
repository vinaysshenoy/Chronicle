package com.vinaysshenoy.chronicle.specs.expressions

import com.vinaysshenoy.chronicle.expr.GreaterThan
import org.amshove.kluent.`should be false`
import org.amshove.kluent.`should be true`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object GreaterThanSpec: Spek({

  describe("A greater than expression set to a particular value") {
    val greaterThan = GreaterThan(5L)

    describe("A value greater than the set value") {
      val test = 6L

      on("evaluating against the test") {
        it("should return true") {
          greaterThan.evaluate(test).`should be true`()
        }
      }
    }

    describe("A value lesser than the set value") {
      val test = 4L

      on("evaluating against the test") {
        it("should return false") {
          greaterThan.evaluate(test).`should be false`()
        }
      }
    }

    describe("A value equal to the set value") {
      val test = 5L

      on("evaluating against the test") {
        it("should return false") {
          greaterThan.evaluate(test).`should be false`()
        }
      }
    }
  }
})