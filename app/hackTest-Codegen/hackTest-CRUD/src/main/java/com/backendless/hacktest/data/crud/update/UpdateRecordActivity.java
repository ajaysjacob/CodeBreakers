package com.backendless.hacktest.data.crud.update;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.backendless.hacktest.data.*;
import com.backendless.hacktest.data.crud.R;
import com.backendless.hacktest.data.crud.common.DataApplication;
import com.backendless.hacktest.data.crud.common.DefaultCallback;
import com.backendless.hacktest.data.crud.common.SendEmailActivity;
import com.backendless.geo.GeoPoint;

import java.util.Collections;

import java.util.Random;
import java.util.UUID;

public class UpdateRecordActivity extends Activity
{
  private TextView titleView;
  private EditText codeView;
  private Button runCodeButton, sendCodeButton;

  private String code;
  private String table;

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
    codeView = (EditText) findViewById( R.id.sampleCodeEdit );
    runCodeButton = (Button) findViewById( R.id.runCodeButton );
    sendCodeButton = (Button) findViewById( R.id.sendCodeButton );

    String titleTemplate = getResources().getString( R.string.update_title_template );
    String title = String.format( titleTemplate, table );

    titleView.setText( title );

    if( table.equals( "accounts" ) )
    {
      code = getAccountsUpdateCode();
    }

    codeView.setText( code );

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
      updateAccountsRecord();
    }
  }

  private void onSendCodeButtonClicked()
  {
    Intent nextIntent = new Intent( getBaseContext(), SendEmailActivity.class );
    nextIntent.putExtra( "code", code );
    nextIntent.putExtra( "table", table );
    nextIntent.putExtra( "method", "Update" );
    startActivity( nextIntent );
  }

  private String getAccountsUpdateCode()
  {
    return "public void update( accounts accounts )\n"
            + "{\n"
            + "  accounts.setTodo( UUID.randomUUID().toString() );\n"
            + "  accounts.setUsername( UUID.randomUUID().toString() );\n"
            + "  accounts.setPassword( UUID.randomUUID().toString() );\n"
            + "  accounts.saveAsync( new AsyncCallback<accounts>()\n"
            + "  {\n"
            + "    @Override\n"
            + "    public void handleResponse( accounts updatedRecord )\n"
            + "    {\n"
            + "      //work with object\n"
            + "    }\n"
            + "    @Override\n"
            + "    public void handleFault( BackendlessFault fault )\n"
            + "    {\n"
            + "      Toast.makeText( getBaseContext(), fault.getMessage(), Toast.LENGTH_SHORT ).show();\n"
            + "    }\n"
            + "  }\n"
            + "}";
  }

  private void updateAccountsRecord()
  {
    accounts.findFirstAsync( new DefaultCallback<accounts>( UpdateRecordActivity.this )
    {
      @Override
      public void handleResponse( accounts response )
      {
        super.handleResponse( response );
        accounts accounts = response;
        accounts.setTodo( UUID.randomUUID().toString() );
        accounts.setUsername( UUID.randomUUID().toString() );
        accounts.setPassword( UUID.randomUUID().toString() );

        accounts.saveAsync( new DefaultCallback<accounts>( UpdateRecordActivity.this )
        {
          @Override
          public void handleResponse( accounts response )
          {
            super.handleResponse( response );
            Intent nextIntent = new Intent( UpdateRecordActivity.this, UpdateSuccessActivity.class );
            startActivity( nextIntent );
          }
        } );
      }
    } );
  }
}