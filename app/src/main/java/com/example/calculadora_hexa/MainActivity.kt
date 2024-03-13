package com.example.calculadora_hexa

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private lateinit var tvInput: TextView
    private lateinit var tvResult: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas
        tvInput = findViewById(R.id.imput)
        tvResult = findViewById(R.id.result)

        // Configurar clics de botones
        val btnClear: Button = findViewById(R.id.button_clear)
        val btnBack: Button = findViewById(R.id.button_back)
        val btnEquals: Button = findViewById(R.id.button_equals)
        val btn0: Button = findViewById(R.id.button_0)
        val btn1: Button = findViewById(R.id.button_1)
        val btn2: Button = findViewById(R.id.button_2)
        val btn3: Button = findViewById(R.id.button_3)
        val btn4: Button = findViewById(R.id.button_4)
        val btn5: Button = findViewById(R.id.button_5)
        val btn6: Button = findViewById(R.id.button_6)
        val btn7: Button = findViewById(R.id.button_7)
        val btn8: Button = findViewById(R.id.button_8)
        val btn9: Button = findViewById(R.id.button_9)
        val btnA: Button = findViewById(R.id.button_a)
        val btnB: Button = findViewById(R.id.button_b)
        val btnC: Button = findViewById(R.id.button_c)
        val btnD: Button = findViewById(R.id.button_d)
        val btnE: Button = findViewById(R.id.button_e)
        val btnF: Button = findViewById(R.id.button_f)
        val btnPlus: Button = findViewById(R.id.button_plus)
        val btnMinus: Button = findViewById(R.id.button_minus)
        val btnMultiply: Button = findViewById(R.id.button_multiply)
        val btnDivide: Button = findViewById(R.id.button_divide)
        val btnDot: Button = findViewById(R.id.button_dot)

        // Configurar OnClickListener para cada botón
        btnClear.setOnClickListener { clearInput() }
        btnBack.setOnClickListener { removeLastCharFromInput() }
        btnEquals.setOnClickListener { calculateResult() }

        // Dígitos hexadecimales
        btn0.setOnClickListener { appendToInput("0") }
        btn1.setOnClickListener { appendToInput("1") }
        btn2.setOnClickListener { appendToInput("2") }
        btn3.setOnClickListener { appendToInput("3") }
        btn4.setOnClickListener { appendToInput("4") }
        btn5.setOnClickListener { appendToInput("5") }
        btn6.setOnClickListener { appendToInput("6") }
        btn7.setOnClickListener { appendToInput("7") }
        btn8.setOnClickListener { appendToInput("8") }
        btn9.setOnClickListener { appendToInput("9") }
        btnA.setOnClickListener { appendToInput("A") }
        btnB.setOnClickListener { appendToInput("B") }
        btnC.setOnClickListener { appendToInput("C") }
        btnD.setOnClickListener { appendToInput("D") }
        btnE.setOnClickListener { appendToInput("E") }
        btnF.setOnClickListener { appendToInput("F") }

        // Operadores
        btnPlus.setOnClickListener { appendToInput("+") }
        btnMinus.setOnClickListener { appendToInput("-") }
        btnMultiply.setOnClickListener { appendToInput("*") }
        btnDivide.setOnClickListener { appendToInput("/") }

        // Otros
        btnDot.setOnClickListener { appendToInput(".") }
    }

    private fun appendToInput(str: String) {
        tvInput.append(str)
    }

    private fun removeLastCharFromInput() {
        val currentText = tvInput.text.toString()
        if (currentText.isNotEmpty()) {
            tvInput.text = currentText.substring(0, currentText.length - 1)
        }
    }

    private fun clearInput() {
        tvInput.text = ""
        tvResult.text = ""
    }

    private fun calculateResult() {
        val expression = tvInput.text.toString()

        if (expression.isEmpty()) {
            // Si no hay ninguna expresión, no hay nada que calcular
            return
        }

        val parts = expression.split(Regex("(?<=[+/-])|(?=[+/-])"))

        if (parts.size != 3) {
            // Si la expresión no tiene el formato correcto, muestra un mensaje de error
            tvResult.text = "Error"
            return
        }

        // Obtener los operandos y el operador
        val operand1 = parts[0]
        val operator = parts[1]
        val operand2 = parts[2]

        try {
            // Convertir los operandos hexadecimales a números decimales
            val num1 = operand1.toInt(16)
            val num2 = operand2.toInt(16)

            // Realizar la operación según el operador
            val result = when (operator) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> num1 / num2
                else -> throw IllegalArgumentException("Operador no válido")
            }

            // Convertir el resultado decimal a hexadecimal
            val hexResult = Integer.toHexString(result).toUpperCase()

            // Mostrar el resultado en el TextView tvResult
            tvResult.text = hexResult
        } catch (e: NumberFormatException) {
            // Si hay un error al convertir los operandos, muestra un mensaje de error
            tvResult.text = "Error"
        } catch (e: IllegalArgumentException) {
            // Si el operador no es válido, muestra un mensaje de error
            tvResult.text = "Error"
        } catch (e: ArithmeticException) {
            // Si hay una división por cero, muestra un mensaje de error
            tvResult.text = "Error: División por cero"
        }
    }
}