# Custom Calendar View

Custom calendar View is a fragment that display calendar. It can be used as embedded fragment. User can then swipe left/right to navigate to different months.

With this library, user can select the start of the week.  By default calendar start on Sunday.

Custom calendar View can be used with Android 2.3 and above.

![ScreenShot](http://nsa33.casimages.com/img/2015/02/17/15021704532437812.png)

# Setup

For Eclipse/ADT : Download the source code, check out the sample project to see how the library works.
If you see JAR mismatched error, replace your android-support-v4.jar to the jar inside the project.

# Features

You can embed Custom calendar fragment in your activity with below code:

```sh
CustomCalFragment customCalFragment = new CustomCalFragment();
Bundle args = new Bundle();
Calendar cal = Calendar.getInstance();
args.putInt(CustomCalFragment.MONTH, cal.get(Calendar.MONTH) + 1);
args.putInt(CustomCalFragment.YEAR, cal.get(Calendar.YEAR));
customCalFragment.setArguments(args);

FragmentTransaction t = getSupportFragmentManager().beginTransaction();
t.replace(R.id.calendar1, customCalFragment);
t.commit();
```

if you want to customize the startDayOfWeek, just use

```sh
Bundle args = new Bundle();
args.putInt(CustomCalFragment.START_DAY_OF_WEEK, CustomCalFragment.TUESDAY); // Tuesday
customCalFragment.setArguments(args);
```

# Set min / max date

You can use below methods:

```sh
public void setMinDate(Date minDate);
public void setMinDateFromString(String minDateString, String dateFormat);

public void setMaxDate(Date minDate);
public void setMaxDateFromString(String maxDateString, String dateFormat);
```

# Work with listener

Custom calendar view inform user via CustomCalListener.

```sh
final CustomCalListener listener = new CustomCalListener() {

    @Override
    public void onSelectDate(Date date, View view) {
        Toast.makeText(getApplicationContext(), formatter.format(date),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChangeMonth(int month, int year) {
        String text = "month: " + month + " year: " + year;
        Toast.makeText(getApplicationContext(), text,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClickDate(Date date, View view) {
        Toast.makeText(getApplicationContext(),
                "Long click " + formatter.format(date),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCaldroidViewCreated() {
        Toast.makeText(getApplicationContext(),
                "Custom Calendar View is created !",
                Toast.LENGTH_SHORT).show();
    }
};

customCalFragment.setCaldroidListener(listener);

```
