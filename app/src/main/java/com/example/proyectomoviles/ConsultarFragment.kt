package com.example.proyectomoviles

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import java.util.*

class ConsultarFragment : Fragment() {

    private lateinit var etFechaInicio: EditText
    private lateinit var etFechaFin: EditText
    private lateinit var tablaResultados: TableLayout
    private lateinit var btnConsultar: Button
    private lateinit var rgTipoConsulta: RadioGroup
    private lateinit var rgCategoria: RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_consultar, container, false)

        // Referencias
        etFechaInicio = view.findViewById(R.id.et_fecha_inicio)
        etFechaFin = view.findViewById(R.id.et_fecha_fin)
        btnConsultar = view.findViewById(R.id.btn_consultar)
        tablaResultados = view.findViewById(R.id.tabla_resultados)
        rgTipoConsulta = view.findViewById(R.id.rg_tipo_consulta)
        rgCategoria = view.findViewById(R.id.rg_categoria_consulta)

        // Picker de fecha inicio
        etFechaInicio.setOnClickListener {
            mostrarDatePicker { fecha ->
                etFechaInicio.setText(fecha)
            }
        }

        // Picker de fecha fin
        etFechaFin.setOnClickListener {
            mostrarDatePicker { fecha ->
                etFechaFin.setText(fecha)
            }
        }

        // Acción del botón consultar
        btnConsultar.setOnClickListener {
            agregarFilaSimulada()
        }

        return view
    }

    private fun mostrarDatePicker(onFechaSeleccionada: (String) -> Unit) {
        val calendario = Calendar.getInstance()
        val anio = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(requireContext(), { _, y, m, d ->
            val fechaFormateada = String.format("%02d/%02d/%04d", d, m + 1, y)
            onFechaSeleccionada(fechaFormateada)
        }, anio, mes, dia)

        datePicker.show()
    }

    private fun agregarFilaSimulada() {
        // Crear una nueva fila
        val fila = TableRow(requireContext())

        // Datos simulados (puedes reemplazar con valores reales luego)
        val datos = listOf("2025-06-06", "13:30", "Cita", "Pendiente", "Simulación")

        // Estilo base para celdas
        val params = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )

        for (dato in datos) {
            val celda = TextView(requireContext())
            celda.text = dato
            celda.setPadding(8, 8, 8, 8)
            celda.layoutParams = params
            fila.addView(celda)
        }

        tablaResultados.addView(fila)
    }
}
