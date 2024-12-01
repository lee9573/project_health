package com.example.healthappproject

import android.os.Bundle
import androidx.navigation.NavDirections
import kotlin.Int
import kotlin.String

public class RecomendFragmentDirections private constructor() {
  private data class ActionRecomendFragmentToDietFragment(
    public val nameInput: String,
    public val kcalInput: String,
    public val gInput: String,
    public val carboInput: String,
    public val proteinInput: String,
    public val fatInput: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_recomendFragment_to_dietFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("name_input", this.nameInput)
        result.putString("kcal_input", this.kcalInput)
        result.putString("g_input", this.gInput)
        result.putString("carbo_input", this.carboInput)
        result.putString("protein_input", this.proteinInput)
        result.putString("fat_input", this.fatInput)
        return result
      }
  }

  public companion object {
    public fun actionRecomendFragmentToDietFragment(
      nameInput: String,
      kcalInput: String,
      gInput: String,
      carboInput: String,
      proteinInput: String,
      fatInput: String,
    ): NavDirections = ActionRecomendFragmentToDietFragment(nameInput, kcalInput, gInput,
        carboInput, proteinInput, fatInput)
  }
}
