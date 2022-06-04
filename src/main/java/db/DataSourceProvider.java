package db;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public enum DataSourceProvider {
    INSTANCE;

    private PGSimpleDataSource ds;

    public DataSource getDataSource(){
        if (ds == null){
            ds = new PGSimpleDataSource();
            ds.setServerNames(new String[] {"localhost"});
            ds.setPortNumbers(new int[] {5432});
            ds.setDatabaseName("coin_keeper");
            ds.setUser("postgres");
            ds.setPassword("postgres");
        }
        return ds;
    }

}
