package com.ecommerce.filterproducts.infrastructure.adapter.file.system.util;

import org.apache.commons.lang3.StringUtils;

public class FileSystemUtils {
    public static String cleanRecord(String record) {
        return StringUtils.isEmpty(record) ? null : record.trim();
    }
}
