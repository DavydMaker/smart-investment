package br.com.davydmaker.smartinvestment.repository.sql;

public class CompanyQueries {

    public static final String INSERT = "INSERT INTO company ( " +
            "   name, " +
            " ) VALUES ( " +
            "   :name " +
            " )";

    public static final String DELETE = "UPDATE company " +
            " SET excluded = 1 " +
            " WHERE id = :id";

    public static final String UPDATE_STATUS = "UPDATE company " +
            " SET status = :status " +
            " WHERE id = :id";

    public static final String LIST = "SELECT * " +
            " FROM company " +
            " WHERE excluded = 0 %1$s";

}