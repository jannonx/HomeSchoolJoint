package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.base.BaseFragment;
import com.overdose.homeschooljoint.bean.MySubject;
import com.overdose.homeschooljoint.utils.UserUtils;
import com.overdose.homeschooljoint.utils.timetable.TimetableView;
import com.overdose.homeschooljoint.utils.timetable.listener.ISchedule;
import com.overdose.homeschooljoint.utils.timetable.model.Schedule;

import java.util.List;

import butterknife.BindView;

public class CourseFragment extends BaseFragment {

  public static final String TAG = "CourseFragment";
  @BindView(R.id.id_timetableView)
  TimetableView mIdTimetableView;

  public static CourseFragment newInstance() {
    Bundle args = new Bundle();
    CourseFragment fragment = new CourseFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public int getLayoutResId() {
    return R.layout.fragment_couorse;

  }

  @Override
  public void onUnBind() {

  }

  @Override
  protected void initialization() {

    List<MySubject> mySubjects = UserUtils.loadDefaultSubjects();
    mIdTimetableView.source(mySubjects)
        .callback(new ISchedule.OnItemClickListener() {
          @Override
          public void onItemClick(View v, List<Schedule> scheduleList) {
            display(scheduleList);
          }
        })
        .callback(new ISchedule.OnWeekChangedListener() {
          @Override
          public void onWeekChanged(int curWeek) {
            Toast.makeText(getContext(), "第" + curWeek + "周", Toast.LENGTH_SHORT).show();
          }
        })
        .showView();
  }


  /**
   * 显示内容
   *
   * @param beans
   */
  protected void display(List<Schedule> beans) {
    String str = "";
    for (Schedule bean : beans) {
      str += bean.getName() + "、";
    }
    Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
  }
}
