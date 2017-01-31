package com.backendless.hacktest.data.crud.create;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.backendless.hacktest.data.*;
import com.backendless.hacktest.data.crud.R;
import com.backendless.hacktest.data.crud.common.DataApplication;
import com.backendless.hacktest.data.crud.common.DefaultCallback;
import com.backendless.hacktest.data.crud.common.SendEmailActivity;
import com.backendless.geo.GeoPoint;

import java.util.ArrayList;
import java.util.Collections;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CreateRecordActivity extends Activity
{
  private TextView titleView;
  private EditText codeEdit;
  private Button runCodeButton, sendCodeButton;

  private String code;
  private String table;

                                    
  class CreateaccountsRecordTask extends AsyncTask<Void, Void, accounts>
  {
    accounts accounts = new accounts();

    @Override
    protected void onPreExecute()
    {
      accounts.setTodo( UUID.randomUUID().toString() );
      accounts.setUsername( UUID.randomUUID().toString() );
      accounts.setPassword( UUID.randomUUID().toString() );
    }

    @Override
    protected accounts doInBackground( Void... voids )
    {
      return accounts.save();
    }
  };
                                    

  public void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.sample_code_template );

    DataApplication dataApplication = (DataApplication) getApplication();
    table = dataApplication.getChosenTable();

    initUI();
  }

  private void initUI()
  {
    titleView = (TextView) findViewById( R.id.sampleCodeTitle );
    codeEdit = (EditText) findViewById( R.id.sampleCodeEdit );
    runCodeButton = (Button) findViewById( R.id.runCodeButton );
    sendCodeButton = (Button) findViewById( R.id.sendCodeButton );

    String titleTemplate = getResources().getString( R.string.create_record_title_template );
    String title = String.format( titleTemplate, table );
    titleView.setText( title );

    if( table.equals( "accounts" ) )
    {
      code = getAccountsCreationCode();
    }

    codeEdit.setText( code );

    runCodeButton.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View view )
      {
        onRunCodeButtonClicked();
      }
    } );

    sendCodeButton.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View view )
      {
        onSendCodeButtonClicked();
      }
    } );
  }

  private void onRunCodeButtonClicked()
  {
    if( table.equals( "accounts" ) )
    {
      createAccountsRecord();
    }
  }

  private void onSendCodeButtonClicked()
  {
    Intent nextIntent = new Intent( getBaseContext(), SendEmailActivity.class );
    nextIntent.putExtra( "code", code );
    nextIntent.putExtra( "table", table );
    nextIntent.putExtra( "method", "Create" );
    startActivity( nextIntent );
  }

  private String getAccountsCreationCode()
  {
    return "accounts accounts = new accounts();\n"
            + "accounts.setTodo( UUID.randomUUID().toString() );\n"
            + "accounts.setUsername( UUID.randomUUID().toString() );\n"
            + "accounts.setPassword( UUID.randomUUID().toString() );\n"
            + "accounts.saveAsync( new AsyncCallback<accounts>()\n"
            + "{\n"
            + "  @Override\n"
            + "  public void handleResponse( accounts savedAccounts )\n"
            + "  {\n"
            + "    accounts = savedAccounts;\n"
            + "  }\n"
            + "  @Override\n"
            + "  public void handleFault( BackendlessFault fault )\n"
            + "  {\n"
            + "    Toast.makeText( getBaseContext(), fault.getMessage(), Toast.LENGTH_SHORT ).show();\n"
            + "  }\n"
            + "});";
  }

  private void createAccountsRecord()
  {
    accounts accounts = null;

    try
    {
      accounts = new CreateaccountsRecordTask().execute().get( 30, TimeUnit.SECONDS );
    }
    catch ( Exception e )
    {
      Toast.makeText( this, e.getMessage(), Toast.LENGTH_SHORT ).show();
      return;
    }

    Intent nextIntent = new Intent( CreateRecordActivity.this, CreateSuccessActivity.class );
    startActivity( nextIntent );
  }
}