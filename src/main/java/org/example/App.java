package org.example;


import lombok.extern.slf4j.Slf4j;
import org.example.utils.StringArgsValidator;

@Slf4j
public class App
{
    public static void main( String[] args )
    {

        StringArgsValidator f = new StringArgsValidator();

        System.out.println(f.getKek());
        System.out.println( "Hello World!" );

        for (String a:
             args) {
            System.out.println(a);
        }
    }
}
