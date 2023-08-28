package dc.kotlin.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvAgeInMinutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            { view, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate?.text = selectedDate

                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val finalDate = dateFormat.parse(selectedDate)

                val selectedDateInMinutes = finalDate.time / 60000
                val currentDate = dateFormat.parse(dateFormat.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate.time / 60000
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                tvAgeInMinutes?.text = differenceInMinutes.toString()
            },
            year,
            month,
            day).show()
    }
}