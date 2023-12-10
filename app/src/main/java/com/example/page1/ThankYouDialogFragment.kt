package com.example.page1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import java.util.Random

class ThankYouDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.thank_you, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val barcodeTextView = view.findViewById<TextView>(R.id.barcodeTextView)
        val randomBarcode = generateRandomBarcode()
        barcodeTextView.text = randomBarcode
    }

    private fun generateRandomBarcode(): String {
        val random = Random()
        val randomBarcode = StringBuilder()
        repeat(12) {
            randomBarcode.append(random.nextInt(10))
        }
        return "Ticket Barcode: $randomBarcode"
    }
}
