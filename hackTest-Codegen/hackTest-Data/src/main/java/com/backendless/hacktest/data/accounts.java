package com.backendless.hacktest.data;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

public class accounts
{
  private java.util.Date created;
  private java.util.Date updated;
  private String todo;
  private String objectId;
  private String username;
  private String ownerId;
  private String password;
  public java.util.Date getCreated()
  {
    return created;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getTodo()
  {
    return todo;
  }

  public void setTodo( String todo )
  {
    this.todo = todo;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername( String username )
  {
    this.username = username;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword( String password )
  {
    this.password = password;
  }

                                                    
  public accounts save()
  {
    return Backendless.Data.of( accounts.class ).save( this );
  }

  public Future<accounts> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<accounts> future = new Future<accounts>();
      Backendless.Data.of( accounts.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<accounts> callback )
  {
    Backendless.Data.of( accounts.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( accounts.class ).remove( this );
  }

  public Future<Long> removeAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Long> future = new Future<Long>();
      Backendless.Data.of( accounts.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( accounts.class ).remove( this, callback );
  }

  public static accounts findById( String id )
  {
    return Backendless.Data.of( accounts.class ).findById( id );
  }

  public static Future<accounts> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<accounts> future = new Future<accounts>();
      Backendless.Data.of( accounts.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<accounts> callback )
  {
    Backendless.Data.of( accounts.class ).findById( id, callback );
  }

  public static accounts findFirst()
  {
    return Backendless.Data.of( accounts.class ).findFirst();
  }

  public static Future<accounts> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<accounts> future = new Future<accounts>();
      Backendless.Data.of( accounts.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<accounts> callback )
  {
    Backendless.Data.of( accounts.class ).findFirst( callback );
  }

  public static accounts findLast()
  {
    return Backendless.Data.of( accounts.class ).findLast();
  }

  public static Future<accounts> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<accounts> future = new Future<accounts>();
      Backendless.Data.of( accounts.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<accounts> callback )
  {
    Backendless.Data.of( accounts.class ).findLast( callback );
  }

  public static BackendlessCollection<accounts> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( accounts.class ).find( query );
  }

  public static Future<BackendlessCollection<accounts>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<accounts>> future = new Future<BackendlessCollection<accounts>>();
      Backendless.Data.of( accounts.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<accounts>> callback )
  {
    Backendless.Data.of( accounts.class ).find( query, callback );
  }
}