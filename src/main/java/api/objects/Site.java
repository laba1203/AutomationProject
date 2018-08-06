package api.objects;

import org.testng.Assert;
import util.EnvFactory;

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


    public String getTalkableIntegrationScript() {
        if(siteSlug == null){
            Assert.fail("Site Slug is not defined in the object of api.objects.Site.class");
        }
        String talkableServer = getTalkableServer();
        return "<script>\n" +
                "  window._talkableq = window._talkableq || [];\n" +
                "  _talkableq.push(['init', { site_id: \"" + getSiteSlug() + "\"" + talkableServer + " }]);\n" +
                "\n" +
                "  window._talkableq.push(['authenticate_customer', {\n" +
                "    email: '', // Optional, pass when available. Example: 'customer@example.com'\n" +
                "    first_name: '', // Optional, pass when available. Example: 'John'\n" +
                "    last_name: '' // Optional, pass when available. Example: 'Smith'\n" +
                "  }]);\n" +
                "\n" +
                "  window._talkableq.push(['register_affiliate', {}]);\n" +
                "</script>\n" +
                "<script async src=\"//di6re4dxelnn2.cloudfront.net/integration/clients/" + getSiteSlug() + ".min.js\" type=\"text/javascript\"></script>\n"
                ;
    }

    private String getTalkableServer(){
        switch (EnvFactory.getEnvType()){
            case PROD:
                return "";
            case BASTION:
                return ", server: \"//bastion.talkable.com\"";
            case VOID:
                return ", server: \"//void.talkable.com\"";
            case BART:
                return ", server: \"//bast.talkable.com\"";
            default:
                return "";
        }
    }
}
