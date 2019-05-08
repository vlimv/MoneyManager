package vlimv.moneymanager;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.udojava.evalex.Expression;

import java.math.BigDecimal;
import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import vlimv.moneymanager.Adapters.CategoryGridAdapter;

public class AddExpenseActivity extends AppCompatActivity implements View.OnClickListener {

    String expression = "";
    TextView resultTextView;
    ArrayList<Button> digitButtons;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_expense);

        GridView grid;
        final String[] web = {
                "Travel",
                "Meat",
                "Date",
                "Laundry",
                "Fitness",
                "Fish",
                "Alcohol",
                "Electricity"
        } ;
        int[] imageId = {
                R.drawable.plane,
                R.drawable.meat,
                R.drawable.love,
                R.drawable.washing_machine,
                R.drawable.fitness,
                R.drawable.fish,
                R.drawable.alcohol,
                R.drawable.electricity,
        };
        int[] colorId = {
                R.color.amber,
                R.color.emerald,
                R.color.azurite,
                R.color.coral,
                R.color.gold,
                R.color.sapphire,
                R.color.pezzottaite,
                R.color.onyx
        };

        CategoryGridAdapter adapter = new CategoryGridAdapter(AddExpenseActivity.this, web, imageId, colorId);
        grid = findViewById(R.id.categories_grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(AddExpenseActivity.this, "You Clicked at " + web[+ position], Toast.LENGTH_SHORT).show();

            }
        });

        digitButtons = new ArrayList<>();
        digitButtons.add((Button)findViewById(R.id.digit0));
        digitButtons.add((Button)findViewById(R.id.digit1));
        digitButtons.add((Button)findViewById(R.id.digit2));
        digitButtons.add((Button)findViewById(R.id.digit3));
        digitButtons.add((Button)findViewById(R.id.digit4));
        digitButtons.add((Button)findViewById(R.id.digit5));
        digitButtons.add((Button)findViewById(R.id.digit6));
        digitButtons.add((Button)findViewById(R.id.digit7));
        digitButtons.add((Button)findViewById(R.id.digit8));
        digitButtons.add((Button)findViewById(R.id.digit9));

        for (int i = 0; i < digitButtons.size(); i++) {
            digitButtons.get(i).setOnClickListener(this);
        }
        Button plusButton = findViewById(R.id.plus_sign);
        Button minusButton = findViewById(R.id.minus_sign);
        Button multButton = findViewById(R.id.multi_sign);
        Button equalsButton = findViewById(R.id.equal_sign);
        Button dotButton = findViewById(R.id.dot_sign);
        Button eraseButton = findViewById(R.id.erase_sign);

        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        multButton.setOnClickListener(this);
        equalsButton.setOnClickListener(this);
        dotButton.setOnClickListener(this);
        eraseButton.setOnClickListener(this);

        resultTextView = findViewById(R.id.result_text);



    }

    @Override
    public void onClick(View v) {
        if (digitButtons.contains(v)) {
            expression += digitButtons.indexOf(v);
        } else {
            if (expression.length() > 0) {
                char lastChar = expression.charAt(expression.length() - 1);
                boolean isDigit = Character.isDigit(lastChar);
                boolean isSign = lastChar == '+' || lastChar == '-' || lastChar == '*';
                boolean isDot = lastChar == '.';
                if (!isDigit && isSign) {
                    expression = removeLastChar(expression);

                }
                switch(v.getId()) {
                    case R.id.plus_sign:
                        expression += "+";
                        break;
                    case R.id.minus_sign:
                        expression += "-";
                        break;
                    case R.id.multi_sign:
                        expression += "*";
                        break;
                    case R.id.dot_sign:
                        if (!isDot)
                            expression += ".";
                        break;
                    case R.id.erase_sign:
                        expression = removeLastChar(expression);
                        break;
                    case R.id.equal_sign:
                        BigDecimal resultBD = new Expression(expression).eval();
                        float result = resultBD.floatValue();
                        expression = result + "";
                }
            }
        }

        resultTextView.setText(expression);
    }
    private String removeLastChar (String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
}
