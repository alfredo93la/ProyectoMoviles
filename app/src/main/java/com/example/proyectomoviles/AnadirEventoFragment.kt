package com.example.proyectomoviles

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.util.*

class AnadirEventoFragment : Fragment() {

    // Declaración de vistas
    private lateinit var etFecha: EditText
    private lateinit var etHora: EditText
    private lateinit var spinnerStatus: Spinner
    private lateinit var rgCategoria: RadioGroup
    private lateinit var btnGuardar: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_anadir_evento, container, false)

        // Referencias
        etFecha = view.findViewById(R.id.et_fecha)
        etHora = view.findViewById(R.id.et_hora)
        spinnerStatus = view.findViewById(R.id.spinner_status)
        rgCategoria = view.findViewById(R.id.rg_categoria)
        btnGuardar = view.findViewById(R.id.btn_guardar)

        // Configurar el Spinner de estatus
        val estatusOpciones = arrayOf("Pendiente", "Realizado", "Aplazado")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, estatusOpciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerStatus.adapter = adapter

        // DatePicker
        etFecha.setOnClickListener {
            val calendario = Calendar.getInstance()
            val anio = calendario.get(Calendar.YEAR)
            val mes = calendario.get(Calendar.MONTH)
            val dia = calendario.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(requireContext(), { _, y, m, d ->
                val fechaFormateada = String.format("%02d/%02d/%04d", d, m + 1, y)
                etFecha.setText(fechaFormateada)
            }, anio, mes, dia).show()
        }

        // TimePicker
        etHora.setOnClickListener {
            val calendario = Calendar.getInstance()
            val hora = calendario.get(Calendar.HOUR_OF_DAY)
            val minuto = calendario.get(Calendar.MINUTE)

            TimePickerDialog(requireContext(), { _, h, m ->
                val horaFormateada = String.format("%02d:%02d", h, m)
                etHora.setText(horaFormateada)
            }, hora, minuto, true).show()
        }

        // Botón guardar
        btnGuardar.setOnClickListener {
            val categoria = when (rgCategoria.checkedRadioButtonId) {
                R.id.rb_cita -> "Cita"
                R.id.rb_junta -> "Junta"
                R.id.rb_entrega -> "Entrega de Proyecto"
                R.id.rb_examen -> "Examen"
                R.id.rb_otro -> "Otro"
                else -> ""
            }

            val fecha = etFecha.text.toString()
            val hora = etHora.text.toString()
            val estatus = spinnerStatus.selectedItem.toString()

            Toast.makeText(requireContext(), "Evento guardado: $categoria - $fecha $hora - $estatus", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
