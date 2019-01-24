package com.company.InjectTest.annoationfactory.constant;

import org.apache.commons.lang3.StringUtils;

public enum FeedSourceType {
    NA("NotFound"),
    FIN("FinancialStatements"),
    FSP("FSPerShare"),
    TSO("TSO"),
    REF("Reference"),
    MAR("MarketCap"),
    ALL("All");

    private String label;

    FeedSourceType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public static FeedSourceType getByLabel(String label) {
        for (FeedSourceType feedSourceType : FeedSourceType.values()) {
            if (StringUtils.equals(label, feedSourceType.getLabel())) {
                return feedSourceType;
            }
        }
        return FeedSourceType.NA;
    }

    public static FeedSourceType getByName(String name) {
        try {
            return FeedSourceType.valueOf(name);
        } catch (Exception e) {
            return FeedSourceType.NA;
        }
    }
}
