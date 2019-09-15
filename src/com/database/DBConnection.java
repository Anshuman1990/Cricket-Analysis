/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database;

import java.sql.Connection;

/**
 *
 * @author Anspro
 */
public interface DBConnection {
    
    public Connection getConnection();
}
