// Generated by view binder compiler. Do not edit!
package com.example.healthappproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.example.healthappproject.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentChallengeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ProgressBar challengeProgress;

  @NonNull
  public final RecyclerView challengesRecyclerView;

  @NonNull
  public final TextView recommendText;

  @NonNull
  public final ViewPager2 youtubeBannerPager;

  private FragmentChallengeBinding(@NonNull ConstraintLayout rootView,
      @NonNull ProgressBar challengeProgress, @NonNull RecyclerView challengesRecyclerView,
      @NonNull TextView recommendText, @NonNull ViewPager2 youtubeBannerPager) {
    this.rootView = rootView;
    this.challengeProgress = challengeProgress;
    this.challengesRecyclerView = challengesRecyclerView;
    this.recommendText = recommendText;
    this.youtubeBannerPager = youtubeBannerPager;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentChallengeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentChallengeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_challenge, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentChallengeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.challenge_progress;
      ProgressBar challengeProgress = ViewBindings.findChildViewById(rootView, id);
      if (challengeProgress == null) {
        break missingId;
      }

      id = R.id.challengesRecyclerView;
      RecyclerView challengesRecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (challengesRecyclerView == null) {
        break missingId;
      }

      id = R.id.recommend_text;
      TextView recommendText = ViewBindings.findChildViewById(rootView, id);
      if (recommendText == null) {
        break missingId;
      }

      id = R.id.youtube_banner_pager;
      ViewPager2 youtubeBannerPager = ViewBindings.findChildViewById(rootView, id);
      if (youtubeBannerPager == null) {
        break missingId;
      }

      return new FragmentChallengeBinding((ConstraintLayout) rootView, challengeProgress,
          challengesRecyclerView, recommendText, youtubeBannerPager);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
