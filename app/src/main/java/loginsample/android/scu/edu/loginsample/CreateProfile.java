package loginsample.android.scu.edu.loginsample;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class CreateProfile extends ActionBarActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        TextView FName = (TextView) findViewById(R.id.fName);
        TextView LName = (TextView) findViewById(R.id.lName);
        TextView Age = (TextView) findViewById(R.id.Age);
        TextView Skills = (TextView) findViewById(R.id.Skills);

        final CheckBox Cleaning = (CheckBox) findViewById(R.id.Cleaning);
        final CheckBox Moving = (CheckBox) findViewById(R.id.Moving);
        final CheckBox Handywork = (CheckBox) findViewById(R.id.Handywork);
        final CheckBox Painting = (CheckBox) findViewById(R.id.Painting);
        final CheckBox Organizing = (CheckBox) findViewById(R.id.Organizing);
        final CheckBox Deliveries = (CheckBox) findViewById(R.id.Deliveries);

        final EditText firstName = (EditText) findViewById(R.id.inputFName);
        final EditText lastName = (EditText) findViewById(R.id.inputLname);
        final EditText inputAge = (EditText) findViewById(R.id.inputAge);



        Button Done = (Button) findViewById(R.id.Done);
        ImageButton Picture = (ImageButton) findViewById(R.id.Picture);

        FName.setText("First Name:");
        LName.setText("Last Name:");
        Age.setText("Age:");
        Skills.setText("Skills");

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle user = new Bundle();
                user.putString("fName",firstName.getText().toString());
                user.putString("lName",lastName.getText().toString());
                user.putString("age",inputAge.getText().toString());


                //adding activites to array to be passed as user info
                String [] activites = new String[6];
                int i = 0;
                if(Cleaning.isChecked() == true){
                    activites[i] = Cleaning.getText().toString();
                    i++;
                }
                if(Moving.isChecked() == true){
                    activites[i] = Moving.getText().toString();
                    i++;
                }
                if(Handywork.isChecked() == true){
                    activites[i] = Handywork.getText().toString();
                    i++;
                }
                if(Painting.isChecked() == true){
                    activites[i] = Organizing.getText().toString();
                    i++;
                }
                if(Organizing.isChecked() == true){
                    activites[i] = Organizing.getText().toString();
                    i++;
                }
                if(Deliveries.isChecked() == true){
                    activites[i] = Deliveries.getText().toString();
                    i++;
                }

                user.putStringArray("activites",activites);


                Intent intent = new Intent(CreateProfile.this,ViewProfile.class);
                intent.putExtra("user1",user);
                startActivity(intent);





            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_profile, menu);
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
