package com.p6spy.engine.spy.appender;

//import org.hibernate.jdbc.util.BasicFormatterImpl;
//import org.hibernate.jdbc.util.Formatter;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class SQLFormatter implements MessageFormattingStrategy {

  //  private final Formatter formatter = new BasicFormatterImpl();

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql) {
        return sql + ";";//formatter.format(sql); 
        
    }

}