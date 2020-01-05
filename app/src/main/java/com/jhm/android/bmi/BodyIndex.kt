package com.jhm.android.bmi

class BodyIndex {
    var age: Int = 0
    var tall: Double = 0.0
    var weight: Double = 0.0

    fun getBMIIndex(bmi: Double): BMIIndex =
        when (bmi) {
            in 0.00 .. 10.0 -> BMIIndex.OUT_OF_RANGE
            in 10.0 .. 18.5 -> BMIIndex.LOW
            in 18.5 .. 23.0 -> BMIIndex.MIDDLE
            in 23.0 .. 30.0 -> BMIIndex.HIGH
            in 30.0 .. 50.0 -> BMIIndex.VERY_HIGH
            else -> BMIIndex.OUT_OF_RANGE
        }

    fun getStandardWeightIndex(standardWeight: Double): StandardWeightIndex =
        when (weight) {
            in standardWeight*0.0 .. standardWeight*0.7 -> StandardWeightIndex.OUT_OF_RANGE
            in standardWeight*0.7 .. standardWeight*0.8 -> StandardWeightIndex.VERY_LOW
            in standardWeight*0.8 .. standardWeight*0.9 -> StandardWeightIndex.LOW
            in standardWeight*0.9 .. standardWeight*1.1 -> StandardWeightIndex.MIDDLE
            in standardWeight*1.1 .. standardWeight*1.2 -> StandardWeightIndex.HIGH
            in standardWeight*1.2 .. standardWeight*1.3 -> StandardWeightIndex.VERY_HIGH
            else -> StandardWeightIndex.OUT_OF_RANGE
        }

    fun getBMI(): Double {
        if (this.tall == 0.0 || this.weight == 0.0) {
            return 0.0
        }
        return weight / ((tall /100) * (tall /100))
    }

    fun getStandardWeight(): Double {
        if (this.tall == 0.0 || this.weight == 0.0) {
            return 0.0
        }
        return (tall - 100) * 0.9
    }
}

enum class BMIIndex { LOW, MIDDLE, HIGH, VERY_HIGH, OUT_OF_RANGE }
enum class StandardWeightIndex { VERY_LOW, LOW, MIDDLE, HIGH, VERY_HIGH,OUT_OF_RANGE }