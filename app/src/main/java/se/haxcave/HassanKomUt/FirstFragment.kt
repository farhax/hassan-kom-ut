package se.haxcave.HassanKomUt

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import se.haxcave.HassanKomUt.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var storage: Storage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        storage = Storage(requireContext())
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        val message = storage.getMessage()
        binding.action.text = message.message

        if (message.number.isBlank()) {
            binding.action.visibility = View.GONE
            return
        }


        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            // Request permissions for sms
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.SEND_SMS), 0)
            return
        }

        binding.action.visibility = View.VISIBLE


        binding.action.setOnClickListener {
            sendSMS(message)
        }
    }

    private fun sendSMS(message: Message) {
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(message.number.toString(), null, message.message, null, null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}