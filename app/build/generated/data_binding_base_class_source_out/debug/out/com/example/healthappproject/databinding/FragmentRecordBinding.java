// Generated by view binder compiler. Do not edit!
package com.example.healthappproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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

public final class FragmentRecordBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final LinearLayout aerobicContainer;

  @NonNull
  public final LinearLayout armContainer;

  @NonNull
  public final LinearLayout backContainer;

  @NonNull
  public final Button buttonAddAerobicSet;

  @NonNull
  public final Button buttonAddArmSet;

  @NonNull
  public final Button buttonAddBackSet;

  @NonNull
  public final Button buttonAddChestSet;

  @NonNull
  public final Button buttonAddLegSet;

  @NonNull
  public final Button buttonStartSetTimer;

  @NonNull
  public final Button buttonStartTimer;

  @NonNull
  public final LinearLayout chestContainer;

  @NonNull
  public final ImageButton imageButtonAerobic;

  @NonNull
  public final ImageButton imageButtonArm;

  @NonNull
  public final ImageButton imageButtonBack;

  @NonNull
  public final ImageButton imageButtonCore;

  @NonNull
  public final ImageButton imageButtonLeg;

  @NonNull
  public final LinearLayout legContainer;

  @NonNull
  public final LinearLayout recordSetSection;

  @NonNull
  public final LinearLayout setTimerSection;

  @NonNull
  public final TextView textSetTimer;

  @NonNull
  public final TextView textTodayTime;

  @NonNull
  public final TextView textYesterdayTime;

  @NonNull
  public final LinearLayout timeRecordSection;

  private FragmentRecordBinding(@NonNull ConstraintLayout rootView,
      @NonNull LinearLayout aerobicContainer, @NonNull LinearLayout armContainer,
      @NonNull LinearLayout backContainer, @NonNull Button buttonAddAerobicSet,
      @NonNull Button buttonAddArmSet, @NonNull Button buttonAddBackSet,
      @NonNull Button buttonAddChestSet, @NonNull Button buttonAddLegSet,
      @NonNull Button buttonStartSetTimer, @NonNull Button buttonStartTimer,
      @NonNull LinearLayout chestContainer, @NonNull ImageButton imageButtonAerobic,
      @NonNull ImageButton imageButtonArm, @NonNull ImageButton imageButtonBack,
      @NonNull ImageButton imageButtonCore, @NonNull ImageButton imageButtonLeg,
      @NonNull LinearLayout legContainer, @NonNull LinearLayout recordSetSection,
      @NonNull LinearLayout setTimerSection, @NonNull TextView textSetTimer,
      @NonNull TextView textTodayTime, @NonNull TextView textYesterdayTime,
      @NonNull LinearLayout timeRecordSection) {
    this.rootView = rootView;
    this.aerobicContainer = aerobicContainer;
    this.armContainer = armContainer;
    this.backContainer = backContainer;
    this.buttonAddAerobicSet = buttonAddAerobicSet;
    this.buttonAddArmSet = buttonAddArmSet;
    this.buttonAddBackSet = buttonAddBackSet;
    this.buttonAddChestSet = buttonAddChestSet;
    this.buttonAddLegSet = buttonAddLegSet;
    this.buttonStartSetTimer = buttonStartSetTimer;
    this.buttonStartTimer = buttonStartTimer;
    this.chestContainer = chestContainer;
    this.imageButtonAerobic = imageButtonAerobic;
    this.imageButtonArm = imageButtonArm;
    this.imageButtonBack = imageButtonBack;
    this.imageButtonCore = imageButtonCore;
    this.imageButtonLeg = imageButtonLeg;
    this.legContainer = legContainer;
    this.recordSetSection = recordSetSection;
    this.setTimerSection = setTimerSection;
    this.textSetTimer = textSetTimer;
    this.textTodayTime = textTodayTime;
    this.textYesterdayTime = textYesterdayTime;
    this.timeRecordSection = timeRecordSection;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentRecordBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentRecordBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_record, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentRecordBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.aerobicContainer;
      LinearLayout aerobicContainer = ViewBindings.findChildViewById(rootView, id);
      if (aerobicContainer == null) {
        break missingId;
      }

      id = R.id.armContainer;
      LinearLayout armContainer = ViewBindings.findChildViewById(rootView, id);
      if (armContainer == null) {
        break missingId;
      }

      id = R.id.backContainer;
      LinearLayout backContainer = ViewBindings.findChildViewById(rootView, id);
      if (backContainer == null) {
        break missingId;
      }

      id = R.id.button_add_aerobic_set;
      Button buttonAddAerobicSet = ViewBindings.findChildViewById(rootView, id);
      if (buttonAddAerobicSet == null) {
        break missingId;
      }

      id = R.id.button_add_arm_set;
      Button buttonAddArmSet = ViewBindings.findChildViewById(rootView, id);
      if (buttonAddArmSet == null) {
        break missingId;
      }

      id = R.id.button_add_back_set;
      Button buttonAddBackSet = ViewBindings.findChildViewById(rootView, id);
      if (buttonAddBackSet == null) {
        break missingId;
      }

      id = R.id.button_add_chest_set;
      Button buttonAddChestSet = ViewBindings.findChildViewById(rootView, id);
      if (buttonAddChestSet == null) {
        break missingId;
      }

      id = R.id.button_add_leg_set;
      Button buttonAddLegSet = ViewBindings.findChildViewById(rootView, id);
      if (buttonAddLegSet == null) {
        break missingId;
      }

      id = R.id.button_start_set_timer;
      Button buttonStartSetTimer = ViewBindings.findChildViewById(rootView, id);
      if (buttonStartSetTimer == null) {
        break missingId;
      }

      id = R.id.button_start_timer;
      Button buttonStartTimer = ViewBindings.findChildViewById(rootView, id);
      if (buttonStartTimer == null) {
        break missingId;
      }

      id = R.id.chestContainer;
      LinearLayout chestContainer = ViewBindings.findChildViewById(rootView, id);
      if (chestContainer == null) {
        break missingId;
      }

      id = R.id.imageButtonAerobic;
      ImageButton imageButtonAerobic = ViewBindings.findChildViewById(rootView, id);
      if (imageButtonAerobic == null) {
        break missingId;
      }

      id = R.id.imageButtonArm;
      ImageButton imageButtonArm = ViewBindings.findChildViewById(rootView, id);
      if (imageButtonArm == null) {
        break missingId;
      }

      id = R.id.imageButtonBack;
      ImageButton imageButtonBack = ViewBindings.findChildViewById(rootView, id);
      if (imageButtonBack == null) {
        break missingId;
      }

      id = R.id.imageButtonCore;
      ImageButton imageButtonCore = ViewBindings.findChildViewById(rootView, id);
      if (imageButtonCore == null) {
        break missingId;
      }

      id = R.id.imageButtonLeg;
      ImageButton imageButtonLeg = ViewBindings.findChildViewById(rootView, id);
      if (imageButtonLeg == null) {
        break missingId;
      }

      id = R.id.legContainer;
      LinearLayout legContainer = ViewBindings.findChildViewById(rootView, id);
      if (legContainer == null) {
        break missingId;
      }

      id = R.id.record_set_section;
      LinearLayout recordSetSection = ViewBindings.findChildViewById(rootView, id);
      if (recordSetSection == null) {
        break missingId;
      }

      id = R.id.set_timer_section;
      LinearLayout setTimerSection = ViewBindings.findChildViewById(rootView, id);
      if (setTimerSection == null) {
        break missingId;
      }

      id = R.id.text_set_timer;
      TextView textSetTimer = ViewBindings.findChildViewById(rootView, id);
      if (textSetTimer == null) {
        break missingId;
      }

      id = R.id.text_today_time;
      TextView textTodayTime = ViewBindings.findChildViewById(rootView, id);
      if (textTodayTime == null) {
        break missingId;
      }

      id = R.id.text_yesterday_time;
      TextView textYesterdayTime = ViewBindings.findChildViewById(rootView, id);
      if (textYesterdayTime == null) {
        break missingId;
      }

      id = R.id.time_record_section;
      LinearLayout timeRecordSection = ViewBindings.findChildViewById(rootView, id);
      if (timeRecordSection == null) {
        break missingId;
      }

      return new FragmentRecordBinding((ConstraintLayout) rootView, aerobicContainer, armContainer,
          backContainer, buttonAddAerobicSet, buttonAddArmSet, buttonAddBackSet, buttonAddChestSet,
          buttonAddLegSet, buttonStartSetTimer, buttonStartTimer, chestContainer,
          imageButtonAerobic, imageButtonArm, imageButtonBack, imageButtonCore, imageButtonLeg,
          legContainer, recordSetSection, setTimerSection, textSetTimer, textTodayTime,
          textYesterdayTime, timeRecordSection);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
