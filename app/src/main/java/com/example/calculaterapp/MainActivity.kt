package com.example.calculaterapp

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.calculaterapp.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var tempResult:String

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rotateScreen()
        onClickScientificOperation()
    }

    private fun rotateScreen() {
        binding.rotate?.setOnClickListener { setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) }
        binding.mini?.setOnClickListener { setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) }
    }

    fun onClickNumber(v: View) {
        val newDigit = (v as Button).text.toString()
        val oldDigit = binding.txtOperation?.text.toString()
        val newTextNumber = oldDigit + newDigit
        binding.txtOperation?.text = newTextNumber
    }

    fun clearInput(v: View){
        binding.txtResult.text = ""
        binding.txtOperation.text = ""
    }

    fun deleteLastDigit(v: View){
        val number = binding.txtOperation.text
        if (binding.txtOperation.text.isNotBlank()){
            binding.txtOperation.text = number.substring(0,number.length - 1)
        }
    }

    fun signNumber(v: View){
        tempResult = (binding.txtOperation.text.toString().toDouble() * -1.0).toString()
        binding.txtOperation.text = tempResult
    }

    fun onClickOperation(v: View){
        when((v as Button).id){
            binding.btnPlus.id -> { appendOnClick("+")}
            binding.btnMinus.id -> {appendOnClick("-")}
            binding.btnDiv.id -> {appendOnClick("/")}
            binding.btnMulti.id -> {appendOnClick("*")}
            binding.btnStartBracket.id -> {appendOnClick("(")}
            binding.btnEndBracket.id -> {appendOnClick(")")}
        }
    }

    private fun appendOnClick(operation: String) {
        binding.txtOperation.append(operation)
    }

    private fun onClickScientificOperation(){
        binding.e?.setOnClickListener {appendOnClick("e")}
        binding.Log10?.setOnClickListener {appendOnClick("log")}
        binding.ln?.setOnClickListener {appendOnClick("ln")}
        binding.cos?.setOnClickListener {appendOnClick("cos")}
        binding.sin?.setOnClickListener {appendOnClick("sin")}
        binding.tan?.setOnClickListener {appendOnClick("tan")}
        binding.acos?.setOnClickListener {appendOnClick("cos")}
        binding.asin?.setOnClickListener {appendOnClick("asin")}
        binding.atan?.setOnClickListener {appendOnClick("atan")}
        binding.cosh?.setOnClickListener {appendOnClick("acosh")}
        binding.sinh?.setOnClickListener {appendOnClick("sinh")}
        binding.tanh?.setOnClickListener {appendOnClick("tanh")}
        binding.pi?.setOnClickListener {appendOnClick("pi")}
        binding.sqrt?.setOnClickListener {appendOnClick("sqrt")}
        binding.rad?.setOnClickListener {appendOnClick("rad")}//
        binding.x2?.setOnClickListener {appendOnClick("^(2)")}
        binding.x3?.setOnClickListener {appendOnClick("^(3)")}
        binding.Factorial?.setOnClickListener {appendOnClick("!")}
        binding.percent?.setOnClickListener {appendOnClick("%")}
    }

    fun result(v: View){
        try {
            val input= ExpressionBuilder(binding.txtOperation.text.toString()).build()
            val output=input.evaluate()
            val longOutput=output.toLong()
            if(output==longOutput.toDouble()){
                binding.txtOperation?.text=longOutput.toString()
            }else{
                binding.txtResult?.text=output.toString()
            }
        }catch (e:Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }
}