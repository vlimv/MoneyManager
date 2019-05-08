package vlimv.moneymanager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by HP on 23-Oct-18.
 */

public class CategoryTabsAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;

    public CategoryTabsAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                IncomeOutcomeFragment tab1 = new IncomeOutcomeFragment();
                return tab1;
            case 1:
                IncomeOutcomeFragment tab2 = new IncomeOutcomeFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
