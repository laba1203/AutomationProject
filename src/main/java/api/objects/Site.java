package api.objects;

public class Site {
    private String apiKey;
    private String siteSlug;

    public Site setData(String siteSlug, String apiKey){
        this.apiKey = apiKey;
        this.siteSlug = siteSlug;

        return this;
    }



    public String getApiKey() {
        return apiKey;
    }

    public String getSiteSlug() {
        return siteSlug;
    }
}
