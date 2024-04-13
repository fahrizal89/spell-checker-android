package id.fahrizal.spellchecker

import id.fahrizal.spellchecker.domain.SpellChecker
import org.junit.Assert
import org.junit.Test

internal class SpellCheckerTest {

    @Test
    fun case1() {
        val input = "kantor"
        val data  = "kantong"

        val diff = SpellChecker.differentiate(input, data)

        Assert.assertEquals(2, diff)
    }

    @Test
    fun case2() {
        val input = "intention"
        val data  = "execution"

        val diff = SpellChecker.differentiate(input, data)

        Assert.assertEquals(5, diff)
    }
}