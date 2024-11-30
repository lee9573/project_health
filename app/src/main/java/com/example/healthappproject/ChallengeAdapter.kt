package com.example.healthappproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthappproject.databinding.ItemChallengeBinding

class ChallengeAdapter(
    private var challenges: List<String>,
    private val onCheckedChange: (Boolean) -> Unit
) : RecyclerView.Adapter<ChallengeAdapter.ChallengeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val binding = ItemChallengeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChallengeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        holder.bind(challenges[position])
    }

    override fun getItemCount(): Int = challenges.size

    fun updateChallenges(newChallenges: List<String>) {
        challenges = newChallenges
        notifyDataSetChanged()
    }

    inner class ChallengeViewHolder(private val binding: ItemChallengeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(challenge: String) {
            binding.challengeText.text = challenge
            binding.challengeCheckbox.setOnCheckedChangeListener { _, isChecked ->
                onCheckedChange(isChecked)
            }
        }
    }
}
