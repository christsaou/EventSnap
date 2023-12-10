package com.example.page1
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import java.text.DecimalFormat
import android.text.TextWatcher
import android.widget.ImageView
import android.text.InputFilter
import android.text.Spanned
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**class BookingDialog : DialogFragment() {

    private lateinit var listener: BookingDialogListener

    interface BookingDialogListener {
        fun onBookingConfirmed(numTickets: Int, totalPrice: Double)
    }

    fun setBookingDialogListener(listener: BookingDialogListener) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_booking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val numTicketsEditText: EditText = view.findViewById(R.id.numTicketsEditText)
        val totalPriceTextView: TextView = view.findViewById(R.id.totalPriceTextView)
        val confirmBookingButton: Button = view.findViewById(R.id.confirmBookingButton)

        val backButton: ImageView = view.findViewById(R.id.backButton)

        backButton.setOnClickListener {
            dismiss()
        }


        // Add a custom InputFilter to restrict input to the range 1-10
        numTicketsEditText.filters = arrayOf(RangeInputFilter(1, 10))

        numTicketsEditText.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: android.text.Editable?) {
                updateTotalPrice(numTicketsEditText, totalPriceTextView)
            }
        })

        numTicketsEditText.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: android.text.Editable?) {
                updateTotalPrice(numTicketsEditText, totalPriceTextView)
            }
        })

        confirmBookingButton.setOnClickListener {
            val numTickets = numTicketsEditText.text.toString().toInt()

            val ticketPrice = 25.0
            listener.onBookingConfirmed(numTickets, numTickets * ticketPrice)

            // Show the thank-you dialog
            val thankYouDialog = ThankYouDialogFragment()
            thankYouDialog.show(requireFragmentManager(), "ThankYouDialog")

            dismiss()
        }
    }

    private fun updateTotalPrice(numTicketsEditText: EditText, totalPriceTextView: TextView) {
        val numTickets = numTicketsEditText.text.toString().toIntOrNull() ?: 0

        val ticketPrice = 25.0
        val totalPrice = numTickets * ticketPrice

        val decimalFormat = DecimalFormat("#.00")
        val formattedTotalPrice = "$" + decimalFormat.format(totalPrice)

        totalPriceTextView.text = "Total Price: $formattedTotalPrice"
    }
}
// Custom InputFilter to restrict input to a specified range
private class RangeInputFilter(private val minValue: Int, private val maxValue: Int) : InputFilter {
    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        try {
            // Convert the new input to an integer
            val input = (dest?.subSequence(0, dstart).toString() + source + dest?.subSequence(
                dend,
                dest.length
            )).toInt()

            // Check if the input is within the specified range
            if (isInRange(input)) {
                return null // Accept the input
            }
        } catch (ignored: NumberFormatException) {
            // Ignore the exception, the input is not a valid integer
        }

        // Input is not within the specified range, so reject it
        return ""
    }

    private fun isInRange(value: Int): Boolean {
        return value >= minValue && value <= maxValue
    }
} */
















// seconf
/**class BookingDialog : DialogFragment() {

    private lateinit var listener: BookingDialogListener
    private lateinit var eventData: EventsDataResponse // Added property to hold event data

    interface BookingDialogListener {
        fun onBookingConfirmed(numTickets: Int, totalPrice: Double)
    }

    fun setBookingDialogListener(listener: BookingDialogListener) {
        this.listener = listener
    }

    // Function to set event data when creating the dialog
    fun setEventData(eventData: EventsDataResponse) {
        this.eventData = eventData
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_booking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val numTicketsEditText: EditText = view.findViewById(R.id.numTicketsEditText)
        val totalPriceTextView: TextView = view.findViewById(R.id.totalPriceTextView)
        val confirmBookingButton: Button = view.findViewById(R.id.confirmBookingButton)

        // Add a custom InputFilter to restrict input to the range 1-5 (changed from 1-10)
        numTicketsEditText.filters = arrayOf(RangeInputFilter(1, 5))

        numTicketsEditText.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: android.text.Editable?) {
                updateTotalPrice(numTicketsEditText, totalPriceTextView)
            }
        })

        confirmBookingButton.setOnClickListener {
            val numTickets = numTicketsEditText.text.toString().toInt()

            // Use eventData.price to get the event-specific price
            val ticketPrice = eventData.price ?: 25.0

            listener.onBookingConfirmed(numTickets, numTickets * ticketPrice.toDouble())

            // Show the thank-you dialog
            val thankYouDialog = ThankYouDialogFragment()
            thankYouDialog.show(requireFragmentManager(), "ThankYouDialog")

            dismiss()
        }
    }

    private fun updateTotalPrice(numTicketsEditText: EditText, totalPriceTextView: TextView) {
        val numTickets = numTicketsEditText.text.toString().toIntOrNull() ?: 0

        // Use eventData.price to get the event-specific price
        val ticketPrice = eventData.price ?: 25.0

        val totalPrice = numTickets * ticketPrice.toDouble()

        val decimalFormat = DecimalFormat("#.00")
        val formattedTotalPrice = "$" + decimalFormat.format(totalPrice)

        totalPriceTextView.text = "Total Price: $formattedTotalPrice"
    }

    // Custom InputFilter to restrict input to a specified range
    private class RangeInputFilter(private val minValue: Int, private val maxValue: Int) :
        InputFilter {
        override fun filter(
            source: CharSequence?,
            start: Int,
            end: Int,
            dest: Spanned?,
            dstart: Int,
            dend: Int
        ): CharSequence? {
            try {
                // Convert the new input to an integer
                val input =
                    (dest?.subSequence(0, dstart).toString() + source + dest?.subSequence(
                        dend,
                        dest.length
                    )).toInt()

                // Check if the input is within the specified range
                if (isInRange(input)) {
                    return null // Accept the input
                }
            } catch (ignored: NumberFormatException) {
                // Ignore the exception, the input is not a valid integer
            }

            // Input is not within the specified range, so reject it
            return ""
        }

        private fun isInRange(value: Int): Boolean {
            return value >= minValue && value <= maxValue
        }
    }
} */

