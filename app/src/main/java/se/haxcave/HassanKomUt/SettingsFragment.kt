package se.haxcave.HassanKomUt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import se.haxcave.HassanKomUt.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SettingsFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var storage: Storage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        storage = Storage(requireContext())
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.editName.addTextChangedListener { s ->
            save(name = s.toString())
        }

        binding.editNumber.addTextChangedListener { s ->
            save(number = s.toString())
        }

        binding.editMessage.addTextChangedListener { s ->
            save(message = s.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        val message = storage.getMessage()
        binding.editName.setText(message.name)
        binding.editNumber.setText(message.number.toString())
        binding.editMessage.setText(message.message)
    }

    private fun save(
        name: String = binding.editName.text.toString(),
        number: String = binding.editNumber.text.toString(),
        message: String = binding.editMessage.text.toString()
    ) {
        storage.save(Message(name, number, message))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}