package com.example.healthappproject

import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.healthappproject.databinding.FragmentRecordBinding

class RecordFragment : Fragment() {

    private var _binding: FragmentRecordBinding? = null
    private val binding get() = _binding!!

    private var startTime: Long = 0
    private var accumulatedTime: Long = 0
    private var isTiming = false
    private val handler = Handler()
    private lateinit var runnable: Runnable

    private var setTimerRunning = false
    private var setTimerStartTime: Long = 0
    private val setTimerDuration: Long = 30000 // 30초
    private lateinit var setTimerRunnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonStartTimer.setOnClickListener {
            if (isTiming) {
                stopTimer()
            } else {
                startTimer()
            }
        }

        binding.buttonStartSetTimer.setOnClickListener {
            if (setTimerRunning) {
                stopSetTimer()
            } else {
                startSetTimer()
            }
        }
    }

    private fun startTimer() {
        startTime = SystemClock.elapsedRealtime()
        isTiming = true
        binding.buttonStartTimer.text = "Stop"

        runnable = object : Runnable {
            override fun run() {
                val elapsedMillis = SystemClock.elapsedRealtime() - startTime + accumulatedTime
                val seconds = (elapsedMillis / 1000) % 60
                val minutes = (elapsedMillis / 1000) / 60
                binding.textTodayTime.text = String.format("Record Time: %02d:%02d", minutes, seconds)
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(runnable)
    }

    private fun stopTimer() {
        isTiming = false
        accumulatedTime += SystemClock.elapsedRealtime() - startTime
        binding.buttonStartTimer.text = "Start"
        handler.removeCallbacks(runnable)
    }

    private fun startSetTimer() {
        setTimerStartTime = SystemClock.elapsedRealtime()
        setTimerRunning = true
        binding.buttonStartSetTimer.text = "Stop Set Timer"

        setTimerRunnable = object : Runnable {
            override fun run() {
                val elapsedMillis = SystemClock.elapsedRealtime() - setTimerStartTime
                val remainingMillis = setTimerDuration - elapsedMillis
                if (remainingMillis > 0) {
                    val seconds = (remainingMillis / 1000) % 60
                    binding.textSetTimer.text = String.format("Set Timer: 00:%02d", seconds)
                    handler.postDelayed(this, 1000)
                } else {
                    binding.textSetTimer.text = "Set Timer: 00:00"
                    stopSetTimer()
                }
            }
        }
        handler.post(setTimerRunnable)
    }

    private fun stopSetTimer() {
        setTimerRunning = false
        binding.buttonStartSetTimer.text = "Start Set Timer"
        handler.removeCallbacks(setTimerRunnable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
