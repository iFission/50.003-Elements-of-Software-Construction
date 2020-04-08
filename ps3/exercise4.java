// output should be:
// true, false, true, true
// however, the following is obtained
// false, false, true, true

import java.util.Calendar;
import java.util.Date;

public class exercise4 {
	public static void main(final String[] args) throws InterruptedException {
		final Calendar cal1 = new CalendarSubclass();
		cal1.setTime(new Date());
		Thread.sleep(1000);
		final Calendar cal2 = new CalendarSubclass();
		cal2.setTime(new Date());
		System.out.println(cal2.after(cal1));
		System.out.println(cal1.after(cal2));
		System.out.println(cal1.after(cal1));
		System.out.println(cal2.after(cal2));
	}
}

class CalendarSubclass extends Calendar {

	Calendar calendar = Calendar.getInstance();

	@Override
	public boolean after(final Object when) {
		if (when instanceof Calendar && super.compareTo((Calendar) when) == 0) {
			//if (when instanceof Calendar && ((Calendar) when).toString().equals(this.toString())) {
			//if (when instanceof Calendar && equals((Calendar) when)) {		
			System.out.println("lala");
			return true;
		}
		return calendar.after(when);
	}

	@Override
	public int compareTo(final Calendar anotherCalendar) {
		return compareDays(this.getFirstDayOfWeek(), anotherCalendar.getFirstDayOfWeek());
	}

	private int compareDays(final int currentFirstDayOfWeek, final int anotherFirstDayOfWeek) {
		return (currentFirstDayOfWeek > anotherFirstDayOfWeek) ? 1
				: (currentFirstDayOfWeek == anotherFirstDayOfWeek) ? 0 : -1;
	}

	// Implementation of other Calendar abstract methods skipped

	@Override
	public void add(final int field, final int amount) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void computeFields() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void computeTime() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getGreatestMinimum(final int field) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLeastMaximum(final int field) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaximum(final int field) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinimum(final int field) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void roll(final int field, final boolean up) {
		// TODO Auto-generated method stub

	}
}
