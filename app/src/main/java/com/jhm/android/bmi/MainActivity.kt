package com.jhm.android.bmi

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        picker_main_age.minValue = 10
        picker_main_age.maxValue = 120

        picker_main_tall.minValue = 120
        picker_main_tall.maxValue = 220

        picker_main_weight.minValue = 30
        picker_main_weight.maxValue = 150

        val clientBody = BodyIndex()

        picker_main_age.setOnValueChangedListener { _, _, newVal ->
            clientBody.age = newVal
        }

        picker_main_tall.setOnValueChangedListener { _, _, newVal ->
            clientBody.tall = newVal.toDouble()
            updateView(clientBody)
        }

        picker_main_weight.setOnValueChangedListener { _, _, newVal ->
            clientBody.weight = newVal.toDouble()
            updateView(clientBody)
        }
    }

    private fun updateView(clientBody: BodyIndex) {
        val bmi = clientBody.getBMI()
        val standardWeight = clientBody.getStandardWeight()

        val bmiIndex = clientBody.getBMIIndex(bmi)
        val standardWeightIndex = clientBody.getStandardWeightIndex(standardWeight)

        text_main_bmi.text = "%.1f".format(bmi)
        text_main_standardWeight.text = "%.1f".format(standardWeight)

        when (bmiIndex) {
            BMIIndex.LOW -> text_main_bmi.setTextColor(Color.parseColor("#f58d42"))
            BMIIndex.MIDDLE -> text_main_bmi.setTextColor(Color.parseColor("#3fdb37"))
            BMIIndex.HIGH -> text_main_bmi.setTextColor(Color.parseColor("#f58d42"))
            BMIIndex.VERY_HIGH -> text_main_bmi.setTextColor(Color.parseColor("#f54242"))
            BMIIndex.OUT_OF_RANGE -> text_main_bmi.setTextColor(Color.parseColor("#dd42f5"))
        }

        when(standardWeightIndex) {
            StandardWeightIndex.VERY_LOW -> text_main_standardWeight.setTextColor(Color.parseColor("#f54242"))
            StandardWeightIndex.LOW -> text_main_standardWeight.setTextColor(Color.parseColor("#f58d42"))
            StandardWeightIndex.MIDDLE -> text_main_standardWeight.setTextColor(Color.parseColor("#3fdb37"))
            StandardWeightIndex.HIGH -> text_main_standardWeight.setTextColor(Color.parseColor("#f58d42"))
            StandardWeightIndex.VERY_HIGH -> text_main_standardWeight.setTextColor(Color.parseColor("#f54242"))
            StandardWeightIndex.OUT_OF_RANGE -> text_main_standardWeight.setTextColor(Color.parseColor("#dd42f5"))
        }
    }

}