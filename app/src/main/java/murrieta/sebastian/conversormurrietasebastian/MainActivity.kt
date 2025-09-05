package murrieta.sebastian.conversormurrietasebastian

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var tvConversionSeleccionada: TextView
    private lateinit var etCifra: EditText
    private lateinit var tvResultado: TextView
    private lateinit var btnTemperatura: Button
    private lateinit var btnVolumen: Button
    private lateinit var btnLongitud: Button
    private lateinit var btnMasa: Button
    private lateinit var btnConvertir: Button

    private var tipoConversion: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        configurarBotonesTipoConversion()
        configurarBotonConvertir()
    }

    private fun initViews() {
        tvConversionSeleccionada = findViewById(R.id.tvConversionSeleccionada)
        etCifra = findViewById(R.id.etCifra)
        tvResultado = findViewById(R.id.tvResultado)
        btnTemperatura = findViewById(R.id.btnTemperatura)
        btnVolumen = findViewById(R.id.btnVolumen)
        btnLongitud = findViewById(R.id.btnLongitud)
        btnMasa = findViewById(R.id.btnMasa)
        btnConvertir = findViewById(R.id.btnConvertir)
    }

    private fun configurarBotonesTipoConversion() {
        btnTemperatura.setOnClickListener { seleccionarTipoConversion("Temperatura") }
        btnVolumen.setOnClickListener { seleccionarTipoConversion("Volumen") }
        btnLongitud.setOnClickListener { seleccionarTipoConversion("Longitud") }
        btnMasa.setOnClickListener { seleccionarTipoConversion("Masa") }
    }

    private fun seleccionarTipoConversion(tipo: String) {
        tipoConversion = tipo
        tvConversionSeleccionada.text = "Conversión seleccionada: $tipo"
        etCifra.text.clear()
        tvResultado.text = "Resultado"
    }

    private fun configurarBotonConvertir() {
        btnConvertir.setOnClickListener {
            realizarConversion()
        }
    }

    private fun realizarConversion() {
        val entrada = etCifra.text.toString()
        if (entrada.isEmpty()) {
            etCifra.error = "Ingrese una cantidad"
            return
        }

        val valor = entrada.toDoubleOrNull()
        if (valor == null) {
            etCifra.error = "Cantidad inválida"
            return
        }

        val resultado: Double? = when (tipoConversion) {
            "Temperatura" -> convertirTemperatura(valor)
            "Volumen" -> convertirVolumen(valor)
            "Longitud" -> convertirLongitud(valor)
            "Masa" -> convertirMasa(valor)
            else -> null
        }

        if (resultado != null) {
            tvResultado.text = "Resultado: %.2f".format(resultado)
        } else {
            tvResultado.text = "Seleccione un tipo de conversión"
        }
    }

    private fun convertirTemperatura(valor: Double): Double {
        // Example: Celsius to Fahrenheit
        return (valor * 9 / 5) + 32
    }

    private fun convertirVolumen(valor: Double): Double {
        // Example: Liters to Gallons
        return valor * 0.264172
    }

    private fun convertirLongitud(valor: Double): Double {
        // Example: Meters to Feet
        return valor * 3.28084
    }

    private fun convertirMasa(valor: Double): Double {
        // Example: Kilograms to Pounds
        return valor * 2.20462
    }
}
