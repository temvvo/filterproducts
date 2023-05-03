package com.ecommerce.filterproducts.infrastructure.adapter.db.migration;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;


public class V2__insert_data extends BaseJavaMigration {
    static final String COMMA = ",";
    static final String OPEN_BRACKET = "(";
    static final String CLOSE_BRACKET = ")";


    @Override
    public void migrate(Context context) throws Exception {
        migrateFromCsv(context, "product.csv", "product", new String[]{"id", "sequence"});
        migrateFromCsv(context, "size-1.csv", "size", new String[]{"id", "product_id", "back_soon", "special"});
        migrateFromCsv(context, "stock.csv", "stock", new String[]{"size_id", "quantity"});
    }

    /**
     * Builds and execute migration from CSV file into database
     * @param context
     * @param fileName
     * @param table
     * @param fields
     * @throws IOException
     */
    private void migrateFromCsv(Context context, String fileName, String table, String[] fields) throws IOException {
        final String INSERT = "INSERT INTO";
        final String VALUES = "VALUES";
        final String SPACE = " ";
        FileReader csvFile = new FileReader(getClass().getClassLoader().getResource(fileName).getPath());
        Iterable<CSVRecord> csvRecords = CSVFormat.EXCEL.parse(csvFile);
        csvRecords.forEach(record -> {
            try {
                context.getConnection().createStatement().execute( String.join( SPACE,INSERT, table,
                        getFields(fields), VALUES, getValuesFromRecord(record,fields.length)));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     *
     * @param fields
     * @return comma separated fields
     */
    private String getFields(String[] fields) {
        return OPEN_BRACKET + String.join(COMMA,fields) + CLOSE_BRACKET;
    }

    /**
     *
     * @param record
     * @param length
     * @return comma separated values
     */
    private String getValuesFromRecord(CSVRecord record, int length) {
        final String SINGLE_QUOTE = "'";
        StringBuilder sb = new StringBuilder();
        sb.append(OPEN_BRACKET);
        for (int i = 0; i < length; i++) {
            sb.append(SINGLE_QUOTE)
                    .append(record.get(i))
                    .append(SINGLE_QUOTE)
                    .append(COMMA);
        }
        if (sb.length() > 0) {
            // remove last comma
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(CLOSE_BRACKET);
        return sb.toString();
    }
}
