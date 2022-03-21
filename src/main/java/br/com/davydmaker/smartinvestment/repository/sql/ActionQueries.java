package br.com.davydmaker.smartinvestment.repository.sql;

public class ActionQueries {

    public static final String INSERT = "INSERT INTO action ( " +
            "   company_id, " +
            "   ticker, " +
            "   price " +
            " ) VALUES ( " +
            "   :company_id, " +
            "   :ticker, " +
            "   :price " +
            " )";

    public static final String UPDATE_PRICE = "UPDATE action " +
            " SET price = :price " +
            " WHERE ticker = :ticker";

}