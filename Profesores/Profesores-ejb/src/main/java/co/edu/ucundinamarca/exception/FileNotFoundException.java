/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.exception;

/**
 *
 * @author david
 */
public class FileNotFoundException extends RuntimeException{
     public FileNotFoundException(String message) {
        super(message);
    }
}
