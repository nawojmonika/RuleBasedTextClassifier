package Sztuczna.Algorithms.interfaces;

public enum FeldmanProperties {
    LAND_BOUNDERIES("LandBounderies"),
    NATURAL_RESOURCES("NaturalResources"),
    POPULATION("Population"),
    CAPITAL("Capital"),
    MEMBER_OF("MemberOf"),
    EXPORT_COMMODITIES("ExportCommodities"),
    EXPORT_PARTNERS("ExportPartners"),
    IMPORT_COMMODITIES("ImportCommodities"),
    IMPORT_PARTNERS("ImportPartners"),
    INDUSTRIES("Industries"),
    AGRICULTURE("Agriculture");

    private String property;

    FeldmanProperties(String property) {
        this.property = property;
    }

    public String getPropertyName() {
        return property;
    }
}
