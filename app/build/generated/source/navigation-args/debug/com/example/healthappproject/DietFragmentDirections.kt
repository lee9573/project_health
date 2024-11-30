package com.example.healthappproject

import android.os.Bundle
import androidx.navigation.NavDirections
import kotlin.Int
import kotlin.String

public class DietFragmentDirections private constructor() {
  private data class ActionDietFragmentToRecomendFragment(
    public val mealType: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_dietFragment_to_recomendFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("mealType", this.mealType)
        return result
      }
  }

  public companion object {
    public fun actionDietFragmentToRecomendFragment(mealType: String): NavDirections =
        ActionDietFragmentToRecomendFragment(mealType)
  }
}
