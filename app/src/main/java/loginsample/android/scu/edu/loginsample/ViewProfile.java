package loginsample.android.scu.edu.loginsample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class ViewProfile extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        TextView name = (TextView) findViewById(R.id.Name);
        TextView Age = (TextView) findViewById(R.id.Age);
        TextView Location = (TextView) findViewById(R.id.Location);

        Bundle user = getIntent().getExtras();

        if(user == null){
            Toast.makeText(getApplicationContext(), "user null", Toast.LENGTH_SHORT).show();
        }

        String userName = user.getString("fName") + " " + user.getString("lName");
        String userAge = user.getString("age");

        name.setText("name is " + userName);
        Age.setText("age is " + userAge);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
