package com.backendless.hacktest.data.crud.retrieve;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.geo.GeoPoint;
import com.backendless.hacktest.data.*;
import com.backendless.hacktest.data.crud.R;
import com.backendless.hacktest.data.crud.common.DataApplication;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowEntityActivity extends Activity
{
  private TextView titleView, propertyView, valueView;
  private ListView propertiesView;

  private String type;
  private String table;
  private String property;
  private String relation;
  private String relatedTable;
  private String relatedProperty;

  public void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.entity_properties );

    Intent intent = getIntent();
    type = intent.getStringExtra( "type" );
    property = intent.getStringExtra( "property" );
    relation = intent.getStringExtra( "relation" );
    relatedTable = intent.getStringExtra( "relatedTable" );
    relatedProperty = intent.getStringExtra( "relatedProperty" );

    DataApplication dataApplication = (DataApplication) getApplication();
    table = dataApplication.getChosenTable();

    initUI();
  }

  private void initUI()
  {
    titleView = (TextView) findViewById( R.id.title );
    propertyView = (TextView) findViewById( R.id.propertyHint );
    valueView = (TextView) findViewById( R.id.valueHint );
    propertiesView = (ListView) findViewById( R.id.propertiesList );

    if( type.equals( "Find First" ) )
    {
      titleView.setText( "First " + table );
      initList();
    }
    else if( type.equals( "Find Last" ) )
    {
      titleView.setText( "Last " + table );
      initList();
    }
    else if( type.equals( "Find with Relations" ) )
    {
      titleView.setText( "Find with Relations" );
      propertyView.setText( table + "." + property );
      if( relatedProperty == null )
        valueView.setText( relatedTable );
      else
        valueView.setText( relatedTable + "." + relatedProperty );
      initRelationList();
    }
  }

  private void initList()
  {
    List<HashMap<String, String>> properties = new ArrayList<HashMap<String, String>>();
    if( table.equals( "accounts" ) )
    {
      accounts accounts = (accounts) RetrieveRecordActivity.getResultObject();
      HashMap<String, String> property;
      property = new HashMap<String, String>();
      property.put( "property", "created" );
      property.put( "value", String.valueOf( accounts.getCreated() ) );
      properties.add( property );
                                                            
      property = new HashMap<String, String>();
      property.put( "property", "updated" );
      property.put( "value", String.valueOf( accounts.getUpdated() ) );
      properties.add( property );
                                                            
      property = new HashMap<String, String>();
      property.put( "property", "todo" );
      property.put( "value", String.valueOf( accounts.getTodo() ) );
      properties.add( property );
                                                            
      property = new HashMap<String, String>();
      property.put( "property", "objectId" );
      property.put( "value", String.valueOf( accounts.getObjectId() ) );
      properties.add( property );
                                                            
      property = new HashMap<String, String>();
      property.put( "property", "username" );
      property.put( "value", String.valueOf( accounts.getUsername() ) );
      properties.add( property );
                                                            
      property = new HashMap<String, String>();
      property.put( "property", "ownerId" );
      property.put( "value", String.valueOf( accounts.getOwnerId() ) );
      properties.add( property );
                                                            
      property = new HashMap<String, String>();
      property.put( "property", "password" );
      property.put( "value", String.valueOf( accounts.getPassword() ) );
      properties.add( property );
                                                                }

    ListAdapter adapter = new SimpleAdapter( this, properties, R.layout.property_row, new String[] { "property", "value" }, new int[] { R.id.property, R.id.value } );
    propertiesView.setAdapter( adapter );
  }

  private void initRelationList()
  {
    List<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();
    BackendlessCollection collection = RetrieveRecordActivity.getResultCollection();

    for( Object object : collection.getCurrentPage() )
    {
      if( table.equals( "accounts" ) )
      {
        accounts accounts = (accounts) object;
        String propertyValue = "";
        String getterMethodName = "get".concat( property.substring( 0, 1 ).toUpperCase().concat( property.substring( 1 ) ) );
        try
        {
          propertyValue = String.valueOf( accounts.getClass().getDeclaredMethod( getterMethodName ).invoke( accounts ) );
        }
        catch( InvocationTargetException e )
        {
          Toast.makeText( this, e.getMessage(), Toast.LENGTH_LONG );
        }
        catch( NoSuchMethodException e )
        {
          Toast.makeText( this, e.getMessage(), Toast.LENGTH_LONG );
        }
        catch( IllegalAccessException e )
        {
          Toast.makeText( this, e.getMessage(), Toast.LENGTH_LONG );
        }
        
      }

    }
    ListAdapter adapter = new SimpleAdapter( this, rows, R.layout.property_row, new String[] { "property", "value" }, new int[] { R.id.property, R.id.value } );
    propertiesView.setAdapter( adapter );
}
}