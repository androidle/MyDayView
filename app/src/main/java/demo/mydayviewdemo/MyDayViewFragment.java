package demo.mydayviewdemo;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import org.jetbrains.annotations.Nullable;

/**
 * Created by  Leevin
 * on 2016/7/10 ,18:21.
 */
public class MyDayViewFragment extends Fragment implements ViewSwitcher.ViewFactory {

    private static final String NUM_OF_DAYS = "num_of_days";
    private static final String TIME_MILLIS = "time_millis";
    private static final int VIEW_ID = 1;
    private int mNumDays;
    protected ViewSwitcher mViewSwitcher;
    protected Animation mInAnimationForward;
    protected Animation mOutAnimationForward;
    protected Animation mInAnimationBackward;
    protected Animation mOutAnimationBackward;
    Time mSelectedDay = new Time();
    private CalendarController mCalendarController;
    private Menu mMenu;

   /* private Runnable mTZUpdater = new Runnable() {
        @Override
        public void run() {
            if (!MyDayViewFragment.this.isAdded()) {
                return;
            }
            String tz = Utils.getTimeZone(getActivity(), mTZUpdater);
            mSelectedDay.timezone = tz;
            mSelectedDay.normalize(true);
        }
    };*/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.day_view_fragment_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_clear:
                Toast.makeText(getActivity(), "clear all", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_today:
                goToToday();
                break;
            case R.id.menu_refresh:
                Toast.makeText(getActivity(), "refresh", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private void goToToday() {
        long now = System.currentTimeMillis();
        Time time = new Time();
        time.set(now);
        goTo(time,false,true);
        mCalendarController.setTime(time.toMillis(true));
        Toast.makeText(getActivity(), "togoToday", Toast.LENGTH_SHORT).show();
    }

    public static MyDayViewFragment newInstance(long timeMillis, int numOfDays) {
        MyDayViewFragment myDayViewFragment = new MyDayViewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(NUM_OF_DAYS, numOfDays);
        bundle.putLong(TIME_MILLIS, timeMillis);
        myDayViewFragment.setArguments(bundle);
        return myDayViewFragment;
    }

    public MyDayViewFragment() {
        mSelectedDay.setToNow();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initArguments();
        Context context = getActivity();

        mInAnimationForward = AnimationUtils.loadAnimation(context, R.anim.slide_left_in);
        mOutAnimationForward = AnimationUtils.loadAnimation(context, R.anim.slide_left_out);
        mInAnimationBackward = AnimationUtils.loadAnimation(context, R.anim.slide_right_in);
        mOutAnimationBackward = AnimationUtils.loadAnimation(context, R.anim.slide_right_out);

        mCalendarController = CalendarController.getInstance(getActivity());
    }

    private void initArguments() {
        mNumDays = getArguments().getInt(NUM_OF_DAYS);
        long timeMillis = getArguments().getLong(TIME_MILLIS);
        if (timeMillis == 0) {
            mSelectedDay.setToNow();
        } else {
            mSelectedDay.set(timeMillis);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_day_view, container, false);
        mViewSwitcher = (ViewSwitcher) v.findViewById(R.id.switcher);
        mViewSwitcher.setFactory(this);
        mViewSwitcher.getCurrentView().requestFocus();
        ((MyDayView) mViewSwitcher.getCurrentView()).updateTitle();
        // Set up floating action button
        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.add);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "add", Toast.LENGTH_SHORT).show();
            }
        });

        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
//        long currentTimeMillis = System.currentTimeMillis();
//        Time currentTime = new Time();
//        currentTime.set(currentTimeMillis);
//        String today = "" + currentTime.monthDay ;
        MyDayView currentView = (MyDayView) mViewSwitcher.getCurrentView();
        String today = "" + currentView.mCurrentTime.monthDay;
        menu.findItem(R.id.menu_today).setTitle(today);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public View makeView() {
        // TODO: 2016/7/10 init timeZone
        MyDayView view = new MyDayView(getActivity(), mCalendarController, mViewSwitcher, mNumDays);
        view.setLayoutParams(new ViewSwitcher.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        view.setSelected(mSelectedDay, false, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        mTZUpdater.run();
        eventsChanged();
        MyDayView view = (MyDayView) mViewSwitcher.getCurrentView();
        view.handleOnResume();
        view.restartCurrentTimeUpdates();

        view = (MyDayView) mViewSwitcher.getNextView();
        view.handleOnResume();
        view.restartCurrentTimeUpdates();
    }

    public void eventsChanged() {
        if (mViewSwitcher == null) {
            return;
        }
        MyDayView view = (MyDayView) mViewSwitcher.getCurrentView();
//        view.clearCachedEvents();
        view.reloadEvents();

        view = (MyDayView) mViewSwitcher.getNextView();
//        view.clearCachedEvents();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // TODO: 2016/7/10  
    }

    @Override
    public void onPause() {
        super.onPause();
        // TODO: 2016/7/10
    }

    /**
     * Returns the selected time in milliseconds. The milliseconds are measured
     * in UTC milliseconds from the epoch and uniquely specifies any selectable
     * time.
     *
     * @return the selected time in milliseconds
     */
    public long getSelectedTimeInMillis() {
        if (mViewSwitcher == null) {
            return -1;
        }
        MyDayView view = (MyDayView) mViewSwitcher.getCurrentView();
        if (view == null) {
            return -1;
        }
        return view.getSelectedTimeInMillis();
    }

    public MyDayView getNextView() {
        return (MyDayView) mViewSwitcher.getNextView();
    }

    private void goTo(Time goToTime, boolean ignoreTime, boolean animateToday) {
        if (mViewSwitcher == null) {
            // The view hasn't been set yet. Just save the time and use it later.
            mSelectedDay.set(goToTime);
            return;
        }

        MyDayView currentView = (MyDayView) mViewSwitcher.getCurrentView();

        // How does goTo time compared to what's already displaying?
        int diff = currentView.compareToVisibleTimeRange(goToTime);

        if (diff == 0) {
            // In visible range. No need to switch view
            currentView.setSelected(goToTime, ignoreTime, animateToday);
        } else {
            // Figure out which way to animate
            if (diff > 0) {
                mViewSwitcher.setInAnimation(mInAnimationForward);
                mViewSwitcher.setOutAnimation(mOutAnimationForward);
            } else {
                mViewSwitcher.setInAnimation(mInAnimationBackward);
                mViewSwitcher.setOutAnimation(mOutAnimationBackward);
            }

            MyDayView next = (MyDayView) mViewSwitcher.getNextView();
            if (ignoreTime) {
                next.setFirstVisibleHour(currentView.getFirstVisibleHour());
            }

            next.setSelected(goToTime, ignoreTime, animateToday);
            next.reloadEvents();
            mViewSwitcher.showNext();
            next.requestFocus();
            next.updateTitle();
            next.restartCurrentTimeUpdates();
        }
    }

    public String getCurrentTitle() {
        Time time = mSelectedDay;
        int year = time.year;
        int month = time.month;
        return year + "年" + month+1 + "月";
    }



}