/**class BookingDialog : DialogFragment() {

    private lateinit var listener: BookingDialogListener
    private lateinit var eventData: EventsDataResponse // Added property to hold event data

    interface BookingDialogListener {
        fun onBookingConfirmed(numTickets: Int, totalPrice: Double)
    }

    fun setBookingDialogListener(listener: BookingDialogListener) {
        this.listener = listener
    }

    // Function to set event data when creating the dialog
    fun setEventData(eventData: EventsDataResponse) {
        this.eventData = eventData
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_booking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val numTicketsEditText: EditText = view.findViewById(R.id.numTicketsEditText)
        val totalPriceTextView: TextView = view.findViewById(R.id.totalPriceTextView)
        val confirmBookingButton: Button = view.findViewById(R.id.confirmBookingButton)

        // Add a custom InputFilter to restrict input to the range 1-5 (changed from 1-10)
        numTicketsEditText.filters = arrayOf(RangeInputFilter(1, 5))

        numTicketsEditText.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: android.text.Editable?) {
                updateTotalPrice(numTicketsEditText, totalPriceTextView)
            }
        })

        confirmBookingButton.setOnClickListener {
            val numTickets = numTicketsEditText.text.toString().toInt()

            // Use eventData.price to get the event-specific price
            val ticketPrice = eventData.price ?: 25.0
            val totalPrice = numTickets * ticketPrice.toDouble()

            listener.onBookingConfirmed(numTickets, totalPrice)

            // Make a PUT request to the backend
            makeBookingRequest(eventData.id.orEmpty(), numTickets, totalPrice)

            // Show the thank-you dialog
            val thankYouDialog = ThankYouDialogFragment()
            thankYouDialog.show(requireFragmentManager(), "ThankYouDialog")

            dismiss()
        }
    }

    private fun updateTotalPrice(numTicketsEditText: EditText, totalPriceTextView: TextView) {
        val numTickets = numTicketsEditText.text.toString().toIntOrNull() ?: 0

        // Use eventData.price to get the event-specific price
        val ticketPrice = eventData.price ?: 25.0

        val totalPrice = numTickets * ticketPrice.toDouble()

        val decimalFormat = DecimalFormat("#.00")
        val formattedTotalPrice = "$" + decimalFormat.format(totalPrice)

        totalPriceTextView.text = "Total Price: $formattedTotalPrice"
    }

    // Custom InputFilter to restrict input to a specified range
    private class RangeInputFilter(private val minValue: Int, private val maxValue: Int) :
        InputFilter {
        override fun filter(
            source: CharSequence?,
            start: Int,
            end: Int,
            dest: Spanned?,
            dstart: Int,
            dend: Int
        ): CharSequence? {
            try {
                // Convert the new input to an integer
                val input =
                    (dest?.subSequence(0, dstart).toString() + source + dest?.subSequence(
                        dend,
                        dest.length
                    )).toInt()

                // Check if the input is within the specified range
                if (isInRange(input)) {
                    return null // Accept the input
                }
            } catch (ignored: NumberFormatException) {
                // Ignore the exception, the input is not a valid integer
            }

            // Input is not within the specified range, so reject it
            return ""
        }

        private fun isInRange(value: Int): Boolean {
            return value >= minValue && value <= maxValue
        }
    }

    private fun makeBookingRequest(eventId: String, numTickets: Int, totalPrice: Double) {
        val bookingRequest = BookingRequest(eventId, numTickets, totalPrice)

        // Create Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://event-snap.cyclic.app/") // Replace with your base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create BackendService
        val backendService = retrofit.create(BackendService::class.java)

        // Make PUT request
        backendService.bookEvent(eventId, bookingRequest).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Booking successful
                    // You may handle the success response here
                } else {
                    // Booking failed
                    // You may handle the failure response here
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Booking failed
                // You may handle the failure response here
            }
        })
    }
}*/

