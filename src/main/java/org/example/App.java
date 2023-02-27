package org.example;

import org.example.database.DatabaseType;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Zomato zomato = new Zomato(DatabaseType.InMemoryStore);



    }
}
