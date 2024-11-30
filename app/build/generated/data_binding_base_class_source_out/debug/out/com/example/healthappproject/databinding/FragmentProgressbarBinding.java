// Generated by view binder compiler. Do not edit!
package com.example.healthappproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.healthappproject.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentProgressbarBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView perDis;

  @NonNull
  public final ProgressBar progressKcal;

  @NonNull
  public final LinearLayout progressKcalDisplay;

  private FragmentProgressbarBinding(@NonNull ConstraintLayout rootView, @NonNull TextView perDis,
      @NonNull ProgressBar progressKcal, @NonNull LinearLayout progressKcalDisplay) {
    this.rootView = rootView;
    this.perDis = perDis;
    this.progressKcal = progressKcal;
    this.progressKcalDisplay = progressKcalDisplay;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentProgressbarBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentProgressbarBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_progressbar, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentProgressbarBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.per_dis;
      TextView perDis = ViewBindings.findChildViewById(rootView, id);
      if (perDis == null) {
        break missingId;
      }

      id = R.id.progress_kcal;
      ProgressBar progressKcal = ViewBindings.findChildViewById(rootView, id);
      if (progressKcal == null) {
        break missingId;
      }

      id = R.id.progress_kcal_display;
      LinearLayout progressKcalDisplay = ViewBindings.findChildViewById(rootView, id);
      if (progressKcalDisplay == null) {
        break missingId;
      }

      return new FragmentProgressbarBinding((ConstraintLayout) rootView, perDis, progressKcal,
          progressKcalDisplay);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