class BookingDialog : DialogFragment() {

    private lateinit var listener: BookingDialogListener
    private lateinit var eventData: EventsDataResponse // Added property to hold event data

    interface BookingDialogListener {
        fun onBookingConfirmed(numTickets: Int, totalPrice: Double)
    }

    fun setBookingDialogListener(listener: BookingDialogListener) {
        this.listener = listener
    }

    // Function to set event data when creating the dialog
    fun setEventData(eventData: EventsDataResponse) {
        this.eventData = eventData
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_booking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val numTicketsEditText: EditText = view.findViewById(R.id.numTicketsEditText)
        val totalPriceTextView: TextView = view.findViewById(R.id.totalPriceTextView)
        val confirmBookingButton: Button = view.findViewById(R.id.confirmBookingButton)

        // Add a custom InputFilter to restrict input to the range 1-5 (changed from 1-10)
        numTicketsEditText.filters = arrayOf(RangeInputFilter(1, 5))

        numTicketsEditText.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: android.text.Editable?) {
                updateTotalPrice(numTicketsEditText, totalPriceTextView)
            }
        })

        confirmBookingButton.setOnClickListener {
            val numTickets = numTicketsEditText.text.toString().toInt()

            // Use eventData.price to get the event-specific price
            val ticketPrice = eventData.price ?: 25.0
            val totalPrice = numTickets * ticketPrice.toDouble()

            listener.onBookingConfirmed(numTickets, totalPrice)

            // Make a PUT request to the backend
            makeBookingRequest(eventData.id.orEmpty(), numTickets, totalPrice)

            // Show the thank-you dialog
            val thankYouDialog = ThankYouDialogFragment()
            thankYouDialog.show(requireFragmentManager(), "ThankYouDialog")

            dismiss()
        }
    }

    private fun updateTotalPrice(numTicketsEditText: EditText, totalPriceTextView: TextView) {
        val numTickets = numTicketsEditText.text.toString().toIntOrNull() ?: 0

        // Use eventData.price to get the event-specific price
        val ticketPrice = eventData.price ?: 25.0

        val totalPrice = numTickets * ticketPrice.toDouble()

        val decimalFormat = DecimalFormat("#.00")
        val formattedTotalPrice = "$" + decimalFormat.format(totalPrice)

        totalPriceTextView.text = "Total Price: $formattedTotalPrice"
    }

    // Custom InputFilter to restrict input to a specified range
    private class RangeInputFilter(private val minValue: Int, private val maxValue: Int) :
        InputFilter {
        override fun filter(
            source: CharSequence?,
            start: Int,
            end: Int,
            dest: Spanned?,
            dstart: Int,
            dend: Int
        ): CharSequence? {
            try {
                // Convert the new input to an integer
                val input =
                    (dest?.subSequence(0, dstart).toString() + source + dest?.subSequence(
                        dend,
                        dest.length
                    )).toInt()

                // Check if the input is within the specified range
                if (isInRange(input)) {
                    return null // Accept the input
                }
            } catch (ignored: NumberFormatException) {
                // Ignore the exception, the input is not a valid integer
            }

            // Input is not within the specified range, so reject it
            return ""
        }

        private fun isInRange(value: Int): Boolean {
            return value >= minValue && value <= maxValue
        }
    }

    private fun makeBookingRequest(eventId: String, numTickets: Int, totalPrice: Double) {
        val bookingRequest = BookingRequest(eventId, numTickets, totalPrice)

        // Create Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://event-snap.cyclic.app/") // Replace with your base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create BackendService
        val backendService = retrofit.create(BackendService::class.java)

        // Make PUT request
        backendService.bookEvent(eventId, bookingRequest).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Booking successful
                    // You may handle the success response here
                } else {
                    // Booking failed
                    // You may handle the failure response here
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Booking failed
                // You may handle the failure response here
            }
        })
    }
}




















